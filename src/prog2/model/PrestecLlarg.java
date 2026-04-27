package prog2.model;

import java.util.Date;

public class PrestecLlarg extends Prestec {
    public PrestecLlarg(Exemplar exemplar, Usuari usuari) {
        super(exemplar, usuari);
    }

    public PrestecLlarg(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);
    }

    @Override
    public String tipusPrestec() {
        return "Llarg";
    }

    @Override
    public long duradaPrestec() {
        return 140_000L;
    }
}
