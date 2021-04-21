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
import Logic.Curso;
import Logic.Estudiante;
import Logic.Grupo;
import Logic.Profesor;
import Logic.Service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoDao {

    public void create(Grupo o) throws Exception {
        String sql = "insert into Grupo (Profesor_id_Profe,Horario,Curso_NRC) "
                + "values(?,?,?)";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, o.getProfesoridProfe().getIdProfe());//preguntar
        stm.setString(2, o.getHorario());
        stm.setInt(3, o.getCurso().getNrc());
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

    public List<Grupo> findByNombre(int id) {
        List<Grupo> r = new ArrayList<>();
        String sql = "select * from Grupo where Profesor_id_Profe like ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {
        }
        return r;
    }

    public List<Grupo> findByCurso(int n) {
        List<Grupo> r = new ArrayList<>();
        String sql = "select * from Grupo where Curso_NRC = ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setInt(1, n);
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                r.add(from(rs));
            }
        } catch (SQLException ex) {

        }
        return r;
    }

    public Grupo read(int num) throws Exception {
        String sql = "select * from Grupo where num_Grup=?";
        PreparedStatement stm = DataBase.instance().prepareStatement(sql);
        stm.setInt(1, num);
        ResultSet rs = DataBase.instance().executeQuery(stm);
        if (rs.next()) {
            System.out.println("Hola que tal");
            return from(rs);
        } else {
            return null;
        }
    }

    public List<Estudiante> buscarNota(int numGrup, Profesor pr) throws Exception {
        List<Estudiante> r = new ArrayList<>();
        String sql = "select * from Inscripcion where Grupo_num_Grup = ?";
        try {
            PreparedStatement stm = DataBase.instance().prepareStatement(sql);
            stm.setInt(1, numGrup);
            ResultSet rs = DataBase.instance().executeQuery(stm);
            while (rs.next()) {
                Estudiante est = findbyGroup(rs, pr);
                if (est != null) {
                    r.add(est);
                }

            }
        } catch (SQLException ex) {
        }
        return r;
    }

    private Estudiante findbyGroup(ResultSet rs, Profesor p) throws SQLException, Exception {
        int grupo = rs.getInt("Grupo_num_Grup");
        int estudiante = rs.getInt("Estudiante_id");
        Grupo gr = read(grupo);
        if (gr.getProfesoridProfe().getIdProfe() == p.getIdProfe()) {
            Estudiante est = Service.getInstance().buscarEstudiante(estudiante);
            return est;
        }
        return null;

    }

    public Grupo from(ResultSet rs) {
        try {
            Grupo r = new Grupo();
            r.setNumGrup(rs.getInt("num_Grup"));
            String id = rs.getString("Profesor_id_Profe");
            int idd = Integer.parseInt(id);
            Profesor pr = Service.getInstance().buscarProfesor(idd);
            r.setProfesoridProfe(pr);
            r.setHorario(rs.getString("Horario"));
            String nrc = rs.getString("Curso_NRC");
            int nrcc = Integer.parseInt(nrc);
            Curso cursito = Service.getInstance().buscarCurso(nrcc);
            r.setCurso(cursito);

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
