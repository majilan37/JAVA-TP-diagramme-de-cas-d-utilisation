package TP5.ex1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Formation {
    private final String nom;
    private final Enseignant responsable;
    private final Set<Enseignant> enseignants = new LinkedHashSet<>();

    public Formation(String nom, Enseignant responsable) {
        if (responsable == null) {
            throw new IllegalArgumentException("Le responsable est obligatoire.");
        }

        this.nom = nom;
        this.responsable = responsable;
        this.enseignants.add(responsable);
    }

    public String getNom() {
        return nom;
    }

    public Enseignant getResponsable() {
        return responsable;
    }

    public void ajouterEnseignant(Enseignant enseignant) {
        if (enseignant == null) {
            throw new IllegalArgumentException("L'enseignant est obligatoire.");
        }
        enseignants.add(enseignant);
    }

    public List<Enseignant> getEnseignants() {
        return new ArrayList<>(enseignants);
    }

    public String editerRecapitulatifHoraire(Enseignant demandeur) {
        if (!responsable.equals(demandeur)) {
            throw new SecurityException("Seul l'enseignant responsable peut editer le recapitulatif de la formation.");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Recapitulatif de la formation ").append(nom).append('\n');
        builder.append("Responsable: ").append(responsable.getNomComplet()).append('\n');
        builder.append("----------------------------------------").append('\n');

        int totalFormation = 0;
        for (Enseignant enseignant : enseignants) {
            int totalHeures = Reservation.getReservationsPourEnseignant(enseignant).stream()
                    .mapToInt(Reservation::getDuree)
                    .sum();
            totalFormation += totalHeures;

            builder.append(enseignant.getNomComplet())
                    .append(" - ")
                    .append(totalHeures)
                    .append("h")
                    .append('\n');
        }

        builder.append("Total formation: ").append(totalFormation).append("h");
        return builder.toString();
    }
}
