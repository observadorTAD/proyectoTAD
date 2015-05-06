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
public class UsuarioController implements IPersonaController {

    private final UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }

    public void crearNuevoUsuario(String correo, String password, String nombre, String apellidos, String nombreUsuario) {
        Usuario usuario = new Usuario(nombreUsuario, null, correo, password, nombre, apellidos);
        usuarioDAO.crearNuevoUsuario(usuario);
    }

    @Override
    public boolean login(String correo, String password) {
        return usuarioDAO.isUser(correo, password);
    }

    @Override
    public Usuario getUsuario(String correo) {
        return usuarioDAO.getUsuario(correo);
    }

    @Override
    public void updateUsuario(String correo, String password, String nombreUsuario, String nombre, String apellidos, String descripcion) {
        usuarioDAO.updateUsuario(correo, password, nombreUsuario, nombre, apellidos);
    }

    @Override
    public void removeUsuario(String correo) {
        usuarioDAO.removeUsuario(correo);
    }

    @Override
    public void addEventos(String correo, List<Evento> eventos) {
        usuarioDAO.addEventos(correo, eventos);
    }

    @Override
    public List<Evento> getEventos(String correo) {
        return usuarioDAO.getEventos(correo);
    }

    @Override
    public boolean isArtista(String correo) {
        return false;
    }

    public boolean loginAdmin(String correo, String password) {
        return (correo.equals("admin") && password.equals("admin"));
    }

    public List<Usuario> getUsuarios() {
        return usuarioDAO.getUsuarios();
    }

    public int countUsuarios() {
        return usuarioDAO.countUsuarios();
    }
}
