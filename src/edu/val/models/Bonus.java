package edu.val.models;

import edu.val.Fenetre;

import java.awt.*;

public class Bonus extends ScrollingSprite implements Collisionnable{
    public Bonus() {
//        super(Fenetre.LARGEUR - 50, Fenetre.HAUTEUR/2, 30, 30, (int) (Math.random() * 2) + 1);
        super(Fenetre.LARGEUR, Fenetre.HAUTEUR/2, 30, 30, -1f);
    }

    @Override
    public void dessine(Graphics2D dessin) {
        dessin.setColor(Color.yellow);
        dessin.fillOval( x,y - hauteur/2,largeur, hauteur);
    }

    public void deplacement(Flappy flappy, Graphics2D dessin) {
        // si il y a collision avec le flappy
        System.out.println("here");
        x += vitesseX;
        if(this.collision(flappy)) {
            // clear the list of bonus
            dessin.clearRect(x, y, largeur, hauteur);
            Fenetre.score += 1000;
        }

    }
}
