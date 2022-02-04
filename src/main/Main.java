package main;

public class Main {

    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        ControleurJeuNim controleurJeu=new ControleurJeu(ihm);
        controleurJeuNim.jouer();
    }
}
