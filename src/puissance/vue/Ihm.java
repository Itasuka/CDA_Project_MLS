package puissance.vue;

import puissance.controleur.ControleurPuissance;

import java.util.Scanner;

public class Ihm {
    private final Scanner sc = new Scanner(System.in);
    private ControleurPuissance ct;

    public void setControleurPuissance(ControleurPuissance controleur){this.ct = controleur;}

    public void afficherErreur(String erreur){
        System.out.println(erreur);
    }

    public String nomJoueur(int num) {
        System.out.println("Veuillez entrer le nom du joueur " + num + ".");
        String nom = sc.next();
        sc.nextLine();
        return nom;
    }

    public void etatPartie() {
        System.out.println(ct.getLaGrille());
    }

    public int leCoup(String nom, String couleur) {
        while(true){
            System.out.println(nom + " de l'équipe " + couleur + " à vous de jouer, indiquez dans qu'elle colonne vous voulez placer votre jeton");
            if(sc.hasNextInt()){
                int taille = sc.nextInt();
                sc.nextLine();
                return taille;
            }else{
                System.out.println("La colonne indiquée est invalide !");
                sc.nextLine();
            }
        }
    }

    public int rejouer(String nom) {
        System.out.println(nom + " a gagné cette partie. Voulez vous rejouer ? 1 pour rejouer ou 0 sinon.");
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

    public int rejouerEgalite(){
        System.out.println("Personne n'a gagné cette partie ! \n Voulez vous rejouer ? 1 pour oui ou 0 pour non.");
        while(true){
            if(sc.hasNextInt()){
                int choix = sc.nextInt();
                if (choix == 1 || choix == 0) {
                    sc.nextLine();
                    return choix;
                }
                else{
                    System.out.println("Il faut entrer 0 ou 1 !");
                    sc.next();
                }
            } else {
                System.out.println("Il faut entrer 0 ou 1 !");
                sc.next();
            }
        }
    }

}
