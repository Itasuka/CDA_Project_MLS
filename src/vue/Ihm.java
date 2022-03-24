package vue;

import controleur.ControleurPuissance;

import java.util.Scanner;

public abstract class Ihm {
    protected static final Scanner sc = new Scanner(System.in);

    /**
     * Fonction permettant d'afficher le message de l'erreur
     * @param erreur le message d'erreur
     */
    public void afficherErreur(String erreur) {
        System.out.println(erreur);
    }

    /**
     * Fonction permettant de récupérer le nom du joueur
     * @param num le numéro du joueur dont on veut le nom
     * @return le nom du joueur associé au numéro
     */
    public String nomJoueur(int num) {
        System.out.println("Veuillez entrer le nom du joueur " + num + ".");
        String nom = sc.next();
        sc.nextLine();
        return nom;
    }

    /**
     * Fonction permettant de savoir le choix du jeu
     * @return 0 pour Nim, 1 pour puissance4
     */
    public static int choixJeu() {
        System.out.println("A quel jeu voulez-vous jouer ? 0 pour le jeu de Nim, 1 pour puissance 4.");
        while (true) {
            if (sc.hasNextInt()) {
                int choix = sc.nextInt();
                if (choix == 1 || choix == 0) {
                    sc.nextLine();
                    return choix;
                }
            }
            System.out.println("Votre choix est invalide : il faut entrer 0 ou 1 !");
            sc.nextLine();
        }
    }


    /**
     * Fonction permettant de demander si les joueurs veulent rejouer
     * @param nom le nom du vainqueur de la partie
     * @return 1 pour rejouer, 0 pour pas rejouer
     */
    public int rejouer(String nom) {
        System.out.println(nom + " a gagné cette partie. Voulez vous rejouer ? 1 pour rejouer ou 0 sinon.");
        while (true) {
            if (sc.hasNextInt()) {
                int choix = sc.nextInt();
                if (choix == 1 || choix == 0) {
                    sc.nextLine();
                    return choix;
                }
            }
            System.out.println("Votre choix est invalide : il faut entrer 0 ou 1 !");
            sc.nextLine();
        }
    }

    /**
     * Fonction permettant d'afficher le vainqueur total (de toutes les parties)
     * @param nom le nom du joueur gagnant
     * @param nbpartiesG le nombre de parties gagnés du joueur
     */
    public void afficherVainqueurTotal(String nom, int nbpartiesG) {
        System.out.println("Le gagnant est " + nom + " avec un total de " + nbpartiesG + " parties gagnées !");
    }

    /**
     * Fonction permettant d'afficher le score des deux joueurs
     * @param nomj1 le nom du joueur 1
     * @param gagnej1 le nombre de parties gagnées du joueur 1
     * @param nomj2 le nom du joueur 2
     * @param gagnej2 le nombre de parties gagnées du joueur 2
     */
    public void afficherScore(String nomj1, int gagnej1, String nomj2, int gagnej2) {
        System.out.println("Le score de " + nomj1 + " est de " + gagnej1 + " parties gagnées \n" +
                "Le score de " + nomj2 + " est de " + gagnej2 + " parties gagnées");
    }

    /**
     * Fonction permettant d'afficher le nombre de parties gagnées dans le cas d'une égalité
     * @param nbpartiesq le nombre de parties gagnées
     */
    public void egalite(int nbpartiesq) {
        System.out.println("Il y a une parfaite égalité avec un nombre de victoire de " + nbpartiesq + " chacun !");
    }

    /**
     * Fonction affichant l'état de la partie
     * @param laPartie les tas de la partie
     */
    public void etatPartie(String laPartie) {
        System.out.println(laPartie);
    }



}