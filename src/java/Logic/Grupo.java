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
public class Grupo {

   
    private int numGrup;
    private String horario;
    private List<Estudiante> inscripcionList;
    private Profesor profesoridProfe;
    private Curso curso;
    public Grupo() {
        this.inscripcionList=new ArrayList<>();
        this.profesoridProfe=new Profesor();
        this.curso = new Curso();
        this.numGrup = 0;
        this.horario = "";
    }

    public Grupo(int numGrup, String horario,Curso cur) {
        this.numGrup = numGrup;
        this.horario = horario;
        this.inscripcionList=new ArrayList<>();
        this.profesoridProfe=new Profesor();
        this.curso = cur;
    }

    public int getNumGrup() {
        return numGrup;
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
        final Grupo other = (Grupo) obj;
        if (this.numGrup != other.numGrup) {
            return false;
        }
        if (!Objects.equals(this.horario, other.horario)) {
            return false;
        }
        if (!Objects.equals(this.inscripcionList, other.inscripcionList)) {
            return false;
        }
        if (!Objects.equals(this.profesoridProfe, other.profesoridProfe)) {
            return false;
        }
        return true;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Grupo{" + "numGrup=" + numGrup + ", horario=" + horario + ", inscripcionList=" + inscripcionList + ", profesoridProfe=" + profesoridProfe + '}';
    }

    public void setNumGrup(int numGrup) {
        this.numGrup = numGrup;
    }

    public List<Estudiante> getInscripcionList() {
        return inscripcionList;
    }

    public void setInscripcionList(List<Estudiante> inscripcionList) {
        this.inscripcionList = inscripcionList;
    }

 

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Profesor getProfesoridProfe() {
        return profesoridProfe;
    }

    public void setProfesoridProfe(Profesor profesoridProfe) {
        this.profesoridProfe = profesoridProfe;
    }


    
}
