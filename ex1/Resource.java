package TP5.ex1;

import java.time.LocalDate;

public abstract class Resource {
    protected final int id;
    protected final String nom;

    protected Resource(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public boolean estDisponible(LocalDate date, int heureDebut, int heureFin) {
        return !Reservation.aConflit(this, date, heureDebut, heureFin);
    }

    public abstract Reservation reserver(Enseignant enseignant, LocalDate date, int heureDebut, int heureFin);

    @Override
    public String toString() {
        return "%s{id=%d, nom='%s'}".formatted(getClass().getSimpleName(), id, nom);
    }
}
