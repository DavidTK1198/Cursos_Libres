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
    private Curso current;

    public Model() {
        this.reset();
    }

    public final Curso getCurrent() {
        return current;
    }

    public final void setCurrent(Curso current) {
        this.current = current;
    }

    private void reset() {
        setCurrent(new Curso());
    }
    
}
