package TP5.ex2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Offer {
    private final int id;
    private String name;
    private String duration;
    private LocalDateTime startDate;
    private double price;
    private Agent agent;

    private static List<Offer> offers;

    static {
        offers = new ArrayList<>();
    }

    private static int count;

    public Offer(Agent agent, String name, String duration, LocalDateTime startDate, double price) {
        id = ++count;
        this.name = name;
        this.duration = duration;
        this.startDate = startDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public double getPrice() {
        return price;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static void create(Agent agent, String name, String duration, LocalDateTime startDate, double price) {
        Offer offer = new Offer(agent, name, duration, startDate, price);
        offers.add(offer);
    }

    public static void update(Offer offer, String name, String duration, LocalDateTime startDate, double price) {
        offer.setName(name);
        offer.setDuration(duration);
        offer.setStartDate(startDate);
        offer.setPrice(price);
    }

    public static List<Offer> getAll() {
        return List.copyOf(offers);
    }

    public static List<Offer> getAll(Predicate<Offer> predicate) {
        if (predicate != null) {
            return offers.stream().filter(predicate).toList();
        }

        return getAll();
    }

    public static Optional<Offer> view(int id) {
        return offers.stream().filter((offer) -> offer.id == id)
                .findFirst();
    }
}
