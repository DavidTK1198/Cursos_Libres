/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author DavidTK1198
 */
public class Profesor  {

    private int idProfe;
    private String nomProfe;
    private String telProfe;
    private String correoProfe;
    private List<Grupo> grupoList;
    private Usuario usuarioIdUsu;

    public Profesor(int idProfe, String nomProfe, String telProfe, String correoProfe) {
        this.idProfe = idProfe;
        this.nomProfe = nomProfe;
        this.telProfe = telProfe;
        this.correoProfe = correoProfe;
        this.usuarioIdUsu=new Usuario();
        this.grupoList=new ArrayList<>();
    }

    public Profesor() {
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
        final Profesor other = (Profesor) obj;
        if (this.idProfe != other.idProfe) {
            return false;
        }
        if (!Objects.equals(this.nomProfe, other.nomProfe)) {
            return false;
        }
        if (!Objects.equals(this.telProfe, other.telProfe)) {
            return false;
        }
        if (!Objects.equals(this.correoProfe, other.correoProfe)) {
            return false;
        }
        return true;
    }

    public int getIdProfe() {
        return idProfe;
    }

    public void setIdProfe(int idProfe) {
        this.idProfe = idProfe;
    }
    
    
    public String getNomProfe() {
        return nomProfe;
    }

    public void setNomProfe(String nomProfe) {
        this.nomProfe = nomProfe;
    }

    public String getTelProfe() {
        return telProfe;
    }

    public void setTelProfe(String telProfe) {
        this.telProfe = telProfe;
    }

    public String getCorreoProfe() {
        return correoProfe;
    }

    public void setCorreoProfe(String correoProfe) {
        this.correoProfe = correoProfe;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    public Usuario getUsuarioIdUsu() {
        return usuarioIdUsu;
    }

    public void setUsuarioIdUsu(Usuario usuarioIdUsu) {
        this.usuarioIdUsu = usuarioIdUsu;
    }

    
}
