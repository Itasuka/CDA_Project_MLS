package controleur;

import vue.Ihm;
import modele.CoupInvalideException;
import modele.CoupPuissance4;
import modele.Grille;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ControleurPuissanceRobot extends ControleurPuissance{
    public ControleurPuissanceRobot(Ihm ihm) {
        super(ihm);
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
            Map<Integer, Set<CoupPuissance4>> lesCoupsMap = new TreeMap<Integer, Set<CoupPuissance4>>();
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
                if ((i == 7) && !(lesCoups.equals(null))) {
                    for (CoupPuissance4 leCoup : lesCoups) {
                        laGrille().gererCoup(leCoup);
                        break;
                    }
                } else {
                    for (CoupPuissance4 leCoup : lesCoups) {
                        grilletest = new Grille(laGrille());
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
