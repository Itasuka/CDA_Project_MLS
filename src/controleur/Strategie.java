package controleur;

import modele.CoupInvalideException;

public interface Strategie {
    void jouerStrategie() throws CoupInvalideException;
}
