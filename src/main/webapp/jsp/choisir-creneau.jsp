<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.teleexpertise.model.Creneau" %>

<!DOCTYPE html>

<html lang="fr">

<head>

    <meta charset="UTF-8">

    <title>Choisir un créneau</title>

</head>

<body>

<h1>Choisir un créneau</h1>

<%
    List<Creneau> creneaux =
            (List<Creneau>) request.getAttribute(
                    "creneaux"
            );

    Long specialisteId =
            (Long) request.getAttribute(
                    "specialisteId"
            );
%>

<form action="/tele_expertise_war/choisir-creneau"
      method="post">

    <input type="hidden"
           name="specialisteId"
           value="<%= specialisteId %>">


    <input type="hidden"
           name="consultationId"
           value="<%= request.getAttribute("consultationId") %>">

    <label>Créneau :</label>

    <select name="creneauId" required>

        <option value="">
            -- Choisir un créneau --
        </option>

        <%
            for (Creneau creneau : creneaux) {
        %>

        <option value="<%= creneau.getId() %>">

            <%= creneau.getHeureDebut() %>
            -
            <%= creneau.getHeureFin() %>

        </option>

        <%
            }
        %>

    </select>

    <br><br>

    <button type="submit">
        Choisir ce créneau
    </button>

</form>

</body>

</html>