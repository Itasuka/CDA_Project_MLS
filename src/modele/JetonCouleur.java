package modele;

public class JetonCouleur {
    private String couleur;
    private String leJeton;
    private int ligne;
    private int colonne;

    public JetonCouleur(String nomCouleur){
        if (nomCouleur.equals("R")){
            this.couleur = rouge;
            this.leJeton =  "●" + this.reset;
        }
        else if (nomCouleur.equals("J")){
            this.couleur = jaune;
            this.leJeton =  "●" + this.reset;
        }
        else {
            this.couleur = "";
            this.leJeton =  "_" + this.reset;
        }
    }

    public String getCouleur(){ return this.couleur;}
    public int getLigne(){return ligne;}
    public int getColonne(){return colonne;}
    public void setLigne(int ligne){ this.ligne = ligne; }
    public void setColonne(int colonne){ this.colonne = colonne; }

    public final String reset = "\u001B[0m";
    public final String rouge = "\u001B[31m";
    public final String jaune = "\u001B[33m";


    @Override
    public String toString(){
        return this.couleur + this.leJeton;
    }
}
