package controleur;

import modele.CoupInvalideException;
import modele.CoupNim;
import modele.JoueurNim;
import modele.Tas;
import vue.IhmNim;

public class ControleurJeuNim {
    private IhmNim leIhmNim;
    private Tas lesTas;
    private JoueurNim j1;
    private JoueurNim j2;
    private JoueurNim jCourant;

    public ControleurJeuNim(IhmNim ihmNim) {
        this.leIhmNim = ihmNim;
    }

    /**
     * Fonction permettant d'initialiser la partie
     * @return le nombre de tas avec lequel on va joueur
     */
    private int creerPartie() {
        int nbTas = 0;
        while (nbTas <= 0) {
            nbTas = leIhmNim.nombreTas();
        }
        lesTas = new Tas(nbTas);
        lesTas.initialiser();
        j1 = new JoueurNim(leIhmNim.nomJoueur(1));
        j2 = new JoueurNim(leIhmNim.nomJoueur(2));
        return nbTas;
    }

    /**
     * Fonction appellant la fonction d'affichage de la partie de l'ihm
     * @param lesTas la partie en cours
     */
    private void etatPartie(Tas lesTas) {
        leIhmNim.etatPartie(lesTas.toString());
    }

    /**
     * Fonction permettant de demander un coup via l'ihm et de faire un coup
     * @param j le joueur qui va jouer
     * @throws CoupInvalideException si le coup est invalide
     */
    private void faireLeCoup(JoueurNim j) throws CoupInvalideException {
        String nom = j.getNom();
        String coup = leIhmNim.leCoup(nom);
        int numTas = Integer.parseInt(coup.substring(0,coup.length()/2));
        int nbAl = Integer.parseInt(coup.substring(coup.length()/2));
        lesTas.gererCoup(creerCoupNim(numTas, nbAl));
    }

    /**
     * Fonction permettant de créer un coup à partir d'un numéro de tas et du nombre d'allumettes
     * @param numTas le numéro du tas du coup
     * @param nbAll le nombre d'allumettes du coup
     * @return le coup créé
     */
    private CoupNim creerCoupNim(int numTas, int nbAll) {
        return new CoupNim(numTas, nbAll);
    }

    /**
     * Fonction permettant de faire un tour au joueur
     * @param j le joueur qui va jouer
     */
    private void faireUnTour(JoueurNim j) {
        etatPartie(lesTas);
        boolean flag1 = true;
        while (flag1) {
            try{
                faireLeCoup(j);
                flag1 = false;
            }
            catch (CoupInvalideException e){
                leIhmNim.afficherErreur(e.getMessage());
            }

        }
    }

    /**
     * Fonction appellant la fonction d'affichage rejouer de l'ihm avec le nom du vainqueur
     * @param j le nom du vainqueur
     * @return 1 si les joueurs veulent rejouer, 0 sinon
     */
    private int rejouer(JoueurNim j) {
        String nom = j.getNom();
        return leIhmNim.rejouer(nom);
    }

    /**
     * Fonction recréant une nouvelle partie
     * @param nbTas le nombre de tas avec lequel les joueurs vont jouer
     */
    private void recreerPartie(int nbTas) {
        lesTas = new Tas(nbTas);
        lesTas.initialiser();
    }

    /**
     * Fonction permettant de donner le nom et le nombre de partie du joueur gagnant à la fonction afficherVainqueurTotal de l'ihm
     * ou à la fonction égalité si il y a égalité
     * @param j1 le joueur 1
     * @param j2 le joueur 2
     */
    private void afficherVainqueurTotal(JoueurNim j1, JoueurNim j2) {
        if (j1.getNbPartiesGagnees() > j2.getNbPartiesGagnees()) {
            String nom = j1.getNom();
            int nbpartiesg = j1.getNbPartiesGagnees();
            leIhmNim.afficherVainqueurTotal(nom, nbpartiesg);
        } else if (j1.getNbPartiesGagnees() < j2.getNbPartiesGagnees()) {
            String nom = j2.getNom();
            int nbpartiesq = j2.getNbPartiesGagnees();
            leIhmNim.afficherVainqueurTotal(nom, nbpartiesq);
        } else {
            leIhmNim.egalite(j1.getNbPartiesGagnees());
        }
    }


    /**
     * Fonction permettant de jouer des parties jusqu'à ce qu'on ne veuille plus jouer
     */
    public void jouer() {
        boolean rejouer = true;
        int nbTas = creerPartie();
        while (rejouer) {
            rejouer = false;
            jCourant = j1;
            while (true) {
                faireUnTour(jCourant);
                if (lesTas.partieTerminee()) {
                    JoueurNim gagnant = jCourant;
                    gagnant.gagnePartie();
                    int recommencer = rejouer(gagnant);
                    if (recommencer == 1) {
                        rejouer = true;
                        recreerPartie(nbTas);
                        break;
                    } else {
                        leIhmNim.afficherScore(j1.getNom(), j1.getNbPartiesGagnees(), j2.getNom(), j2.getNbPartiesGagnees());
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
