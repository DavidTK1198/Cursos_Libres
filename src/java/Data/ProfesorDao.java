package Data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logic.Profesor;
import Logic.Service;
import Logic.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;import java.util.logging.Level;
import java.util.logging.Logger;
;

/**
 *
 * @author Daniel Madrigal
 */
public class ProfesorDao {
     public void create(Profesor o) throws Exception {
        String sql = "insert into Profesor (id_Profe,Nom_Profe,Tel_Profe,Correo_Profe,Usuario_Id_Usu, Especialidad) "
                + "values(?,?,?,?,?,?)";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, o.getIdProfe());
        stm.setString(2, o.getNomProfe());
        stm.setString(3, o.getTelProfe());
        stm.setString(4, o.getCorreoProfe());
        stm.setInt(5, o.getUsuarioIdUsu().getIdUsu());
        stm.setString(6, o.getEspecialidad());
        int count = DataBase.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("El profesor ya existe");
        }
    }
      
        public List<Profesor> findAll() {
        List<Profesor> r = new ArrayList<>();
        String sql = "select * from Profesor";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }

    public List<Profesor> findByNombre(String atributo) {
        List<Profesor> r = new ArrayList<>();
        String sql = "select * from Profesor where Nom_Profe like ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setString(1, "%" +atributo + "%");
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }
    
    public Profesor read(int id) throws Exception{
        String sql="select * from Profesor where Id_Profe=?";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  DataBase.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            return null;
        }
    }

    public Profesor from (ResultSet rs){
        try {
            Profesor r= new Profesor();
            r.setIdProfe(rs.getInt("id_Profe"));
            r.setNomProfe(rs.getString("Nom_Profe"));
            r.setTelProfe(rs.getString("Tel_Profe"));
            r.setCorreoProfe(rs.getString("Correo_Profe"));
            int us = rs.getInt(5);
            Usuario es;
            try {
                es = Service.getInstance().buscarUsuario(us);
            } catch (Exception ex) {
                return null;
            }
            r.setUsuarioIdUsu(es);
            r.setEspecialidad(rs.getString("Especialidad"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    public  void close(){
    }
}
