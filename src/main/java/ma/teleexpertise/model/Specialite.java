package ma.teleexpertise.model;

public enum Specialite {

    CARDIOLOGIE(
            "Maladies du cœur et des vaisseaux sanguins"
    ),

    PNEUMOLOGIE(
            "Maladies respiratoires et pulmonaires"
    ),

    NEUROLOGIE(
            "Troubles du système nerveux"
    ),

    GASTRO_ENTEROLOGIE(
            "Maladies du système digestif"
    ),

    ENDOCRINOLOGIE(
            "Troubles hormonaux et métaboliques"
    ),

    DERMATOLOGIE(
            "Maladies de la peau"
    ),

    RHUMATOLOGIE(
            "Maladies des articulations, os et muscles"
    ),

    PSYCHIATRIE(
            "Troubles mentaux et psychologiques"
    ),

    NEPHROLOGIE(
            "Maladies des reins"
    ),

    ORTHOPEDIE(
            "Traumatismes et pathologies des os, articulations et muscles"
    );

    private final String description;

    Specialite(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}