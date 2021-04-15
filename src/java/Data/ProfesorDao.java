package Data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logic.Profesor;
import Logic.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;;

/**
 *
 * @author Daniel Madrigal
 */
public class ProfesorDao {
     public void create(Profesor o) throws Exception {
        String sql = "insert into Profesor (id_Profe,Nom_Profe,Tel_Profe,Correo_Profe,Usuario_Id_Usu) "
                + "values(?,?,?,?,?)";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, o.getIdProfe());
        stm.setString(2, o.getNomProfe());
        stm.setString(3, o.getTelProfe());
        stm.setString(4, o.getCorreoProfe());
        stm.setInt(5, o.getUsuarioIdUsu().getIdUsu());
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

    public List<Profesor> findByNombre(Profesor o) {
        List<Profesor> r = new ArrayList<>();
        String sql = "select * from Profesor where Id_Profe like ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setString(2, "%" +o.getIdProfe() + "%");
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
            throw new Exception ("El Profesor no Existe");
        }
    }

    public Profesor from (ResultSet rs){
        try {
            Profesor r= new Profesor();
            r.setIdProfe(rs.getInt("id_Profe"));
            r.setNomProfe(rs.getString("Nom_Profe"));
            r.setTelProfe(rs.getString("Tel_Profe"));
            r.setCorreoProfe(rs.getString("Correo_Profe"));
            Object us = rs.getObject(5);
            Usuario usario = (Usuario)us;
            r.setUsuarioIdUsu(usario);
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    public  void close(){
    }
}
