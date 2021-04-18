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
public class Usuario{
    private String rol;
    private int idUsu;
    private String clave;

    public Usuario() {
        rol = "";
        idUsu = 0;
        clave = "";
    }

    public Usuario(String rol, int idUsu, String clave) {
        this.rol = rol;
        this.idUsu = idUsu;
        this.clave = clave;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    @Override
    public String toString() {
        return "Usuario{" + "rol=" + rol + ", idUsu=" + idUsu + ", clave=" + clave + '}';
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
        final Usuario other = (Usuario) obj;
        if (this.idUsu != other.idUsu) {
            return false;
        }
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        return true;
    }
    public void generarClave(){
        String contra = "";
        int n =3;
        String p = "";
        for(int i=0; i<5; i++){
            n =(int) (Math.random() * n) + 1;
            p = Integer.toString(n);
            contra = contra.concat(p);
        }
        this.setClave(contra);
    }

 
  

    

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


    
}
