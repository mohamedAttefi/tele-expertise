<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.teleexpertise.model.DemandeExpertise" %>
<%@ page import="ma.teleexpertise.model.Priorite" %>
<%@ page import="ma.teleexpertise.model.StatutDemande" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Demandes d'expertise</title>
</head>

<body>

<h1>Demandes d'expertise</h1>
<form action="${pageContext.request.contextPath}/demandes-expertise"
      method="get">

    <label>Priorité :</label>

    <select name="priorite">

        <option value="">
            Toutes les priorités
        </option>

        <%
            for (Priorite p : Priorite.values()) {
        %>

        <option value="<%= p %>"
                <%= p.toString().equals(request.getParameter("priorite"))
                        ? "selected"
                        : "" %>>

            <%= p %>

        </option>

        <%
            }
        %>

    </select>


    <label>Statut :</label>

    <select name="statut">

        <option value="">
            Tous les statuts
        </option>

        <%
            for (StatutDemande s : StatutDemande.values()) {
        %>

        <option value="<%= s %>"
                <%= s.toString().equals(request.getParameter("statut"))
                        ? "selected"
                        : "" %>>

            <%= s %>

        </option>

        <%
            }
        %>

    </select>


    <button type="submit">
        Filtrer
    </button>

</form>

<br>

<%
    List<DemandeExpertise> demandes =
            (List<DemandeExpertise>) request.getAttribute("demandes");
%>

<% if (demandes == null || demandes.isEmpty()) { %>

<p>Aucune demande d'expertise.</p>

<% } else { %>

<table border="1">

    <tr>
        <th>Patient</th>
        <th>Spécialiste</th>
        <th>Question</th>
        <th>Priorité</th>
        <th>Statut</th>
        <th>Date</th>
    </tr>

    <%
        for (DemandeExpertise d : demandes) {
    %>

    <tr>

        <td>
            <%= d.getConsultation()
                    .getPatient()
                    .getNom() %>
            <%= d.getConsultation()
                    .getPatient()
                    .getPrenom() %>
        </td>

        <td>
            <%= d.getSpecialiste().getNom() %>
            <%= d.getSpecialiste().getPrenom() %>
        </td>

        <td>
            <%= d.getQuestion() %>
        </td>

        <td>
            <%= d.getPriorite() %>
        </td>

        <td>
            <%= d.getStatut() %>
        </td>

        <td>
            <%= d.getDateCreation() %>
        </td>

    </tr>

    <%
        }
    %>

</table>

<% } %>

</body>
</html>