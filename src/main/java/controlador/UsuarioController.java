/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.DAO.UsuarioDAO;
import modelo.entidades.Evento;
import modelo.entidades.Usuario;

/**
 *
 * @author Alberto Lo
 */
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }

    public void crearNuevoUsuario(String correo, String password, String nombre, String apellidos, String nombreUsuario) {
        Usuario usuario = new Usuario(nombreUsuario, null, correo, password, nombre, apellidos);
        usuarioDAO.crearNuevoUsuario(usuario);
    }

    public boolean isUser(String correo, String password) {
        return usuarioDAO.isUser(correo, password);
    }

    public Usuario getUsuario(String correo) {
        return usuarioDAO.getUsuario(correo);
    }

    public void updateUsuario(String correo, String password, String nombreUsuario, String nombre, String apellidos) {
        usuarioDAO.updateUsuario(correo, password, nombreUsuario, nombre, apellidos);
    }

    public void removeUsuario(String correo) {
        usuarioDAO.removeUsuario(correo);
    }

    public void addEventos(String correo, List<Evento> eventos) {
        usuarioDAO.addEventos(correo, eventos);
    }

    public List<Evento> getEventos(String correo) {
        return usuarioDAO.getEventos(correo);
    }
}
