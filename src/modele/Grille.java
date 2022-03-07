package modele;

public class Grille {
    private int tailleGrille;
    private String[][] laGrille;

    public Grille(int nbCols){
        this.tailleGrille = nbCols;
        this.laGrille = new String[nbCols][nbCols];
    }

    public void initialiser(){
        for (int i=0; i<tailleGrille; i++){
            for (int j=0; j<tailleGrille; j++){
                laGrille[i][j] = "_";
            }
        }
    }

    public boolean grillePleine(){
        for (int i=0; i<tailleGrille; i++){
            if (laGrille[0][i].equals("_")){
                return false;
            }
        }
        return true;
    }


    public boolean partieTerminee(int lD, int cD){
        int res = 1;
        for (int i=-1; i<=1; i++){
            for (int j=-1; j<=1; j++){
                res += chercheLaVictoire(lD+i,cD+j,i,j) +
                        chercheLaVictoire(lD-i,cD-j,-i,-j);
                if (res>=4){
                    return true;
                }
                else{
                    res = 1;
                }
            }
        }
        return false;
    }

    public int chercheLaVictoire(int lD,int cD,int lM,int cM){
        if (lM==0 && cM==0){
            return 0;
        }
        if(lD<0 || lD>tailleGrille-1 || cD<0 || cD>tailleGrille-1){
            return 0;
        }
        if (laGrille[lD][cD].equals(laGrille[lD - lM][cD - cM])){
            return 1 + chercheLaVictoire(lD+lM,cD+cM,lM,cM);
        }
        else{
            return 0;
        }
    }

    public void gererCoup(int colonne, JetonCouleur couleur) throws CoupInvalideException {
        if (colonne<1 || colonne>tailleGrille || !laGrille[0][colonne-1].equals("_")){
            throw new CoupInvalideException("La colonne indiquée est invalide !");
        }
        for (int i=tailleGrille-1; i>=0; i--){
            if (laGrille[i][colonne-1].equals("_")){
                laGrille[i][colonne-1] = couleur.toString();
                couleur.setColonne(colonne-1);
                couleur.setLigne(i);
                break;
            }
        }
    }
    @Override
    public String toString(){
        String s = "";
        for (int i=1; i<=tailleGrille; i++){
            s+= " "+i;
        }
        s+= "\n";
        for (String[] ligne : laGrille){
            s+="|";
            for (String cases : ligne){
                s+=cases+"|";
            }
            s+=" \n";
        }
        return s;
    }

}
