package vue;

public class IhmNimRobot extends IhmNim {
    /**
     * ORDI : Fonction permettant d'afficher que l'ordinateur à jouer
     * @param tas le numéro du tas
     * @param al le nombre d'allumettes à retirer
     */
    public void lOrdiAJoue(int tas, int al){
        System.out.println("\u001B[35m >>>>> L'ordinateur a retiré "+al+" allumettes dans le tas "+tas+" ! <<<<<\u001B[0m");
    }
}
