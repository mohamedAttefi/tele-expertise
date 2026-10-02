<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.teleexpertise.model.FileAttente" %>

<!DOCTYPE html>

<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>File d'attente</title>
</head>

<body>

<h1>File d'attente</h1>

<%
    List<FileAttente> patients =
            (List<FileAttente>) request.getAttribute("patients");
%>

<table border="1">

    <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Heure d'arrivée</th>
        <th>N° sécurité sociale</th>
        <th>Tension</th>
        <th>Fréquence cardiaque</th>
        <th>Température</th>
        <th>Fréquence respiratoire</th>
        <th>Poids</th>
        <th>Taille</th>
    </tr>

    <%
        for (FileAttente attente : patients) {
    %>

    <tr>

        <td>
            <%= attente.getPatient().getNom() %>
        </td>

        <td>
            <%= attente.getPatient().getPrenom() %>
        </td>

        <td>
            <%= attente.getHeureArrivee() %>
        </td>

        <td>
            <%= attente.getPatient()
                    .getNumeroSecuriteSociale() %>
        </td>

        <td>
            <%= attente.getPatient().getTensionArterielle() %>
        </td>

        <td>
            <%= attente.getPatient().getFrequenceCardiaque() %>
        </td>

        <td>
            <%= attente.getPatient().getTemperature() %>
        </td>

        <td>
            <%= attente.getPatient().getFrequenceRespiratoire() %>
        </td>

        <td>
            <%= attente.getPatient().getPoids() %> kg
        </td>

        <td>
            <%= attente.getPatient().getTaille() %> cm
        </td>

    </tr>

    <%
        }
    %>

</table>

</body>
</html>