package ma.teleexpertise.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.persistence.EntityManager;
import ma.teleexpertise.config.JPAUtil;
import ma.teleexpertise.dao.DemandeExpertiseDAO;
import ma.teleexpertise.dao.SpecialisteDAO;
import ma.teleexpertise.model.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/demande-expertise")
public class DemanderExpertiseServlet extends HttpServlet {

    private DemandeExpertiseDAO demandeExpertiseDAO;

    @Override
    public void init() {
        demandeExpertiseDAO = new DemandeExpertiseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String consultationId =
                request.getParameter("consultationId");

        request.setAttribute(
                "consultationId",
                consultationId
        );

        request.getRequestDispatcher(
                "/jsp/demander-expertise.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Long consultationId =
                Long.parseLong(
                        request.getParameter("consultationId")
                );

        Specialite specialite =
                Specialite.valueOf(
                        request.getParameter("specialite")
                );

        SpecialisteDAO specialisteDAO = new SpecialisteDAO();
        List<Specialiste> specialistes =
                specialisteDAO.trouverDisponiblesParSpecialite(
                        specialite
                );

        request.setAttribute("consultationId", consultationId);
        request.setAttribute("specialistes", specialistes);
        request.setAttribute("specialite", specialite);

        request.getRequestDispatcher(
                "/jsp/choisir-specialiste.jsp"
        ).forward(request, response);
    }
}