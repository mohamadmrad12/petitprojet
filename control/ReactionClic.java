package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Position;
import model.Avancer;

/**
 * Contrôleur qui gère les clics de souris pour déclencher le saut.
 * Il démarre aussi le thread de défilement de la ligne au premier clic.
 */
public class ReactionClic extends MouseAdapter {

    private Position position;      // le modèle du cercle
    private Avancer threadAvancer;  // thread pour faire défiler la ligne
    private boolean started = false; // pour ne lancer le thread qu'une seule fois

    public ReactionClic(Position position) {
        this.position = position;
    }

    // Méthode pour transmettre le thread Avancer
    public void setThreadAvancer(Avancer t) {
        this.threadAvancer = t;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 🔹 Déclenche un saut
        position.jump(); 

        // 🔹 Affiche la hauteur dans la console
        System.out.println("Hauteur = " + position.getHauteur());

        // 🔹 Si le thread de défilement n'est pas encore lancé, le démarrer
        if (!started) {
    started = true;
    new Thread(() -> {
        try {
            while (true) {
                position.incrementerAvancement();
                Thread.sleep(50); // vitesse du défilement
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }).start();
}

}
}
