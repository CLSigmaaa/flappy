package edu.val.models;

import edu.val.Fenetre;

import java.awt.*;

public class Decor extends ScrollingSprite{
    private int index_liste_decor;
    public Decor(int x, int y, int largeur, int hauteur, float vitesseX) {
        super(x, y, largeur, hauteur, vitesseX);
    }

    public Color couleur;

    public Decor(int x, int y, int largeur, int hauteur, float vitesseX, Color couleur, int index_liste_decor) {
        super(x, y, largeur, hauteur, vitesseX);
        this.index_liste_decor = index_liste_decor;
        this.couleur = couleur;
    }

    @Override
    public void dessine(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, hauteur);
    }
    // Même stratagème que pour les tuyaux
    @Override
    public void deplacement() {
        // Si le décor est sorti de l'écran
        if(x + largeur <= 0) {
            // Si c'est le premier décor de la liste
            if (index_liste_decor == 0) {
                // On récupère les coordonnées x du dernier tuyau puis on ajoute l'espace entre les tuyaux
                x = Fenetre.listeDecors[Fenetre.NB_DECORS - 1].getX() + Fenetre.ESPACE_DECORS;
            } else {
                // On récupère les coordonnées x du tuyau précédent puis on ajoute l'espace entre les tuyaux
                x = Fenetre.listeDecors[index_liste_decor - 1].getX() + Fenetre.ESPACE_DECORS;
            }
        } else {
            super.deplacement();
        }
    }
}
