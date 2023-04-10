package edu.val.models;

import edu.val.Fenetre;

import java.awt.*;

public class Flappy extends MovingSprite implements Collisionnable{

    public Flappy() {
        super(50, Fenetre.HAUTEUR / 2, 30, 30, 0, 0);
    }

    @Override
    public void dessine(Graphics2D dessin) {
        dessin.setColor(Color.red);
        dessin.fillOval( x,y - hauteur/2,largeur, hauteur);
    }

    @Override
    public boolean collision(Collisionnable target) {
        return Collisionnable.super.collision(target) || y + hauteur > Fenetre.HAUTEUR;
    }

    @Override
    public void deplacement() {
        vitesseY += 0.12f;
        y += vitesseY;
    }
}
