package prog2.model;

import java.io.Serializable;

/**
 * Classe base abstracta per als usuaris del sistema de biblioteca.
 * Gestiona dades personals i comptadors de préstecs actius.
 */
public abstract class Usuari implements InUsuari, Serializable {
    private String email;
    private String nom;
    private String adreca;
    private int numPrestecsNormals;
    private int numPrestecsLlargs;

    public Usuari(String email, String nom, String adreca) {
        this.email = email;
        this.nom = nom;
        this.adreca = adreca;
        this.numPrestecsNormals = 0;
        this.numPrestecsLlargs = 0;
    }

    @Override
    public void setEmail(String email) { this.email = email; }

    @Override
    public String getEmail() { return email; }

    @Override
    public void setNom(String nom) { this.nom = nom; }

    @Override
    public String getNom() { return nom; }

    @Override
    public void setAdreca(String adreca) { this.adreca = adreca; }

    @Override
    public String getAdreca() { return adreca; }

    @Override
    public void setNumPrestecsNormals(int numPrestecsNormals) { this.numPrestecsNormals = numPrestecsNormals; }

    @Override
    public int getNumPrestecsNormals() { return numPrestecsNormals; }

    @Override
    public void setNumPrestecsLlargs(int numPrestecstLlargs) { this.numPrestecsLlargs = numPrestecstLlargs; }

    @Override
    public int getNumPrestecsLlargs() { return numPrestecsLlargs; }

    @Override
    public String toString() {
        return "Tipus=" + tipusUsuari() + ", Email=" + email + ", Nom=" + nom + ", Adreca=" + adreca
                + ", Num. prestecs normals=" + numPrestecsNormals
                + ", Num. prestecs llargs=" + numPrestecsLlargs;
    }
}
