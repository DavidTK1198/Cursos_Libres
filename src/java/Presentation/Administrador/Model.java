/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Administrador;

import Logic.Administrador;
import Logic.Curso;
import Logic.Profesor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Madrigal
 */
public class Model {
    private Administrador current;
     private List<Profesor> profesores;
     private List<Curso> cursos;

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
        public Model() {
       this.reset();
    }

    public final Administrador getCurrent() {
        return current;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public final void setCurrent(Administrador current) {
        this.current = current;
    }

    private void reset() {
        setCurrent(new Administrador());
         this.profesores=new ArrayList<>();
         this.cursos=new ArrayList<>();
    }
    
}
