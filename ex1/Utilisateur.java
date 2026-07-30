package TP5.ex1;

import java.util.List;

public abstract class Utilisateur {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String email;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    public void consulterPlanning() {
        List<Reservation> reservations = Reservation.getReservationsDesSalles();

        System.out.println("Planning des salles");
        System.out.println("-------------------");

        if (reservations.isEmpty()) {
            System.out.println("Aucune salle reservee.");
            return;
        }

        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
