package ma.teleexpertise.controller;

import jakarta.servlet.ServletException;
import ma.teleexpertise.dao.FileAttenteDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.teleexpertise.dao.PatientDAO;
import ma.teleexpertise.model.Patient;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/nouveau-patient")
public class NouveauPatientServlet extends HttpServlet {

    private PatientDAO patientDAO;

    private FileAttenteDAO fileAttenteDAO;

    @Override
    public void init() {

        patientDAO = new PatientDAO();

        fileAttenteDAO = new FileAttenteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/nouveau-patient.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");

        String prenom = request.getParameter("prenom");

        LocalDate dateNaissance =
                LocalDate.parse(
                        request.getParameter("dateNaissance")
                );

        String numero =
                request.getParameter("numeroSecuriteSociale");

        String telephone =
                request.getParameter("telephone");

        String adresse =
                request.getParameter("adresse");

        String tension =
                request.getParameter("tensionArterielle");

        int frequenceCardiaque =
                Integer.parseInt(
                        request.getParameter("frequenceCardiaque")
                );

        double temperature =
                Double.parseDouble(
                        request.getParameter("temperature")
                );

        int frequenceRespiratoire =
                Integer.parseInt(
                        request.getParameter("frequenceRespiratoire")
                );

        double poids =
                Double.parseDouble(
                        request.getParameter("poids")
                );

        double taille =
                Double.parseDouble(
                        request.getParameter("taille")
                );

        Patient patient = new Patient(
                nom,
                prenom,
                dateNaissance,
                numero,
                telephone,
                adresse
        );

        patient.setTensionArterielle(tension);
        patient.setFrequenceCardiaque(frequenceCardiaque);
        patient.setTemperature(temperature);
        patient.setFrequenceRespiratoire(frequenceRespiratoire);
        patient.setPoids(poids);
        patient.setTaille(taille);

        patientDAO.ajouterPatient(patient);
        fileAttenteDAO.ajouter(patient);
        response.sendRedirect(
                request.getContextPath()
                        + "/file-attente"
        );
    }
}