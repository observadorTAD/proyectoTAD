package controlador;

import java.util.List;
import modelo.DAO.UsuarioDAO;
import modelo.entidades.Evento;
import modelo.entidades.Usuario;

/**
 * Controlador de la entidad Usuario
 * @author Alberto Lo
 */
public class UsuarioController implements IPersonaController {

    private final UsuarioDAO usuarioDAO;
/**
 * Constructor del controlador.
 */
    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }
/**
 * Crea un nuevo usuario en la persistencia.
 * @param correo
 * @param password
 * @param nombre
 * @param apellidos
 * @param nombreUsuario 
 */
    public void crearNuevoUsuario(String correo, String password, String nombre, String apellidos, String nombreUsuario) {
        Usuario usuario = new Usuario(nombreUsuario, null, correo, password, nombre, apellidos);
        usuarioDAO.crearNuevoUsuario(usuario);
    }
/**
 * Devuelve si una combinación de correo y contraseña es correcta.
 * @param correo
 * @param password
 * @return valor
 */
    @Override
    public boolean login(String correo, String password) {
        return usuarioDAO.isUser(correo, password);
    }
/**
 * Indica si un correo está registrado como usuario.
 * @param correo
 * @return usuario
 */
    @Override
    public Usuario getUsuario(String correo) {
        return usuarioDAO.getUsuario(correo);
    }
/**
 * Actualiza un determinado usuario con los valores indicados.
 * @param correo
 * @param password
 * @param nombreUsuario
 * @param nombre
 * @param apellidos
 * @param descripcion 
 */
    @Override
    public void updateUsuario(String correo, String password, String nombreUsuario, String nombre, String apellidos, String descripcion) {
        usuarioDAO.updateUsuario(correo, password, nombreUsuario, nombre, apellidos);
    }
/**
 * Elimina un usuario de la persistencia.
 * @param correo 
 */
    @Override
    public void removeUsuario(String correo) {
        usuarioDAO.removeUsuario(correo);
    }
/**
 * Añade un evento a la lista de eventos que asistirá un determinado usuario.
 * @param correo
 * @param eventos 
 */
    @Override
    public void addEventos(String correo, List<Evento> eventos) {
        usuarioDAO.addEventos(correo, eventos);
    }
/**
 * Devuelve la lista de eventos a los que asiste un usuario
 * @param correo
 * @return eventos
 */
    @Override
    public List<Evento> getEventos(String correo) {
        return usuarioDAO.getEventos(correo);
    }
/**
 * Devuelve si un artista está registrado en el sistema.
 * @param correo
 * @return resultado
 */
    @Override
    public boolean isArtista(String correo) {
        return false;
    }
/**
 * Devuelve si una combinación de correo y contraseña es correcta.
 * @param correo
 * @param password
 * @return resultado
 */
    public boolean loginAdmin(String correo, String password) {
        return (correo.equals("admin") && password.equals("admin"));
    }
/**
 * Devuelve todos los usuarios registrados.
 * @return usuarios
 */
    public List<Usuario> getUsuarios() {
        return usuarioDAO.getUsuarios();
    }
/**
 * Indica el número de usuarios registrados
 * @return numero de usuarios
 */
    public int countUsuarios() {
        return usuarioDAO.countUsuarios();
    }
}
