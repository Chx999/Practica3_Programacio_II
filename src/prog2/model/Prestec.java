package prog2.model;

import java.io.Serializable;
import java.util.Date;

public abstract class Prestec implements InPrestec, Serializable {
    private Exemplar exemplar;
    private Usuari usuari;
    private Date dataCreacio;
    private Date dataLimitRetorn;
    private boolean retornat;

    public Prestec(Exemplar exemplar, Usuari usuari) {
        this(exemplar, usuari, new Date());
    }

    public Prestec(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        this.exemplar = exemplar;
        this.usuari = usuari;
        this.dataCreacio = dataCreacio;
        this.dataLimitRetorn = new Date(this.dataCreacio.getTime() + duradaPrestec());
        this.retornat = false;
    }

    @Override
    public void setExemplar(Exemplar exemplar) { this.exemplar = exemplar; }

    @Override
    public Exemplar getExemplar() { return exemplar; }

    @Override
    public void setUsuari(Usuari usuari) { this.usuari = usuari; }

    @Override
    public Usuari getUsuari() { return usuari; }

    @Override
    public void setDataCreacio(Date data) { this.dataCreacio = data; }

    @Override
    public Date getDataCreacio() { return dataCreacio; }

    @Override
    public void setDataLimitRetorn(Date data) { this.dataLimitRetorn = data; }

    @Override
    public Date getDataLimitRetorn() { return dataLimitRetorn; }

    @Override
    public void setRetornat(boolean retornat) { this.retornat = retornat; }

    @Override
    public boolean getRetornat() { return retornat; }

    @Override
    public void retorna() {
        this.retornat = true;
    }

    @Override
    public boolean prestecEndarrerit() {
        return !retornat && new Date().after(dataLimitRetorn);
    }

    @Override
    public String toString() {
        return "Tipus=" + tipusPrestec() + ", Exemplar=" + exemplar.getTitol() + ", Usuari=" + usuari.getNom()
                + ", Data de creacio=" + dataCreacio + ", Data limit retorn=" + dataLimitRetorn
                + ", Retornat=" + retornat;
    }
}
