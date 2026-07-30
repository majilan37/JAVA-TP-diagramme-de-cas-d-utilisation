package TP5.ex1;

import java.time.LocalDate;

public abstract class Resource {
    protected final int id;
    protected String nom;
    protected boolean disponible;

    public Resource(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.disponible = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return nom;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public abstract void reserver(Enseignant enseignant, LocalDate date, int heureDebut, int heureFin);

    public abstract void liberer();

}
