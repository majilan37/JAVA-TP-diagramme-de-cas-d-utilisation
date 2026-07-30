package TP5.ex1;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Materiel extends Resource {
    private final String categorie;
    private static final Random random = new Random();
    private static final String[] CATEGORIES = { "Ordinateur Portable", "Video Projecteur" };

    public Materiel(int id, String nom, String categorie) {
        super(id, nom);
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    @Override
    public Reservation reserver(Enseignant enseignant, LocalDate date, int heureDebut, int heureFin) {
        return Reservation.creer(enseignant, this, date, heureDebut, heureFin);
    }

    @Override
    public String toString() {
        return "Materiel ID: %d | Nom: %s | Categorie: %s".formatted(id, nom, categorie);
    }

    public static Materiel genererMaterielAleratoire() {
        int id = random.nextInt(1000, 1999);
        String nom = "Materiel " + (char) random.nextInt(65, 91);
        String categorie = CATEGORIES[random.nextInt(CATEGORIES.length)];

        return new Materiel(id, nom, categorie);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Materiel other)) {
            return false;
        }
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
