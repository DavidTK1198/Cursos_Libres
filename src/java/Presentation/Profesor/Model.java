/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Profesor;

import Logic.Curso;
import Logic.Grupo;
import Logic.Profesor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Madrigal
 */
public class Model {

    private Profesor current;
    private List<Grupo> mios;
    private List<Curso> misCursos;

    public Model() {
        this.reset();
    }

    public final Profesor getCurrent() {
        return current;
    }

    public void setCurrent(Profesor current) {
        this.current = current;
    }

    public void reset() {
        setCurrent(new Profesor());
        this.setMios(new ArrayList<>());
        this.setMisCursos(new ArrayList<>());
        
    }

    public List<Grupo> getMios() {
        return mios;
    }

    public void setMios(List<Grupo> mios) {
        this.mios = mios;
    }

    public List<Curso> getMisCursos() {
        return misCursos;
    }

    public void setMisCursos(List<Curso> misCursos) {
        this.misCursos = misCursos;
    }
    
    
}
