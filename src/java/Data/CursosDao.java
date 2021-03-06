package Data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Logic.Curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

;

/**
 *
 * @author Daniel Madrigal
 */
public class CursosDao {

    public void create(Curso o) throws Exception {
        String sql = "insert into Curso (NRC,Nom_Cur,Des_Cur,Oferta,Precio,Tematica) "
                + "values(?,?,?,?,?,?)";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, o.getNrc());
        stm.setString(2, o.getNomCur());
        stm.setString(3, o.getDesCur());
        stm.setBoolean(4, o.getOferta());
        stm.setFloat(5, o.getPrecio());
         stm.setString(6, o.getTematica());
        int count = DataBase.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("El Curso ya existe");
        }
    }

    public List<Curso> findAll() {
        List<Curso> r = new ArrayList<>();
        String sql = "select * from Curso";
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
    
    public List<Curso> findxOferta() {
        List<Curso> r = new ArrayList<>();
        String sql = "select * from Curso where Oferta = 1";
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

    public List<Curso> findByNombre(String nrc) {
        List<Curso> r = new ArrayList<>();
        String sql = "select * from Curso where Nom_Cur like ? ";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setString(1, "%" + nrc + "%");
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }
     public List<Curso> findByTematica(String tem) {
        List<Curso> r = new ArrayList<>();
        String sql = "select * from Curso where Tematica like ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setString(1, "%" + tem + "%");
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }

    public Curso read(int nrc) throws Exception {
        String sql = "select * from Curso where NRC=?";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, nrc);
        ResultSet rs = DataBase.instance().executeQuery(stm);
        if (rs.next()) {
            return from(rs);
        } else {
            return null;
        }
    }

    public Curso from(ResultSet rs) {
        try {
            Curso r = new Curso();
            r.setNrc(rs.getInt("NRC"));
            r.setNomCur(rs.getString("Nom_Cur"));
            r.setDesCur(rs.getString("Des_Cur"));
            r.setOferta(rs.getBoolean("Oferta"));
            r.setPrecio(rs.getFloat("Precio"));
            r.setTematica(rs.getString("Tematica"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean updateStatus(boolean oferta, int nrc) {
        try {
            String sql = "update Curso set oferta = ? where NRC = ?";
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setInt(2, nrc);
            stm.setBoolean(1, oferta);
          
            DataBase.instance().executeUpdate(stm);
            return true;

        } catch (SQLException ex) {
            return false;
        }
    }

    public void close() {
    }
}
