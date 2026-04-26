package prog2.model;

public class PrestecLlarg extends Prestec {
    public PrestecLlarg(Exemplar exemplar, Usuari usuari) {
        super(exemplar, usuari);
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
