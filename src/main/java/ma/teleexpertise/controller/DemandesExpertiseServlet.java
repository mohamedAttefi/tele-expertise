package ma.teleexpertise.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.teleexpertise.dao.DemandeExpertiseDAO;
import ma.teleexpertise.model.DemandeExpertise;
import ma.teleexpertise.model.Priorite;
import ma.teleexpertise.model.StatutDemande;

import java.io.IOException;
import java.util.List;

@WebServlet("/demandes-expertise")
public class DemandesExpertiseServlet extends HttpServlet {

    private DemandeExpertiseDAO demandeExpertiseDAO;

    @Override
    public void init() {
        demandeExpertiseDAO = new DemandeExpertiseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer tous les demandes
        List<DemandeExpertise> demandes =
                demandeExpertiseDAO.trouverToutes();

        // Récupérer les filtres
        String prioriteParam =
                request.getParameter("priorite");

        String statutParam =
                request.getParameter("statut");

        // Filtrer par priorité
        if (prioriteParam != null && !prioriteParam.isEmpty()) {

            Priorite priorite =
                    Priorite.valueOf(prioriteParam);

            demandes = demandes.stream()
                    .filter(d -> d.getPriorite() == priorite)
                    .toList();
        }

        // Filtrer par statut
        if (statutParam != null && !statutParam.isEmpty()) {

            StatutDemande statut =
                    StatutDemande.valueOf(statutParam);

            demandes = demandes.stream()
                    .filter(d -> d.getStatut() == statut)
                    .toList();
        }

        request.setAttribute("demandes", demandes);

        request.getRequestDispatcher(
                "/jsp/demandes-expertise.jsp"
        ).forward(request, response);
    }
}