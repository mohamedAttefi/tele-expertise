package ma.teleexpertise.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.teleexpertise.config.JPAUtil;
import ma.teleexpertise.model.*;

import java.io.IOException;

@WebServlet("/envoyer-expertise")
public class ExpertiseEnvoyeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Long consultationId =
                Long.parseLong(
                        request.getParameter("consultationId")
                );

        Long specialisteId =
                Long.parseLong(
                        request.getParameter("specialisteId")
                );

        Long creneauId =
                Long.parseLong(
                        request.getParameter("creneauId")
                );

        String question =
                request.getParameter("question");

        String donneesMedicales =
                request.getParameter("donneesMedicales");

        String analyses =
                request.getParameter("analyses");

        Priorite priorite =
                Priorite.valueOf(
                        request.getParameter("priorite")
                );

        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Consultation consultation =
                    em.find(Consultation.class, consultationId);

            Specialiste specialiste =
                    em.find(Specialiste.class, specialisteId);

            Creneau creneau =
                    em.find(Creneau.class, creneauId);

            if (consultation == null ||
                    specialiste == null ||
                    creneau == null) {

                em.getTransaction().rollback();

                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Consultation, spécialiste ou créneau introuvable."
                );

                return;
            }

            if (creneau.getStatut() != StatutCreneau.DISPONIBLE) {

                em.getTransaction().rollback();

                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Ce créneau n'est plus disponible."
                );

                return;
            }

            creneau.setStatut(StatutCreneau.RESERVE);

            consultation.setStatut(
                    StatutConsultation.EN_ATTENTE_AVIS_SPECIALISTE
            );

            DemandeExpertise demande =
                    new DemandeExpertise(
                            consultation,
                            specialiste,
                            creneau,
                            question,
                            donneesMedicales,
                            analyses,
                            priorite
                    );

            em.persist(demande);

            em.getTransaction().commit();

            response.sendRedirect(
                    request.getContextPath()
                            + "/expertise-envoyee"
            );

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Erreur lors de l'envoi de la demande."
            );

        } finally {
            em.close();
        }
    }
}