package model;

public class Descendre extends Thread {

    private Position position; // modèle à mettre à jour

    public Descendre(Position position) {
        this.position = position;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                position.move();   // met à jour la position
                Thread.sleep(100); // pause entre chaque mise à jour
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // arrêter le thread proprement
        }
    }

    public void demandeArret() {
        this.interrupt(); // méthode pour arrêter le thread
    }
}
