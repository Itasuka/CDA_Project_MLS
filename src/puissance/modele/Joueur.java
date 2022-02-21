package puissance.modele;

import java.util.Objects;

/**
 * Created by kahlem .
 */
public class Joueur {
    private String nom;
    private int nbPartiesGagnees;
    private String couleur;

    /**
     * @param nom du joueur à créer
     * @param couleur du joueur à créer
     */
    public Joueur(String nom,String couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the nbPartiesGagnees
     */
    public int getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }

    /**
     * @return the couleur
     */
    public String getCouleur(){ return couleur; }

    /**
     * incrémente le nombre de parties gagnées par le joueur
     */
    public void gagnePartie() {
        nbPartiesGagnees++;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return nbPartiesGagnees == joueur.nbPartiesGagnees && Objects.equals(nom, joueur.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, nbPartiesGagnees);
    }
}
