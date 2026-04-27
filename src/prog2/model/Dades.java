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

        if (esLlarg && exemplar.isDisponible()){
             prestec = new PrestecLlarg(exemplar,usuari,new Date());
        }else{
             prestec = new PrestecNormal(exemplar,usuari,new Date());
        }

       if (!exemplar.isDisponible()) {
           throw new BiblioException("L'exemplar no esta disponible");
       }else{
           if (esLlarg){
               if (exemplar.getAdmetPrestecLlarg()){
                   prestec = new PrestecLlarg(exemplar,usuari,new Date());
               }else{
                   throw new BiblioException("L'exemplar no s'admet prestec llarg");
               }
           }else{
               prestec = new PrestecNormal(exemplar,usuari,new Date());
           }
       }

        prestecs.afegir(prestec);
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
