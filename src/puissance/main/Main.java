package puissance.main;
import puissance.controleur.ControleurPuissance;
import puissance.vue.Ihm;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        ControleurPuissance controleurPuissance = new ControleurPuissance(ihm);
        controleurPuissance.jouer();
    }
}
