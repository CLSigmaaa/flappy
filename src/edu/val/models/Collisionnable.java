package edu.val.models;

public interface Collisionnable extends Dessinable {

    default boolean collision(Collisionnable target) {

        return this.getCentreX() > target.getX()
                && this.getCentreX() < target.getX() + target.getLargeur()
                && this.getCentreY() > target.getY()
                && this.getCentreY() < target.getY() + target.getHauteur();

//
//        if(target instanceof Sprite) {
//            Sprite sprite = (Sprite) target;
//        }
//
//        if(target instanceof Sprite sprite) {
//
//        }

    }

}
