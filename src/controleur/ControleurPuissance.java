package controleur;

import modele.*;
import vue.Ihm;
import vue.IhmPuissance;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ControleurPuissance extends Controleur {

    public ControleurPuissance(Ihm ihm) {
        super(ihm);
    }

    public Grille laGrille() {
        return (Grille) getPlateau();
    }

    @Override
    public IhmPuissance getLeIhm() {
        return (IhmPuissance) this.leIhm;
    }

    private void avecOuSansR() {
        int choix = getLeIhm().rotation();
        if (choix == 1) {
            laGrille().setTourner(true);
            getJ1().setNbRotations(4);
            getJ2().setNbRotations(4);
        } else {
            laGrille().setTourner(false);
        }
    }

    public void init() {
        setJ1(new Joueur("", ""));
        setJ2(new Joueur("", ""));
        setPlateau(new Grille(7));
        avecOuSansR();
        setJ1(new Joueur(getLeIhm().nomJoueur(1), "R"));
        setJ2(new Joueur(getLeIhm().nomJoueur(2), "J"));
    }

    public void faireUnTour(Joueur j) {
        etatPartie();
        String nom = j.getNom();
        JetonCouleur jetonCourant = j.getMonJeton();
        boolean flag1 = true;
        if (laGrille().getTourner() && j.getNbRotations() > 0) {
            while (flag1) {
                int choix = getLeIhm().placerOuRotation(nom, jetonCourant.toString());
                if (choix == 0) {
                    try {
                        int col = getLeIhm().leCoup(nom, jetonCourant.toString());
                        laGrille().gererCoup(new CoupPuissance4(col, jetonCourant));
                        flag1 = false;
                    } catch (CoupInvalideException e) {
                        getLeIhm().afficherErreur(e.getMessage());
                    }
                } else if (choix == 1) {
                    try {
                        setPlateau(laGrille().pivoterAGauche());
                        j.faitRotation();
                        flag1 = false;
                    } catch (CoupInvalideException e) {
                        getLeIhm().afficherErreur(e.getMessage());
                    }
                } else {
                    try {
                        setPlateau(laGrille().pivoterADroite());
                        j.faitRotation();
                        flag1 = false;
                    } catch (CoupInvalideException e) {
                        getLeIhm().afficherErreur(e.getMessage());
                    }
                }
            }
        } else {
            while (flag1) {
                try {
                    if (j.getNbRotations() == 0) {
                        getLeIhm().plusDeRotationsDispo();
                    }
                    int col = getLeIhm().leCoup(nom, jetonCourant.toString());
                    laGrille().gererCoup(new CoupPuissance4(col, jetonCourant));
                    flag1 = false;
                } catch (CoupInvalideException e) {
                    getLeIhm().afficherErreur(e.getMessage());
                }
            }
        }
    }

    public boolean partieGagnee(Joueur j) {
        JetonCouleur jetonCourant = j.getMonJeton();
        if (laGrille().getTourner()) {
            if (laGrille().getPartieFinie() == 1) {
                setjCourant(getJ1());
                return true;
            } else if (laGrille().getPartieFinie() == 2) {
                setjCourant(getJ2());
                return true;
            } else if (laGrille().getPartieFinie() == 3) {
                return true;
            } else if (laGrille().partieTerminee(jetonCourant.getLigne(), jetonCourant.getColonne())) {
                return true;
            }
        } else {
            if (laGrille().partieTerminee(jetonCourant.getLigne(), jetonCourant.getColonne())) {
                return true;
            }
        }
        return laGrille().grillePleine();
    }

    public boolean finPartie(Joueur j) {
        etatPartie();
        JetonCouleur jetonCourant = j.getMonJeton();
        if (laGrille().grillePleine() || laGrille().getPartieFinie() == 3) {
            if (getLeIhm().rejouerEgalite() == 1) {
                recreerPartie();
                return true;
            }
        } else if (laGrille().partieTerminee(jetonCourant.getLigne(), jetonCourant.getColonne()) || laGrille().getPartieFinie() == 1 || laGrille().getPartieFinie() == 2) {
            j.gagnePartie();
            if (rejouer(j) == 1) {
                recreerPartie();
                return true;
            }
        }
        afficherFin(getJ1(), getJ2());
        return false;
    }


    public int rejouer(Joueur j) {
        String nom = j.getNom();
        return getLeIhm().rejouer(nom);
    }

    public void recreerPartie() {
        setPlateau(new Grille(7));
        avecOuSansR();
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

    public void etatPartie() {
        getLeIhm().etatPartie(laGrille().toString());
    }
}
