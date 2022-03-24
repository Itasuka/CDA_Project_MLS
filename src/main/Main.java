package main;
import controleur.ControleurJeuNim;
import controleur.ControleurJeuNimRobot;
import controleur.ControleurPuissance;
import vue.Ihm;
import vue.IhmNim;
import vue.IhmPuissance;

public class Main {
    public static void main(String[] args) {
        if (Ihm.choixJeu()==0){
            if (Ihm.jouerAvecOrdiOuPas()){
                ControleurJeuNimRobot controleur = new ControleurJeuNimRobot(new IhmNim());
                controleur.jouer();
            }
            else{
                ControleurJeuNim controleur = new ControleurJeuNim(new IhmNim());
                controleur.jouer();
            }
        }
        else{
            ControleurPuissance controleur = new ControleurPuissance(new IhmPuissance());
            controleur.jouer();
        }
    }
}
