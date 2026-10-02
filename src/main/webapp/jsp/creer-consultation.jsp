<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.teleexpertise.model.Patient" %>

<!DOCTYPE html>

<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Créer une consultation</title>
</head>

<body>

<h1>Créer une consultation</h1>

<form action="/tele_expertise_war/creer-consultation"
      method="post">

    <label>Patient :</label>

    <select name="patientId" required>

        <option value="">-- Sélectionner un patient --</option>

        <%
            List<Patient> patients =
                    (List<Patient>) request.getAttribute("patients");

            for (Patient patient : patients) {
        %>

        <option value="<%= patient.getId() %>">

            <%= patient.getNom() %>
            <%= patient.getPrenom() %>

        </option>

        <%
            }
        %>

    </select>

    <br><br>

    <label>Motif de consultation :</label>

    <input type="text"
           name="motif"
           required>

    <br><br>

    <label>Observations :</label>

    <br>

    <textarea name="observations"
              rows="6"
              cols="50"
              required></textarea>

    <br><br>

    <p>
        Coût de la consultation :
        <strong>150 DH</strong>
    </p>

    <button type="submit">
        Créer la consultation
    </button>

</form>

</body>
</html>