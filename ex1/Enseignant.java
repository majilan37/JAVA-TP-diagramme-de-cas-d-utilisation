package TP5.ex1;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Enseignant extends Utilisateur {
    private static int count;
    private static final Random random = new Random();
    private static final String[] prenoms = {
            "Mohamed", "Ahmed", "Youssef", "Omar",
            "Amine", "Salma", "Sara", "Lina"
    };
    private static final String[] noms = {
            "Alami", "Bennani", "Alaoui", "Tazi",
            "Amrani", "El Idrissi", "Chraibi", "Fassi"
    };
    private static final String[] grades = { "Professeur", "Maitre de conference", "Charge de cours", "Vacataire" };

    private final String grade;

    public Enseignant(String nom, String prenom, String email, String grade) {
        count++;
        this.id = count;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public Reservation reserveResource(Resource resource, LocalDate date, int heureDebut, int heureFin) {
        return resource.reserver(this, date, heureDebut, heureFin);
    }

    public void consulterRecapitulatifHoraire() {
        List<Reservation> reservations = Reservation.getReservationsPourEnseignant(this);
        int totalHeures = reservations.stream()
                .mapToInt(Reservation::getDuree)
                .sum();

        System.out.println("Recapitulatif horaire de " + getNomComplet());
        System.out.println("----------------------------------------");

        if (reservations.isEmpty()) {
            System.out.println("Aucune reservation.");
            return;
        }

        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        System.out.println("Total heures reservees: " + totalHeures + "h");
    }

    public void editerRecapitulatifFormation(Formation formation) {
        System.out.println(formation.editerRecapitulatifHoraire(this));
    }

    @Override
    public String toString() {
        return "Enseignant ID: %d | Nom: %s | Prenom: %s | Grade: %s".formatted(id, nom, prenom, grade);
    }

    public static Enseignant genererEnseignantAleratoire() {
        String nom = noms[random.nextInt(noms.length)];
        String prenom = prenoms[random.nextInt(prenoms.length)];
        String email = String.format("%s.%s@gmail.com", nom.toLowerCase(), prenom.toLowerCase());
        String grade = grades[random.nextInt(grades.length)];

        return new Enseignant(nom, prenom, email, grade);
    }
}
