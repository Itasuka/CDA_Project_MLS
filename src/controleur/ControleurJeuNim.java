package controleur;

import modele.*;
import vue.Ihm;
import vue.IhmNim;

public class ControleurJeuNim extends Controleur{
    private int nbTas;

    public Tas lesTas(){return (Tas) getPlateau();}

    @Override
    public IhmNim getLeIhm() {
        return (IhmNim) this.leIhm;
    }

    public int getNbTas(){return nbTas;}
    public void setNbTas(int nb){ this.nbTas=nb;}

    public ControleurJeuNim(Ihm ihm) {
        super(ihm);
    }

    public void init(){
        setNbTas(creerPartie());
    }

    public boolean partieGagnee(Joueur j){
        return lesTas().partieTerminee();
    }

    public boolean finPartie(Joueur j){
        etatPartie(lesTas());
        j.gagnePartie();
        if (rejouer(j)==1){
            recreerPartie();
            return true;
        }
        getLeIhm().afficherScore(getJ1().getNom(),getJ1().getNbPartiesGagnees(),getJ2().getNom(),getJ2().getNbPartiesGagnees());
        afficherVainqueurTotal(getJ1(),getJ2());
        return false;
    }

    public void recreerPartie(){
        recreerPartie(nbTas);
    }


    public int choixContrainte(){
        int choix = getLeIhm().choixContrainte();
        if (choix==1){
            return getLeIhm().nombreMaxAllumettes();
        }
        else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Fonction permettant d'initialiser la partie
     * @return le nombre de tas avec lequel on va joueur
     */
    public int creerPartie() {
        int nbTas = 0;
        while (nbTas <= 0) {
            nbTas = getLeIhm().nombreTas();
        }
        setPlateau(new Tas(nbTas));
        lesTas().initialiser();
        lesTas().setNbMax(choixContrainte());
        setJ1(new Joueur(getLeIhm().nomJoueur(1)));
        setJ2(new Joueur(getLeIhm().nomJoueur(2)));
        return nbTas;
    }

    /**
     * Fonction appellant la fonction d'affichage de la partie de l'ihm
     * @param lesTas la partie en cours
     */
    public void etatPartie(Tas lesTas) {
        getLeIhm().etatPartie(lesTas.toString());
    }

    /**
     * Fonction permettant de demander un coup via l'ihm et de faire un coup
     * @param j le joueur qui va jouer
     * @throws CoupInvalideException si le coup est invalide
     */
    public void faireLeCoup(Joueur j) throws CoupInvalideException {
        String nom = j.getNom();
        String coup = getLeIhm().leCoup(nom,lesTas().getNbMax());
        int numTas = Integer.parseInt(coup.substring(0,coup.length()/2));
        int nbAl = Integer.parseInt(coup.substring(coup.length()/2));
        lesTas().gererCoup(creerCoupNim(numTas, nbAl));
    }

    /**
     * Fonction permettant de cr??er un coup ?? partir d'un num??ro de tas et du nombre d'allumettes
     * @param numTas le num??ro du tas du coup
     * @param nbAll le nombre d'allumettes du coup
     * @return le coup cr????
     */
    public CoupNim creerCoupNim(int numTas, int nbAll) {
        return new CoupNim(numTas, nbAll);
    }

    /**
     * Fonction permettant de faire un tour au joueur
     * @param j le joueur qui va jouer
     */
    public void faireUnTour(Joueur j) {
        etatPartie(lesTas());
        boolean flag1 = true;
        while (flag1) {
            try{
                faireLeCoup(j);
                flag1 = false;
            }
            catch (CoupInvalideException e){
                getLeIhm().afficherErreur(e.getMessage());
            }

        }
    }

    /**
     * Fonction appellant la fonction d'affichage rejouer de l'ihm avec le nom du vainqueur
     * @param j le nom du vainqueur
     * @return 1 si les joueurs veulent rejouer, 0 sinon
     */
    private int rejouer(Joueur j) {
        String nom = j.getNom();
        return getLeIhm().rejouer(nom);
    }

    /**
     * Fonction recr??ant une nouvelle partie
     * @param nbTas le nombre de tas avec lequel les joueurs vont jouer
     */
    private void recreerPartie(int nbTas) {
        setPlateau(new Tas(nbTas));
        lesTas().initialiser();
        lesTas().setNbMax(choixContrainte());
    }

    /**
     * Fonction permettant de donner le nom et le nombre de partie du joueur gagnant ?? la fonction afficherVainqueurTotal de l'ihm
     * ou ?? la fonction ??galit?? si il y a ??galit??
     * @param j1 le joueur 1
     * @param j2 le joueur 2
     */
    private void afficherVainqueurTotal(Joueur j1, Joueur j2) {
        if (j1.getNbPartiesGagnees() > j2.getNbPartiesGagnees()) {
            String nom = j1.getNom();
            int nbpartiesg = j1.getNbPartiesGagnees();
            getLeIhm().afficherVainqueurTotal(nom, nbpartiesg);
        } else if (j1.getNbPartiesGagnees() < j2.getNbPartiesGagnees()) {
            String nom = j2.getNom();
            int nbpartiesq = j2.getNbPartiesGagnees();
            getLeIhm().afficherVainqueurTotal(nom, nbpartiesq);
        } else {
            getLeIhm().egalite(j1.getNbPartiesGagnees());
        }
    }

}
