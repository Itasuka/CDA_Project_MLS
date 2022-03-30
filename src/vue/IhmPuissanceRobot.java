package vue;

public class IhmPuissanceRobot extends IhmPuissance {
    /**
     * ORDI : Fonction permettant d'afficher que l'ordinateur à jouer
     * @param coup le coup effectué
     */
    public void lOrdiAJoue(String coup){
        System.out.println("\u001B[35m>>>>> L'ordinateur a "+coup+" <<<<<\u001B[0m");
    }
}
