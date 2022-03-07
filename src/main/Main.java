package main;
import controleur.ControleurPuissance;
import vue.Ihm;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        ControleurPuissance controleurPuissance = new ControleurPuissance(ihm);
        controleurPuissance.jouer();
    }
}
