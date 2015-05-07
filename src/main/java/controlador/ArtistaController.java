package controlador;

import java.util.List;
import modelo.DAO.ArtistaDAO;
import modelo.entidades.Artista;
import modelo.entidades.Evento;
import modelo.entidades.Usuario;

/**
 * Controlador de la entidad Artista
 * @author Alberto Lo
 */
public class ArtistaController implements IPersonaController {
/**
 * Devuelve un artista dado un email.
 * @param artista
 * @return artista
 */
    public Artista getArtista(String artista) {
        return new Artista(artista, artista, artista, artista, artista, artista);
    }

    private final ArtistaDAO artistaDAO;
/**
 * Inicializa el controlador
 */
    public ArtistaController() {
        artistaDAO = new ArtistaDAO();
    }
/**
 * Crea un nuevo artista
 * @param correo
 * @param password
 * @param nombre
 * @param apellidos
 * @param nombreUsuario 
 */
    public void crearNuevoArtista(String correo, String password, String nombre, String apellidos, String nombreUsuario) {
        Usuario artista = new Usuario(nombreUsuario, null, correo, password, nombre, apellidos);
        artistaDAO.crearNuevoArtista(artista);
    }
/**
 * Devuelve si un email es un artista o no.
 * @param user
 * @return resultado
 */
    @Override
    public boolean isArtista(String user) {
        return artistaDAO.isUser(user);
    }
/**
 * Devuelve si una combinación de email y contraseña es correcta.
 * @param email
 * @param pass
 * @return resultado
 */
    @Override
    public boolean login(String email, String pass) {
        return artistaDAO.login(email, pass);
    }
/**
 * Añade a un artista un conjunto de eventos
 * @param correo
 * @param eventos 
 */
    @Override
    public void addEventos(String correo, List<Evento> eventos) {
        artistaDAO.addEventos(correo, eventos);
    }
/**
 * Devuelve todos los eventos organizados por un artista
 * @param correo
 * @return eventos
 */
    @Override
    public List<Evento> getEventos(String correo) {
        return artistaDAO.getEventos(correo);
    }
/**
 * Indica si un correo está registrado en el sistema como artista.
 * @param correo
 * @return artista
 */
    @Override
    public Artista getUsuario(String correo) {
        return artistaDAO.getArtista(correo);
    }
/**
 * Elimina un artista de la persistencia.
 * @param correo 
 */
    @Override
    public void removeUsuario(String correo) {
        artistaDAO.removeArtista(correo);
    }
/**
 * Actualiza los valores de un determinado usuario.
 * @param correo
 * @param password
 * @param nombreUsuario
 * @param nombre
 * @param apellidos
 * @param descripcion 
 */
    @Override
    public void updateUsuario(String correo, String password, String nombreUsuario, String nombre, String apellidos, String descripcion) {
        artistaDAO.updateArtista(correo, password, nombreUsuario, nombre, apellidos, descripcion);
    }
/**
 * Devuelve todos los artistas registrados
 * @return artistas
 */
    public List<Artista> getArtistas() {
        return artistaDAO.getArtistas();
    }
    
    /**
     * Devuelve el numero de artistas registrados.
     * @return numero de artistas
     */
    public int countArtitas(){
        return artistaDAO.countArtistas();
    }

}
