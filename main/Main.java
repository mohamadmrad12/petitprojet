package main;

import javax.swing.JFrame;
import model.Position;
import model.Descendre;
import model.Parcours;
import model.Avancer;  // Thread qui fait défiler la ligne
import view.Affichage;
import control.ReactionClic;

public class Main {

    public static void main(String[] args) {

        // 🔹 Modèle : position verticale et horizontale du cercle
        Position position = new Position();

        // 🔹 Modèle : parcours (ligjne brisée), dépend de la position pour décalage X
        Parcours parcours = new Parcours(position);

        // 🔹 Vue : affichage du cercle et du parcours
        Affichage monAffichage = new Affichage(position, parcours);

        // 🔹 Contrôleur : réaction au clic de la souris pour faire sauter le cercle
        ReactionClic reaction = new ReactionClic(position);
        monAffichage.addMouseListener(reaction);

        // 🔹 Fenêtre principale
        JFrame maFenetre = new JFrame("Jeu Du Cercle");
        maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maFenetre.add(monAffichage);
        maFenetre.pack();
        maFenetre.setLocationRelativeTo(null); // centre la fenêtre
        maFenetre.setVisible(true);

        // 🔹 Thread qui fait descendre le cercle (gravité)
        Descendre threadDescendre = new Descendre(position);
        threadDescendre.start();

        // 🔹 Thread qui fera défiler la ligne (avancement horizontal)
        // Ne démarre pas automatiquement, il sera lancé au premier clic
        Avancer threadAvancer = new Avancer(position, monAffichage);

        // 🔹 On transmet ce thread au ReactionClic pour qu'il démarre dès le premier clic
        reaction.setThreadAvancer(threadAvancer);
    }
}
