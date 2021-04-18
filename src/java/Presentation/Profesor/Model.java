/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Profesor;

import Logic.Profesor;

/**
 *
 * @author Daniel Madrigal
 */
public class Model {

    Profesor current;

    public Model() {
        this.setCurrent(new Profesor());
    }

    public final Profesor getCurrent() {
        return current;
    }

    public void setCurrent(Profesor current) {
        this.current = current;
    }

    public void reset() {
        setCurrent(new Profesor());
    }
}
