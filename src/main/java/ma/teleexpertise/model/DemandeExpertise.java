package ma.teleexpertise.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "demandes_expertise")
public class DemandeExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "specialiste_id", nullable = false)
    private Specialiste specialiste;

    @ManyToOne
    @JoinColumn(name = "creneau_id", nullable = false)
    private Creneau creneau;

    @Column(nullable = false, length = 2000)
    private String question;

    @Column(length = 3000)
    private String donneesMedicales;

    @Column(length = 3000)
    private String analyses;

    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    private StatutDemande statut;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "avis_specilite", length = 3000)
    private String avisSpecialiste;

    @Column(length = 3000)
    private String recommandations;

    @Column(name = "date_reponse")
    private LocalDateTime dateReponse;

    public DemandeExpertise() {
    }

    public DemandeExpertise(Consultation consultation, Specialiste specialiste, Creneau creneau, String question, String donneesMedicales, String analyses, Priorite priorite) {

        this.consultation = consultation;
        this.specialiste = specialiste;
        this.creneau = creneau;
        this.question = question;
        this.donneesMedicales = donneesMedicales;
        this.analyses = analyses;
        this.priorite = priorite;

        this.statut = StatutDemande.EN_ATTENTE;
        this.dateCreation = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public Specialiste getSpecialiste() {
        return specialiste;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public String getQuestion() {
        return question;
    }

    public String getAvisSpecialiste() {
        return avisSpecialiste;
    }

    public String getRecommandations() {
        return recommandations;
    }

    public LocalDateTime getDateReponse() {
        return dateReponse;
    }

    public String getDonneesMedicales() {
        return donneesMedicales;
    }

    public String getAnalyses() {
        return analyses;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public StatutDemande getStatut() {
        return statut;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setStatut(StatutDemande statut) {
        this.statut = statut;
    }

    public void setAvisSpecialiste(String avisSpecialiste) {
        this.avisSpecialiste = avisSpecialiste;
    }

    public void setRecommandations(String recommandations) {
        this.recommandations = recommandations;
    }

    public void setDateReponse(LocalDateTime dateReponse) {
        this.dateReponse = dateReponse;
    }
}