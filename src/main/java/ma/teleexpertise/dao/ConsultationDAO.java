package ma.teleexpertise.dao;

import jakarta.persistence.EntityManager;
import ma.teleexpertise.config.JPAUtil;
import ma.teleexpertise.model.Consultation;

public class ConsultationDAO {

    public void ajouter(Consultation consultation) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(consultation);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();
        }
    }
}