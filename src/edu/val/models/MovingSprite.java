package edu.val.models;

public abstract class MovingSprite extends Sprite{

    protected float vitesseX;
    protected float vitesseY;

    public MovingSprite(int x, int y, int largeur, int hauteur, float vitesseX, float vitesseY) {
        super(x, y, largeur, hauteur);
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
    }

    public void deplacement() {
        // FIXME: Don't instantiate a new Point object each time

//        System.out.println("toprightcorner: " + this.getTopRightCorner().x + " " + this.getTopRightCorner().y);
        this.setBottomLeftCorner(new Point(this.x, this.y + this.hauteur));
        this.setBottomRightCorner(new Point(this.x + this.largeur, this.y + this.hauteur));
        this.setTopLeftCorner(new Point(this.x, this.y));
        this.setTopRightCorner(new Point(this.x + this.largeur, this.y));
        this.setBorders(new Point[]{this.getTopLeftCorner(), this.getTopRightCorner(), this.getBottomRightCorner(), this.getBottomLeftCorner()});
    };

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
