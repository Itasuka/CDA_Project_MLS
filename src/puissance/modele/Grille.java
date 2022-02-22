package puissance.modele;

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
                laGrille[i][j] = " ";
            }
        }
    }

    public boolean grillePleine(){
        for (String[] ligne : laGrille){
            for (String cases : ligne){
                if (cases.equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }



    public boolean chercheLaVictoire (int lDep, int colDep, int lMove, int colMove){
        String couleur = " ";
        int compteur = 0;
        while (lDep>=0 && lDep<tailleGrille && colDep>=0 && colDep<tailleGrille){
            if (laGrille[lDep][colDep].equals(couleur)){
                compteur++;
            }
            else{
                compteur = 1;
                couleur = laGrille[lDep][colDep];
            }
            if(compteur>=4 && !couleur.equals(" ")){
                return true;
            }
            lDep+=lMove;
            colDep+=colMove;
        }
        return false;
    }

    public boolean partieTerminee (){
        //Horizontale
        for (int i=0; i<tailleGrille; i++){
            if (chercheLaVictoire(i,0,0,1)){
                return true;
            }
        }
        //Verticale
        for(int i=0; i<tailleGrille; i++){
            if (chercheLaVictoire(0,i,1,0)){
                return true;
            }
        }
        //Diagonale HautGauche --> BasDroit
        for(int i=0; i<tailleGrille-3; i++){
            if (chercheLaVictoire(i,0,1,1)){
                return true;
            }
        }
        for(int i=1; i<tailleGrille-3; i++){
            if (chercheLaVictoire(0,i,1,1)){
                return true;
            }
        }
        //Diagonale BasGauche --> HautDroit
        for(int i=3; i<tailleGrille; i++){
            if (chercheLaVictoire(i,0,-1,1)){
                return true;
            }
        }
        for(int i=1; i<tailleGrille-3; i++){
            if (chercheLaVictoire(tailleGrille,i,-1,1)){
                return true;
            }
        }
        return false;
    }


    public void gererCoup(int colonne, JetonCouleur couleur) throws ColonneInvalideException {
        if (colonne<1 || colonne>7 || !laGrille[0][colonne-1].equals(" ")){
            throw new ColonneInvalideException("La colonne indiquée est invalide !");
        }
        for (int i=tailleGrille-1; i>=0; i--){
            if (laGrille[i][colonne-1].equals(" ")){
                laGrille[i][colonne-1] = couleur.toString();
                break;
            }
        }
    }
    @Override
    public String toString(){
        String s = "";
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
