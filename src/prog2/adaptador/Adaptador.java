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

public class Adaptador {
    private Dades dades;

    public Adaptador() {
        this.dades = new Dades();
    }

    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

    public List<String> recuperaExemplars() {
        ArrayList<String> result = new ArrayList<>();
        Iterator<Exemplar> it = dades.recuperaExemplars().iterator();
        while (it.hasNext()) {
            Exemplar e = it.next();
            result.add(e.toString());
        }
        return result;
    }

    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    public List<String> recuperaUsuaris() {
        ArrayList<String> result = new ArrayList<>();
        Iterator<Usuari> it = dades.recuperaUsuaris().iterator();
        while (it.hasNext()) {
            Usuari u = it.next();
            result.add(u.toString());
        }
        return result;
    }

    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    public void retornarPrestec(int position) throws BiblioException {
        dades.retornarPrestec(position);
    }

    public List<String> recuperaPrestecs() {
        ArrayList<String> result = new ArrayList<>();
        Iterator<Prestec> it = dades.recuperaPrestecs().iterator();
        while (it.hasNext()) {
            Prestec p = it.next();
            result.add(p.toString());
        }
        return result;
    }

    public List<String> recuperaPrestecsNoRetornats() {
        ArrayList<String> result = new ArrayList<>();
        Iterator<Prestec> it = dades.recuperaPrestecsNoRetornats().iterator();
        while (it.hasNext()) {
            Prestec p = it.next();
            result.add(p.toString());
        }
        return result;
    }

    public void guardaDades(String camiDesti) throws BiblioException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(camiDesti))) {
            oos.writeObject(dades);
        } catch (IOException e) {
            throw new BiblioException("Error en guardar dades: " + e.getMessage());
        }
    }

    public void carregaDades(String camiOrigen) throws BiblioException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(camiOrigen))) {
            Object obj = ois.readObject();
            this.dades = (Dades) obj;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            throw new BiblioException("Error en carregar dades: " + e.getMessage());
        }
    }
}
