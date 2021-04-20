/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Grupo;

import Logic.Grupo;

/**
 *
 * @author Daniel Madrigal
 */
public class Model {
        Grupo current;

    public Model() {
        this.setCurrent(new Grupo());
    }

    public Grupo getCurrent() {
        return current;
    }

    public final void setCurrent(Grupo current) {
        this.current = current;
    }

    public void reset() {
        setCurrent(new Grupo());
    }
}
