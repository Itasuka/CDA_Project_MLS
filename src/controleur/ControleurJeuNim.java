package controleur;
import modele.*;
import vue.*;

public class ControleurJeuNim {
    private Ihm ihm = new Ihm();
    private CoupNim lecoup = new CoupNim();

    public CoupNim creerCoupNim(int numTas, int nbAll){
        return new CoupNim(numTas,nbAll);
    }

    public String etatPartie(Tas lestas){
        return lestas.toString();
    }

    public leCoup(Joueur j){

    }

}
