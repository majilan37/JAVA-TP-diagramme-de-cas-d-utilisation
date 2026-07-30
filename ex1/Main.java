package TP5.ex1;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Reservation, Enseignants, Etudiants, Formation, Planning des salles,
        // Récapitulatif Horaire

        // List des resource
        Materiel[] materiels = Stream.generate(Materiel::genererMaterielAleratoire)
                .limit(10)
                .toArray(Materiel[]::new);

        Salle[] salles = Stream.generate(Salle::genererSalleAleratoire)
                .distinct()
                .limit(25)
                .toArray(Salle[]::new);

        Collections.shuffle(null);

        Resource[] resources = Stream.of(salles, materiels)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.shuffle(list);
                    return list;
                })).toArray(Resource[]::new);

        Enseignant[] enseignants = Stream.generate(Enseignant::genererEnseignantAleratoire)
                .distinct()
                .limit(8)
                .toArray(Enseignant[]::new);
    }
}
