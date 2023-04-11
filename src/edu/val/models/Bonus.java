package edu.val.models;

import edu.val.Fenetre;

import java.awt.*;

public class Bonus extends ScrollingSprite implements Collisionnable{
    private float vitesseY;
    public Bonus() {
//        super(Fenetre.LARGEUR - 50, Fenetre.HAUTEUR/2, 30, 30, (int) (Math.random() * 2) + 1);
        super(Fenetre.LARGEUR, Fenetre.HAUTEUR/2, 30, 30, (int) ((Math.random() * 4) + 1) * -1);
        // random number between -1 and 1 for vitesseY
        vitesseY = (float) Math.random() * 2 - 1;;
    }

    @Override
    public void dessine(Graphics2D dessin) {
        dessin.setColor(Color.yellow);
        dessin.fillOval( x,y - hauteur/2,largeur, hauteur);
    }
    @Override
    public void deplacement() {
        System.out.println("vitesseY = " + vitesseY);
        System.out.println("VitesseX = " + vitesseX);

        x += vitesseX;
        y += vitesseY;
    }
    // Méthode qui retourne true s'il y a une collsision avec Flappy sinon False
    public boolean collision(Flappy flappy) {

        if (Collisionnable.super.collision(flappy)) {
            return true;
        }
        return false;
    }
}
