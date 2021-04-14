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

import Logic.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

      public void create(Usuario o) throws Exception {
        String sql = "insert into Usuario (Rol,Id_Usu,Clave) "
                + "values(?,?)";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setString(1, o.getRol());
        stm.setInt(2, o.getIdUsu());
        stm.setString(3, o.getClave());
        int count = DataBase.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("El usuario ya existe");
        }
    }
      
        public List<Usuario> findAll() {
        List<Usuario> r = new ArrayList<>();
        String sql = "select * from Usuario";
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

    public List<Usuario> findByNombre(Usuario o) {
        List<Usuario> r = new ArrayList<>();
        String sql = "select * from Usuario where Id_Usu like ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setString(2, "%" +o.getIdUsu() + "%");
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }
    
    public Usuario read(int id) throws Exception{
        String sql="select * from Usuario where Id_Usu=?";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(2, id);
        ResultSet rs =  DataBase.instance().executeQuery(stm);           
        if (rs.next()) {
            System.out.println("Hola que tal");
            return from(rs);
        }
        else{
            throw new Exception ("El usuario no Existe");
        }
    }

    public Usuario from (ResultSet rs){
        try {
            Usuario r= new Usuario();
            r.setRol(rs.getString("Rol"));
            r.setIdUsu(rs.getInt("Id_Usu"));
            r.setClave(rs.getString("Clave"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    public  void close(){
    }
}