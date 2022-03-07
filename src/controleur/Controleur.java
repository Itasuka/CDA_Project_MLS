package controleur;

import modele.CoupInvalideException;
import modele.Joueur;
import vue.Ihm;

public abstract class Controleur {
    private Joueur j1;
    private Joueur j2;
    private Joueur jCourant;

    public void setJ1(Joueur j){
        this.j1 = j;
    }
    public void setJ2(Joueur j){
        this.j2 = j;
    }
    public void setJCourant (Joueur j){
        this.jCourant = j;
    }

    public abstract void init();
    public abstract boolean faireUnTour(Joueur j);
    public boolean partieGagnee(Joueur j){
        etatPartie();
        j.gagnePartie();
        if (rejouer(j)==1){
            recreerPartie();
            return true;
        }
        else{
            afficherFin(j1,j2);
            return false;
        }
    }
    public abstract int rejouer(Joueur j);
    public abstract void recreerPartie();
    public abstract void afficherFin(Joueur j1, Joueur j2);
    public abstract String etatPartie();

    public void jouer() {
        init();
        boolean rejouer = true;
        while (rejouer){
            jCourant = j1;
            while (true){
                if (faireUnTour(jCourant)) {
                    rejouer = partieGagnee(jCourant);
                    break;
                }
                else if (jCourant == j1){
                    jCourant = j2;
                }
                else{
                    jCourant = j1;
                }
            }
        }
    }
}
