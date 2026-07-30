package TP5.ex1;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Enseignant responsable = new Enseignant("Alami", "Salma", "salma.alami@gmail.com", "Professeur");
        Enseignant enseignant2 = new Enseignant("Bennani", "Ahmed", "ahmed.bennani@gmail.com", "Vacataire");
        Etudiant etudiant = new Etudiant("Kadiri", "Hamza", "hamza.kadiri@gmail.com");

        Formation formation = new Formation("Master Informatique", responsable);
        formation.ajouterEnseignant(enseignant2);

        Salle salleA = new Salle(101, "Salle A", 35);
        Salle salleB = new Salle(102, "Salle B", 40);
        Materiel ordinateur = new Materiel(201, "Laptop 1", "Ordinateur Portable");
        Materiel projecteur = new Materiel(202, "Projecteur 1", "Video Projecteur");

        responsable.reserveResource(salleA, LocalDate.of(2026, 8, 3), 8, 10);
        responsable.reserveResource(projecteur, LocalDate.of(2026, 8, 3), 8, 10);
        enseignant2.reserveResource(salleB, LocalDate.of(2026, 8, 3), 10, 12);
        enseignant2.reserveResource(ordinateur, LocalDate.of(2026, 8, 4), 14, 16);

        System.out.println("Consultation du planning des salles par un enseignant");
        responsable.consulterPlanning();
        System.out.println();

        System.out.println("Consultation du planning des salles par un etudiant");
        etudiant.consulterPlanning();
        System.out.println();

        responsable.consulterRecapitulatifHoraire();
        System.out.println();
        enseignant2.consulterRecapitulatifHoraire();
        System.out.println();

        responsable.editerRecapitulatifFormation(formation);
    }
}
