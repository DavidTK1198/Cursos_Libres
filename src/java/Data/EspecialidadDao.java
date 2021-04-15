package Data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author DavidTK1198
 */

import Logic.Especialidad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EspecialidadDao {

      public void create(Especialidad o) throws Exception {
        String sql = "insert into Especialidad (id_esp,Nom_esp) "
                + "values(?,?)";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, o.getIdEsp());
        stm.setString(2, o.getNomesp());
        int count = DataBase.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("La especialidad ya existe");
        }
    }
      
        public List<Especialidad> findAll() {
        List<Especialidad> r = new ArrayList<>();
        String sql = "select * from Especialidad";
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

    public List<Especialidad> findByNombre(Especialidad o) {
        List<Especialidad> r = new ArrayList<>();
        String sql = "select * from Especialidad where Nom_esp like ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setString(2, "%" +o.getNomesp() + "%");
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }
    
    public Especialidad read(int id) throws Exception{
        String sql="select * from Especialidad where id_esp=?";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  DataBase.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            throw new Exception ("La especialidad no Existe");
        }
    }

    public Especialidad from (ResultSet rs){
        try {
            Especialidad r= new Especialidad();
            r.setIdEsp(rs.getInt("id_esp"));
            r.setNomesp(rs.getString("Nom_esp"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    public  void close(){
    }
}