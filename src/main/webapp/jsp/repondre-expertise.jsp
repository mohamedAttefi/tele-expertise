<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="ma.teleexpertise.model.DemandeExpertise" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Répondre à l'expertise</title>
</head>

<body>

<%
    DemandeExpertise demande =
            (DemandeExpertise) request.getAttribute("demande");
%>

<h1>Répondre à la demande d'expertise</h1>

<h3>Question du médecin :</h3>

<p>
    <%= demande.getQuestion() %>
</p>

<h3>Données médicales :</h3>

<p>
    <%= demande.getDonneesMedicales() %>
</p>

<h3>Analyses / examens :</h3>

<p>
    <%= demande.getAnalyses() %>
</p>

<hr>

<form action="${pageContext.request.contextPath}/envoyer-reponse-expertise"
      method="post">

    <input type="hidden"
           name="id"
           value="<%= demande.getId() %>">

    <label>Avis du spécialiste :</label>

    <br>

    <textarea name="avisSpecialiste"
              rows="6"
              cols="70"
              required></textarea>

    <br><br>

    <label>Recommandations :</label>

    <br>

    <textarea name="recommandations"
              rows="6"
              cols="70"
              required></textarea>

    <br><br>

    <button type="submit">
        Envoyer la réponse
    </button>

</form>

</body>
</html>