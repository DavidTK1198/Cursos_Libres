/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import java.util.Objects;
/**
 *
 * @author DavidTK1198
 */

public class Especialidad {
    private int idEsp;
    private String nomesp;

    public Especialidad() {
    }

    public Especialidad(int idEsp, String nomesp) {
        this.idEsp = idEsp;
        this.nomesp = nomesp;
    }

    public int getIdEsp() {
        return idEsp;
    }

    public void setIdEsp(int idEsp) {
        this.idEsp = idEsp;
    }


    public String getNomesp() {
        return nomesp;
    }

    public void setNomesp(String nomesp) {
        this.nomesp = nomesp;
    } 

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Especialidad other = (Especialidad) obj;
        if (this.idEsp != other.idEsp) {
            return false;
        }
        if (!Objects.equals(this.nomesp, other.nomesp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Especialidad{" + "idEsp=" + idEsp + ", nomesp=" + nomesp + '}';
    }
    
}
