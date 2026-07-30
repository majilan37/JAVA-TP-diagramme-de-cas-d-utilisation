package TP5.ex1;

import java.time.LocalDate;
import java.util.Random;

class Salle extends Resource {
    private int capacite;
    private static final Random random = new Random();

    public Salle(int id, String nom, int capacite) {
        super(id, nom);
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
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
        return "Salle ID: %d | Nom: %s | Disponible: %b".formatted(id, nom, disponible);
    }

    public static Salle genererSalleAleratoire() {
        int id = random.nextInt(65, 90);
        String nom = "Salle " + (char) id;
        int capacite = random.nextInt(30, 40);

        return new Salle(id, nom, capacite);
    }
}
