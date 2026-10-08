package ma.teleexpertise.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.persistence.EntityManager;

import ma.teleexpertise.config.JPAUtil;
import ma.teleexpertise.model.DemandeExpertise;

import java.io.IOException;

@WebServlet("/repondre-expertise")
public class RepondreExpertiseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(
                request.getParameter("id")
        );

        EntityManager em = JPAUtil.getEntityManager();

        try {
            DemandeExpertise demande =
                    em.find(DemandeExpertise.class, id);

            if (demande == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Demande introuvable."
                );
                return;
            }

            request.setAttribute("demande", demande);

            request.getRequestDispatcher(
                    "/jsp/repondre-expertise.jsp"
            ).forward(request, response);

        } finally {
            em.close();
        }
    }
}