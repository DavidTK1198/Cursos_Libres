package Logic;

import Data.CursosDao;
import Data.GrupoDao;
import Data.UserDao;

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

    
    public Service() {
        users = new UserDao();
        cursos = new CursosDao();
        grupos = new GrupoDao();
        
    }

    public static Service getInstance() {
        if (my_instance == null) {
            my_instance = new Service();
        }

        return my_instance;
    }
     public Usuario login(Usuario u) throws Exception{
        Usuario result=users.read(u.getIdUsu());
        if(result==null)  throw new Exception("Usuario no existe");
        if(!result.getClave().equals(u.getClave()))throw new Exception("User does not exist");
        return result;
    } 
     public void agregarCurso(Curso cursito) throws Exception{
         cursos.create(cursito);
     }
     public Curso buscarCurso(int id) throws Exception{
         Curso cur = cursos.read(id);
         return cur;
     }
     public boolean actualizarStatusCurso(boolean ofer,int nrc) throws Exception{
         boolean b = cursos.updateStatus(ofer, nrc);
         return b;
     }
      public void agregarGrupo(Grupo Grupito) throws Exception{
         grupos.create(Grupito);
     }
       public Grupo buscarGrupo(int id) throws Exception{
         Grupo grup = grupos.read(id);
         return grup;
     }
     
}
