package vue;

public class IhmNim extends Ihm {

    /**
     * NIM : Fonction permettant de savoir si les joueurs veulent jouer à Nim avec une contrainte sur le nombre d'allumettes
     *
     * @return 1 pour avec contrainte, 0 pour sans contrainte
     */
    public int choixContrainte() {
        System.out.println("Voulez-vous ajouter une contrainte sur le nombre d'allumettes à retirer ? 1 pour oui, 0 sinon");
        while (true) {
            if (sc.hasNextInt()) {
                int choixContrainte = sc.nextInt();
                if (choixContrainte == 1 || choixContrainte == 0) {
                    sc.nextLine();
                    return choixContrainte;
                }
            }
            System.out.println("Votre choix est invalide : il faut entrer 0 ou 1 !");
            sc.nextLine();
        }
    }

    /**
     * NIM : Fonction permettant de connaitre le nombre d'allumettes pour la contrainte
     *
     * @return le nombre d'allumettes pour la contrainte
     */
    public int nombreMaxAllumettes() {
        System.out.println("Veuillez entrer le nombre maximum d'allumettes que vous pourrez retirer");
        while (true) {
            if (sc.hasNextInt()) {
                int nbMax = sc.nextInt();
                if (nbMax>0) {
                    sc.nextLine();
                    return nbMax;
                }
            }
            System.out.println("Il faut entrer un nombre > 0.");
            sc.nextLine();
        }
    }


    /**
     * NIM : Fonction demandant avec combien de tas les joueurs veulent jouer
     *
     * @return le nombre de tas avec lequel les joueurs veulent jouer
     */
    public int nombreTas() {
        System.out.println("Veuillez entrer le nombre de tas >0 avec lequel vous voulez jouer.");
        while (true) {
            if (sc.hasNextInt()) {
                int nb = sc.nextInt();
                sc.nextLine();
                return nb;
            } else {
                System.out.println("Il faut entrer un nombre.");
                sc.nextLine();
            }
        }
    }

    /**
     * NIM :Fonction demandant au joueur de jouer un coup et récupèrant son coup
     *
     * @param nom le nom du joueur dont on veut le coup
     * @return le coup sous forme d'un string
     */
    public String leCoup(String nom, int nb) {
        System.out.println(nom + " à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.");
        if (nb != Integer.MAX_VALUE) {
            System.out.println("Vous pouvez retirer " + nb + " allumettes maximum.");
        }
        while (true) {
            if (sc.hasNextInt()) {
                int numTas = sc.nextInt();
                if (sc.hasNextInt()) {
                    int nbAl = sc.nextInt();
                    sc.nextLine();
                    return String.valueOf(numTas) + String.valueOf(nbAl);
                }
            }
            System.out.println("Le coup est invalide, rejouez");
            sc.nextLine();
        }
    }

    /**
     * ORDI : Fonction permettant d'afficher que l'ordinateur à jouer
     */
    public void lOrdiAJoue(int tas, int al){
        System.out.println("L'ordinateur a retiré "+al+" allumettes dans le tas "+tas+" !");
    }
}
