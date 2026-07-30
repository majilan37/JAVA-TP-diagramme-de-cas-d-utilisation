package TP5.ex1;

import java.time.LocalDate;
import java.util.Random;

public class Materiel extends Resource {
    private String categorie;
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
    public void reserver(Enseignant enseignant, LocalDate date, int heureDebut, int heureFin) {
        if (this.disponible == false) {
            System.out.println("Resource n'est pas disponible");
        } else {
            Reservation.cree(enseignant, this, date, heureDebut, heureFin);
        }
    }

    @Override
    public void liberer() {
        Reservation reservation = Reservation.cherche(id);

        if (reservation == null) {
            throw new RuntimeException("Resevation introuvable");
        }

        Reservation.remove(reservation);
    }

    @Override
    public String toString() {
        return "Materiel ID: %d | Nom: %s | Disponible: %b".formatted(id, nom, disponible);
    }

    public static Materiel genererMaterielAleratoire() {
        int id = random.nextInt(65, 90);
        String nom = "Materiel " + (char) id;
        String categorie = CATEGORIES[random.nextInt(CATEGORIES.length)];

        return new Materiel(id, nom, categorie);
    }

}
