package puissance.modele;

public class Grille {
    private int tailleGrille;
    private String[][] laGrille;

    public Grille(int nbCols){
        this.tailleGrille = nbCols;
        this.laGrille = new String[nbCols][nbCols];
    }

    public void initialiser(){
        for (String[] ligne : laGrille){
            for (String cases : ligne){
                cases = " ";
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

    public boolean partieTerminées(int ligne, int colonne,String couleur){
        //Test victoire verticale du pion vers le bas
        int compteur = 0;
        for (int i=ligne+1; i<=tailleGrille; i++){
            if(laGrille[i][colonne].equals(couleur)){
                compteur++;
            }
        }
        if(compteur>=4){
            return true;
        }
        //Test victoire horizontale de droite à gauche
        compteur=0;
        for(int i=colonne-1; i>=0; i--){
            if(laGrille[ligne][i].equals(couleur)){
                compteur++;
            }
        }
        for (int i=colonne+1; i<=tailleGrille;i++){
            if(laGrille[ligne][i].equals(couleur)){
                compteur++;
            }
        }
        if (compteur>=4){
            return true;
        }
        //Test victoire diagonale HautGauche<->BasDroit \
        compteur=0;
        for (int i=ligne-1; i>=0; i--){
            for (int j=colonne-1; j>=0; j--){
                if(laGrille[i][j].equals(couleur)){
                    compteur++;
                }
            }
        }
        for (int i=ligne+1; i<=tailleGrille; i++){
            for (int j=colonne+1; j<=tailleGrille; j++){
                if(laGrille[i][j].equals(couleur)){
                    compteur++;
                }
            }
        }
        if(compteur>=4){
            return true;
        }
        //Test victoire diagonale BasGauche<->HautDroit /
        compteur=0;
        for (int i=ligne+1; i<=tailleGrille; i++){
            for (int j=colonne-1; j>=0; j--){
                if(laGrille[i][j].equals(couleur)){
                    compteur++;
                }
            }
        }
        for (int i=ligne-1; i>=0; i--){
            for (int j=colonne+1; j<=tailleGrille; j++){
                if(laGrille[i][j].equals(couleur)){
                    compteur++;
                }
            }
        }
        if(compteur>=4){
            return true;
        }
        return false;
    }

    public void gererCoup(int colonne, String couleur) throws ColonneInvalideException {
        boolean jetonPlace = false;
        for (int i=tailleGrille; i>=0; i--){
            if (laGrille[i][colonne].equals(" ")){
                laGrille[i][colonne] = couleur;
                jetonPlace = true;
                break;
            }
        }
        if(!jetonPlace){
            throw new ColonneInvalideException("La colonne indiquée est invalide !");
        }
    }
    @Override
    public String toString(){
        String s = "";
        for (String[] ligne : laGrille){
            s+="[";
            for (String cases : ligne){
                s+=cases+"|";
            }
            s+="] \n";
        }
        return s;
    }

}
