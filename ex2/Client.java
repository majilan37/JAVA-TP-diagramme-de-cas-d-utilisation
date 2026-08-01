package TP5.ex2;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Client extends User {
    private String address;

    public Client(String firstName, String lastName, String address, String phoneNumber, String email) {
        super(firstName, lastName, Role.CLIENT, phoneNumber, email);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void getOffers() {
    }

    public void addReservation(Offer offer, LocalDateTime startDate, int participants) {
        Reservation.create(offer, this, email, phoneNumber, startDate, participants);
    }

    public List<Reservation> getReservations() {
        Predicate<Reservation> predicate = (reservation) -> {
            return reservation.getClient().equals(this);
        };

        return Reservation.getAll(predicate);
    }

    public Optional<Reservation> getReservation(int id) {
        Optional<Reservation> optionalReservation = Reservation.view(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            if (!reservation.getClient().equals(this)) {
                System.out.println("You cannot view this reservation, it doesn't belong to you");
                return null;
            }

        }
        return optionalReservation;
    }

    public void getTravelHistory() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Client other)) {
            return false;
        }

        return this.id == other.id;
    }
}
