package modele;


import modele.CoupInvalideException;
import modele.CoupNim;

/**
 * <p>La classe Tas représente l'état courant de la partie
 * Elle est caractérisée par un tableau. Chaque case du tableau enregistre le nombre d'allumettes de chaque tas.
 * Le nombre d'allumettes du premier tas étant dans la case 0.
 * </p>
 *
 * @author Kahlem
 */
public class Tas extends Plateau {

    /**
     * représente l'état courant de la partie
     */
    private int[] lesTas;

    /**
     * nombre max d'allumettes pouvant être retirée
     */
    private int nbMax = Integer.MAX_VALUE;

    /**
     * permet de modifier nbMax
     * @param nb nouvelle valeur de nbMax
     */
    public void setNbMax(int nb){this.nbMax=nb;}

    /**
     * renvoie nbMax
     * @return nbMax
     */
    public int getNbMax(){return nbMax;}

    public int[] getLesTas(){return lesTas;}

    public Tas(int[] lesTas){this.lesTas = lesTas;}
    /**
     * Fonctionn créant un ensemble de nbTas tas avec 0 allumettes dans chaque tas
     * @param nbTas le nombre de tas de la partie
     */
    public Tas(int nbTas){
        lesTas = new int[nbTas];
    }

    /**
     * Fonction ajoutant les allumettes dans chacun des tas de la manière suivante :
     * dans le ième tas, on place 2*i - 1 allumettes.
     */
    public void initialiser(){
        for (int i = 0; i < lesTas.length; i++) {
            lesTas[i] = 2 * i + 1;
        }
    }

    /**
     * Fonction retournant vrai si la partie est terminée et faux sinon
     * @return un boolean selon si la partie est terminée ou non
     */
    public boolean partieTerminee() {
        return nbAllumette() == 0;
    }

    /**
     * Fonction retournant le nombre d'allumettes selon un tas
     * @param numeroTas le numero du tas dont on veut savoir le nombre d'allumettes
     */
    public int nbAllumettes(int numeroTas) {
        return lesTas[numeroTas - 1];
    }

    /**
     * Retourne le nombre total d'allumettes de la partie
     */
    public int nbAllumette(){

    int total = 0;
    for (int nb : lesTas)
        total+=nb;
    return total;
    }

    /**
     * Fonction retournant l'état de la partie sous forme d'une chaîne de caractères constituées des batons correspondant au nombre d'allumettes pour chaque tas.
     *
     */
    @Override
    public String toString() {
        String s="";
        int compteur = 1;
        for (int nbAllumettes : lesTas) {
            s+= compteur+" ";
            compteur++;
            for (int i = 1; i <= nbAllumettes; i++) {

                s+="| ";
            }
           s+="\n";
        }
        return s;
    }

    /**
     * Fonction modifiant l'état de la partie en fonction du coup passé en paramètre
     * @param c le coup a effectué
     * @throws CoupInvalideException si le coup est invalide
     */
    public void gererCoup(Coup c) throws CoupInvalideException {
        CoupNim coup = (CoupNim) c;
        int numeroTas= coup.getNumeroTas();
        int nb = coup.getNbAllumettes();

        if (numeroTas >= 1 && numeroTas <= lesTas.length && nb >= 1 && nb <= nbAllumettes(numeroTas) && coup.getNbAllumettes() <= this.nbMax) {
            lesTas[numeroTas - 1] -= nb;

        } else {
            throw new CoupInvalideException("Le coup est invalide, rejouez");
        }
    }
}