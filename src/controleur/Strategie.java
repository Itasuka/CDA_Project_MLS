package controleur;

import modele.CoupInvalideException;

public interface Strategie {
    public void jouerStrategie() throws CoupInvalideException;
}
