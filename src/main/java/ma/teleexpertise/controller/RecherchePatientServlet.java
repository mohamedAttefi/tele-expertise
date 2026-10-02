package ma.teleexpertise.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.teleexpertise.dao.PatientDAO;
import ma.teleexpertise.model.Patient;

import java.io.IOException;

@WebServlet(value ="/rechercher-patient")
public class RecherchePatientServlet extends HttpServlet {

    private PatientDAO patientDAO;

    @Override
    public void init() {
        patientDAO = new PatientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/rechercher-patient.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String numero =
                request.getParameter("numeroSecuriteSociale");

        Patient patient =
                patientDAO.chercherParNumeroSecuriteSociale(numero);

        if (patient != null) {

            request.setAttribute("patient", patient);

        } else {

            request.setAttribute(
                    "message",
                    "Patient introuvable."
            );
        }

        request.getRequestDispatcher("/jsp/rechercher-patient.jsp")
                .forward(request, response);
    }
}