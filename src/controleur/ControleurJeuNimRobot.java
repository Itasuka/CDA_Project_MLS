package controleur;

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


}
