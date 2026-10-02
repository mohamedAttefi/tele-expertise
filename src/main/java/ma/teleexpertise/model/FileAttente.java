package ma.teleexpertise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file_attente")
public class FileAttente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Patient patient;

    @Column(name = "heure_arrivee")
    private LocalDateTime heureArrivee;

    public FileAttente() {
    }

    public FileAttente(Patient patient) {
        this.patient = patient;
        this.heureArrivee = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getHeureArrivee() {
        return heureArrivee;
    }
}