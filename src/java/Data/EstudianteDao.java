package Data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logic.Estudiante;
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
public class EstudianteDao {
     public void create(Estudiante o) throws Exception {
        String sql = "insert into Estudiante (idEstudiante,Nom_Est,Tel_Est,Correo_Est,Usuario_Id_Usu) "
                + "values(?,?,?,?,?)";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, o.getIdEstudiante());
        stm.setString(2, o.getNomEst());
        stm.setString(3, o.getTelEst());
        stm.setString(4, o.getCorreoEst());
        stm.setInt(5, o.getUsuarioIdUsu().getIdUsu());
        int count = DataBase.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("El estudiante ya existe");
        }
    }
      
        public List<Estudiante> findAll() {
        List<Estudiante> r = new ArrayList<>();
        String sql = "select * from Estudiante";
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

    public List<Estudiante> findByNombre(Estudiante o) {
        List<Estudiante> r = new ArrayList<>();
        String sql = "select * from Estudiante where Nom_Est like ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setString(2, "%" +o.getIdEstudiante() + "%");
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }
    
    public Estudiante read(int id) throws Exception{
        String sql="select * from Estudiante where idEstudiante=?";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  DataBase.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            throw new Exception ("El Estudiante no Existe");
        }
    }

    public Estudiante from (ResultSet rs){
        try {
            Estudiante r= new Estudiante();
            r.setIdEstudiante(rs.getInt("idEstudiante"));
            r.setNomEst(rs.getString("Nom_Est"));
            r.setTelEst(rs.getString("Tel_Est"));
            r.setCorreoEst(rs.getString("Correo_Est"));
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