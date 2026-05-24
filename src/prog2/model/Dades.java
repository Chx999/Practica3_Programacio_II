package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Implementació principal del model de dades de la biblioteca.
 * <p>
 * Gestiona les col·leccions d'exemplars, usuaris i préstecs, i aplica
 * les regles de negoci de l'aplicació.
 * </p>
 */
public class Dades implements InDades, Serializable {
    private LlistaExemplars exemplars;
    private LlistaUsuaris usuaris;
    private LlistaPrestecs prestecs;

    public Dades() {
        this.exemplars = new LlistaExemplars();
        this.usuaris = new LlistaUsuaris();
        this.prestecs = new LlistaPrestecs();
    }

    /**
     * Afegeix un nou exemplar a la col·lecció.
     *
     * @param id identificador únic de l'exemplar
     * @param titol títol de l'exemplar
     * @param autor autor de l'exemplar
     * @param admetPrestecLlarg indica si admet préstec llarg
     * @throws BiblioException si ja existeix un exemplar amb el mateix id
     */
    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        Exemplar exemplar = new Exemplar(id, titol, autor, admetPrestecLlarg);
        this.exemplars.afegir(exemplar);
    }

    /**
     * Recupera la llista d'exemplars.
     *
     * @return còpia de la llista d'exemplars
     */
    @Override
    public ArrayList<Exemplar> recuperaExemplars() {
        return exemplars.getArrayList();
    }

    /**
     * Afegeix un nou usuari al sistema.
     *
     * @param email correu electrònic únic de l'usuari
     * @param nom nom de l'usuari
     * @param adreca adreça de l'usuari
     * @param esEstudiant true si és estudiant; false si és professor
     * @throws BiblioException si ja existeix un usuari amb el mateix email
     */
    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        Usuari usuari;
        if (esEstudiant){
             usuari = new Estudiant(email,nom,adreca);
        }else{
            usuari = new Professor(email,nom,adreca);
        }
        usuaris.afegir(usuari);
    }

    /**
     * Recupera la llista d'usuaris.
     *
     * @return còpia de la llista d'usuaris
     */
    @Override
    public ArrayList<Usuari> recuperaUsuaris() {
        return usuaris.getArrayList();
    }

    /**
     * Afegeix un préstec nou fent les validacions de negoci necessàries.
     *
     * @param exemplarPos posició de l'exemplar dins la llista
     * @param usuariPos posició de l'usuari dins la llista
     * @param esLlarg true si el préstec és llarg
     * @throws BiblioException si no es compleixen les restriccions de préstec
     */
    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        Prestec prestec;
        Exemplar exemplar = exemplars.getAt(exemplarPos);
        Usuari usuari = usuaris.getAt(usuariPos);

       if (!exemplar.isDisponible()){
           throw new BiblioException("L'exemplar no esta disponible");
       }
       if (esLlarg && !exemplar.getAdmetPrestecLlarg()){
           throw new BiblioException("L'exemplar no s'admet prestec llarg");
       }

       // Verifica si l'usuari te presctecs endarrerits
       Iterator<Prestec> it = this.prestecs.getArrayList().iterator();
       while (it.hasNext()){
           Prestec aux = it.next();
           if (aux.getUsuari().equals(usuari) && !aux.getRetornat() && aux.prestecEndarrerit()){
              throw new BiblioException("L'usuari te prestecs endarrerits");
           }
       }

       // Comprova si l'usuari excedeix el seu limit de presctecs
        if (esLlarg){
            if (usuari.getNumPrestecsLlargs() >= usuari.getMaxPrestecsLlargs()){
                throw new BiblioException("Superat limit de prestecs llargs");
            }
        }else{
            if (usuari.getNumPrestecsNormals() >= usuari.getMaxPrestecsNormals()){
                throw new BiblioException("Superat limit de prestecs normals");
            }
        }

        if (esLlarg){
            prestec = new PrestecLlarg(exemplar,usuari,new Date());
        }else{
            prestec = new PrestecNormal(exemplar,usuari,new Date());
        }

        this.prestecs.afegir(prestec);

        // Ha d'actualitzar els estats de l'usuari i de l'exemplar
        exemplar.setDisponible(false);
        if (esLlarg){
            usuari.setNumPrestecsLlargs(usuari.getNumPrestecsLlargs() + 1);
        }else{
           usuari.setNumPrestecsNormals(usuari.getNumPrestecsNormals() + 1);
        }


    }

    /**
     * Marca un préstec com a retornat.
     *
     * @param position posició del préstec dins la llista
     * @throws BiblioException si el préstec ja havia estat retornat
     */
    @Override
    public void retornarPrestec(int position) throws BiblioException {
        Prestec prestec = this.prestecs.getAt(position);

        if (prestec.getRetornat()) {
            throw new BiblioException("El prestec ja ha estat retornat");
        }

        prestec.retorna();
    }

    /**
     * Recupera tots els préstecs registrats.
     *
     * @return còpia de la llista de préstecs
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecs() {
        return prestecs.getArrayList();
    }

    /**
     * Recupera només els préstecs que encara no han estat retornats.
     *
     * @return llista de préstecs no retornats
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats() {
        ArrayList<Prestec> noRetornats = new ArrayList<>();
        Iterator<Prestec> it = this.prestecs.getArrayList().iterator();
        while (it.hasNext()) {
            Prestec p = it.next();
            if (!p.getRetornat()) {
                noRetornats.add(p);
            }
        }
        return noRetornats;
    }
}
