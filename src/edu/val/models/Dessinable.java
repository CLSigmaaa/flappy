package edu.val.models;

import java.awt.*;

public interface Dessinable {

    int getX();
    int getY();
    int getLargeur();
    int getHauteur();

    Point getTopLeftCorner();
    Point getTopRightCorner();
    Point getBottomRightCorner();
    Point getBottomLeftCorner();
    Point[] getBorders();
    default int getCentreX() {
        return getX() + getLargeur() / 2;
    }

    default int getCentreY() {
        return getY() + getHauteur() / 2;
    }

    void dessine(Graphics2D dessin);
}
