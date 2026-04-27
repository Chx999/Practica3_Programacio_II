package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class LlistaExemplars extends Llista<Exemplar> implements Serializable {

    /**
     * Afegir element a la llista. Afegeix l'element t a la llista
     */
    @Override
    public void afegir(Exemplar t) throws BiblioException {
        Iterator<Exemplar> it = super.getArrayList().iterator();
        while (it.hasNext()){
            Exemplar aux = it.next();
            if (aux.getId().equals(t.getId())){
                throw new BiblioException("NO es poden afegir dos exemplars amb el mateix identificador");
            }
        }
        super.afegir(t);
    }
}
