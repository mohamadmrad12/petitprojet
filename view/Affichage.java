package view;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.List;
import model.Position;
import model.Parcours;

public class Affichage extends JPanel {

    private Position position; // cercle
    private Parcours parcours; // ligne qui bouge

    // --- constantes du modèle ---
    private static final int BEFORE = 100;    
    private static final int AFTER  = 200;    
    private static final int H_MIN  = 0;      
    private static final int H_MAX  = 200;    
    private static final int LARGEUR_OVALE = 80;
    private static final int HAUTEUR_OVALE = 160;

    // --- ratios modèle → vue ---
    private static final double RATIO_X = 3.0;
    private static final double RATIO_Y = 3.0;

    private final int POSITION_X; // X fixe du cercle

    public Affichage(Position position, Parcours parcours) {
        this.position = position;
        this.parcours = parcours;

        int largeur = (int)((BEFORE + AFTER) * RATIO_X);
        int hauteur = (int)((H_MAX - H_MIN) * RATIO_Y);
        setPreferredSize(new Dimension(largeur, hauteur));

        setBackground(Color.WHITE);

        POSITION_X = (int)(BEFORE * RATIO_X - LARGEUR_OVALE / 2);

        // Timer pour redessiner la vue
        new Timer(20, e -> repaint()).start();
    }

    // Transforme un point du modèle en point dans la vue
    private Point modeleVersVue(Point p, int decalageX) {
        int xV = (int)((p.x - decalageX) * RATIO_X); // décalage horizontal pour "faire défiler" la ligne
        int yV = (int)((H_MAX - p.y) * RATIO_Y);
        return new Point(xV, yV);
    }

    // Dessine la ligne du parcours derrière le cercle
    private void dessinerParcours(Graphics g, int decalageX) {
        g.setColor(Color.BLUE);
        List<Point> points = parcours.getPoints();
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = modeleVersVue(points.get(i), decalageX);
            Point p2 = modeleVersVue(points.get(i + 1), decalageX);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int decalageX = 0; // tu peux incrémenter cette valeur pour simuler le mouvement de la ligne

        // Cercle au premier plan
        int yCercle = (int)((H_MAX - position.getHauteur()) * RATIO_Y - HAUTEUR_OVALE);
        g.setColor(Color.WHITE);
        g.fillOval(POSITION_X, yCercle, LARGEUR_OVALE, HAUTEUR_OVALE);
        g.setColor(Color.BLACK);
        g.drawOval(POSITION_X, yCercle, LARGEUR_OVALE, HAUTEUR_OVALE);

        // Ligne derrière le cercle
        dessinerParcours(g, decalageX);
    }
}
