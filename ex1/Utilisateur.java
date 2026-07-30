package TP5.ex1;

import java.util.stream.Stream;

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

    public void consulterPlanning() {
        Stream<Reservation> reservations = Reservation.database.stream()
                .filter((reservation) -> reservation.getResource() instanceof Salle);

        long totalSalleReservee = reservations
                .count();

        System.out.println("Total des salle reservée: " + totalSalleReservee);
        System.out.println("--------------");
        System.out.println("Salle réservée: ");

        reservations.forEach((reservation) -> {
            System.out.println(reservation.getResource());
        });
    }

}
