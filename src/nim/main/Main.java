package nim.main;
import nim.controleur.ControleurJeuNim;
import nim.vue.Ihm;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        ControleurJeuNim controleurJeuNim=new ControleurJeuNim(ihm);
        controleurJeuNim.jouer();
    }
}
