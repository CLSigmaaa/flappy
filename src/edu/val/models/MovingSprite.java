package edu.val.models;

public abstract class MovingSprite extends Sprite{

    protected float vitesseX;
    protected float vitesseY;

    public MovingSprite(int x, int y, int largeur, int hauteur, float vitesseX, float vitesseY) {
        super(x, y, largeur, hauteur);
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
    }

    public abstract void deplacement();

    public float getVitesseX() {
        return vitesseX;
    }

    public void setVitesseX(float vitesseX) {
        this.vitesseX = vitesseX;
    }

    public float getVitesseY() {
        return vitesseY;
    }

    public void setVitesseY(float vitesseY) {
        this.vitesseY = vitesseY;
    }
}
