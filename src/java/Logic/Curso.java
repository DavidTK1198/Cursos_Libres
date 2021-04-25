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
public class Curso  {
    private int  nrc;
    private String nomCur;
    private String desCur;
    private boolean oferta;
    private Float precio;
    private String tematica;

    public Curso() {
        this.nrc = 0;
        this.nomCur = "";
        this.desCur = "";
        this.oferta = false;
        this.precio = 0.0f;
        this.tematica = "";
    }
    public Curso(int nrc, String nomCur, String desCur, boolean oferta, Float precio,String tem) {
        this.nrc = nrc;
        this.nomCur = nomCur;
        this.desCur = desCur;
        this.oferta = oferta;
        this.precio = precio;
        this.tematica = tem;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
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
        final Curso other = (Curso) obj;
        if (this.nrc != other.nrc) {
            return false;
        }
        if (this.oferta != other.oferta) {
            return false;
        }
        if (!Objects.equals(this.nomCur, other.nomCur)) {
            return false;
        }
        if (!Objects.equals(this.desCur, other.desCur)) {
            return false;
        }
        if (!Objects.equals(this.precio, other.precio)) {
            return false;
        }
        return true;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }
    

    public String getNomCur() {
        return nomCur;
    }

    public void setNomCur(String nomCur) {
        this.nomCur = nomCur;
    }

    public String getDesCur() {
        return desCur;
    }

    public void setDesCur(String desCur) {
        this.desCur = desCur;
    }

    public boolean getOferta() {
        return oferta;
    }

    public void setOferta(boolean oferta) {
        this.oferta = oferta;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Curso{" + "nrc=" + nrc + ", nomCur=" + nomCur + ", desCur=" + desCur + ", oferta=" + oferta + ", precio=" + precio + '}';
    }


    
}
