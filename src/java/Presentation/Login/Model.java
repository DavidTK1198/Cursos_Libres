/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Login;

import Logic.Curso;
import Logic.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private Usuario current;
    private List<Curso> cursos;
    

    public Model() {
        reset();
    }

    public final Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
    }

    private void reset() {
        setCurrent(new Usuario());
        this.setCursos(new ArrayList<>());
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    
    
}
