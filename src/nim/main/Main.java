package nim.main;
import controleur.*;
import nim.controleur.ControleurJeuNim;
import nim.vue.Ihm;
import vue.*;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        ControleurJeuNim controleurJeuNim=new ControleurJeuNim(ihm);
        controleurJeuNim.jouer();
    }
}
