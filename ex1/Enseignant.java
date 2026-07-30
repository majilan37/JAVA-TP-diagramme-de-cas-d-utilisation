package TP5.ex1;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

public class Enseignant extends Utilisateur {
    private static int count;
    private String grade;

    private static final Random random = new Random();
    private static final String[] prenoms = {
            "Mohamed", "Ahmed", "Youssef", "Omar",
            "Amine", "Salma", "Sara", "Lina"
    };

    private static final String[] noms = {
            "Alami", "Bennani", "Alaoui", "Tazi",
            "Amrani", "El Idrissi", "Chraibi", "Fassi"
    };

    private static final String[] grades = { "Professeur", "Maitre de conferance", "Charge de cours", "Vacataire" };

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

    public void reserveResource(Resource resource, LocalDate date, int heureDebut, int heureFin) {
        Reservation.cree(this, resource, date, heureDebut, heureFin);
    }

    public void consulterRecapitulatifHoraire() {

        Stream<Reservation> reservations = Reservation.database.stream();
        reservations.filter((reservation) -> reservation.getEnseignant().getId() == reservation.getId())
                .forEach(System.out::println);
    }

    @Override
    public String toString() {
        return String.format("Enseingnant ID: %d | Nom: %s | Prénom: %s", id, nom, prenom);
    }

    public static Enseignant genererEnseignantAleratoire() {
        String nom = noms[random.nextInt(noms.length)];
        String prenom = prenoms[random.nextInt(prenoms.length)];
        String email = String.format("%s.%s@gmail.com", nom.toLowerCase(), prenom.toLowerCase());
        String grade = grades[random.nextInt(grades.length)];

        return new Enseignant(prenom, prenom, email, grade);
    }
}
