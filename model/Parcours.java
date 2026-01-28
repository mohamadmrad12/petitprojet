package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Parcours {

    private ArrayList<Point> points;
    private Position position;
    private static final Random rand = new Random();

    private static final int X_MIN = 50;  
    private static final int X_MAX = 100; 
    private static final int Y_MIN = Position.HAUTEUR_MIN; 
    private static final int Y_MAX = Position.HAUTEUR_MAX; 

    private int dernierX = 0; // pour savoir où continuer le parcours
    private int yMilieu;      // Y initial du cercle pour la ligne horizontale

    public Parcours(Position position) {
        this.position = position;
        points = new ArrayList<>();

        yMilieu = Position.HAUTEUR_MAX / 2;

        // Créer 3 premiers points horizontaux
        int x = 0;
        for (int i = 0; i < 3; i++) {
            points.add(new Point(x, yMilieu));
            dernierX = x;
            x += 50;
        }
    }

    // Cette méthode ajoute de nouveaux points si nécessaire
    public void avancer() {
        int ESPACE_MIN = Position.HAUTEUR_OVALE + 10;
        int MAX_DELTA_Y = 15; // variations plus douces
        
        while (dernierX - position.getAvancement() < 1000) {
            dernierX += X_MIN + rand.nextInt(X_MAX - X_MIN + 1);
            
            int dernierY = points.get(points.size() - 1).y;
            int deltaY = rand.nextInt(2 * MAX_DELTA_Y + 1) - MAX_DELTA_Y;
            int y = dernierY + deltaY;
            
            // 🔹 CORRECTION : Ne pas descendre sous 50% de la hauteur max
            // Au lieu de Y_MAX / 3 (33), on met Y_MAX / 2 (50)
            int Y_MIN_EFFECTIF = Y_MAX / 2;  // La ligne reste dans la moitié supérieure
            y = Math.max(Y_MIN_EFFECTIF, Math.min(y, Y_MAX - ESPACE_MIN));
            
            points.add(new Point(dernierX, y));
        }
    }

    public ArrayList<Point> getPoints() {
        // On génère d'abord les points nécessaires
        avancer();

        ArrayList<Point> pointsDecales = new ArrayList<>();
        for (Point p : points) {
            pointsDecales.add(new Point(p.x - position.getAvancement(), p.y));
        }
        return pointsDecales;
    }
}