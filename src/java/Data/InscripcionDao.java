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
import Logic.Estudiante;
import Logic.Grupo;
import Logic.Inscripcion;
import Logic.Service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDao {

    public void create(Inscripcion o) throws Exception {
        String sql = "insert into Inscripcion (Estudiante_id,Grupo_num_Grup,Nota) "
                + "values(?,?,?)";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, o.getEstudiante().getIdEstudiante());
        stm.setInt(2, o.getGruponumGrup().getNumGrup());
        stm.setFloat(3, o.getNota());
        int count = DataBase.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("La incripcion ya existe");
        }
    }

    public List<Inscripcion> findAll() {
        List<Inscripcion> r = new ArrayList<>();
        String sql = "select * from Inscripcion";
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

    public List<Inscripcion> findByNombre(int idest, int numGrup) {
        List<Inscripcion> r = new ArrayList<>();
        String sql = "select * from Inscripcion where Grup_num_Grup = ? AND Estudiante_id = ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setInt(1, numGrup);
            stm.setInt(2, idest);
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }

    public List<Inscripcion> findByEstudiante(int idest) {
        List<Inscripcion> r = new ArrayList<>();
        String sql = "select * from Inscripcion where Estudiante_id = ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setInt(1, idest);
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                Inscripcion ins = from(rs);
                if (ins != null) {
                    r.add(ins);
                }
            }
        } catch (SQLException ex) {
        }
        return r;
    }

    public Inscripcion read(int id) throws Exception {
        String sql = "select * from Inscripcion where Estudiante_id=?";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(4, id);
        ResultSet rs = DataBase.instance().executeQuery(stm);
        if (rs.next()) {
            return from(rs);
        } else {
            throw new Exception("La inscripcion no Existe");
        }
    }

    public boolean actualizarNota(int idEst, int nota,int g) throws Exception {
        String sql = "update inscripcion set Nota=? where Estudiante_id=? AND Grupo_num_Grup = ?";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, nota);
        stm.setInt(2, idEst);
        stm.setInt(3, g);
        int count = DataBase.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Error al cargar la nota");
        }
        return true;
    }

    public Inscripcion from(ResultSet rs) {
        try {
            Inscripcion r = new Inscripcion();
            String gr = rs.getString("Grupo_num_Grup");
            int numero = Integer.parseInt(gr);
            Grupo gru = Service.getInstance().buscarGrupo(numero);
            r.setGruponumGrup(gru);
            String idd = rs.getString("Estudiante_id");
            int id = Integer.parseInt(idd);
            Estudiante est = Service.getInstance().buscarEstudiante(id);
            r.setGruponumGrup(gru);
            r.setEstudiante(est);
            r.setNota(rs.getFloat("Nota"));

            return r;
        } catch (SQLException ex) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    public void close() {
    }
}
