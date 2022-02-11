package vue;
import java.util.Scanner;
import controleur.*;

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

    public void etatPartie(String letas){
        System.out.println(letas);
    }

    public void leCoup(ControleurJeuNim ctl, String nom){
        while(true){
            System.out.println(nom + " à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.");
            if(sc.hasNextInt()) {
                int numTas = sc.nextInt();
                if(sc.hasNextInt()){
                    int nbAl = sc.nextInt();
                    ctl.setLeCoup(ctl.creerCoupNim(numTas,nbAl));
                }
            }
            else{
                System.out.println("Le coup est invalide rejouez.");
            }
        }
    }

    public int rejouer(String nom){
        while(true){
            System.out.println(nom+" as gagné cette partie. Voulez vous rejouer ? 1 pour rejouer ou 0 sinon.");
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

    public void afficherVainqueurTotal(String nom, int nbpartiesg){
        System.out.println("Le gagnant est "+nom+" avec un total de " + nbpartiesg + " parties gagnées !");
    }




}
