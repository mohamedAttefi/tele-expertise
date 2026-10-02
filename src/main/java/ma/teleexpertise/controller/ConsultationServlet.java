package ma.teleexpertise.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.teleexpertise.dao.ConsultationDAO;
import ma.teleexpertise.dao.PatientDAO;
import ma.teleexpertise.model.Consultation;
import ma.teleexpertise.model.Patient;

import java.io.IOException;

@WebServlet("/creer-consultation")
public class ConsultationServlet extends HttpServlet {

    private PatientDAO patientDAO;
    private ConsultationDAO consultationDAO;

    @Override
    public void init() {

        patientDAO = new PatientDAO();
        consultationDAO = new ConsultationDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setAttribute(
                "patients",
                patientDAO.trouverTous()
        );

        request.getRequestDispatcher(
                "/jsp/creer-consultation.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        Long patientId = Long.parseLong(
                request.getParameter("patientId")
        );

        String motif =
                request.getParameter("motif");

        String observations =
                request.getParameter("observations");

        Patient patient =
                patientDAO.trouverParId(patientId);

        Consultation consultation =
                new Consultation(
                        patient,
                        motif,
                        observations
                );

        consultationDAO.ajouter(consultation);

        response.sendRedirect(
                request.getContextPath()
                        + "/demander-expertise?consultationId="
                        + consultation.getId()
        );
    }
}