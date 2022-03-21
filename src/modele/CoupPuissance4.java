package modele;

public class CoupPuissance4 extends Coup{
    int col;
    JetonCouleur jeton;

    public CoupPuissance4(int col, JetonCouleur jeton){
        this.col = col;
        this.jeton = jeton;
    }

    public int getCol(){return col;}
    public JetonCouleur getJeton(){return jeton;}
}
