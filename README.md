# 🏥 Système de Télé-Expertise Médicale

Application web Java permettant de faciliter la collaboration à distance entre **médecins généralistes** et **médecins spécialistes**, tout en améliorant la prise en charge et le suivi des patients.

Le système permet notamment l'enregistrement des patients, la gestion des consultations, la demande de télé-expertise, la gestion des créneaux des spécialistes et le suivi des avis médicaux.

---

## 📋 Table des matières

* [Présentation](#-présentation)
* [Objectifs](#-objectifs)
* [Fonctionnalités](#-fonctionnalités)
* [Rôles](#-rôles)
* [Processus métier](#-processus-métier)
* [Architecture](#-architecture)
* [Technologies](#-technologies)
* [Structure du projet](#-structure-du-projet)
* [Installation](#-installation)
* [Configuration de la base de données](#-configuration-de-la-base-de-données)
* [Lancement](#-lancement)
* [Sécurité](#-sécurité)
* [Tests](#-tests)
* [Améliorations possibles](#-améliorations-possibles)
* [Auteur](#-auteur)

---

## 📌 Présentation

Le **Système de Télé-Expertise Médicale** est une application web développée dans le cadre d'un projet Java Web.

L'objectif est de permettre à un **médecin généraliste** de demander l'avis d'un **médecin spécialiste** lorsqu'un cas médical nécessite une expertise complémentaire.

L'application couvre plusieurs étapes du parcours patient :

```text
Patient
   ↓
Accueil par l'infirmier
   ↓
Enregistrement / Signes vitaux
   ↓
File d'attente
   ↓
Consultation par le généraliste
   ↓
 ┌───────────────────────┐
 │                       │
Prise en charge       Télé-expertise
directe                   │
 │                        ↓
Diagnostic             Spécialiste
Traitement                │
 │                        ↓
Consultation          Avis médical
terminée                  │
                          ↓
                    Consultation terminée
```

---

## 🎯 Objectifs

Le projet a pour objectifs de :

* Centraliser les informations des patients.
* Faciliter l'enregistrement des patients.
* Gérer la file d'attente.
* Permettre aux généralistes de créer des consultations.
* Faciliter la demande d'avis auprès d'un spécialiste.
* Gérer les spécialités médicales.
* Gérer les créneaux disponibles des spécialistes.
* Permettre aux spécialistes de répondre aux demandes d'expertise.
* Calculer le coût total d'une prise en charge.
* Assurer une authentification sécurisée des utilisateurs.

---

# 🚀 Fonctionnalités

## 👩‍⚕️ Module Infirmier

### Accueil du patient

L'infirmier peut :

* Rechercher un patient existant.
* Afficher les informations du patient.
* Ajouter de nouveaux signes vitaux.
* Créer un nouveau dossier patient.
* Ajouter automatiquement le patient à la file d'attente.

### Informations patient

Les informations peuvent inclure :

* Nom
* Prénom
* Date de naissance
* Numéro de sécurité sociale
* Téléphone
* Adresse
* Antécédents
* Allergies
* Traitements en cours
* Tension artérielle
* Fréquence cardiaque
* Température
* Fréquence respiratoire
* Poids
* Taille

### Liste des patients

L'infirmier peut consulter les patients enregistrés pendant la journée.

La liste est triée par :

```text
Heure d'arrivée
        ↓
Plus ancien
        ↓
Plus récent
```

La recherche et le filtrage utilisent la **Stream API**.

---

# 👨‍⚕️ Module Médecin Généraliste

## Création d'une consultation

Le généraliste peut :

* Sélectionner un patient.
* Consulter son dossier médical.
* Saisir le motif de consultation.
* Saisir les observations.
* Créer une consultation.

Le coût de base d'une consultation est fixé à :

```text
150 DH
```

---

## 📋 Demande de télé-expertise

Lorsque le généraliste a besoin de l'avis d'un spécialiste :

1. Sélectionner une spécialité.
2. Afficher les spécialistes correspondants.
3. Filtrer les spécialistes disponibles.
4. Trier les spécialistes par tarif.
5. Sélectionner un spécialiste.
6. Consulter ses créneaux.
7. Sélectionner un créneau disponible.
8. Poser une question au spécialiste.
9. Ajouter les données ou analyses nécessaires.
10. Envoyer la demande.

La consultation passe alors au statut :

```text
EN_ATTENTE_AVIS_SPECIALISTE
```

---

## 💰 Calcul du coût total

Le coût total peut être composé de :

```text
Consultation
     +
Expertise
     +
Actes médicaux
     =
Coût total
```

Exemple :

```text
Consultation : 150 DH
Expertise     : 300 DH
Radiographie  : 200 DH
-----------------------
Total         : 650 DH
```

Le calcul utilise notamment les **Lambda expressions** et la méthode `map().sum()`.

---

# 👨‍⚕️ Module Médecin Spécialiste

## Profil

Le spécialiste peut configurer :

* Sa spécialité.
* Son tarif.
* La durée moyenne d'une consultation.

La durée prédéfinie est :

```text
30 minutes
```

---

## 🕐 Gestion des créneaux

Les créneaux sont prédéfinis.

| Créneau       | Statut       |
| ------------- | ------------ |
| 09:00 - 09:30 | Disponible   |
| 09:30 - 10:00 | Disponible   |
| 10:00 - 10:30 | Disponible   |
| 10:30 - 11:00 | Indisponible |
| 11:00 - 11:30 | Disponible   |
| 11:30 - 12:00 | Disponible   |

Le système gère automatiquement :

* Les créneaux disponibles.
* Les créneaux réservés.
* Les créneaux passés.
* Les annulations.

Un créneau réservé devient automatiquement :

```text
INDISPONIBLE
```

Un créneau passé est automatiquement archivé.

---

## 📩 Gestion des demandes d'expertise

Le spécialiste peut :

* Voir les demandes reçues.
* Filtrer les demandes par statut.
* Filtrer les demandes par priorité.
* Consulter les informations du patient.
* Consulter la question du généraliste.
* Consulter les données médicales nécessaires.
* Rédiger son avis.
* Ajouter ses recommandations.
* Terminer la demande.

Les filtres utilisent la **Stream API**.

---

# 🩺 Télé-expertise

Le système prend en charge deux modes de communication.

### Télé-expertise synchrone

Communication en temps réel entre les médecins :

```text
Généraliste
     ↕
Spécialiste
```

Elle peut être réalisée par :

* Visioconférence
* Téléphone
* Discussion en temps réel

### Télé-expertise asynchrone

Le généraliste envoie le dossier au spécialiste.

```text
Généraliste
     ↓
Dossier médical
     ↓
Spécialiste
     ↓
Avis médical
```

Le spécialiste peut ensuite fournir son avis et ses recommandations.

---

# 👥 Rôles

L'application possède trois rôles principaux :

| Rôle              | Responsabilités                              |
| ----------------- | -------------------------------------------- |
| 👩‍⚕️ Infirmier   | Enregistrement et accueil des patients       |
| 👨‍⚕️ Généraliste | Consultations et demandes d'expertise        |
| 👨‍⚕️ Spécialiste | Gestion du profil et réponses aux expertises |

---

# 🔐 Authentification

L'application utilise une authentification basée sur les **sessions HTTP**.

Fonctionnalités :

* Login
* Logout
* Gestion des sessions
* Contrôle des rôles
* Protection des pages selon le rôle

Les mots de passe sont protégés avec :

```text
BCrypt
```

La protection contre les attaques **CSRF** est également prévue.

---

# 🏗️ Architecture

Le projet suit une architecture Web basée sur le modèle **MVC (Model - View - Controller)**.

```text
                    ┌──────────────────┐
                    │     Navigateur   │
                    │      Client      │
                    └────────┬─────────┘
                             │ HTTP
                             ↓
                    ┌──────────────────┐
                    │    Controller    │
                    │    Servlets      │
                    └────────┬─────────┘
                             │
                             ↓
                    ┌──────────────────┐
                    │      Service     │
                    │  Logique métier  │
                    └────────┬─────────┘
                             │
                             ↓
                    ┌──────────────────┐
                    │       DAO        │
                    │ Accès aux données│
                    └────────┬─────────┘
                             │
                             ↓
                    ┌──────────────────┐
                    │    PostgreSQL    │
                    │    Database      │
                    └──────────────────┘
```

### Model

Contient les entités métier et les données.

Exemples :

```text
Patient
Consultation
Specialiste
Specialite
Creneau
Expertise
ActeMedical
Utilisateur
```

### View

Interface utilisateur réalisée avec :

* JSP
* JSTL
* HTML
* CSS

### Controller

Les **Servlets** reçoivent les requêtes HTTP et contrôlent le flux de l'application.

Exemples :

```text
LoginServlet
PatientServlet
ConsultationServlet
DemanderExpertiseServlet
ExpertiseServlet
CreneauServlet
```

---

# 🛠️ Technologies

## Backend

* Java 17
* Jakarta EE
* Servlets
* JSP
* JSTL
* JPA
* Hibernate
* HTTP

## Base de données

* PostgreSQL

## Serveur

* Apache Tomcat

## Gestion de projet

* Maven
* Git
* GitHub

## Sécurité

* BCrypt
* Sessions HTTP
* Protection CSRF

## Tests

* JUnit
* Mockito

---

# 📂 Structure du projet

Une structure possible du projet :

```text
tele-expertise/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ma/
│   │   │       └── teleexpertise/
│   │   │           ├── controller/
│   │   │           ├── model/
│   │   │           ├── dao/
│   │   │           ├── service/
│   │   │           ├── filter/
│   │   │           └── util/
│   │   │
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       └── persistence.xml
│   │   │
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       │
│   │       ├── css/
│   │       ├── js/
│   │       ├── jsp/
│   │       └── index.jsp
│   │
│   └── test/
│       └── java/
│
├── pom.xml
└── README.md
```

---

# 🗄️ Base de données

Le projet utilise **PostgreSQL**.

Créer une base de données :

```sql
CREATE DATABASE tele_expertise;
```

Puis configurer les informations de connexion dans :

```text
persistence.xml
```

Exemple :

```xml
<property
    name="jakarta.persistence.jdbc.url"
    value="jdbc:postgresql://localhost:5432/tele_expertise"/>

<property
    name="jakarta.persistence.jdbc.user"
    value="postgres"/>

<property
    name="jakarta.persistence.jdbc.password"
    value="YOUR_PASSWORD"/>
```

Le projet utilise **JPA/Hibernate** pour gérer la persistance des entités.

---

# ⚙️ Installation

## 1. Cloner le projet

```bash
git clone https://github.com/mohamedAttefi/tele-expertise.git
```

Puis :

```bash
cd tele-expertise
```

## 2. Vérifier Java

```bash
java -version
```

Le projet nécessite :

```text
Java 17+
```

## 3. Vérifier Maven

```bash
mvn -version
```

## 4. Configurer PostgreSQL

Créer la base :

```sql
CREATE DATABASE tele_expertise;
```

Puis modifier les informations de connexion dans :

```text
persistence.xml
```

## 5. Installer les dépendances

```bash
mvn clean install
```

---

# ▶️ Lancement

Compiler le projet :

```bash
mvn clean package
```

Le fichier `.war` sera généré dans :

```text
target/
```

Exemple :

```text
target/tele_expertise.war
```

Déployer ensuite le fichier WAR sur **Apache Tomcat**.

L'application sera accessible avec une URL similaire à :

```text
http://localhost:8080/tele_expertise/
```

---

# 🧪 Tests

Les tests unitaires sont réalisés avec :

* JUnit
* Mockito

Lancer les tests :

```bash
mvn test
```

---

# 🩻 Actes techniques médicaux

Le système peut gérer différents actes médicaux :

* Radiographie
* Échographie
* IRM
* Électrocardiogramme
* Actes dermatologiques
* Fond d'œil
* Analyse de sang
* Analyse d'urine

Ces actes peuvent être pris en compte dans le calcul du coût total.

---

# 🌟 Utilisation de la Stream API

Le projet utilise la **Stream API Java** pour plusieurs opérations métier.

Exemples :

### Filtrer les spécialistes

```java
specialistes.stream()
    .filter(s -> s.getSpecialite() == specialite)
    .filter(Specialiste::isDisponible)
    .sorted(Comparator.comparing(Specialiste::getTarif))
    .toList();
```

### Filtrer les patients du jour

```java
patients.stream()
    .filter(p -> p.getDateEnregistrement().equals(LocalDate.now()))
    .sorted(Comparator.comparing(Patient::getHeureArrivee))
    .toList();
```

---

# 🧮 Lambda Expressions

Les expressions Lambda sont notamment utilisées pour calculer le coût total.

Exemple :

```java
double total = actes.stream()
    .mapToDouble(ActeMedical::getPrix)
    .sum();
```

---

# 🔒 Sécurité

Le projet prend en compte plusieurs mécanismes de sécurité :

* Authentification par session.
* Gestion des rôles.
* BCrypt pour le hachage des mots de passe.
* Protection CSRF.
* Contrôle d'accès aux fonctionnalités.
* Validation des données reçues depuis les formulaires.

---

# 🔮 Améliorations possibles

Plusieurs fonctionnalités peuvent être ajoutées dans de futures versions :

* 👨‍💼 Rôle administrateur.
* Gestion complète du staff.
* Notifications en temps réel.
* Visioconférence intégrée.
* Messagerie entre médecins.
* Historique complet des consultations.
* Génération de rapports PDF.
* Tableau de bord statistique.
* Notifications par email.
* Recherche avancée des patients.
* Gestion des prescriptions.
* Audit des actions des utilisateurs.

---

# 👨‍💼 Bonus : Administration

Deux solutions sont possibles pour gérer le staff.

### Option 1 — Scripts SQL

Ajouter les utilisateurs directement dans la base de données.

### Option 2 — Administrateur

Créer un rôle :

```text
ADMIN
```

L'administrateur pourrait :

* Ajouter des infirmiers.
* Ajouter des généralistes.
* Ajouter des spécialistes.
* Modifier les profils.
* Désactiver des comptes.
* Gérer les spécialités.

---

# 📊 Exemple de workflow

```text
┌─────────────┐
│  Infirmier  │
└──────┬──────┘
       │
       ↓
Enregistrer patient
       │
       ↓
File d'attente
       │
       ↓
┌─────────────┐
│ Généraliste │
└──────┬──────┘
       │
       ↓
Créer consultation
       │
       ├───────────────┐
       ↓               ↓
Prise en charge    Télé-expertise
directe                │
       │               ↓
       │          Choisir spécialité
       │               │
       │               ↓
       │          Choisir spécialiste
       │               │
       │               ↓
       │          Choisir créneau
       │               │
       │               ↓
       │          Envoyer demande
       │               │
       │               ↓
       │        ┌──────────────┐
       │        │ Spécialiste  │
       │        └──────┬───────┘
       │               │
       │               ↓
       │          Avis médical
       │               │
       └───────────────┘
               ↓
       Consultation terminée
```

---

# 📜 Statuts principaux

### Consultation

```text
EN_COURS
EN_ATTENTE_AVIS_SPECIALISTE
TERMINEE
```

### Expertise

```text
EN_ATTENTE
TERMINEE
```

### Priorité

```text
URGENTE
NORMALE
NON_URGENTE
```

### Créneau

```text
DISPONIBLE
INDISPONIBLE
ARCHIVE
```

---

# 📄 Licence

Ce projet est réalisé dans un cadre pédagogique.

---

# 👨‍💻 Auteur

**Mohamed Attefi**

Projet Java Web — Système de Télé-Expertise Médicale

```text
Java 17 • Jakarta EE • JSP • Servlet • JPA • Hibernate • PostgreSQL • Tomcat • Maven
```
