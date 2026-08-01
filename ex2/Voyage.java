package TP5.ex2;

public class Voyage {
    private final int id;
    private final Reservation reservation;

    private static int count;

    public Voyage(Reservation reservation) {
        id = ++count;
        this.reservation = reservation;
    }

    public int getId() {
        return id;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
