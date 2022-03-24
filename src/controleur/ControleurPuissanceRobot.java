package controleur;

import modele.*;
import vue.Ihm;

import java.util.*;

public class ControleurPuissanceRobot extends ControleurPuissance {
    public ControleurPuissanceRobot(Ihm ihm) {
        super(ihm);
    }

    public void init() {
        setJ1(new Joueur("", ""));
        setJ2(new Joueur("", ""));
        setPlateau(new Grille(7));
        avecOuSansR();
        setJ1(new Joueur(getLeIhm().nomJoueur(1), "R"));
        setJ2(new Joueur("Olerobot", "J"));
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
        }
        else if (j == getJ2()){
            try{
                jouerStrategie();
            }
            catch (CoupInvalideException e){}
        }
    }

    public Grille jouerStrategie() throws CoupInvalideException {
        Grille grilletest = new Grille(laGrille());
        grilletest.pivoterADroite();
        if (grilletest.getPartieFinie() == 2) {
            return laGrille().pivoterADroite();
        }
        grilletest.pivoterAGauche();
        if (grilletest.getPartieFinie() == 2) {
            return laGrille().pivoterAGauche();
        } else {
            Map<Integer, HashSet<CoupPuissance4>> lesCoupsMap = new TreeMap<>();
            for (int i = 1; i<8; i++){
                lesCoupsMap.put(i,new HashSet<>());
            }
            for (int i = 1; i < 8; i++) {
                grilletest = new Grille(laGrille());
                CoupPuissance4 lecoup = new CoupPuissance4(i, getJ2().getMonJeton());
                grilletest.gererCoup(lecoup);
                int nbalignes = grilletest.compteurAlignes(getJ2().getMonJeton().getLigne(), getJ2().getMonJeton().getColonne());
                if (nbalignes == 1) {
                    lesCoupsMap.get(1).add(lecoup);
                }
                if (nbalignes == 2) {
                    lesCoupsMap.get(3).add(lecoup);
                }

                if (nbalignes == 3) {
                    lesCoupsMap.get(5).add(lecoup);
                }
                if (nbalignes == 4) {
                    lesCoupsMap.get(7).add(lecoup);
                }
            }
            for (int i = 1; i < 8; i++) {
                grilletest = new Grille(laGrille());
                CoupPuissance4 lecoup = new CoupPuissance4(i, getJ1().getMonJeton());
                grilletest.gererCoup(lecoup);
                int nbalignes = grilletest.compteurAlignes(getJ2().getMonJeton().getLigne(), getJ2().getMonJeton().getColonne());
                CoupPuissance4 lecoupordi = new CoupPuissance4(i, getJ2().getMonJeton());
                if (nbalignes == 2) {
                    lesCoupsMap.get(3).add(lecoupordi);
                }
                if (nbalignes == 3) {
                    lesCoupsMap.get(5).add(lecoup);
                }
                if (nbalignes == 4) {
                    lesCoupsMap.get(7).add(lecoup);
                }
            }
            for (int i = 7; i > 0; i--) {
                Set<CoupPuissance4> lesCoups = lesCoupsMap.get(i);
                if ((i == 7) && lesCoups != null) {
                    for (CoupPuissance4 leCoup : lesCoups) {
                        laGrille().gererCoup(leCoup);
                        break;
                    }
                    break;
                } else {
                    for (CoupPuissance4 leCoup : lesCoups) {
                        grilletest = new Grille(laGrille());
                        grilletest.gererCoup(leCoup);
                        grilletest.pivoterADroite();
                        if (!(grilletest.getPartieFinie() == 1)) {
                            grilletest.pivoterAGauche();
                            if (!(grilletest.getPartieFinie() == 1)) {
                                laGrille().gererCoup(leCoup);
                                return laGrille();
                            }
                        }
                    }
                }
            }
        }
        return laGrille();
    }
}
