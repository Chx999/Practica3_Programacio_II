package prog2.model;

import prog2.vista.BiblioException;

import java.util.ArrayList;

public interface InLlista<T> {
    /**
     * Retornar nombre d'elements continguts a la llista
     */
    int getSize();

    /**
     * Afegir element a la llista. Afegeix l'element t a la llista
     */
    void afegir(T t) throws BiblioException;

    /**
     * Esborrar element de la llista. Esborra l'element t a la llista
     */
    void esborrar(T t);

    /**
     * Retornar element de la llista a la posició position
     */
    T getAt(int position);

    /**
     * Buidar tots el elements de la llista
     */
    void clear();

    /**
     * Retornar true si la llista és buida
     */
    boolean isEmpty();

    /**
     * Retornar l'ArrayList que es fa servir dins de la classe
     */
    ArrayList<T> getArrayList();
}
