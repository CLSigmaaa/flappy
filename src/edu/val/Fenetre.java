package edu.val;

import edu.val.models.Decor;
import edu.val.models.Flappy;
import edu.val.models.Tuyau;
import edu.val.models.Bonus;
import edu.val.models.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Fenetre extends Canvas {
    public static Graphics2D dessin;
    private boolean bonusCollision = false;
    private boolean gameOver = false;
    public static int gap = 100;
    public static int score = 0;
    public static int NB_TUYAUX = 1;
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
    public Color CouleurTuyau = Color.green;
    public Color CouleurDecor = Color.gray;

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
                        flappy.setVitesseY(-4.5f);
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

            // On calcule la hauteur du tuyau du haut avec un nombre random entre le min (20% de la hauteur de la fenetre) et le max (60% de la hauteur de la fenetre)
            int hauteurTuyauHaut = 250;//(int) (Math.random() * (tuyau_max_hauteur - tuyau_min_hauteur)) + (int) tuyau_min_hauteur;
            // On calcule la hauteur du tuyau du bas en soustrayant la hauteur du tuyau du haut à la hauteur de la fenetre ainsi qu'à la variable gap (qui correspond à l'espace vertical entre les deux tuyaux)
            int hauteurTuyauBas = HAUTEUR - hauteurTuyauHaut - gap;

            listeTuyauxBas[i] = new Tuyau(
                    LARGEUR + ESPACE_TUYAUX * i,
                    HAUTEUR - hauteurTuyauBas,
                    80,
                    hauteurTuyauBas,
                    4,
                    CouleurTuyau,
                    i);

            listeTuyauxHaut[i] = new Tuyau(
                    LARGEUR + ESPACE_TUYAUX * i,
                    0,
                    80,
                    hauteurTuyauHaut,
                    4,
                    CouleurTuyau,
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
                    CouleurDecor,
                    i
            );

        }


        try {
            long frame = 0;

            while(!gameOver) {
                frame ++;



                // Affichage
                Fenetre.dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();


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

                    // On vérifie si le score est un multiple de 10 != 0 et on vérifie si le nombre de frame est un multiple de 60 pour exécuter la condition
                    // une seule fois par seconde (cela évite que la condition soit verifiée autant de fois que de frame passées)
                    if (score % 10 == 0 && score != 0 && frame % 60 == 0) {
                        // Dans ce cas on appelle la méthode incrementVitesseX (qui augmente la vitesseX du tuyau de 0.5f)
                        tuyau_bas.incrementVitesseX();
                        tuyau_haut.incrementVitesseX();
                        System.out.println(tuyau_bas.getVitesseX());
                    }

                    // On calcule la hauteur du tuyau du haut avec un nombre random entre le min (20% de la hauteur de la fenetre) et le max (60% de la hauteur de la fenetre)
                    int hauteurTuyauHaut = 250;//(int) (Math.random() * (tuyau_max_hauteur - tuyau_min_hauteur)) + (int) tuyau_min_hauteur;
                    // On calcule la hauteur du tuyau du bas en soustrayant la hauteur du tuyau du haut à la hauteur de la fenetre ainsi qu'à la variable gap (qui correspond à l'espace vertical entre les deux tuyaux)
                    int hauteurTuyauBas = HAUTEUR - hauteurTuyauHaut - gap;

                    tuyau_bas.deplacement(hauteurTuyauBas);
                    tuyau_bas.dessine(dessin);
//


                    if (flappy.collision(tuyau_bas) || flappy.collision(tuyau_haut)) {
                        // On sort de la boucle
                        gameOver = true;
                    }

                    // On regarde si le score est un multiple de 40 et s'il est différent de 0 (pour éviter de faire un spawn un bonus au démarrage)
                    if (score != 0 && score % 20 == 0) {
                        // Si il n'y a aucun bonus
                        if(listeBonus.size() == 0)
                        {
                            // Alors on instancie un Bonus
                            listeBonus.add(new Bonus());
                        }
                    }
                }

                // Gestion des Bonus
                // On parcourt les bonus
                for (Bonus bonus : listeBonus) {
                    // On vérifie que le bonus n'est pas nul sinon NullPointerException
                    if (bonus != null) {
                        // On gère les mouvements ainsi que l'affichage
                        bonus.deplacement();
                        bonus.dessine(dessin);

                        // Vérifier s'il y a une collision avec les bordures
                        if(bonus.getY() > Fenetre.HAUTEUR ||(bonus.getX() + bonus.getLargeur() < 0)) {
                            listeBonus.set(0, null);
                            bonusCollision = true;
                        }
                        // Si il y a une collision avec flappy
                        if (bonus.collision(flappy)) {

                            // On détruit l'objet en le mettant à null et pour le retirer de la liste par la suite
                            listeBonus.set(0, null);

                            // on incrémente le score de 50
                            score += 50;

                            // On veut le supprimer de la liste mais impossible de le faire dans la boucle à cause de ConcurrentModificationException
                            // On ajoute donc une variable pour gérer cela en dehors de la boucle pour éviter cette erreur
                            bonusCollision = true;
                        }
                    }
                }
                // Si il y a eu une collision entre le bonus et flappy
                if (bonusCollision) {
                    // on supprime le bonus
                    listeBonus.clear();
                    // on sort de la condition jusqu'à qu'il y ait une nouvelle collsion entre flappy et bonus
                    bonusCollision = false;
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
