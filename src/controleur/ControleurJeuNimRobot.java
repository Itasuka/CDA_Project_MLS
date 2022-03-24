package controleur;

import modele.CoupInvalideException;
import modele.Joueur;
import modele.Tas;
import vue.Ihm;

public class ControleurJeuNimRobot extends ControleurJeuNim {
    public ControleurJeuNimRobot(Ihm ihm) {
        super(ihm);
    }

    private int creerPartie(){
        int nbTas = 0;
        while (nbTas <= 0) {
            nbTas = getLeIhm().nombreTas();
        }
        setPlateau(new Tas(nbTas));
        lesTas().initialiser();
        lesTas().setNbMax(choixContrainte());
        setJ1(new Joueur(getLeIhm().nomJoueur(1)));
        setJ2(new Joueur("Olerobot"));
        return nbTas;
    }

    public void faireLeCoup(Joueur j) throws CoupInvalideException {
        String nom = j.getNom();
        if (j == getJ1()){
            String coup = getLeIhm().leCoup(nom,lesTas().getNbMax());
            int numTas = Integer.parseInt(coup.substring(0,coup.length()/2));
            int nbAl = Integer.parseInt(coup.substring(coup.length()/2));
            lesTas().gererCoup(creerCoupNim(numTas, nbAl));
        }
        else{
            jouerStrategie();
        }
    }
}
