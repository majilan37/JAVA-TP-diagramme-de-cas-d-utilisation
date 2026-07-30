package TP5.ex1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private static int count;
    private final int id;
    private Enseignant enseignant;
    private Resource resource;
    private LocalDate date;
    private int heureDebut;
    private int heureFin;
    public static List<Reservation> database;

    static {
        database = new ArrayList<>();
    }

    private Reservation(Enseignant enseignant, Resource resource, LocalDate date, int heureDebut, int heureFin) {
        count++;
        id = count;
        this.enseignant = enseignant;
        this.resource = resource;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public static void cree(Enseignant enseignant, Resource resource, LocalDate date, int heureDebut, int heureFin) {
        boolean resourceDisponible = resource.getDisponible();

        if (!resourceDisponible) {
            throw new RuntimeException("Resource déja reservée");
        }

        resource.setDisponible(false);
        database.add(new Reservation(enseignant, resource, date, heureDebut, heureFin));
    }

    public static void remove(Reservation reservation) {
        Resource resource = chercheResrouce(reservation);
        resource.setDisponible(false);
        boolean suprimmee = database.remove(reservation);
        if (suprimmee) {
            System.out.println("Reservation supprimmée: " + reservation.getId());
        } else {
            System.out.println("Impossible de supprimer cette reservation: " + reservation.getId());
        }
    }

    public static Resource chercheResrouce(Reservation reservation) {
        Reservation r = Reservation.cherche(reservation.getId());

        return r.getResource();
    }

    public static Reservation cherche(int id) {
        Reservation reservation = null;

        for (Reservation r : database) {
            if (r.getId() == id) {
                reservation = r;
            }
        }

        return reservation;
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

    @Override
    public String toString() {
        return String.format(
                "Reservation ID: %d | Resource Reservée: (%s) | Enseignant: (%s) | Date: %s | Heure debut: %s | Heure fin: %s ",
                id,
                resource, enseignant, date, heureDebut, heureFin);
    }
}
