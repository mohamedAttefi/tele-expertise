<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ma.teleexpertise.model.Patient" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Recherche patient</title>
</head>

<body>

<h1>Rechercher un patient</h1>

<form action="rechercher-patient"
      method="post">

    <label>Numéro de sécurité sociale :</label>

    <input type="text"
           name="numeroSecuriteSociale"
           required>

    <button type="submit">Rechercher</button>

</form>

<%
    Patient patient = (Patient) request.getAttribute("patient");

    if (patient != null) {
%>

<h2>Patient trouvé</h2>

<p>Nom : <%= patient.getNom() %></p>
<p>Prénom : <%= patient.getPrenom() %></p>
<p>Date de naissance : <%= patient.getDateNaissance() %></p>
<p>Téléphone : <%= patient.getTelephone() %></p>
<p>Adresse : <%= patient.getAdresse() %></p>

<%
    }
%>
<%
    String message = (String) request.getAttribute("message");

    if (message != null) {
%>

<p><%= message %></p>

<a href="/tele_expertise_war/nouveau-patient">
    Créer un nouveau patient
</a>

<%
    }
%>

</body>
</html>