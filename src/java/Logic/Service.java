package Logic;

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

    public Service() {
        users = new UserDao();
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
}
