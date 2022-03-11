package main;
import controleur.ControleurJeuNim;
import controleur.ControleurPuissance;
import vue.Ihm;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        if (ihm.choixJeu()==0){
            ControleurJeuNim controleur = new ControleurJeuNim(ihm);
            controleur.jouer();
        }
        else{
            ControleurPuissance controleur = new ControleurPuissance(ihm);
            controleur.jouer();
        }

    }
}
