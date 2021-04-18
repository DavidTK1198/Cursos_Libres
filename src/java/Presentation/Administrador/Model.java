/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Administrador;

import Logic.Administrador;

/**
 *
 * @author Daniel Madrigal
 */
public class Model {
    private Administrador current;
        public Model() {
        this.setCurrent(new Administrador());
    }

    public final Administrador getCurrent() {
        return current;
    }

    public final void setCurrent(Administrador current) {
        this.current = current;
    }

    public void reset() {
        setCurrent(new Administrador());
    }
    
}
