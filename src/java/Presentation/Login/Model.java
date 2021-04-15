/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Login;

import Logic.Usuario;

public class Model {

    Usuario current;

    public Model() {
        this.setCurrent(new Usuario());
    }

    public final Usuario getCurrent() {
        return current;
    }

    public final void setCurrent(Usuario current) {
        this.current = current;
    }

    public void reset() {
        setCurrent(new Usuario());
    }
}
