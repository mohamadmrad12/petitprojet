package model;

import javax.swing.JPanel;

public class Avancer extends Thread {

    private Position position;     // référence au modèle
    private JPanel vue;            // pour repaint la vue
    private boolean actif = false; // démarre au premier clic

    public Avancer(Position position, JPanel vue) {
        this.position = position;
        this.vue = vue;
    }

    // permet de démarrer le thread
    public void demarrer() {
        actif = true;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (actif) {
                    position.incrementerAvancement(); // méthode à ajouter dans Position
                    vue.repaint();
                }
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
