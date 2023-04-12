package edu.val.models;

import edu.val.Fenetre;

import java.awt.*;

public class Flappy extends MovingSprite implements Collisionnable{

    public Flappy() {
        super(50, Fenetre.HAUTEUR / 2, 25, 25, 0, 0);
    }

    @Override
    public void dessine(Graphics2D dessin) {

        dessin.setColor(Color.PINK);
        dessin.fillOval( borders[0].x,borders[0].y,5, 5);
        dessin.fillOval( borders[1].x,borders[1].y,5, 5);
        dessin.fillOval( borders[2].x,borders[2].y,5, 5);
        dessin.fillOval( borders[3].x,borders[3].y,5, 5);



        dessin.setColor(Color.red);
        dessin.fillOval( x,y,largeur, hauteur);
    }

    @Override
    public boolean collision(Collisionnable target) {
        return Collisionnable.super.collision(target) || y + hauteur > Fenetre.HAUTEUR;
    }

    @Override
    public void deplacement() {

        super.deplacement();
        vitesseY += 0.2f;
        y += vitesseY;
    }


}
