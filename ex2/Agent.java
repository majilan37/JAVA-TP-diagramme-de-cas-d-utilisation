package TP5.ex2;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Agent extends User {
    public Agent(String fistName, String lastName, String email) {
        super(fistName, lastName, Role.AGENT);
        this.email = email;
    }

    public void createOffer(String name, String duration, LocalDateTime startDate, double price) {
        Offer.create(this, name, duration, startDate, price);
    }

    public void updateOffer(int id, String name, String duration, LocalDateTime startDate, double price) {
        Optional<Offer> offer = getOffer(id);
        if (offer.isPresent()) {
            Offer.update(offer.get(), name, duration, startDate, price);
        } else {
            System.err.println("Can't update offer");
        }
    }

    public List<Offer> getOffers() {
        Predicate<Offer> predicate = (offer) -> {
            return offer.getAgent().equals(this);
        };

        return Offer.getAll(predicate);
    }

    public Optional<Offer> getOffer(int id) {
        Optional<Offer> optionalOffer = Offer.view(id);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();

            if (!offer.getAgent().equals(this)) {
                System.out.println("You cannot view this offer, it doesn't belong to you");
                return Optional.empty();
            }

        }
        return optionalOffer;
    }

    public List<Reservation> getReservationsOfOffer(Offer offer) {
        Predicate<Reservation> predicate = (reservation) -> {
            Offer currentOffer = reservation.getOffer();
            return currentOffer.equals(offer) && currentOffer.getAgent().equals(this);
        };

        return Reservation.getAll(predicate);
    }

    public List<Reservation> getReservationsOfClient(Client client) {
        Predicate<Reservation> predicate = (reservation) -> {
            return reservation.getOffer().getAgent().equals(this) && reservation.getClient().equals(client);
        };

        return Reservation.getAll(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Agent other)) {
            return false;
        }

        return this.id == other.id;
    }

}
