/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import prog2.vista.BiblioException;

public class Llista<T> implements Serializable {
   protected ArrayList<T> llista;

   public Llista() {
       llista = new ArrayList<>();
    }

    /**
     * Retornar nombre d'elements continguts a la llista
     */
    public int getSize() {
          // TO-BE-DONE
    }

    /**
     * Afegir element a la llista. Afegeix l'element t a la llista
     */
    public void afegir(T t) throws BiblioException {
          // TO-BE-DONE
    }

    /**
     * Esborrar element de la llista. Esborra l'element t a la llista
     */
    public void esborrar(T t) {
          // TO-BE-DONE
    }

    /**
     * Retornar element de la llista a la posició position
     */
    public T getAt(int position) {
          // TO-BE-DONE
    }

    /**
     * Buidar tots el elements de la llista
     */
    public void clear() {
          // TO-BE-DONE
    }

    /**
     * Retornar true si la llista és buida
     */
    public boolean isEmpty() {
          // TO-BE-DONE
    }

    /**
     * Retornar l'ArrayList que es fa servir dins de la classe
     */
    public ArrayList<T> getArrayList() {
        ArrayList<T> arrlist = new ArrayList<>(llista);
        return arrlist;
    }
}
