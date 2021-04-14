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
public class Administrador  {
    private int idAdmin;
    private String nombreAdmin;
    private String claveAdmin;
    private Usuario usuarioIdUsu;

    public Administrador() {
    }

    public Administrador(int idAdmin, String nombreAdmin, String claveAdmin) {
        this.idAdmin = idAdmin;
        this.nombreAdmin = nombreAdmin;
        this.claveAdmin = claveAdmin;
        this.usuarioIdUsu=new Usuario();
    }

    
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getClaveAdmin() {
        return claveAdmin;
    }

    public void setClaveAdmin(String claveAdmin) {
        this.claveAdmin = claveAdmin;
    }

    public Usuario getUsuarioIdUsu() {
        return usuarioIdUsu;
    }

    public void setUsuarioIdUsu(Usuario usuarioIdUsu) {
        this.usuarioIdUsu = usuarioIdUsu;
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
        final Administrador other = (Administrador) obj;
        if (this.idAdmin != other.idAdmin) {
            return false;
        }
        if (!Objects.equals(this.nombreAdmin, other.nombreAdmin)) {
            return false;
        }
        if (!Objects.equals(this.claveAdmin, other.claveAdmin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Administrador{" + "idAdmin=" + idAdmin + ", nombreAdmin=" + nombreAdmin + ", claveAdmin=" + claveAdmin + '}';
    }
    
}
