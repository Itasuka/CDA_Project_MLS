package vue;

import modele.JetonCouleur;

public class IhmPuissance extends Ihm {


    /**
     * P4 : Fonction permettant de savoir le type de coup entre rotation à droite/gauche ou placer un jeton
     * @param nom le nom du joueur qui va jouer
     * @return 0 pour placer le jeton, 1 pour rotation à gauche, 2 pour rotation à droite
     */
    public int placerOuRotation(String nom, String couleur) {
        while (true) {
            System.out.println(nom + " de l'équipe " + couleur + " à vous de jouer. Voulez-vous placer un jeton ou effectuer une rotation ? 0 pour placer le jeton, 1 pour rotation à gauche ou 2 pour rotation à droite.");
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

    public void plusDeRotationsDispo(){
        System.out.println("Vous n'avez plus de rotation disponible !");
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

    public void lOrdiAJoue(String coup){
        System.out.println("\u001B[35m>>>>> L'ordinateur a "+coup+" <<<<<\u001B[0m");
    }

    /**
     * P4 : Fonction permettant de demander si on veut rejouer quand la grille a été remplie (personne n'a gagné)
     * @return 0 pour non, 1 pour oui
     */
    public int rejouerEgalite() {
        System.out.println("Egalité, personne n'a gagné cette partie ! \n Voulez vous rejouer ? 1 pour oui ou 0 pour non.");
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
}
