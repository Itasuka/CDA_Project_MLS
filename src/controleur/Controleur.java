package controleur;

import modele.CoupInvalideException;
import modele.Joueur;
import vue.Ihm;

public abstract class Controleur {
    protected Ihm leIhm;
    private Joueur j1;
    private Joueur j2;
    private Joueur jCourant;

    public Controleur(Ihm ihm){
        this.leIhm = ihm;
    }

    public abstract Ihm getLeIhm();
    public Joueur getJ1(){return j1;}
    public Joueur getJ2(){return j2;}

    public void setJ1(Joueur j){
        this.j1 = j;
    }
    public void setJ2(Joueur j){
        this.j2 = j;
    }

    public abstract void init();
    public abstract void faireUnTour(Joueur j);
    public abstract boolean partieGagnee(Joueur j);
    public abstract boolean finPartie(Joueur j);
    public abstract void recreerPartie();

    public void jouer() {
        init();
        boolean rejouer = true;
        while (rejouer){
            jCourant = j1;
            while (true){
                faireUnTour(jCourant);
                if (partieGagnee(jCourant)) {
                    rejouer = finPartie(jCourant);
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
