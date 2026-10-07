<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ma.teleexpertise.model.Specialite" %>

<!DOCTYPE html>

<html lang="fr">

<head>

    <meta charset="UTF-8">

    <title>Demander une expertise</title>

</head>

<body>

<h1>Demander une expertise</h1>

<form action="/tele_expertise_war/demande-expertise"
      method="post">

    <input type="hidden"
           name="consultationId"
           value="<%= request.getAttribute("consultationId") %>">

    <label>Spécialité :</label>

    <select name="specialite" required>

        <option value="">
            -- Choisir une spécialité --
        </option>

        <%
            for (Specialite specialite : Specialite.values()) {
        %>

        <option value="<%= specialite %>">
            <%= specialite %> -
            <%= specialite.getDescription() %>
        </option>

        <%
            }
        %>

    </select>

    <br><br>

    <button type="submit">
        Rechercher les spécialistes
    </button>

</form>

</body>

</html>