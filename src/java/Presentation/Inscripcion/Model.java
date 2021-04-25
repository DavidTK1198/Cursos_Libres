/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Inscripcion;

import Logic.Curso;
import Logic.Estudiante;
import Logic.Grupo;
import Logic.Inscripcion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Madrigal
 */
public class Model {
     private Estudiante current;
     private List<Grupo> grupito;
     private Inscripcion inscrip;
     private List<Inscripcion> inscripciones;
     private Grupo grupo;
     private List<Curso> lc;

    public Model() {
        this.reset();
    }

    public final Estudiante getCurrent() {
        return current;
    }

    public final void setCurrent(Estudiante current) {
        this.current = current;
    }

    public void reset() {
        setCurrent(new Estudiante());
        this.setGrupito(new ArrayList<>());
        this.setGrupo(new Grupo());
        this.setInscrip(new Inscripcion());
        this.setInscripciones(new ArrayList<>());
        this.setLc(new ArrayList<>());
       
    }

    public List<Curso> getLc() {
        return lc;
    }

    public void setLc(List<Curso> lc) {
        this.lc = lc;
    }

    public List<Grupo> getGrupito() {
        return grupito;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
    

    public void setGrupito(List<Grupo> grupito) {
        this.grupito = grupito;
    }

    public Inscripcion getInscrip() {
        return inscrip;
    }

    public void setInscrip(Inscripcion inscrip) {
        this.inscrip = inscrip;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
}
