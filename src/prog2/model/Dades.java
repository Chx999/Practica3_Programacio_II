package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.ArrayList;

public class Dades implements InDades, Serializable {
    private final LlistaExemplars exemplars;
    private final LlistaUsuaris usuaris;
    private final LlistaPrestecs prestecs;

    public Dades() {
        this.exemplars = new LlistaExemplars();
        this.usuaris = new LlistaUsuaris();
        this.prestecs = new LlistaPrestecs();
    }

    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        throw new UnsupportedOperationException("TO-DO");
    }

    @Override
    public ArrayList<Exemplar> recuperaExemplars() {
        return exemplars.getArrayList();
    }

    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        throw new UnsupportedOperationException("TO-DO");
    }

    @Override
    public ArrayList<Usuari> recuperaUsuaris() {
        return usuaris.getArrayList();
    }

    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        throw new UnsupportedOperationException("TO-DO");
    }

    @Override
    public void retornarPrestec(int position) throws BiblioException {
        throw new UnsupportedOperationException("TO-DO");
    }

    @Override
    public ArrayList<Prestec> recuperaPrestecs() {
        return prestecs.getArrayList();
    }

    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats() {
        return new ArrayList<>();
    }
}
