package ma.teleexpertise;

import jakarta.persistence.EntityManager;
import ma.teleexpertise.config.JPAUtil;
import ma.teleexpertise.dao.CreneauDAO;
import ma.teleexpertise.dao.PatientDAO;
import ma.teleexpertise.dao.SpecialisteDAO;
import ma.teleexpertise.model.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestJPA {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        Specialiste specialiste = em.find(
                Specialiste.class,
                1L
        );

        em.close();

        if (specialiste == null) {
            System.out.println("Spécialiste introuvable.");
            return;
        }

        CreneauDAO dao = new CreneauDAO();

        dao.ajouter(new Creneau(
                LocalTime.of(9, 0),
                LocalTime.of(9, 30),
                StatutCreneau.DISPONIBLE,
                specialiste
        ));

        dao.ajouter(new Creneau(
                LocalTime.of(9, 30),
                LocalTime.of(10, 0),
                StatutCreneau.DISPONIBLE,
                specialiste
        ));

        dao.ajouter(new Creneau(
                LocalTime.of(10, 0),
                LocalTime.of(10, 30),
                StatutCreneau.DISPONIBLE,
                specialiste
        ));

        dao.ajouter(new Creneau(
                LocalTime.of(10, 30),
                LocalTime.of(11, 0),
                StatutCreneau.RESERVE,
                specialiste
        ));

        dao.ajouter(new Creneau(
                LocalTime.of(11, 0),
                LocalTime.of(11, 30),
                StatutCreneau.DISPONIBLE,
                specialiste
        ));

        dao.ajouter(new Creneau(
                LocalTime.of(11, 30),
                LocalTime.of(12, 0),
                StatutCreneau.DISPONIBLE,
                specialiste
        ));

        System.out.println("Créneaux ajoutés !");
    }
}