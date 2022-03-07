package controleur;

import modele.CoupInvalideException;
import modele.JetonCouleur;
import modele.Joueur;
import modele.Grille;
import vue.Ihm;

public class ControleurPuissance extends Controleur{
    private Ihm leIhm;
    private Grille laGrille;

    public ControleurPuissance(Ihm ihm){
        leIhm = ihm;
    }

    public void init(){
        laGrille = new Grille(7);
        laGrille.initialiser();
        leIhm.setControleurPuissance(this);
        setJ1(new Joueur(leIhm.nomJoueur(1),"R"));
        setJ2(new Joueur(leIhm.nomJoueur(2),"J"));
    }

    public boolean faireUnTour(Joueur j){
        leIhm.etatPartie();
        String nom = j.getNom();
        JetonCouleur jetonCourant = j.getMonJeton();
        boolean flag1 = true;
        while(flag1){
            try{
                laGrille.gererCoup(leIhm.leCoup(nom, jetonCourant.toString()), jetonCourant);
                flag1 = false;
            }
            catch (CoupInvalideException e){
                leIhm.afficher(e.getMessage());
            }
        }
        if(laGrille.partieTerminee(jetonCourant.getLigne(), jetonCourant.getColonne())){
            return true;
        }
        else if (laGrille.grillePleine()){
            leIhm.rejouerEgalite();
            return true;
        }
        return false;
    }

    public int rejouer(Joueur j) {
        String nom = j.getNom();
        return leIhm.rejouer(nom);
    }

    public void recreerPartie() {
        laGrille = new Grille(7);
        laGrille.initialiser();
    }

    public void afficherFin(Joueur j1, Joueur j2) {
        leIhm.afficherScore(j1.getNom(), j1.getNbPartiesGagnees(), j2.getNom(), j2.getNbPartiesGagnees());
        if (j1.getNbPartiesGagnees() > j2.getNbPartiesGagnees()) {
            String nom = j1.getNom();
            int nbpartiesg = j1.getNbPartiesGagnees();
            leIhm.afficherVainqueurTotal(nom, nbpartiesg);
        } else if (j1.getNbPartiesGagnees() < j2.getNbPartiesGagnees()) {
            String nom = j2.getNom();
            int nbpartiesq = j2.getNbPartiesGagnees();
            leIhm.afficherVainqueurTotal(nom, nbpartiesq);
        } else {
            leIhm.egalite(j1.getNbPartiesGagnees());
        }
    }

    public String etatPartie(){
       return laGrille.toString();
    }
}
