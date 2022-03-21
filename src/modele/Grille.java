package modele;


public class Grille extends Plateau {
    private int tailleGrille;
    private JetonCouleur[][] laGrille;
    private boolean tourner;
    private int partieFinie;

    public Grille(int nbCols) {
        this.tailleGrille = nbCols;
        this.laGrille = new JetonCouleur[nbCols][nbCols];
    }

    public int getPartieFinie(){return this.partieFinie;}
    public boolean getTourner(){return this.tourner;}
    public void setTourner(boolean tourner){this.tourner = tourner;}

    public boolean grillePleine() {
        for (int i = 0; i < tailleGrille; i++) {
            if (laGrille[0][i] == null) {
                return false;
            }
        }
        return true;
    }


    public boolean partieTerminee(int lD, int cD) {
        int res = 1;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                res += chercheLaVictoire(lD + i, cD + j, i, j) +
                        chercheLaVictoire(lD - i, cD - j, -i, -j);
                if (res >= 4) {
                    return true;
                } else {
                    res = 1;
                }
            }
        }
        return false;
    }

    public int chercheLaVictoire(int lD, int cD, int lM, int cM) {
        if (lM == 0 && cM == 0) {
            return 0;
        }
        if (lD < 0 || lD > tailleGrille - 1 || cD < 0 || cD > tailleGrille - 1) {
            return 0;
        }
        if (laGrille[lD][cD] != null && laGrille[lD - lM][cD - cM] != null && laGrille[lD][cD].getCouleur().equals(laGrille[lD - lM][cD - cM].getCouleur())) {
            return 1 + chercheLaVictoire(lD + lM, cD + cM, lM, cM);
        } else {
            return 0;
        }
    }

    public void gererCoup(Coup c) throws CoupInvalideException {
        CoupPuissance4 coup = (CoupPuissance4) c;
        int colonne = coup.getCol();
        JetonCouleur couleur = coup.getJeton();
        if (colonne < 1 || colonne > tailleGrille || laGrille[0][colonne - 1] != null) {
            throw new CoupInvalideException("La colonne indiquée est invalide !");
        }
        for (int i = tailleGrille - 1; i >= 0; i--) {
            if (laGrille[i][colonne - 1] == null) {
                laGrille[i][colonne - 1] = couleur;
                couleur.setColonne(colonne - 1);
                couleur.setLigne(i);
                break;
            }
        }
    }

    public Grille pivoterADroite() throws CoupInvalideException {
        Grille nvgrille = new Grille(tailleGrille);
        nvgrille.setTourner(true);
        boolean boolj1=false;
        boolean boolj2=false;
        int res;
        for (int ligne = tailleGrille - 1; ligne >= 0; ligne--) {
            for (int colonne = tailleGrille - 1; colonne >= 0; colonne--) {
                if (!(laGrille[ligne][colonne] ==null)) {
                    JetonCouleur jeton = laGrille[ligne][colonne];
                    nvgrille.gererCoup(new CoupPuissance4(tailleGrille - ligne, jeton));
                    if (nvgrille.partieTerminee(jeton.getLigne(), jeton.getColonne())) {
                        if (jeton.getCouleur().equals("\u001B[31m")) {
                            boolj1 = true;
                        } else {
                            boolj2 = true;
                        }
                    }
                }
            }
        }
        if (boolj1 && !boolj2) { //j1 Bravo
            nvgrille.partieFinie = 1;
        } else if (!boolj1 && boolj2) { //j2 Bravo
            nvgrille.partieFinie = 2;
        } else if (boolj1 && boolj2) { //Dommage
            nvgrille.partieFinie = 3;
        }
        return nvgrille;
    }





    public Grille pivoterAGauche() throws CoupInvalideException {
        boolean boolj1 = false;
        boolean boolj2 = false;
        int resu;
        Grille nvgrille = new Grille(tailleGrille);
        nvgrille.setTourner(true);
        for (int ligne = tailleGrille-1; ligne >= 0; ligne--) {
            for (int colonne = 0; colonne < tailleGrille; colonne++) {
                if (!(laGrille[ligne][colonne]==null)) {
                    JetonCouleur jeton = laGrille[ligne][colonne];
                    nvgrille.gererCoup(new CoupPuissance4(ligne+1, jeton));
                    if (nvgrille.partieTerminee(jeton.getLigne(), jeton.getColonne())) {
                        if (jeton.getCouleur().equals("\u001B[31m")) {
                            boolj1 = true;
                        } else {
                            boolj2 = true;
                        }
                    }
                }
            }
        }
        if (boolj1 && !boolj2) {
            nvgrille.partieFinie = 1;
        } else if (!boolj1 && boolj2) {
            nvgrille.partieFinie = 2;
        } else if (boolj1 && boolj2){
            nvgrille.partieFinie = 3;
        }
        return nvgrille;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i <= tailleGrille; i++) {
            s += " " + i;
        }
        s += "\n";
        for (JetonCouleur[] ligne : laGrille) {
            s += "|";
            for (JetonCouleur cases : ligne) {
                if (cases == null) {
                    s += "_" + "|";
                } else {
                    s += cases.toString() + "|";
                }
            }
            s += " \n";
        }
        return s;
    }

}
