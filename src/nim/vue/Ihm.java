package nim.vue;

import java.util.Scanner;


public class Ihm {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Fonction affichant l'erreur passée en paramètre
     * @param erreur le message d'erreur
     */
    public void afficherErreur(String erreur){
        System.out.println(erreur);
    }

    /**
     * Fonction demandant avec combien de tas les joueurs veulent jouer
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
                sc.next();
            }
        }
    }

    /**
     * Fonction demandant le nom du joueur
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
     * Fonction affichant l'état de la partie
     * @param letas les tas de la partie
     */
    public void etatPartie(String letas) {
        System.out.println(letas);
    }

    /**
     * Fonction demandant au joueur de jouer un coup et récupèrant son coup
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

    /**
     * Fonction permettant de savoir si les joueurs veulent rejouer + affichant le vainqueur de la partie
     * @param nom le nom du vainqueur
     * @return 1 si les joueurs veulent rejouer, 0 sinon
     */
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

    /**
     * Fonction affichant le vainqueur total des parties avec son nombre de parties gagnées
     * @param nom le nom du vainqueur
     * @param nbpartiesG le nombre de parties gagnées du vainqueur
     */
    public void afficherVainqueurTotal(String nom, int nbpartiesG) {
        System.out.println("Le gagnant est " + nom + " avec un total de " + nbpartiesG + " parties gagnées !");
    }

    /**
     * Fonction affichant le score des deux joueurs
     * @param nomj1 le nom du joueur 1
     * @param gagnej1 le nombre de parties gagnées du joueur 1
     * @param nomj2 le nom du joueur 2
     * @param gagnej2 le nombre de parties gagnées du joueur 2
     */
    public void afficherScore(String nomj1, int gagnej1, String nomj2, int gagnej2){
        System.out.println("Le score de "+nomj1+" est de "+gagnej1+" parties gagnées \n" +
                           "Le score de "+nomj2+" est de "+gagnej2+" parties gagnées");
    }

    /**
     * Fonction affichant le cas de l'égalité entre les joueurs avec le nombre de parties gagnées
     * @param nbpartiesq le nombre de parties gagnées
     */
    public void egalite(int nbpartiesq){
        System.out.println("Il y a une parfaite égalité avec un nombre de victoire de "+nbpartiesq+" chacun !");
    }


}
