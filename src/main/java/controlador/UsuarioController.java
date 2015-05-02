/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
    public void crearNuevoUsuario(String correo, String password, String nombre, String apellidos, String nombreUsuario){
        Usuario usuario = new Usuario(nombreUsuario, null, correo, password, nombre, apellidos);
        usuarioDAO.crearNuevoUsuario(usuario);
    }
}
