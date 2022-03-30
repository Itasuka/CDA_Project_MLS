package main;
import controleur.ControleurJeuNim;
import controleur.ControleurJeuNimRobot;
import controleur.ControleurPuissance;
import controleur.ControleurPuissanceRobot;
import vue.*;

public class Main {
    public static void main(String[] args) {
        if (Ihm.choixJeu()==0){
            if (Ihm.jouerAvecOrdiOuPas()){
                ControleurJeuNimRobot controleur = new ControleurJeuNimRobot(new IhmNimRobot());
                controleur.jouer();
            }
            else{
                ControleurJeuNim controleur = new ControleurJeuNim(new IhmNim());
                controleur.jouer();
            }
        }
        else{
            if (Ihm.jouerAvecOrdiOuPas()){
                ControleurPuissanceRobot controleur = new ControleurPuissanceRobot(new IhmPuissanceRobot());
                controleur.jouer();
            }
            else{
                ControleurPuissance controleur = new ControleurPuissance(new IhmPuissance());
                controleur.jouer();
            }

        }

    }
}
