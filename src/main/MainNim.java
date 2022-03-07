package main;
import controleur.ControleurJeuNim;
import vue.IhmNim;

public class MainNim {
    public static void main(String[] args) {
        IhmNim ihmNim = new IhmNim();
        ControleurJeuNim controleurJeuNim=new ControleurJeuNim(ihmNim);
        controleurJeuNim.jouer();
    }
}
