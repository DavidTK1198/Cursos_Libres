/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Estudiante;

import Logic.Estudiante;

/**
 *
 * @author Daniel Madrigal
 */
public class Model {
        Estudiante current;

    public Model() {
        this.setCurrent(new Estudiante());
    }

    public final Estudiante getCurrent() {
        return current;
    }

    public final void setCurrent(Estudiante current) {
        this.current = current;
    }

    public void reset() {
        setCurrent(new Estudiante());
    }
    
}
