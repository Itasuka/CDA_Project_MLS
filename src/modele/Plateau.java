package modele;

public abstract class Plateau {
    public abstract void gererCoup(Coup coup) throws CoupInvalideException;
}
