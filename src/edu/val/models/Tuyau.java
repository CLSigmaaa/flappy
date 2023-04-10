package edu.val.models;

import edu.val.Fenetre;

import java.awt.*;

public class Tuyau extends ScrollingSprite implements Collisionnable {

    private final int index_liste_tuyau;

    public Color couleur;

    public Tuyau(int x, int y, int largeur, int hauteur, float vitesseX, Color couleur, int index_tuyau) {
        super(x, y, largeur, hauteur, vitesseX);
        this.couleur = couleur;
        this.index_liste_tuyau = index_tuyau;
    }

    @Override
    public void dessine(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x,y,largeur, hauteur);
    }

    public void deplacement(int hauteurTuyau) {
        // Si le tuyau sort de l'écran, on le repositionne
        if (x + largeur <= 0) {
            // Si c'est le tuyau du haut, on le place en bas
            if (y != 0) {
                y = Fenetre.HAUTEUR - hauteurTuyau;
            }
            // On change la hauteur du tuyau
            hauteur = hauteurTuyau;

            // Si c'est le premier tuyau, on le place après le dernier (de la liste)
            if (index_liste_tuyau == 0) {
                // On récupère les coordonnées x du dernier tuyau puis on ajoute l'espace entre les tuyaux
                x = Fenetre.listeTuyauxBas[Fenetre.NB_TUYAUX - 1].getX() + Fenetre.ESPACE_TUYAUX;
            } else {
                // On récupère les coordonnées x du tuyau précédent puis on ajoute l'espace entre les tuyaux
                x = Fenetre.listeTuyauxBas[index_liste_tuyau - 1].getX() + Fenetre.ESPACE_TUYAUX;
            }

            // On considère que le tuyau est entièrement passé par le joueur lorsqu'il est sorti de l'écran
            // On incrémente donc le score
            Fenetre.score++;


        } else {
            // Sinon on le déplace simplement vers la gauche
            super.deplacement();
        }
    }

    public void incrementVitesseX() {
        vitesseX += 0.001f;
        System.out.println("Vitesse X : " + vitesseX);
    }
}
