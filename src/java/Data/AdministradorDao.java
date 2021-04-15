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

import Logic.Administrador;
import Logic.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdministradorDao {

      public void create(Administrador o) throws Exception {
        String sql = "insert into Administrador (id_admin,nombre_admin,clave_admin,Usuario_Id_Usu) "
                + "values(?,?,?,?)";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, o.getIdAdmin());
        stm.setString(2, o.getNombreAdmin());
        stm.setString(3, o.getClaveAdmin());
         stm.setInt(4, o.getUsuarioIdUsu().getIdUsu());
        int count = DataBase.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("El usuario administrador ya existe");
        }
    }
      
        public List<Administrador> findAll() {
        List<Administrador> r = new ArrayList<>();
        String sql = "select * from Administrador";
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

    public List<Administrador> findByNombre(Administrador o) {
        List<Administrador> r = new ArrayList<>();
        String sql = "select * from Administrador where nombre_admin like ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setString(2, "%" +o.getNombreAdmin() + "%");
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }
    
    public Administrador read(int id) throws Exception{
        String sql="select * from Administrador where id_admin=?";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs =  DataBase.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            throw new Exception ("El usuario administrador no Existe");
        }
    }

    public Administrador from (ResultSet rs){
        try {
            Administrador r= new Administrador();
            r.setIdAdmin(rs.getInt("id_admin"));
            r.setNombreAdmin(rs.getString("nombre_admin"));
            r.setClaveAdmin(rs.getString("clave_admin"));
            r.setUsuarioIdUsu((Usuario)rs.getObject("Usuario_Id_Usu"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    public  void close(){
    }
}