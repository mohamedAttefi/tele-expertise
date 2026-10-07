package ma.teleexpertise.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.teleexpertise.dao.CreneauDAO;
import ma.teleexpertise.model.Creneau;
import ma.teleexpertise.model.Specialite;

import java.io.IOException;
import java.util.List;

@WebServlet("/choisir-creneau")
public class ChoisirCreneauServlet extends HttpServlet {

    private CreneauDAO creneauDAO;

    @Override
    public void init() {
        creneauDAO = new CreneauDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Long specialisteId =
                Long.parseLong(request.getParameter("specialisteId"));

        Long consultationId =
                Long.parseLong(request.getParameter("consultationId"));

        Specialite specialite =
                Specialite.valueOf(
                        request.getParameter("specialite")
                );

        List<Creneau> creneaux =
                creneauDAO.trouverDisponibles(specialisteId);

        request.setAttribute("creneaux", creneaux);
        request.setAttribute("specialisteId", specialisteId);
        request.setAttribute("consultationId", consultationId);
        request.setAttribute("specialite", specialite);

        request.getRequestDispatcher(
                "/jsp/choisir-creneau.jsp"
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

        String priorite =
                request.getParameter("priorite");

        request.setAttribute("consultationId", consultationId);
        request.setAttribute("specialisteId", specialisteId);
        request.setAttribute("creneauId", creneauId);
        request.setAttribute("specialite", specialite);

        request.getRequestDispatcher(
                "/jsp/demande-expertise.jsp"
        ).forward(request, response);
    }
}