/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Estudiante;

import Logic.Curso;
import Logic.Estudiante;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Madrigal
 */
public class Model {
      private Estudiante current;
      private List<Curso> cursosMios;
      
    public Model() {
        this.reset();
        
    }

    public final Estudiante getCurrent() {
        return current;
    }

    public final void setCurrent(Estudiante current) {
        this.current = current;
    }

    private void reset() {
        setCurrent(new Estudiante());
        this.setCursosMios(new ArrayList<>());
    }

    public List<Curso> getCursosMios() {
        return cursosMios;
    }

    public void setCursosMios(List<Curso> cursosMios) {
        this.cursosMios = cursosMios;
    }
    
    
}
