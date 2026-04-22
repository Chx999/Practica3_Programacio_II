/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2.model;

import java.util.ArrayList;

import prog2.vista.BiblioException;

/**
 *
 * @author dortiz
 */
public interface InDades {

    /**
     * Afegeix exemplar. Llança excepció si l'id ja existeix
     */
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException;

    /**
     * Recuperar préstecs. Retorna un ArrayList amb tots els exemplars
     */
    public ArrayList<Exemplar> recuperaExemplars();

    /**
     * Afegeix usuari. Llança excepció si l'email ja existeix
     */
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException;

    /**
     * Recuperar usuaris. Retorna un ArrayList amb tots els usuaris
     */
    public ArrayList<Usuari> recuperaUsuaris();

    /**
     * Afegeix préstec. Ha de fer diferents comprovacions que poden llançar excepcions.
     * Quan s'afegeix el préstec, s'han de tenir en compte les posicions d'exemplar
     * i usuari dins dels seus ArrayLists
     */
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException;

    /**
     * Retornar préstec. Llança excepció si el prestec ja es vaig retornar.
     * El préstec s'identifica amb la seva posició dins de l'ArrayList
     */
    public void retornarPrestec(int position) throws BiblioException;

    /**
     * Recuperar préstecs. Retorna un ArrayList amb tots els préstecs
     */
    public ArrayList<Prestec> recuperaPrestecs();

    /**
     * Recuperar préstecs. Retorna un ArrayList amb els préstecs no retornats
     */
    public ArrayList<Prestec> recuperaPrestecsNoRetornats();
}
