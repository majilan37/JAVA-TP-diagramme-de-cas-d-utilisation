package TP5.ex2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Reservation {
    private final int id;
    private final Offer offer;
    private final Client client;
    private String email;
    private String phone;
    private LocalDateTime startDate;
    private int participants;

    private static final List<Reservation> reservations;

    static {
        reservations = new ArrayList<>();
    }

    public Reservation(Offer offer, Client client, String email, String phone, LocalDateTime startDate,
            int participants) {
        id = reservations.size() + 1;
        this.offer = offer;
        this.client = client;
        this.email = email;
        this.phone = phone;
        this.startDate = startDate;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public Offer getOffer() {
        return offer;
    }

    public Client getClient() {
        return client;
    }

    public String getEmail() {
        return email;
    }

    public int getParticipants() {
        return participants;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public static void create(Offer offer, Client client, String email, String phone, LocalDateTime startDate,
            int participants) {
        Reservation reservation = new Reservation(offer, client, email, phone, startDate, participants);

        reservations.add(reservation);
    }

    public static List<Reservation> getAll() {
        return List.copyOf(reservations);
    }

    public static List<Reservation> getAll(Predicate<Reservation> predicate) {
        if (predicate == null) {
            return getAll();
        }

        return reservations.stream().filter(predicate).toList();
    }

    public static Optional<Reservation> view(int id) {
        return reservations.stream().filter((r) -> r.id == id)
                .findFirst();
    }

    // public static List<Reservation> getReservationsOfOffer() {

    // }
}
