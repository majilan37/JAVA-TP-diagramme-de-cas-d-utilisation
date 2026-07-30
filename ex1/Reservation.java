package TP5.ex1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Reservation {
    private static int count;
    private static final List<Reservation> database = new ArrayList<>();

    private final int id;
    private final Enseignant enseignant;
    private final Resource resource;
    private final LocalDate date;
    private final int heureDebut;
    private final int heureFin;

    private Reservation(Enseignant enseignant, Resource resource, LocalDate date, int heureDebut, int heureFin) {
        count++;
        this.id = count;
        this.enseignant = enseignant;
        this.resource = resource;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public static Reservation creer(Enseignant enseignant, Resource resource, LocalDate date, int heureDebut, int heureFin) {
        validerCreneau(date, heureDebut, heureFin);

        if (!resource.estDisponible(date, heureDebut, heureFin)) {
            throw new IllegalStateException("La ressource n'est pas disponible pour ce creneau.");
        }

        Reservation reservation = new Reservation(enseignant, resource, date, heureDebut, heureFin);
        database.add(reservation);
        return reservation;
    }

    public static void annuler(int reservationId) {
        Reservation reservation = chercher(reservationId);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation introuvable: " + reservationId);
        }

        database.remove(reservation);
    }

    public static Reservation chercher(int id) {
        for (Reservation reservation : database) {
            if (reservation.id == id) {
                return reservation;
            }
        }
        return null;
    }

    public static List<Reservation> getAll() {
        return List.copyOf(database);
    }

    public static List<Reservation> getReservationsPourRessource(Resource resource) {
        return database.stream()
                .filter(reservation -> reservation.resource.equals(resource))
                .sorted(Comparator.comparing(Reservation::getDate)
                        .thenComparingInt(Reservation::getHeureDebut))
                .collect(Collectors.toList());
    }

    public static List<Reservation> getReservationsDesSalles() {
        return database.stream()
                .filter(reservation -> reservation.resource instanceof Salle)
                .sorted(Comparator.comparing(Reservation::getDate)
                        .thenComparingInt(Reservation::getHeureDebut)
                        .thenComparing(reservation -> reservation.resource.getNom()))
                .collect(Collectors.toList());
    }

    public static List<Reservation> getReservationsPourEnseignant(Enseignant enseignant) {
        return database.stream()
                .filter(reservation -> reservation.enseignant.equals(enseignant))
                .sorted(Comparator.comparing(Reservation::getDate)
                        .thenComparingInt(Reservation::getHeureDebut))
                .collect(Collectors.toList());
    }

    public static boolean aConflit(Resource resource, LocalDate date, int heureDebut, int heureFin) {
        return database.stream().anyMatch(reservation ->
                reservation.resource.equals(resource)
                        && reservation.date.equals(date)
                        && reservation.chevauche(heureDebut, heureFin));
    }

    private static void validerCreneau(LocalDate date, int heureDebut, int heureFin) {
        if (date == null) {
            throw new IllegalArgumentException("La date est obligatoire.");
        }
        if (heureDebut < 0 || heureFin > 24 || heureDebut >= heureFin) {
            throw new IllegalArgumentException("Creneau horaire invalide.");
        }
    }

    private boolean chevauche(int autreHeureDebut, int autreHeureFin) {
        return heureDebut < autreHeureFin && autreHeureDebut < heureFin;
    }

    public int getId() {
        return id;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public Resource getResource() {
        return resource;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public int getDuree() {
        return heureFin - heureDebut;
    }

    @Override
    public String toString() {
        return "Reservation ID: %d | Ressource: %s | Enseignant: %s | Date: %s | Debut: %02dh | Fin: %02dh"
                .formatted(id, resource.getNom(), enseignant.getNomComplet(), date, heureDebut, heureFin);
    }
}
