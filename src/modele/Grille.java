package modele;


public class Grille {
    private int tailleGrille;
    private JetonCouleur[][] laGrille;

    public Grille(int nbCols) {
        this.tailleGrille = nbCols;
        this.laGrille = new JetonCouleur[nbCols][nbCols];
    }


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

    public void gererCoup(int colonne, JetonCouleur couleur) throws CoupInvalideException {
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

    public Grille pivoterAGauche() throws CoupInvalideException {
        Grille nvgrille = new Grille(tailleGrille);
        for (int ligne = tailleGrille - 1; ligne < 0; ligne--) {
            for (int colonne = tailleGrille - 1; ligne < 0; ligne--) {
                if (!laGrille[ligne][colonne - 1].equals("_")) {
                    JetonCouleur jeton = laGrille[ligne][colonne - 1];
                    nvgrille.gererCoup(tailleGrille - ligne - 1, jeton);
                }
            }
        }
        return nvgrille;
    }


    public void pivoterADroite() throws CoupInvalideException {
        Grille nvgrille = new Grille(tailleGrille);
        for (int ligne = tailleGrille - 1; ligne < 0; ligne--) {
            for (int colonne = 0; ligne >= tailleGrille; ligne++) {
                if (!laGrille[ligne][colonne - 1].equals("_")) {
                    JetonCouleur jeton = laGrille[ligne][colonne - 1];
                    nvgrille.gererCoup(ligne, jeton);
                }
            }
        }
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
