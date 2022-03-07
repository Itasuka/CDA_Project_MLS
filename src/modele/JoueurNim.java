package modele;

import java.util.Objects;

public class JoueurNim {
    private String nom;
    private int nbPartiesGagnees;

    /** Constructeur
     * @param nom du joueur à créer
     */
    public JoueurNim(String nom) {
        this.nom = nom;
    }

    /**
     * fonction retournant le nom du joueur
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * fonction retournant le nombre de parties gagnées
     * @return the nbPartiesGagnees
     */
    public int getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }

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
        JoueurNim joueurNim = (JoueurNim) o;
        return nbPartiesGagnees == joueurNim.nbPartiesGagnees && Objects.equals(nom, joueurNim.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, nbPartiesGagnees);
    }
}

