package ma.teleexpertise.dao;

import jakarta.persistence.EntityManager;
import ma.teleexpertise.config.JPAUtil;
import ma.teleexpertise.model.DemandeExpertise;

import java.util.List;

public class DemandeExpertiseDAO {

    public List<DemandeExpertise> trouverToutes() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT d FROM DemandeExpertise d",
                    DemandeExpertise.class
            ).getResultList();

        } finally {
            em.close();
        }
    }

    public void ajouter(DemandeExpertise demande) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(demande);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }
}