package controleur;

import modele.CoupInvalideException;
import modele.CoupPuissance4;
import modele.Grille;
import modele.JetonCouleur;
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

    public int compteurAlignesStrat(int lD, int cD, Grille g) {
        int res = 1;
        int max = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                res += chercheLaVictoireStrat(lD + i, cD + j, i, j, g) +
                        chercheLaVictoireStrat(lD - i, cD - j, -i, -j, g);
                max = Integer.max(res, max);
                if (res >= 4) {
                    return 4;
                } else {
                    res = 1;
                }
            }
        }
        return max;
    }


    public int chercheLaVictoireStrat(int lD, int cD, int lM, int cM, Grille g) {
        Grille grilletest = new Grille(g);
        JetonCouleur[][] laGrille = grilletest.getLaGrille();
        if (lM == 0 && cM == 0) {
            return 0;
        }
        if (lD < 0 || lD > grilletest.getTailleGrille() - 1 || cD < 0 || cD > grilletest.getTailleGrille() - 1) {
            return 0;
        }
        if ((laGrille[lD - lM][cD - cM] != null) && (laGrille[lD][cD] == null || laGrille[lD][cD].getCouleur().equals(laGrille[lD - lM][cD - cM].getCouleur()))) {
            return 1 + chercheLaVictoireStrat(lD + lM, cD + cM, lM, cM, grilletest);
        } else {
            return 0;
        }
    }

    @Override
    public void jouerStrategie() throws CoupInvalideException {
        Map<Integer, ArrayList<CoupPuissance4>> lesCoupsMap = new TreeMap<>();
        for (int o = 1; o < 8; o++) {
            lesCoupsMap.put(o, new ArrayList<>());
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
                    int nbaligne1 = compteurAlignesStrat(lecoup.getJeton().getLigne(), lecoup.getJeton().getColonne(), grilletest);
                    if (nbaligne1 == 4) {
                        lesCoupsMap.get(3).add(lecoup);
                        break;
                    }
                }
                if (nbalignes == 3) {
                    int nbaligne1 = compteurAlignesStrat(lecoup.getJeton().getLigne(), lecoup.getJeton().getColonne(), grilletest);
                    if (nbaligne1 == 4) {
                        lesCoupsMap.get(5).add(lecoup);
                        break;
                    }
                }

                if (nbalignes == 4) {
                    lesCoupsMap.get(7).add(lecoup);
                }
            }
        }
        for (int l = 1; l < 8; l++) {
            Grille grilletest = new Grille(ctl.laGrille());
            if (!grilletest.colonnePleine(l - 1)) {
                CoupPuissance4 lecoup = new CoupPuissance4(l, ctl.getJ1().getMonJeton());
                grilletest.gererCoup(lecoup);
                int nbalignes = grilletest.compteurAlignes(ctl.getJ1().getMonJeton().getLigne(), ctl.getJ1().getMonJeton().getColonne());
                CoupPuissance4 lecoupordi = new CoupPuissance4(l, ctl.getJ2().getMonJeton());
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
        for (int r = 7; r > 0; r--) {
            ArrayList<CoupPuissance4> coups = lesCoupsMap.get(r);
            if (coups.size() == 0) {
            }
            if (coups.size() != 0) {
                Random ran = new Random();
                int coupAleatoire = ran.nextInt(coups.size());
                CoupPuissance4 coup = coups.get(coupAleatoire);
                ctl.laGrille().gererCoup(coup);
                getLeIhm().lOrdiAJoue("placé un jeton dans la colonne " + coup.getCol());
                break;
            }
        }
    }
}


