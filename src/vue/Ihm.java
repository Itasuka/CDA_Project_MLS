package vue;

import java.util.Scanner;

import controleur.*;

public class Ihm {
    private final Scanner sc = new Scanner(System.in);

    public void afficherErreur(String erreur){
        System.out.println(erreur);
    }

    public int nombreTas() {
        System.out.println("Veuillez entrer le nombre de tas >0 avec lequel vous voulez jouer.");
        while (true) {
            if (sc.hasNextInt()) {
                int nb = sc.nextInt();
                sc.nextLine();
                return nb;
            } else {
                System.out.println("Il faut entrer un nombre.");
                sc.next();
            }
        }
    }

    public String nomJoueur(int num) {
        System.out.println("Veuillez entrer le nom du joueur " + num + ".");
        String nom = sc.next();
        sc.nextLine();
        return nom;
    }

    public void etatPartie(String letas) {
        System.out.println(letas);
    }

    public String leCoup(String nom) {
        System.out.println(nom + " à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.");
        while(true){
            if(sc.hasNextInt()){
                int numTas = sc.nextInt();
                if(sc.hasNextInt()){
                    int nbAl = sc.nextInt();
                    sc.nextLine();
                    return String.valueOf(numTas)+String.valueOf(nbAl);
                }else{
                    System.out.println("Le coup est invalide, rejouez");
                    sc.next();
                    sc.nextLine();
                }
            }else{
                System.out.println("Le coup est invalide, rejouez");
                sc.next();
                sc.nextLine();
            }

        }
    }

    public int rejouer(String nom) {
        System.out.println(nom + " as gagné cette partie. Voulez vous rejouer ? 1 pour rejouer ou 0 sinon.");
        while (true) {
            if (sc.hasNextInt()) {
                int choix = sc.nextInt();
                if (choix == 1 || choix == 0) {
                    sc.nextLine();
                    return choix;
                }
            } else {
                System.out.println("Il faut entrer 0 ou 1 !");
                sc.next();
            }
        }
    }

    public void afficherVainqueurTotal(String nom, int nbpartiesG) {
        System.out.println("Le gagnant est " + nom + " avec un total de " + nbpartiesG + " parties gagnées !");
    }
    public void afficherScore(String nomj1, int gagnej1, String nomj2, int gagnej2){
        System.out.println("Le score de "+nomj1+" est de "+gagnej1+" parties gagnées \n" +
                           "Le score de "+nomj2+" est de "+gagnej2+" parties gagnées");
    }

    public void egalite(int nbpartiesq){
        System.out.println("Il y a une parfaite égalité avec un nombre de victoire de "+nbpartiesq+" chacun !");
    }


}
