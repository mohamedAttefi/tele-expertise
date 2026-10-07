<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Demande d'expertise</title>
</head>

<body>

<h1>Demande d'expertise</h1>

<form action="/tele_expertise_war/expertise-envoyee"
      method="post">

    <input type="hidden"
           name="consultationId"
           value="<%= request.getAttribute("consultationId") %>">

    <input type="hidden"
           name="specialisteId"
           value="<%= request.getAttribute("specialisteId") %>">

    <input type="hidden"
           name="creneauId"
           value="<%= request.getAttribute("creneauId") %>">

    <input type="hidden"
           name="specialite"
           value="<%= request.getAttribute("specialite")%>">


    <label>Question pour le spécialiste :</label>
    <br>

    <textarea name="question"
              rows="5"
              cols="60"
              required></textarea>

    <br><br>


    <label>Données médicales :</label>
    <br>

    <textarea name="donneesMedicales"
              rows="5"
              cols="60"
              placeholder="Informations importantes concernant le patient..."></textarea>

    <br><br>


    <label>Analyses / examens :</label>
    <br>

    <textarea name="analyses"
              rows="5"
              cols="60"
              placeholder="Résultats d'analyses, examens, radiographie..."></textarea>

    <br><br>


    <label>Priorité :</label>

    <select name="priorite" required>

        <option value="">
            -- Choisir une priorité --
        </option>

        <option value="URGENTE">
            Urgente
        </option>

        <option value="NORMALE">
            Normale
        </option>

        <option value="NON_URGENTE">
            Non urgente
        </option>

    </select>

    <br><br>


    <button type="submit">
        Envoyer la demande
    </button>

</form>

</body>
</html>