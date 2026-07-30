package TP5.ex1;

import java.util.Random;

public class Etudiant extends Utilisateur {
    private static int count;
    private static final Random random = new Random();
    private static final String[] prenoms = {
            "Anas", "Hamza", "Mehdi", "Zakaria",
            "Ilyas", "Rayan", "Ayoub", "Imane",
            "Nour", "Hiba", "Aya", "Khadija"
    };
    private static final String[] noms = {
            "Berrada", "Kadiri", "Bouziane", "Tahiri",
            "Bouchentouf", "Mansouri", "Jebari", "Sbai",
            "Lahlou", "Rachidi", "Zerouali", "Ouazzani"
    };

    public Etudiant(String nom, String prenom, String email) {
        count++;
        this.id = count;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Etudiant ID: %d | Nom: %s | Prenom: %s".formatted(id, nom, prenom);
    }

    public static Etudiant genererEtudiantAleratoire() {
        String nom = noms[random.nextInt(noms.length)];
        String prenom = prenoms[random.nextInt(prenoms.length)];
        String email = String.format("%s.%s@gmail.com", nom.toLowerCase(), prenom.toLowerCase());

        return new Etudiant(nom, prenom, email);
    }
}
