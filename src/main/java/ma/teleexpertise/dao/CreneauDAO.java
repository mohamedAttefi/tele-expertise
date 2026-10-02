package ma.teleexpertise.dao;

import jakarta.persistence.EntityManager;
import ma.teleexpertise.config.JPAUtil;
import ma.teleexpertise.model.Creneau;

import java.time.LocalTime;
import java.util.List;

public class CreneauDAO {

    public void ajouter(Creneau creneau) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(creneau);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();
        }
    }

    public List<Creneau> trouverDisponibles(Long specialisteId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            List<Creneau> creneaux = em.createQuery(
                            "SELECT c FROM Creneau c " +
                                    "WHERE c.specialiste.id = :id " +
                                    "AND c.statut = 'DISPONIBLE' " +
                                    "ORDER BY c.heureDebut",
                            Creneau.class
                    )
                    .setParameter("id", specialisteId)
                    .getResultList();

            LocalTime maintenant = LocalTime.now();

            return creneaux.stream()
                    .filter(c -> c.getHeureDebut().isAfter(maintenant))
                    .toList();

        } finally {
            em.close();
        }
    }
}