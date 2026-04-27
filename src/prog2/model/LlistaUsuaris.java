package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

public class LlistaUsuaris extends Llista<Usuari> implements Serializable {
    /**
     * Afegir element a la llista. Afegeix l'element t a la llista
     */
    @Override
    public void afegir(Usuari t) throws BiblioException {
        Iterator<Usuari> it = this.llista.iterator();
        while (it.hasNext()){
            Usuari aux = it.next();
            if (aux.getEmail().equals(t.getEmail())){
                throw new BiblioException("NO es poden afegir dos usuaris amb el mateix correu elecctronic");
            }
        }
        super.afegir(t);
    }
}
