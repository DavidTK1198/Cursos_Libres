/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DavidTK1198
 */

public class Estudiante  {
    private int idEstudiante;
    private String nomEst;
    private String telEst;
    private String correoEst;
    private Usuario usuarioIdUsu;
    private List<Inscripcion> inscripcion;

    public Estudiante() {
        this.idEstudiante = 0;
        this.nomEst = "";
        this.telEst = "";
        this.correoEst = "";
        this.usuarioIdUsu = new Usuario();
        this.inscripcion = new ArrayList<>();
    }

    public Estudiante(int idEstudiante, String nomEst, String telEst, String correoEst) {
        this.idEstudiante = idEstudiante;
        this.nomEst = nomEst;
        this.telEst = telEst;
        this.correoEst = correoEst;
        this.inscripcion=new ArrayList<>();
        this.usuarioIdUsu=new Usuario();
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNomEst() {
        return nomEst;
    }

    public List<Inscripcion> getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(List<Inscripcion> inscripcion) {
        this.inscripcion = inscripcion;
    }

    public void setNomEst(String nomEst) {
        this.nomEst = nomEst;
    }

    public String getTelEst() {
        return telEst;
    }

    public void setTelEst(String telEst) {
        this.telEst = telEst;
    }

    public String getCorreoEst() {
        return correoEst;
    }

    public void setCorreoEst(String correoEst) {
        this.correoEst = correoEst;
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
        final Estudiante other = (Estudiante) obj;
        if (this.idEstudiante != other.idEstudiante) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " ";
    }
    public void eliminarInscripcion(int n){
       
        Inscripcion ins;
        for(int i=0; i<this.inscripcion.size();i++){
            ins = inscripcion.get(i);
            if(ins.getGruponumGrup().getNumGrup()!=n){
                inscripcion.remove(i);
            }
        }
    }
    
}
