package controleur;

import modele.CoupInvalideException;
import modele.CoupPuissance4;
import modele.Grille;
import vue.IhmPuissanceRobot;

import java.util.*;

public class StrategieRotation implements Strategie{

    private IhmPuissanceRobot ihm;
    private ControleurPuissanceRobot ctl;

    public StrategieRotation(IhmPuissanceRobot ihm, ControleurPuissanceRobot ctl){
        this.ihm = ihm;
        this.ctl = ctl;
    }

    public IhmPuissanceRobot getLeIhm(){
        return ihm;
    }

    @Override
    public void jouerStrategie() throws CoupInvalideException {
        Grille grilletest = new Grille(ctl.laGrille());
        boolean ajoue = false;
        if (ctl.getJ2().getNbRotations() >= 0) {
            grilletest = grilletest.pivoterADroite();
            if (grilletest.getPartieFinie() == 2) {
                getLeIhm().lOrdiAJoue("pivoté à droite.");
                ctl.getJ2().faitRotation();
                ctl.setPlateau(ctl.laGrille().pivoterADroite());
                ajoue = true;
            }
            if (!ajoue) {
                grilletest = new Grille(ctl.laGrille());
                grilletest = grilletest.pivoterAGauche();
                if (grilletest.getPartieFinie() == 2) {
                    getLeIhm().lOrdiAJoue("pivoté à gauche.");
                    ctl.getJ2().faitRotation();
                    ctl.setPlateau(ctl.laGrille().pivoterAGauche());
                    ajoue = true;
                }
            }
        }
        if (!ajoue) {
            Map<Integer, ArrayList<CoupPuissance4>> lesCoupsMap = new TreeMap<>();
            for (int i = 1; i < 8; i++) {
                lesCoupsMap.put(i, new ArrayList<>());
            }
            for (int i = 1; i < 8; i++) {
                grilletest = new Grille(ctl.laGrille());
                if (!grilletest.colonnePleine(i - 1)) {
                    CoupPuissance4 lecoup = new CoupPuissance4(i, ctl.getJ2().getMonJeton());
                    grilletest.gererCoup(lecoup);
                    int nbalignes = grilletest.compteurAlignes(ctl.getJ2().getMonJeton().getLigne(), ctl.getJ2().getMonJeton().getColonne());
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
            }
            for (int i = 1; i < 8; i++) {
                grilletest = new Grille(ctl.laGrille());
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
                ArrayList<CoupPuissance4> lesCoups = lesCoupsMap.get(i);
                if ((i == 7) && lesCoups.size() != 0) {
                    for (CoupPuissance4 leCoup : lesCoups) {
                        if (!ajoue){
                            getLeIhm().lOrdiAJoue("placé un jeton dans la colonne " + leCoup.getCol());
                            ctl.laGrille().gererCoup(leCoup);
                            break;
                        }
                    }
                    break;
                } else {
                    List<CoupPuissance4> lesCoupsValide = new ArrayList<>();
                    for (CoupPuissance4 leCoup : lesCoups) {
                        if (!ajoue){
                            grilletest = new Grille(ctl.laGrille());
                            grilletest.gererCoup(leCoup);
                            if (ctl.getJ1().getNbRotations() >= 0) {
                                grilletest = grilletest.pivoterADroite();
                                if (!(grilletest.getPartieFinie() == 1)) {
                                    grilletest = new Grille(ctl.laGrille());
                                    grilletest = grilletest.pivoterAGauche();
                                    if (!(grilletest.getPartieFinie() == 1)) {
                                        lesCoupsValide.add(leCoup);
                                    }
                                }
                            } else {
                                lesCoupsValide.add(leCoup);
                            }
                        }
                    }
                    if (lesCoupsValide.size() != 0) {
                        Random ran = new Random();
                        int coupAleatoire = ran.nextInt(lesCoups.size());
                        CoupPuissance4 coup = lesCoupsValide.get(coupAleatoire);
                        getLeIhm().lOrdiAJoue("placé un jeton dans la colonne " + coup.getCol());
                        ctl.laGrille().gererCoup(coup);
                        break;
                    }
                }
            }
        }
    }
}
