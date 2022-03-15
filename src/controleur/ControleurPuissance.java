package controleur;

import modele.CoupInvalideException;
import modele.JetonCouleur;
import modele.Joueur;
import modele.Grille;
import vue.Ihm;
import vue.IhmNim;
import vue.IhmPuissance;

public class ControleurPuissance extends Controleur{
    private Grille laGrille;

    public ControleurPuissance(Ihm ihm) {
        super(ihm);
    }

    @Override
    public IhmPuissance getLeIhm() {
        return (IhmPuissance) this.leIhm;
    }

    public void init(){
        laGrille = new Grille(7);
        setJ1(new Joueur(getLeIhm().nomJoueur(1),"R"));
        setJ2(new Joueur(getLeIhm().nomJoueur(2),"J"));
    }

    public void faireUnTour(Joueur j){
        etatPartie();
        String nom = j.getNom();
        JetonCouleur jetonCourant = j.getMonJeton();
        boolean flag1 = true;
        while(flag1) {
            try {
                laGrille.gererCoup(getLeIhm().leCoup(nom, jetonCourant.toString()), jetonCourant);
                flag1 = false;
            } catch (CoupInvalideException e) {
                getLeIhm().afficherErreur(e.getMessage());
            }
        }
    }

    public boolean partieGagnee(Joueur j){
        JetonCouleur jetonCourant = j.getMonJeton();
        if(laGrille.partieTerminee(jetonCourant.getLigne(), jetonCourant.getColonne())){
            return true;
        }
        return laGrille.grillePleine();
    }

    public boolean finPartie(Joueur j){
        etatPartie();
        JetonCouleur jetonCourant = j.getMonJeton();
        if (laGrille.partieTerminee(jetonCourant.getLigne(), jetonCourant.getColonne())){
            j.gagnePartie();
            if(rejouer(j)==1){
                recreerPartie();
                return true;
            }
        }
        else if (laGrille.grillePleine()) {
            if (getLeIhm().rejouerEgalite()==1){
                recreerPartie();
                return true;
            }
        }
        afficherFin(getJ1(),getJ2());
        return false;
    }


    public int rejouer(Joueur j) {
        String nom = j.getNom();
        return getLeIhm().rejouer(nom);
    }

    public void recreerPartie() {
        laGrille = new Grille(7);
    }

    public void afficherFin(Joueur j1, Joueur j2) {
        getLeIhm().afficherScore(j1.getNom(), j1.getNbPartiesGagnees(), j2.getNom(), j2.getNbPartiesGagnees());
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

    public void etatPartie(){
        getLeIhm().etatPartie(laGrille.toString());
    }
}
