package prog2.adaptador;

import prog2.model.Dades;
import prog2.model.Exemplar;
import prog2.model.Prestec;
import prog2.model.Usuari;
import prog2.vista.BiblioException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Capa d'adaptació entre la vista i el model de dades.
 *
 * Exposa operacions orientades a la vista i delega la lògica de negoci
 * a la classe {@link Dades}.
 */
public class Adaptador {
    private Dades dades;

    /**
     * Crea un adaptador amb una instància nova del model de dades.
     */
    public Adaptador() {
        this.dades = new Dades();
    }

    /**
     * Afegeix un exemplar nou.
     *
     * @param id identificador únic de l'exemplar
     * @param titol títol de l'exemplar
     * @param autor autor de l'exemplar
     * @param admetPrestecLlarg indica si admet préstec llarg
     * @throws BiblioException si hi ha cap error de validació
     */
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

    /**
     * Recupera tots els exemplars en format textual per mostrar a la vista.
     *
     * @return llista de cadenes amb la informació dels exemplars
     */
    public List<String> recuperaExemplars() {
        ArrayList<String> resultado = new ArrayList<>();
        Iterator<Exemplar> it = dades.recuperaExemplars().iterator();
        while (it.hasNext()) {
            Exemplar exemplar = it.next();
            resultado.add(exemplar.toString());
        }
        return resultado;
    }

    /**
     * Afegeix un usuari nou.
     *
     * @param email correu electrònic únic
     * @param nom nom de l'usuari
     * @param adreca adreça de l'usuari
     * @param esEstudiant true si és estudiant; false si és professor
     * @throws BiblioException si hi ha cap error de validació
     */
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    /**
     * Recupera tots els usuaris en format textual.
     *
     * @return llista de cadenes amb la informació dels usuaris
     */
    public List<String> recuperaUsuaris() {
        ArrayList<String> resultado = new ArrayList<>();
        Iterator<Usuari> it = dades.recuperaUsuaris().iterator();
        while (it.hasNext()) {
            Usuari usuari = it.next();
            resultado.add(usuari.toString());
        }
        return resultado;
    }

    /**
     * Afegeix un préstec nou.
     *
     * @param exemplarPos posició de l'exemplar
     * @param usuariPos posició de l'usuari
     * @param esLlarg true si el préstec és llarg
     * @throws BiblioException si no es compleixen les regles de negoci
     */
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    /**
     * Retorna un préstec existent.
     *
     * @param position posició del préstec a la llista
     * @throws BiblioException si el préstec no es pot retornar
     */
    public void retornarPrestec(int position) throws BiblioException {
        dades.retornarPrestec(position);
    }

    /**
     * Recupera tots els préstecs en format textual.
     *
     * @return llista de cadenes amb la informació dels préstecs
     */
    public List<String> recuperaPrestecs() {
        ArrayList<String> resultado = new ArrayList<>();
        Iterator<Prestec> it = dades.recuperaPrestecs().iterator();
        while (it.hasNext()) {
            Prestec prestec = it.next();
            resultado.add(prestec.toString());
        }
        return resultado;
    }

    /**
     * Recupera només els préstecs no retornats en format textual.
     *
     * @return llista de préstecs pendents de retorn
     */
    public List<String> recuperaPrestecsNoRetornats() {
        ArrayList<String> resultado = new ArrayList<>();
        Iterator<Prestec> it = dades.recuperaPrestecsNoRetornats().iterator();
        while (it.hasNext()) {
            Prestec p = it.next();
            resultado.add(p.toString());
        }
        return resultado;
    }

    /**
     * Guarda l'estat actual del model a un fitxer.
     *
     * @param camiDesti ruta del fitxer de destinació
     * @throws BiblioException si es produeix cap error d'entrada/sortida
     */
    public void guardaDades(String camiDesti) throws BiblioException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(camiDesti))) {
            oos.writeObject(dades);
        } catch (IOException e) {
            throw new BiblioException("Error en guardar dades: " + e.getMessage());
        }
    }

    /**
     * Carrega l'estat del model des d'un fitxer.
     *
     * @param camiOrigen ruta del fitxer d'origen
     * @throws BiblioException si es produeix cap error en la càrrega
     */
    public void carregaDades(String camiOrigen) throws BiblioException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(camiOrigen))) {
            Object obj = ois.readObject();
            this.dades = (Dades) obj;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            throw new BiblioException("Error en carregar dades: " + e.getMessage());
        }
    }
}
