/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Curso;

import Logic.Curso;

/**
 *
 * @author Daniel Madrigal
 */
public class Model {
    Curso current;

    public Model() {
        this.setCurrent(new Curso());
    }

    public final Curso getCurrent() {
        return current;
    }

    public final void setCurrent(Curso current) {
        this.current = current;
    }

    public void reset() {
        setCurrent(new Curso());
    }
    
}
