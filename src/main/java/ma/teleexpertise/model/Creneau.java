package ma.teleexpertise.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "creneaux")
public class Creneau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "heure_debut")
    private LocalTime heureDebut;

    @Column(name = "heure_fin")
    private LocalTime heureFin;

    @Enumerated(EnumType.STRING)
    private StatutCreneau statut;

    @ManyToOne
    private Specialiste specialiste;

    public Creneau() {
    }

    public Creneau(
            LocalTime heureDebut,
            LocalTime heureFin,
            StatutCreneau statut,
            Specialiste specialiste
    ) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.statut = statut;
        this.specialiste = specialiste;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public StatutCreneau getStatut() {
        return statut;
    }

    public Specialiste getSpecialiste() {
        return specialiste;
    }

    public void setStatut(StatutCreneau statut) {
        this.statut = statut;
    }
}