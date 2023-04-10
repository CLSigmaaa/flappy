package edu.val;

import edu.val.models.Decor;
import edu.val.models.Flappy;
import edu.val.models.Tuyau;
import edu.val.models.Bonus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Fenetre extends Canvas {
    private boolean alreadyStarted = false;
    private boolean gameOver = false;
    public static int gap = 100;
    public static int score = 0;
    public static int NB_TUYAUX = 5;
    public static int NB_DECORS = 5;
    public static int ESPACE_TUYAUX = 400;
    public static int ESPACE_DECORS = 150;
    protected Flappy flappy;
    public static Tuyau[] listeTuyauxBas = new Tuyau[NB_TUYAUX];
    public static Tuyau[] listeTuyauxHaut = new Tuyau[NB_TUYAUX];
    public static Decor[] listeDecors = new Decor[NB_DECORS];
    public static ArrayList<Bonus> listeBonus = new ArrayList<Bonus>();

    public static int LARGEUR = 500;
    public static int HAUTEUR = 500;
    public static double tuyau_max_hauteur = HAUTEUR * 0.60;
    public static double tuyau_min_hauteur = HAUTEUR * 0.20;
    public Color[] couleurs = {Color.green, Color.blue, Color.red, Color.yellow, Color.orange, Color.pink, Color.cyan, Color.magenta, Color.gray, Color.darkGray, Color.lightGray, Color.white, Color.black};

    protected boolean spacePressed = false;


    public Fenetre() {
        setSize(LARGEUR,HAUTEUR);

        JFrame frame = new JFrame("Super jeu !");
        JPanel panneau = (JPanel) frame.getContentPane();
        panneau.setSize(LARGEUR,HAUTEUR);
        panneau.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
        setBounds(0, 0, LARGEUR , HAUTEUR);
        panneau.add(this);

        frame.setSize(LARGEUR,HAUTEUR);
        frame.pack();
        frame.setResizable(false);
        frame.setIgnoreRepaint(true);
        frame.requestFocus();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {

                    if(!spacePressed) {
                        flappy.setVitesseY(-3);
                    }
                    spacePressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spacePressed = false;
                }
            }
        });

        createBufferStrategy(2);
        this.setFocusable(false);

        frame.setVisible(true);

        demarrer();
    }

    public void demarrer() {

        flappy = new Flappy();
        // display the score with text

        // Loop pour créer les tuyaux
        for(int i = 0; i < listeTuyauxBas.length; i++) {

            int hauteurTuyauHaut = (int)(Math.random() * (tuyau_max_hauteur - tuyau_min_hauteur)) + (int)tuyau_min_hauteur;
            int hauteurTuyauBas = HAUTEUR - hauteurTuyauHaut - gap;

            listeTuyauxBas[i] = new Tuyau(
                    LARGEUR + ESPACE_TUYAUX * i,
                    HAUTEUR - hauteurTuyauBas,
                    80,
                    hauteurTuyauBas,
                    4,
                    couleurs[i],
                    i);

            listeTuyauxHaut[i] = new Tuyau(
                    LARGEUR + ESPACE_TUYAUX * i,
                    0,
                    80,
                    hauteurTuyauHaut,
                    4,
                    couleurs[i],
                    i);

        }

        // Loop pour créer les décors
        for(int i = 0; i < listeDecors.length; i++) {

            int hauteur_decor = 100;
            listeDecors[i] = new Decor(
                    ESPACE_DECORS * i,
                    HAUTEUR - hauteur_decor,
                    80,
                    hauteur_decor,
                    2,
                    Color.BLACK,
                    i
            );
        }


        try {
            long frame = 0;

            while(!gameOver) {
                frame ++;

                // Affichage
                Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

                dessin.setColor(Color.white);
                dessin.fillRect(0,0,LARGEUR,HAUTEUR);

                // Decors
                for(Decor decor : listeDecors) {
                    decor.deplacement();
                    decor.dessine(dessin);
                }

                // Flappy
                flappy.deplacement();
                flappy.dessine(dessin);

                // Tuyaux
                for(int i = 0; i < listeTuyauxBas.length; i++) {
                    Tuyau tuyau_bas = listeTuyauxBas[i];
                    Tuyau tuyau_haut = listeTuyauxHaut[i];

                    // On calcule la hauteur du tuyau du haut avec un nombre random entre le min (20% de la hauteur de la fenetre) et le max (60% de la hauteur de la fenetre)
                    int hauteurTuyauHaut = (int) (Math.random() * (tuyau_max_hauteur - tuyau_min_hauteur)) + (int) tuyau_min_hauteur;
                    // On calcule la hauteur du tuyau du bas en soustrayant la hauteur du tuyau du haut à la hauteur de la fenetre ainsi qu'à la variable gap (qui correspond à l'espace vertical entre les deux tuyaux)
                    int hauteurTuyauBas = HAUTEUR - hauteurTuyauHaut - gap;

                    tuyau_bas.deplacement(hauteurTuyauBas);
                    tuyau_bas.dessine(dessin);
                    tuyau_haut.deplacement(hauteurTuyauHaut);
                    tuyau_haut.dessine(dessin);


                    if (flappy.collision(tuyau_bas) || flappy.collision(tuyau_haut)) {
                        System.out.println("collision");
                        // On sort de la boucle
                        gameOver = true;
                    }

                    if (score != 0 && score % 10 == 0) {
                        System.out.println("spawn bonus");
                        if(listeBonus.size() == 0)
                        {
                            listeBonus.add(new Bonus());
                        }
                    }

                    for (Bonus bonus : listeBonus) {
                        bonus.deplacement(flappy, dessin);
                        bonus.dessine(dessin);
                    }

                }

                // Score
                dessin.setColor(Color.black);
                dessin.drawString("Score : " + score, 10, 20);

                // Affichage
                dessin.dispose();
                getBufferStrategy().show();
                Thread.sleep(17); // Environ 1000 / 60
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JOptionPane.showMessageDialog(this, "Game Over");
        System.exit(0);
    }

    public static void main(String[] args) {
        new Fenetre();
    }

}
