package vue;

import modele.*;
import java.util.Scanner;

public class Ihm {
    private final Scanner sc = new Scanner(System.in);

    public int nombreTas() {
        while(true){
            System.out.println("Veuillez entrer le nombre de tas >0 avec lequel vous voulez jouer.");
            if(sc.hasNextInt()) {
                return sc.nextInt();
            }
            else{
                System.out.println("Il faut entrer un nombre.");
            }
        }
    }

    public String nomJoueur(int num){
        System.out.println("Veuillez entrer le nom du joueur "+num+".");
        return sc.next();
    }

    public void etatPartie(Tas lesTas){
        System.out.println(lesTas);
    }

    public CoupNim leCoup(String joueur){
        while(true){
            System.out.println(joueur + " à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.");
            if(sc.hasNextInt()) {
                int numTas = sc.nextInt();
                if(sc.hasNextInt()){
                    int nbAl = sc.nextInt();
                    return new CoupNim(numTas,nbAl);
                }
            }
            else{
                System.out.println("Le coup est invalide rejouez.");
            }
        }
    }

    public int rejouer(){
        while(true){
            System.out.println("Voulez vous rejouer ? 1 pour rejouer ou 0 sinon.");
            if(sc.hasNextInt()){
                int choix = sc.nextInt();
                if(choix==1 || choix ==0){
                    return choix;
                }
            }
            else{
                System.out.println("Il faut entrer 0 ou 1 !");
            }
        }
    }

    public void afficherVainqueur(Joueur j){
        System.out.println("Le gagnant est j.getnom avec un total de " + j.getNbPartiesGagnees() + " parties gagnées.");
    }


}
