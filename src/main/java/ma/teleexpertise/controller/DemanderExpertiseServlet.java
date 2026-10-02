package ma.teleexpertise.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.teleexpertise.dao.SpecialisteDAO;
import ma.teleexpertise.model.Specialiste;
import ma.teleexpertise.model.Specialite;

import java.io.IOException;
import java.util.List;

@WebServlet("/demander-expertise")
public class DemanderExpertiseServlet extends HttpServlet {

    private SpecialisteDAO specialisteDAO;

    @Override
    public void init() {

        specialisteDAO = new SpecialisteDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

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
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        Long consultationId =
                Long.parseLong(
                        request.getParameter("consultationId")
                );

        Specialite specialite =
                Specialite.valueOf(
                        request.getParameter("specialite")
                );

        List<Specialiste> specialistes =
                specialisteDAO
                        .trouverDisponiblesParSpecialite(
                                specialite
                        );

        request.setAttribute(
                "consultationId",
                consultationId
        );

        request.setAttribute(
                "specialistes",
                specialistes
        );

        request.getRequestDispatcher(
                "/jsp/choisir-specialiste.jsp"+consultationId
        ).forward(request, response);
    }
}