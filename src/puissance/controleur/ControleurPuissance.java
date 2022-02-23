package puissance.controleur;

import puissance.modele.ColonneInvalideException;
import puissance.modele.JetonCouleur;
import puissance.modele.Joueur;
import puissance.modele.Grille;
import puissance.vue.Ihm;

public class ControleurPuissance {
    private Ihm leIhm;
    private Grille laGrille;
    private Joueur j1;
    private Joueur j2;
    private Joueur jCourant;

    public ControleurPuissance(Ihm ihm){this.leIhm = ihm;}

    public Grille getLaGrille(){return laGrille;}

    private void creerPartie() {
        laGrille = new Grille(7);
        laGrille.initialiser();
        leIhm.setControleurPuissance(this);
        j1 = new Joueur(leIhm.nomJoueur(1),"R");
        j2 = new Joueur(leIhm.nomJoueur(2),"J");
    }

    private void etatPartie() {
        leIhm.etatPartie();
    }

    private void faireLeCoup(Joueur j) throws ColonneInvalideException {
        String nom = j.getNom();
        JetonCouleur couleur = j.getMaCouleur();
        laGrille.gererCoup(leIhm.leCoup(nom, couleur.toString()), couleur);
    }


    private void faireUnTour(Joueur j) {
        etatPartie();
        boolean flag1 = true;
        while (flag1) {
            try{
                faireLeCoup(j);
                flag1 = false;
            }
            catch (ColonneInvalideException e){
                leIhm.afficherErreur(e.getMessage());
            }
        }
    }

    private int rejouer(Joueur j) {
        String nom = j.getNom();
        return leIhm.rejouer(nom);
    }

    private void recreerPartie() {
        laGrille = new Grille(7);
        laGrille.initialiser();
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
        creerPartie();
        boolean rejouer = true;
        while (rejouer) {
            rejouer = false;
            jCourant = j1;
            while (true) {
                faireUnTour(jCourant);
                if (laGrille.partieTerminee()) {
                    Joueur gagnant = jCourant;
                    gagnant.gagnePartie();
                    int recommencer = rejouer(gagnant);
                    if (recommencer == 1) {
                        rejouer = true;
                        recreerPartie();
                        break;
                    } else {
                        leIhm.afficherScore(j1.getNom(), j1.getNbPartiesGagnees(), j2.getNom(), j2.getNbPartiesGagnees());
                        afficherVainqueurTotal(j1, j2);
                        break;
                    }
                }
                else if (laGrille.grillePleine()){
                    int recommencer = leIhm.rejouerEgalite();
                    if (recommencer == 1) {
                        rejouer = true;
                        recreerPartie();
                        break;
                    } else {
                        leIhm.afficherScore(j1.getNom(), j1.getNbPartiesGagnees(), j2.getNom(), j2.getNbPartiesGagnees());
                        afficherVainqueurTotal(j1, j2);
                        break;
                    }
                }
                else if (jCourant == j1) {
                    jCourant = j2;
                } else {
                    jCourant = j1;
                }
            }

        }
    }
}
