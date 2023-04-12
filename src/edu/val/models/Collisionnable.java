package edu.val.models;

import edu.val.Fenetre;

import java.awt.*;

public interface Collisionnable extends Dessinable {


    default boolean collision(Collisionnable target) {
        for(Point thisBorder : getBorders()) {
            for(Point targetBorder : target.getBorders()) {


                System.out.println(targetBorder.x);
                if(thisBorder.x > targetBorder.x && thisBorder.x < targetBorder.x + target.getLargeur() && thisBorder.y > targetBorder.y && thisBorder.y < targetBorder.y + target.getHauteur()){
                    Fenetre.dessin.setColor(Color.orange);
                    Fenetre.dessin.fillOval(targetBorder.x, targetBorder.y, 10, 10);
                    Fenetre.dessin.setColor(Color.blue);
                    Fenetre.dessin.fillOval(thisBorder.x, thisBorder.y, 10, 10);

                    return true;
            }}
        }
        return false;
    }
}
