package controleur;

import modele.CoupInvalideException;
import modele.CoupNim;
import modele.Joueur;
import modele.Tas;
import vue.Ihm;
import vue.IhmNimRobot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControleurJeuNimRobot extends ControleurJeuNim {
    public ControleurJeuNimRobot(Ihm ihm) {
        super( ihm);
    }

    public IhmNimRobot getLeIhm() { return (IhmNimRobot) leIhm;}

    public int creerPartie(){
        int nbTas = 0;
        while (nbTas <= 0) {
            nbTas = getLeIhm().nombreTas();
        }
        setPlateau(new Tas(nbTas));
        lesTas().initialiser();
        lesTas().setNbMax(choixContrainte());
        setJ1(new Joueur(getLeIhm().nomJoueur(1)));
        setJ2(new Joueur("OLeRobot"));
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
        if (j == getJ2()){
            jouerStrategie();
        }
    }

    private void jouerStrategie() throws CoupInvalideException {
        List<CoupNim> coupPossible = new ArrayList<>();
        coupPossible.add(creerCoupNim(2,1));
        coupPossible.add(creerCoupNim(3,1));
        for (int i = 0; i <= getNbTas(); i++) {
            for (int j = 1; j <= 2 * i + 1; j++) {
                try {
                    Tas testTas = new Tas(lesTas().getLesTas().clone());
                    testTas.gererCoup(creerCoupNim(i,j));
                    coupPossible.add(creerCoupNim(i,j));
                }
                catch(CoupInvalideException e){
                }
            }
        }
        Random ran = new Random();
        int randomInt = ran.nextInt(coupPossible.size());
        CoupNim coup = coupPossible.get(randomInt);
        lesTas().gererCoup(coup);
        getLeIhm().lOrdiAJoue(coup.getNumeroTas(), coup.getNbAllumettes());
    }

    public void faireUnTour(Joueur j) {
        if (j == getJ1()){
            etatPartie(lesTas());
        }
        boolean flag1 = true;
        while (flag1) {
            try{
                faireLeCoup(j);
                flag1 = false;
            }
            catch (CoupInvalideException e){
                if(j==getJ1()){
                    getLeIhm().afficherErreur(e.getMessage());
                }
            }

        }
    }
}
