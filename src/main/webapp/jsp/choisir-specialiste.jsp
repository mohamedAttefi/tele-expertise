<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.teleexpertise.model.Specialiste" %>

<!DOCTYPE html>

<html lang="fr">

<head>

    <meta charset="UTF-8">

    <title>Choisir un spécialiste</title>

</head>

<body>

<h1>Spécialistes disponibles</h1>

<%
    List<Specialiste> specialistes =
            (List<Specialiste>) request.getAttribute(
                    "specialistes"
            );
%>

<% if (specialistes.isEmpty()) { %>

<p>
    Aucun spécialiste disponible.
</p>

<% } else { %>

<table border="1">

    <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Spécialité</th>
        <th>Tarif</th>
        <th>Disponibilité</th>
    </tr>

    <%
        for (Specialiste s : specialistes) {
    %>

    <tr>

        <td>
            <%= s.getNom() %>
        </td>

        <td>
            <%= s.getPrenom() %>
        </td>

        <td>
            <%= s.getSpecialite() %>
        </td>

        <td>
            <%= s.getTarif() %> DH
        </td>

        <td>
            Disponible
        </td>

    </tr>

    <form action="/tele_expertise_war/choisir-creneau"
          method="get">

        <input type="hidden"
               name="specialisteId"
               value="<%= s.getId() %>">
        <input type="hidden"
               name="consultationId"
               value="<%= request.getAttribute("consultationId")%>">
        <input type="hidden"
               name="specialite"
               value="<%= request.getAttribute("specialite")%>">

        <button type="submit">
            Voir les créneaux
        </button>

    </form>

    <%
        }
    %>

</table>

<% } %>

</body>

</html>