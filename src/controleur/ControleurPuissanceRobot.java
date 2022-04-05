package controleur;

import modele.*;
import vue.Ihm;
import vue.IhmPuissanceRobot;

import java.util.*;

public class ControleurPuissanceRobot extends ControleurPuissance {
    Strategie lastrat;

    public ControleurPuissanceRobot(Ihm ihm) {
        super(ihm);
    }

    public IhmPuissanceRobot getLeIhm() {
        return (IhmPuissanceRobot) this.leIhm;
    }

    public void setLaStrat(Strategie strat) {
        lastrat =strat;
    }

    public void init() {
        setJ1(new Joueur("", ""));
        setJ2(new Joueur("", ""));
        setPlateau(new Grille(7));
        avecOuSansR();
        setJ1(new Joueur(getLeIhm().nomJoueur(1), "R"));
        setJ2(new Joueur("OLeRobot", "J"));
    }

    public void avecOuSansR() {
        int choix = getLeIhm().rotation();
        if (choix == 1) {
            laGrille().setTourner(true);
            setLaStrat(new StrategieRotation(getLeIhm(),this));
            getJ1().setNbRotations(4);
            getJ2().setNbRotations(4);
        } else {
            laGrille().setTourner(false);
            setLaStrat(new StrategieElaboree(getLeIhm(),this));
        }
    }

    public void faireUnTour(Joueur j) {
        if (j == getJ1()) {
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
        } else if (j == getJ2()) {
            try {
                lastrat.jouerStrategie();
            } catch (CoupInvalideException e) {
            }
        }
    }
}
