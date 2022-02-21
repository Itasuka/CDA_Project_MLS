package controleur;

import modele.*;
import vue.*;

public class ControleurJeuNim {
    private Ihm leIhm;
    private Tas lesTas;
    private Joueur j1;
    private Joueur j2;
    private Joueur jCourant;

    public ControleurJeuNim(Ihm ihm) {
        this.leIhm = ihm;
    }

    private int creerPartie() {
        int nbTas = 0;
        while (nbTas <= 0) {
            nbTas = leIhm.nombreTas();
        }
        lesTas = new Tas(nbTas);
        lesTas.initialiser();
        j1 = new Joueur(leIhm.nomJoueur(1));
        j2 = new Joueur(leIhm.nomJoueur(2));
        return nbTas;
    }

    private void etatPartie(Tas lesTas) {
        leIhm.etatPartie(lesTas.toString());
    }

    private void faireLeCoup(Joueur j) throws CoupInvalideException{
        String nom = j.getNom();
        String coup = leIhm.leCoup(nom);
        int numTas = Integer.parseInt(coup.substring(0,coup.length()/2));
        int nbAl = Integer.parseInt(coup.substring(coup.length()/2));
        lesTas.gererCoup(creerCoupNim(numTas, nbAl));
    }

    private CoupNim creerCoupNim(int numTas, int nbAll) {
        return new CoupNim(numTas, nbAll);
    }

    private void faireUnTour(Joueur j) {
        etatPartie(lesTas);
        boolean flag1 = true;
        while (flag1) {
            try{
                faireLeCoup(j);
                flag1 = false;
            }
            catch (CoupInvalideException e){
                leIhm.afficherErreur(e.getMessage());
            }

        }
    }

    private int rejouer(Joueur j) {
        String nom = j.getNom();
        return leIhm.rejouer(nom);
    }

    private void recreerPartie(int nbTas) {
        lesTas = new Tas(nbTas);
        lesTas.initialiser();
    }

    private void afficherVainqueurTotal(Joueur j1, Joueur j2) {
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


    public void jouer() {
        boolean rejouer = true;
        int nbTas = creerPartie();
        while (rejouer) {
            rejouer = false;
            jCourant = j1;
            while (true) {
                faireUnTour(jCourant);
                if (lesTas.partieTerminee()) {
                    Joueur gagnant = jCourant;
                    gagnant.gagnePartie();
                    int recommencer = rejouer(gagnant);
                    if (recommencer == 1) {
                        rejouer = true;
                        recreerPartie(nbTas);
                        break;
                    } else {
                        leIhm.afficherScore(j1.getNom(), j1.getNbPartiesGagnees(), j2.getNom(), j2.getNbPartiesGagnees());
                        afficherVainqueurTotal(j1, j2);
                        break;
                    }
                } else if (jCourant == j1) {
                    jCourant = j2;
                } else {
                    jCourant = j1;
                }
            }

        }
    }


}
