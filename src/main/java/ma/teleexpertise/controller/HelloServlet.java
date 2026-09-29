package ma.teleexpertise.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        response.setContentType("text/html");
        response.getWriter().println("""
                <html>
                    <head>
                        <title>Tele-Expertise</title>
                    </head>
                    <body>
                        <h1>Bienvenue dans le système de télé-expertise médicale</h1>
                        <p>Notre serveur fonctionne !</p>
                    </body>
                </html>
                """);
    }
}