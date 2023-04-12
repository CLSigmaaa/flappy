package edu.val.models;

import edu.val.Fenetre;

import java.awt.*;

public interface Collisionnable extends Dessinable {
    default boolean collision(Collisionnable target) {
        for (Point point : getBorders()) {
            if(point.x > target.getX()
                    && point.x < target.getX() + target.getLargeur()
                    && point.y > target.getY()
                    && point.y < target.getY() + target.getHauteur()) {
                return true;
            }
        }
        return false;
    }
}
