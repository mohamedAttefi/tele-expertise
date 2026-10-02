<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Nouveau patient</title>
</head>

<body>

<h1>Enregistrer un nouveau patient</h1>

<form action="/tele_expertise_war/nouveau-patient"
      method="post">

    <h2>Informations personnelles</h2>

    <label>Nom :</label>
    <input type="text" name="nom" required>
    <br><br>

    <label>Prénom :</label>
    <input type="text" name="prenom" required>
    <br><br>

    <label>Date de naissance :</label>
    <input type="date" name="dateNaissance" required>
    <br><br>

    <label>Numéro de sécurité sociale :</label>
    <input type="text"
           name="numeroSecuriteSociale"
           required>
    <br><br>

    <label>Téléphone :</label>
    <input type="text" name="telephone">
    <br><br>

    <label>Adresse :</label>
    <input type="text" name="adresse">
    <br><br>


    <h2>Signes vitaux</h2>

    <label>Tension artérielle :</label>
    <input type="text"
           name="tensionArterielle"
           placeholder="120/80"
           required>
    <br><br>

    <label>Fréquence cardiaque :</label>
    <input type="number"
           name="frequenceCardiaque"
           placeholder="70"
           required>
    <br><br>

    <label>Température :</label>
    <input type="number"
           step="0.1"
           name="temperature"
           placeholder="37.0"
           required>
    <br><br>

    <label>Fréquence respiratoire :</label>
    <input type="number"
           name="frequenceRespiratoire"
           placeholder="16"
           required>
    <br><br>

    <label>Poids (kg) :</label>
    <input type="number"
           step="0.1"
           name="poids">
    <br><br>

    <label>Taille (cm) :</label>
    <input type="number"
           step="0.1"
           name="taille">
    <br><br>

    <button type="submit">
        Enregistrer le patient
    </button>

</form>

</body>
</html>