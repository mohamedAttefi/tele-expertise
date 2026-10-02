package ma.teleexpertise.dao;

import jakarta.persistence.EntityManager;
import ma.teleexpertise.config.JPAUtil;
import ma.teleexpertise.model.Patient;

import java.util.List;

public class PatientDAO {

    public void ajouterPatient(Patient patient) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(patient);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();
        }
    }

    public Patient trouverParId(Long id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.find(Patient.class, id);

        } finally {

            em.close();
        }
    }

    public List<Patient> trouverTous() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery("SELECT p FROM Patient p ORDER BY p.nom", Patient.class).getResultList();

        } finally {

            em.close();
        }
    }

    public Patient chercherParNumeroSecuriteSociale(String numero) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            List<Patient> patients = em.createQuery("SELECT p FROM Patient p WHERE p.numeroSecuriteSociale = :numero", Patient.class).setParameter("numero", numero).getResultList();

            if (patients.isEmpty()) {
                return null;
            }

            return patients.get(0);

        } finally {
            em.close();
        }
    }
}