package model;

public class Position {

    // 🔹 Attributs
    private int hauteur = 0;        // hauteur actuelle
    private int vitesse = 0;        // vitesse verticale
    private int avancement = 0;     // position horizontale (X) pour le parcours

    // 🔹 Constantes
    public static final int IMPULSION = 10;      // force d'un saut
    public static final int HAUTEUR_OVALE = 10;  // taille de l'ovale pour la vue
    public static final int HAUTEUR_MIN = 0;     // hauteur minimale
    public static final int HAUTEUR_MAX = 100;   // hauteur maximale
    public static final int BEFORE = 200;        // horizon derrière
    public static final int AFTER = 50;          // horizon devant
    public static final int GRAVITE = 2;         // gravité

    // 🔹 Getters
    public int getHauteur() { return hauteur; }
    public int getAvancement() { return avancement; }

    // 🔹 Déclenche un saut
    public void jump() { vitesse = IMPULSION; }

    // 🔹 Met à jour la hauteur selon la vitesse et la gravité
    public void move() {
        hauteur += vitesse;
        vitesse -= GRAVITE;

        if (hauteur < HAUTEUR_MIN) { hauteur = HAUTEUR_MIN; vitesse = 0; }
        if (hauteur > HAUTEUR_MAX) { hauteur = HAUTEUR_MAX; vitesse = 0; }
    }

    // 🔹 Avance horizontalement de delta pixels
    public void avancer(int delta) {
        avancement += delta;
    }

    // 🔹 Avance avec valeur fixe (exemple vitesse de la ligne)
    public void incrementerAvancement() {
        avancement += 5;
    }
}
