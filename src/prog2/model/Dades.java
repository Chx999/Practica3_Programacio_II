package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Dades implements InDades, Serializable {
    private LlistaExemplars exemplars;
    private LlistaUsuaris usuaris;
    private LlistaPrestecs prestecs;

    public Dades() {
        this.exemplars = new LlistaExemplars();
        this.usuaris = new LlistaUsuaris();
        this.prestecs = new LlistaPrestecs();
    }

    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        Exemplar exemplar = new Exemplar(id, titol, autor, admetPrestecLlarg);
        this.exemplars.afegir(exemplar);
    }

    @Override
    public ArrayList<Exemplar> recuperaExemplars() {
        return exemplars.getArrayList();
    }

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

    @Override
    public ArrayList<Usuari> recuperaUsuaris() {
        return usuaris.getArrayList();
    }

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
           if (aux.getUsuari().equals(usuari) && aux.prestecEndarrerit()){
              throw new BootstrapMethodError("L'usuari te presctecs endarrerits");
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
           usuari.setNumPrestecsNormals(usuari.getMaxPrestecsNormals() + 1);
        }


    }

    @Override
    public void retornarPrestec(int position) throws BiblioException {
        this.prestecs.getAt(position).retorna();
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
