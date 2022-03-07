package modele;

/**
 * Created by kahlem .
 * Classe permettant d'enregistrer un coup
 *
 * @author lkahlem
 */
public class CoupNim {
    private int numeroTas;
    private int nbAllumettes;

    /**
     * Constructeur permettant de créer un coup
     * @param numeroTas numéro de la ligne
     * @param nbAllumettes nombre d'allumettes à enlever
     */
    public CoupNim(int numeroTas, int nbAllumettes) {
        this.numeroTas = numeroTas;
        this.nbAllumettes = nbAllumettes;
    }

    /**
     * Fonction retournant le numéro du tas
     * @return the ligne
     */
    public int getNumeroTas() {
        return numeroTas;
    }

    /**
     * Fonction retournant le nombre d'allumettes
     * @return the nbAllumettes
     */
    public int getNbAllumettes() {
        return nbAllumettes;
    }


}


