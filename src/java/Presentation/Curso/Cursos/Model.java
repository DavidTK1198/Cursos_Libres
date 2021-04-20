/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Curso.Cursos;

import Logic.Curso;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Escinf
 */
public class Model{
    List<Curso> Cursos;
    Curso seleccionado;

    public Model() {
        this.reset();
    }

    public void reset(){ 
        List<Curso> rows = new ArrayList<>();        
        seleccionado=null;  
        this.setCursos(rows);
    }
    
    public void setCursos(List<Curso> Cursos){
        this.Cursos =Cursos;    
    }

     public List<Curso> getCursos() {
        return Cursos;
    }

    public Curso getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Curso seleccionado) {
        this.seleccionado = seleccionado;
    }
}
