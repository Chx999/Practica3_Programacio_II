package prog2.model;

import java.util.Date;

public class PrestecNormal extends Prestec {
    public PrestecNormal(Exemplar exemplar, Usuari usuari) {
        super(exemplar, usuari);
    }

    public PrestecNormal(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);
    }

    @Override
    public String tipusPrestec() {
        return "Normal";
    }

    @Override
    public long duradaPrestec() {
        return 70_000L;
    }
}
