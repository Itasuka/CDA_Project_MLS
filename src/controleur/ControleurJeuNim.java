package controleur;
import modele.*;
import vue.*;
import java.util.ArrayList;

public class ControleurJeuNim {
    private Ihm ihm;
    private CoupNim leCoup;
    private Tas letas;
    private ArrayList<Joueur> lesJoueurs = new ArrayList<>();

    public ControleurJeuNim(Ihm ihm){
        this.ihm=ihm;
    }

    public void setLeCoup(CoupNim leCoup) {
        this.leCoup = leCoup;
    }

    public CoupNim creerCoupNim(int numTas, int nbAll){
        return new CoupNim(numTas,nbAll);
    }

    public void etatPartie(Tas lestas){
        ihm.etatPartie(lestas.toString());
    }

    public void rejouer(Joueur j){
        String nom = j.getNom();
        ihm.rejouer(nom);
    }

    public void leCoup(Joueur j) {
        String nom = j.getNom();
        ihm.leCoup(this, nom);
    }

    public void afficherVainqueurTotal(Joueur j){
        String nom = j.getNom();
        int nbpartiesg = j.getNbPartiesGagnees();
        ihm.afficherVainqueurTotal(nom,nbpartiesg);
    }


}
