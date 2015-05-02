/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import modelo.DAO.UsuarioDAO;
import modelo.entidades.Usuario;

/**
 *
 * @author Alberto Lo
 */
public class UsuarioController {
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioController(){
        usuarioDAO = new UsuarioDAO();
    }
    public void crearNuevoUsuario(String correo, String password, String nombre, String apellidos, String nombreUsuario) throws NoSuchAlgorithmException{
        Usuario usuario = new Usuario(nombreUsuario, null, correo, toMD5(password), nombre, apellidos);
        usuarioDAO.crearNuevoUsuario(usuario);
    }
    
    private  String toMD5(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        String salt1 = "&/fda´45";
        String salt2 = "fj=·1/nm";
        String output = salt1 + input + salt2;
        return new String(md.digest(output.getBytes()));
    }
    
    
}
