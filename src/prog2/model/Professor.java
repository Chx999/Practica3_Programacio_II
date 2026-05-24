package prog2.model;

/**
 * Tipus d'usuari professor amb límits de préstec específics.
 */
public class Professor extends Usuari {
    public Professor(String email, String nom, String adreca) {
        super(email, nom, adreca);
    }

    @Override
    public String tipusUsuari() {
        return "Professor";
    }

    @Override
    public int getMaxPrestecsNormals() {
        return 2;
    }

    @Override
    public int getMaxPrestecsLlargs() {
        return 2;
    }
}
