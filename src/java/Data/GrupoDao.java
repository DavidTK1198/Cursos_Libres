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

import Logic.Grupo;
import Logic.Profesor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GrupoDao {

      public void create(Grupo o) throws Exception {
        String sql = "insert into Grupo (num_Grupo,Profesor_id_Profe,Horario) "
                + "values(?,?,?)";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, o.getNumGrup());
        stm.setInt(2, o.getProfesoridProfe().getIdProfe());//preguntar
        stm.setString(3, o.getHorario());
        int count = DataBase.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("El grupo ya existe");
        }
    }
      
        public List<Grupo> findAll() {
        List<Grupo> r = new ArrayList<>();
        String sql = "select * from Grupo";
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

    public List<Grupo> findByNombre(Grupo o) {
        List<Grupo> r = new ArrayList<>();
        String sql = "select * from Grupo where Profesor_id_Profe like ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setString(2, "%" +o.getProfesoridProfe().getIdProfe() + "%");
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }
    
    public Grupo read(int num) throws Exception{
        String sql="select * from Grupo where num_Grup=?";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, num);
        ResultSet rs =  DataBase.instance().executeQuery(stm);           
        if (rs.next()) {
            System.out.println("Hola que tal");
            return from(rs);
        }
        else{
            throw new Exception ("El usuario no Existe");
        }
    }

    public Grupo from (ResultSet rs){
        try {
            Grupo r= new Grupo();
            r.setNumGrup(rs.getInt("num_Grup"));
            r.setProfesoridProfe((Profesor)rs.getObject("Profesor_id_Profe"));
            r.setHorario(rs.getString("Horario"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    public  void close(){
    }
}