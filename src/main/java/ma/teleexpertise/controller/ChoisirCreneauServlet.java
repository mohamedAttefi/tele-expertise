package ma.teleexpertise.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.teleexpertise.dao.CreneauDAO;
import ma.teleexpertise.model.Creneau;

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

        List<Creneau> creneaux =
                creneauDAO.trouverDisponibles(specialisteId);

        request.setAttribute("creneaux", creneaux);
        request.setAttribute("specialisteId", specialisteId);
        request.setAttribute("consultationId", consultationId);

        request.getRequestDispatcher(
                "/jsp/choisir-creneau.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Long consultationId =
                Long.parseLong(request.getParameter("consultationId"));

        Long specialisteId =
                Long.parseLong(request.getParameter("specialisteId"));

        Long creneauId =
                Long.parseLong(request.getParameter("creneauId"));

        request.setAttribute("consultationId", consultationId);
        request.setAttribute("specialisteId", specialisteId);
        request.setAttribute("creneauId", creneauId);

        request.getRequestDispatcher(
                "/jsp/demande-expertise.jsp"
        ).forward(request, response);
    }
}