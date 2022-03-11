package vue;

import controleur.ControleurPuissance;

import java.util.Scanner;

public class Ihm {
    private final Scanner sc = new Scanner(System.in);

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
    public int choixJeu() {
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
     * P4 : Fonction permettant de savoir le type de coup entre rotation à droite/gauche ou placer un jeton
     * @param nom le nom du joueur qui va jouer
     * @return 0 pour placer le jeton, 1 pour rotation à gauche, 2 pour rotation à droite
     */
    public int placerOuRotation(String nom) {
        while (true) {
            System.out.println(nom + "à vous de jouer. Voulez-vous placer un jeton ou effectuer une rotation ? 0 pour placer le jeton, 1 pour rotation à gauche ou 2 pour rotation à droite.");
            if (sc.hasNextInt()) {
                int choix = sc.nextInt();
                if (choix == 1 || choix == 2 || choix == 0) {
                    sc.nextLine();
                    return choix;
                }
            }
            System.out.println("Votre choix est invalide : il faut entrer 0, 1 ou 2 !");
            sc.nextLine();
        }
    }

    /**
     * P4 : Fonction permettant de savoir si les joueurs souhaitent jouer avec la rotation ou non
     * @return 0 pour sans rotations, 1 pour avec rotations
     */
    public int rotation() {
        while (true) {
            System.out.println("Voulez-vous jouer à puissance 4 avec le mode rotation? 0 pour non, 1 pour oui.");
            if (sc.hasNextInt()) {
                int choix = sc.nextInt();
                if (choix == 0 || choix == 1) {
                    sc.nextLine();
                    return choix;
                }
            } else {
                System.out.println("Votre choix est invalide : il faut entrer 0 ou 1 !");
                sc.nextLine();
            }
        }
    }

    /**
     * NIM : Fonction permettant de savoir si les joueurs veulent jouer à Nim avec une contrainte sur le nombre d'allumettes
     * @return 1 pour avec contrainte, 0 pour sans contrainte
     */
    public int choixContrainte() {
        System.out.println("Voulez-vous ajouter une contrainte sur le nombre d'allumettes à retirer ? 1 pour oui, 0 sinon");
        while (true) {
            if (sc.hasNextInt()) {
                int choixContrainte = sc.nextInt();
                if (choixContrainte == 1 || choixContrainte == 0) {
                    sc.nextLine();
                    return choixContrainte;
                }
            } else {
                System.out.println("Votre choix est invalide : il faut entrer 0 ou 1 !");
                sc.nextLine();
            }
        }
    }

    /**
     * NIM : Fonction permettant de connaitre le nombre d'allumettes pour la contrainte
     * @return le nombre d'allumettes pour la contrainte
     */
    public int nombreMaxAllumettes() {
        System.out.println("Veuillez entrer le nombre maximum d'allumettes que vous pourrez retirer");
        while (true) {
            if (sc.hasNextInt()) {
                int nbMax = sc.nextInt();
                sc.nextLine();
                return nbMax;
            } else {
                System.out.println("Il faut entrer un nombre.");
                sc.nextLine();
            }
        }
    }

    /**
     * P4 : Fonction permettant de demander et récupérer le coup
     * @param nom le nom du joueur
     * @param couleur le string du jeton
     * @return la colonne
     */
    public int leCoup(String nom, String couleur) {
        while (true) {
            System.out.println(nom + " de l'équipe " + couleur + " à vous de jouer, indiquez dans qu'elle colonne vous voulez placer votre jeton");
            if (sc.hasNextInt()) {
                int taille = sc.nextInt();
                sc.nextLine();
                return taille;
            } else {
                System.out.println("La colonne indiquée est invalide !");
                sc.nextLine();
            }
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
     * P4 : Fonction permettant de demander si on veut rejouer quand la grille a été remplie (personne n'a gagné)
     * @return 0 pour non, 1 pour oui
     */
    public int rejouerEgalite() {
        System.out.println("Personne n'a gagné cette partie ! \n Voulez vous rejouer ? 1 pour oui ou 0 pour non.");
        while (true) {
            if (sc.hasNextInt()) {
                int choix = sc.nextInt();
                if (choix == 1 || choix == 0) {
                    sc.nextLine();
                    return choix;
                }
            }
            System.out.println("Il faut entrer 0 ou 1 !");
            sc.nextLine();
        }
    }

    /**
     * NIM : Fonction demandant avec combien de tas les joueurs veulent jouer
     * @return le nombre de tas avec lequel les joueurs veulent jouer
     */
    public int nombreTas() {
        System.out.println("Veuillez entrer le nombre de tas >0 avec lequel vous voulez jouer.");
        while (true) {
            if (sc.hasNextInt()) {
                int nb = sc.nextInt();
                sc.nextLine();
                return nb;
            } else {
                System.out.println("Il faut entrer un nombre.");
                sc.nextLine();
            }
        }
    }

    /**
     * Fonction affichant l'état de la partie
     * @param laPartie les tas de la partie
     */
    public void etatPartie(String laPartie) {
        System.out.println(laPartie);
    }

    /**
     * NIM :Fonction demandant au joueur de jouer un coup et récupèrant son coup
     * @param nom le nom du joueur dont on veut le coup
     * @return le coup sous forme d'un string
     */
    public String leCoup(String nom) {
        System.out.println(nom + " à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.");
        while(true){
            if(sc.hasNextInt()){
                int numTas = sc.nextInt();
                if(sc.hasNextInt()){
                    int nbAl = sc.nextInt();
                    sc.nextLine();
                    return String.valueOf(numTas)+String.valueOf(nbAl);
                }
            }
            System.out.println("Le coup est invalide, rejouez");
            sc.nextLine();
        }
    }

}