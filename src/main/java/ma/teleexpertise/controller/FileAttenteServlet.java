package ma.teleexpertise.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.teleexpertise.dao.FileAttenteDAO;

import java.io.IOException;

@WebServlet("/file-attente")
public class FileAttenteServlet extends HttpServlet {

    private FileAttenteDAO fileAttenteDAO;

    @Override
    public void init() {
        fileAttenteDAO = new FileAttenteDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setAttribute(
                "patients",
                fileAttenteDAO.trouverPatientsDuJour()
        );

        request.getRequestDispatcher(
                "/jsp/file-attente.jsp"
        ).forward(request, response);
    }
}