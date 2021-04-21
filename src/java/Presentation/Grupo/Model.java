/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Grupo;

import Logic.Estudiante;
import Logic.Grupo;
import Logic.Profesor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Madrigal
 */
public class Model {
        private Grupo current;
        private Profesor encargado;
        private List<Profesor> profesores;
        private List<Estudiante> estudiantes;
    public Model() {
        this.reset();
    }

    public Grupo getCurrent() {
        return current;
    }

    public final void setCurrent(Grupo current) {
        this.current = current;
    }

    public void reset() {
       this.current=new Grupo();
       this.profesores=new ArrayList<>();
       this.encargado=new Profesor();
       this.estudiantes = new ArrayList<>();
    }

    public Profesor getEncargado() {
        return encargado;
    }

    public void setEncargado(Profesor encargado) {
        this.encargado = encargado;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
}
