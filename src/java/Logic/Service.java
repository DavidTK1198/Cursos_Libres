package Logic;

import Data.CursosDao;
import Data.EstudianteDao;
import Data.GrupoDao;
import Data.InscripcionDao;
import Data.ProfesorDao;
import Data.UserDao;
import java.util.List;

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
    

}
