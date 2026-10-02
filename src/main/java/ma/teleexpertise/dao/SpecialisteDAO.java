package ma.teleexpertise.dao;

import jakarta.persistence.EntityManager;
import ma.teleexpertise.config.JPAUtil;
import ma.teleexpertise.model.Specialiste;
import ma.teleexpertise.model.Specialite;

import java.util.List;

public class SpecialisteDAO {

    public void ajouter(Specialiste specialiste) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(specialiste);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();
        }
    }

    public List<Specialiste> trouverDisponiblesParSpecialite(
            Specialite specialite
    ) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                            "SELECT s FROM Specialiste s " +
                                    "WHERE s.specialite = :specialite " +
                                    "AND s.disponible = true " +
                                    "ORDER BY s.tarif",
                            Specialiste.class
                    )
                    .setParameter("specialite", specialite)
                    .getResultList();

        } finally {

            em.close();
        }
    }
}