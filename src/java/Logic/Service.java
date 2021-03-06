package Logic;

import Data.CursosDao;
import Data.EstudianteDao;
import Data.GrupoDao;
import Data.InscripcionDao;
import Data.ProfesorDao;
import Data.UserDao;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DavidTK1198
 */
public class Service {

    private static Service my_instance = null; //Singleton
    //private PDFMaker my_pdf;
    UserDao users;
    CursosDao cursos;
    GrupoDao grupos;
    ProfesorDao profesores;
    InscripcionDao inscripciones;
    EstudianteDao estudiantes;

    public Service() {
        users = new UserDao();
        cursos = new CursosDao();
        grupos = new GrupoDao();
        profesores = new ProfesorDao();
        estudiantes = new EstudianteDao();
        inscripciones = new InscripcionDao();

    }

    public static Service getInstance() {
        if (my_instance == null) {
            my_instance = new Service();
        }

        return my_instance;
    }
    
        public void resize(File original, File nueva, int width, int height) throws Exception {
        BufferedImage original1 = ImageIO.read(original);
        BufferedImage nueva1 = new BufferedImage(width, height, original1.getType());
        Graphics2D g2 = nueva1.createGraphics();
        g2.drawImage(original1, 0, 0, width, height, null);
        g2.dispose();
        ImageIO.write(nueva1, "png", nueva);
    }


    public Usuario login(Usuario u) throws Exception {
        Usuario result = users.read(u.getIdUsu());
        if (result == null) {
            throw new Exception("Usuario no existe");
        }
        if (!result.getClave().equals(u.getClave())) {
            throw new Exception("User does not exist");
        }
        return result;
    }

    public void agregarCurso(Curso cursito) throws Exception {
        cursos.create(cursito);
    }

    public Curso buscarCurso(int id) throws Exception {
        Curso cur = cursos.read(id);
        return cur;
    }

    public boolean actualizarStatusCurso(boolean ofer, int nrc) throws Exception {
        boolean b = cursos.updateStatus(ofer, nrc);
        return b;
    }

    public void agregarGrupo(Grupo Grupito) throws Exception {
        grupos.create(Grupito);
    }

    public Grupo buscarGrupo(int id) throws Exception {
        Grupo grup = grupos.read(id);
        return grup;
    }

    public void agregarProfesor(Profesor pro) throws Exception {
        profesores.create(pro);
    }

    public void agregarInscripcion(Inscripcion ins) throws Exception {
        inscripciones.create(ins);
    }

    public Profesor buscarProfesor(int id) throws Exception {
        Profesor pr = profesores.read(id);
        return pr;
    }

    public void agregarUsuario(Usuario usu) throws Exception {
        users.create(usu);
    }

    public void agregarEstudiante(Estudiante est) throws Exception {
        this.estudiantes.create(est);
    }

    public Estudiante buscarEstudiante(int id) throws Exception {
        Estudiante pr = this.estudiantes.read(id);
        return pr;
    }

    public Usuario buscarUsuario(int id) throws Exception {
        Usuario pr = this.users.read(id);
        return pr;
    }

    public List<Curso> obtenerCursos() {
        return cursos.findAll();
    }

    public List<Profesor> obtenerProfesores() {
        return profesores.findAll();
    }

    public List<Grupo> obtenerGrupoPorCurso(int nrc) {
        return grupos.findByCurso(nrc);
    }

    public List<Grupo> obtenerGrupoPorProfesor(int id) {
        return grupos.findByNombre(id);
    }

    public List<Estudiante> obtenerEstudiantesG(int id, Profesor p) throws Exception {
        return this.grupos.buscarNota(id, p);
    }

    public boolean ActualizarNota(int id, int nota,int grupo) throws Exception {
        inscripciones.actualizarNota(id, nota,grupo);
        return true;
    }

    public List<Inscripcion> InscripcionesPorEstudiante(int id) {
        List<Inscripcion> list = this.inscripciones.findByEstudiante(id);
        return list;

    }

    public List buscar(String clase, String atributo) {
        switch (clase) {
            case "Profesor":
                return profesores.findByNombre(atributo);
            case "Curso":
                 return cursos.findByNombre(atributo);
            case "Tematica":
                return cursos.findByTematica(atributo);
            default:
                return null;

        }
    }
    public List<Curso> buscarPorOferta(){
        return cursos.findxOferta();
    }
    
    
  
}
