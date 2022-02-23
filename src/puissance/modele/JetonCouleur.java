package puissance.modele;

public class JetonCouleur {
    private String nomCouleur;
    private String couleur;
    private String leJeton = "●" + this.reset;

    public JetonCouleur(String nomCouleur){
        this.nomCouleur = nomCouleur;
        if (nomCouleur.equals("R")){
            this.couleur = rouge;
        }
        else if (nomCouleur.equals("J")){
            this.couleur = jaune;
        }
        else {
            this.couleur = "";
        }
    }

    public String getNomCouleur(){return nomCouleur;}

    public final String reset = "\u001B[0m";
    public final String rouge = "\u001B[31m";
    public final String jaune = "\u001B[33m";


    @Override
    public String toString(){
        return this.couleur + this.leJeton;
    }
}
