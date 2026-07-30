package TP5.ex1;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

class Salle extends Resource {
    private final int capacite;
    private static final Random random = new Random();

    public Salle(int id, String nom, int capacite) {
        super(id, nom);
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    @Override
    public Reservation reserver(Enseignant enseignant, LocalDate date, int heureDebut, int heureFin) {
        return Reservation.creer(enseignant, this, date, heureDebut, heureFin);
    }

    @Override
    public String toString() {
        return "Salle ID: %d | Nom: %s | Capacite: %d".formatted(id, nom, capacite);
    }

    public static Salle genererSalleAleratoire() {
        int id = random.nextInt(100, 999);
        String nom = "Salle " + (char) random.nextInt(65, 91);
        int capacite = random.nextInt(30, 41);

        return new Salle(id, nom, capacite);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Salle other)) {
            return false;
        }
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
