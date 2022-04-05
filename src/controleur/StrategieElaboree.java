package controleur;

import modele.CoupInvalideException;
import modele.CoupPuissance4;
import modele.Grille;
import vue.IhmPuissanceRobot;

import java.util.*;

public class StrategieElaboree implements Strategie {

    private final IhmPuissanceRobot ihm;
    private final ControleurPuissanceRobot ctl;

    public StrategieElaboree(IhmPuissanceRobot ihm, ControleurPuissanceRobot ctl) {
        this.ihm = ihm;
        this.ctl = ctl;
    }

    public IhmPuissanceRobot getLeIhm() {
        return ihm;
    }

    @Override
    public void jouerStrategie() throws CoupInvalideException {
        Map<Integer, ArrayList<CoupPuissance4>> lesCoupsMap = new TreeMap<>();
        for (int i = 1; i < 8; i++) {
            lesCoupsMap.put(i, new ArrayList<>());
        }
        for (int i = 1; i < 8; i++) {
            Grille grilletest = new Grille(ctl.laGrille());
            if (!grilletest.colonnePleine(i - 1)) {
                CoupPuissance4 lecoup = new CoupPuissance4(i, ctl.getJ2().getMonJeton());
                grilletest.gererCoup(lecoup);
                int nbalignes = grilletest.compteurAlignes(ctl.getJ2().getMonJeton().getLigne(), ctl.getJ2().getMonJeton().getColonne());
                if (nbalignes == 1) {
                    lesCoupsMap.get(1).add(lecoup);
                }
                if (nbalignes == 2) {
                    for (int k = 1; k < 8; k++) {
                        CoupPuissance4 lecoup1 = new CoupPuissance4(k, ctl.getJ2().getMonJeton());
                        Grille grilletest1 = new Grille(grilletest);
                        grilletest1.gererCoup(lecoup1);
                        for (int p = 1; p < 8; p++) {
                            CoupPuissance4 lecoup2 = new CoupPuissance4(p, ctl.getJ2().getMonJeton());
                            Grille grilletest2 = new Grille(grilletest1);
                            grilletest2.gererCoup(lecoup2);
                            int nbaligne1 = grilletest2.compteurAlignes(ctl.getJ2().getMonJeton().getLigne(), ctl.getJ2().getMonJeton().getColonne());
                            if (nbaligne1 == 4) {
                                lesCoupsMap.get(3).add(lecoup);
                            }
                        }
                    }
                }
                if (nbalignes == 3) {
                    for (int k = 1; k < 8; k++) {
                        CoupPuissance4 lecoup1 = new CoupPuissance4(k, ctl.getJ2().getMonJeton());
                        Grille grilletest1 = new Grille(grilletest);
                        grilletest1.gererCoup(lecoup1);
                        int nbaligne1 = grilletest1.compteurAlignes(ctl.getJ2().getMonJeton().getLigne(), ctl.getJ2().getMonJeton().getColonne());
                        if (nbaligne1 == 4) {
                            lesCoupsMap.get(5).add(lecoup);
                        }
                    }
                }
                if (nbalignes == 4) {
                    lesCoupsMap.get(7).add(lecoup);
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            Grille grilletest = new Grille(ctl.laGrille());
            if (!grilletest.colonnePleine(i - 1)) {
                CoupPuissance4 lecoup = new CoupPuissance4(i, ctl.getJ1().getMonJeton());
                grilletest.gererCoup(lecoup);
                int nbalignes = grilletest.compteurAlignes(ctl.getJ1().getMonJeton().getLigne(), ctl.getJ1().getMonJeton().getColonne());
                CoupPuissance4 lecoupordi = new CoupPuissance4(i, ctl.getJ2().getMonJeton());
                if (nbalignes == 2) {
                    lesCoupsMap.get(2).add(lecoupordi);
                }
                if (nbalignes == 3) {
                    lesCoupsMap.get(4).add(lecoupordi);
                }
                if (nbalignes == 4) {
                    lesCoupsMap.get(6).add(lecoupordi);
                }
            }
        }
        for (int i = 7; i > 0; i--) {
            ArrayList<CoupPuissance4> coups = lesCoupsMap.get(i);
            if (coups.size() != 0) {
                Random ran = new Random();
                int coupAleatoire = ran.nextInt(coups.size());
                CoupPuissance4 coup = coups.get(coupAleatoire);
                getLeIhm().lOrdiAJoue("placé un jeton dans la colonne " + coup.getCol());
                ctl.laGrille().gererCoup(coup);
                break;
            }
        }
    }
}

