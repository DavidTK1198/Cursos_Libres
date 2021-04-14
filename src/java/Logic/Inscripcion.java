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
public class Inscripcion  {
    private float nota;
    private Estudiante estudiante;
    private Grupo gruponumGrup;
    private int Sec_inscripcion;

    public Inscripcion() {
        
        this.estudiante=new Estudiante();
        this.gruponumGrup=new Grupo();
        this.Sec_inscripcion = 0;
    }

    public int getSec_inscripcion() {
        return Sec_inscripcion;
    }

    public void setSec_inscripcion(int Sec_inscripcion) {
        this.Sec_inscripcion = Sec_inscripcion;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Inscripcion(float nota, Estudiante estudiante, Grupo gruponumGrup) {
        this.nota = nota;
        this.estudiante = estudiante;
        this.gruponumGrup = gruponumGrup;
        this.Sec_inscripcion = 0;
    }
   
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Grupo getGruponumGrup() {
        return gruponumGrup;
    }

    public void setGruponumGrup(Grupo gruponumGrup) {
        this.gruponumGrup = gruponumGrup;
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
        final Inscripcion other = (Inscripcion) obj;
        if (Float.floatToIntBits(this.nota) != Float.floatToIntBits(other.nota)) {
            return false;
        }
        if (!Objects.equals(this.estudiante, other.estudiante)) {
            return false;
        }
        if (!Objects.equals(this.gruponumGrup, other.gruponumGrup)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "nota=" + nota + ", estudiante=" + estudiante + ", gruponumGrup=" + gruponumGrup + '}';
    }
    
    
}
