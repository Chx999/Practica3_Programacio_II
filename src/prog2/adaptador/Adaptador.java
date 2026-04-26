package prog2.adaptador;

import prog2.model.Dades;
import prog2.model.Exemplar;
import prog2.model.Prestec;
import prog2.model.Usuari;
import prog2.vista.BiblioException;

import java.util.ArrayList;
import java.util.List;

public class Adaptador {
    private Dades dades;

    public Adaptador() {
        this.dades = new Dades();
    }

    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        throw new UnsupportedOperationException("TO-DO");
    }

    public List<String> recuperaExemplars() {
        ArrayList<String> result = new ArrayList<>();
        for (Exemplar e : dades.recuperaExemplars()) {
            result.add(e.toString());
        }
        return result;
    }

    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        throw new UnsupportedOperationException("TO-DO");
    }

    public List<String> recuperaUsuaris() {
        ArrayList<String> result = new ArrayList<>();
        for (Usuari u : dades.recuperaUsuaris()) {
            result.add(u.toString());
        }
        return result;
    }

    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        throw new UnsupportedOperationException("TO-DO");
    }

    public void retornarPrestec(int position) throws BiblioException {
        throw new UnsupportedOperationException("TO-DO");
    }

    public List<String> recuperaPrestecs() {
        ArrayList<String> result = new ArrayList<>();
        for (Prestec p : dades.recuperaPrestecs()) {
            result.add(p.toString());
        }
        return result;
    }

    public List<String> recuperaPrestecsNoRetornats() {
        ArrayList<String> result = new ArrayList<>();
        for (Prestec p : dades.recuperaPrestecsNoRetornats()) {
            result.add(p.toString());
        }
        return result;
    }

    public void guardaDades(String camiDesti) throws BiblioException {
        throw new UnsupportedOperationException("TO-DO");
    }

    public void carregaDades(String camiOrigen) throws BiblioException {
        throw new UnsupportedOperationException("TO-DO");
    }
}
