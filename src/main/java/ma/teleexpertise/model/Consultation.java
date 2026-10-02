package ma.teleexpertise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    private String motif;

    @Column(length = 2000)
    private String observations;

    private double cout;

    private LocalDateTime dateConsultation;

    @Enumerated(EnumType.STRING)
    private StatutConsultation statut;

    public Consultation() {
    }

    public Consultation(Patient patient, String motif, String observations) {
        this.patient = patient;
        this.motif = motif;
        this.observations = observations;
        this.cout = 150;
        this.dateConsultation = LocalDateTime.now();
        this.statut = StatutConsultation.EN_COURS;
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getMotif() {
        return motif;
    }

    public String getObservations() {
        return observations;
    }

    public double getCout() {
        return cout;
    }

    public LocalDateTime getDateConsultation() {
        return dateConsultation;
    }

    public StatutConsultation getStatut() {
        return statut;
    }

    public void setStatut(StatutConsultation statut) {
        this.statut = statut;
    }
}