package main;

public class test {

    public static void main(String[] args) {
       /* Une fenêtre avec pour titre "Test" */
       javax.swing.JFrame frame = new javax.swing.JFrame("Test");
       frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
       frame.setSize(400, 300);
       frame.setVisible(true);
       /* Un bouton "coucou" qui écrit "coucou" dans la console chaque fois qu'on clique dessus */
       javax.swing.JButton button = new javax.swing.JButton("coucou");
       button.addActionListener(e -> System.out.println("coucou"));
       frame.getContentPane().add(button);
     
    }
/*dessin un petit personnage rouge   */

}
