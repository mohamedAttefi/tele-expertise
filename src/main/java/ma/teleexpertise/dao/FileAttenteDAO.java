package ma.teleexpertise.dao;

import jakarta.persistence.EntityManager;
import ma.teleexpertise.config.JPAUtil;
import ma.teleexpertise.model.FileAttente;
import ma.teleexpertise.model.Patient;

import java.time.LocalDate;
import java.util.List;

public class FileAttenteDAO {

    public void ajouter(Patient patient) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            FileAttente attente = new FileAttente(patient);

            em.persist(attente);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    public List<FileAttente> trouverTous() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT f FROM FileAttente f ORDER BY f.heureArrivee",
                    FileAttente.class
            ).getResultList();

        } finally {
            em.close();
        }
    }

    public List<FileAttente> trouverPatientsDuJour() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            LocalDate aujourdHui = LocalDate.now();

            List<FileAttente> liste = em.createQuery(
                    "SELECT f FROM FileAttente f",
                    FileAttente.class
            ).getResultList();

            return liste.stream()
                    .filter(f -> f.getHeureArrivee()
                            .toLocalDate()
                            .equals(aujourdHui))
                    .sorted((f1, f2) ->
                            f1.getHeureArrivee()
                                    .compareTo(f2.getHeureArrivee()))
                    .toList();

        } finally {
            em.close();
        }
    }
}