package edu.val.models;

import java.awt.*;

public abstract class Sprite implements Dessinable {

    protected int x;
    protected int y;
    protected int largeur;
    protected int hauteur;

    public void setTopLeftCorner(Point topLeftCorner) {
        this.topLeftCorner = topLeftCorner;
    }

    public void setTopRightCorner(Point topRightCorner) {
        this.topRightCorner = topRightCorner;
    }

    public void setBottomRightCorner(Point bottomRightCorner) {
        this.bottomRightCorner = bottomRightCorner;
    }

    public void setBottomLeftCorner(Point bottomLeftCorner) {
        this.bottomLeftCorner = bottomLeftCorner;
    }

    public void setBorders(Point[] borders) {
        this.borders = borders;
    }

    protected Point topLeftCorner;
    protected Point topRightCorner;
    protected Point bottomRightCorner;
    protected Point bottomLeftCorner;

    public Point getTopLeftCorner() {
        return topLeftCorner;
    }

    public Point getTopRightCorner() {
        return topRightCorner;
    }

    public Point getBottomRightCorner() {
        return bottomRightCorner;
    }

    public Point getBottomLeftCorner() {
        return bottomLeftCorner;
    }

    public Point[] getBorders() {
        return borders;
    }

    protected Point[] borders = {
            topLeftCorner,
            topRightCorner,
            bottomRightCorner,
            bottomLeftCorner
    };
    public Sprite(int x, int y, int largeur, int hauteur) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;

        this.topLeftCorner = new Point(x, y);
        this.topRightCorner = new Point(x + largeur, y);
        this.bottomRightCorner = new Point(x + largeur, y + hauteur);
        this.bottomLeftCorner = new Point(x, y + hauteur);

        this.setBorders(new Point[] {
                topLeftCorner,
                topRightCorner,
                bottomRightCorner,
                bottomLeftCorner
        });
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
}
