package ma.teleexpertise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "specialistes")
public class Specialiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    private double tarif;

    private boolean disponible;

    public Specialiste() {
    }

    public Specialiste(
            String nom,
            String prenom,
            Specialite specialite,
            double tarif
    ) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.tarif = tarif;
        this.disponible = true;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public double getTarif() {
        return tarif;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}