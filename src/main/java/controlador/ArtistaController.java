package controlador;

import java.util.List;
import modelo.DAO.ArtistaDAO;
import modelo.entidades.Artista;
import modelo.entidades.Evento;
import modelo.entidades.Usuario;

/**
 *
 * @author Alberto Lo
 */
public class ArtistaController implements IPersonaController {

    public Artista getArtista(String artista) {
        return new Artista(artista, artista, artista, artista, artista, artista);
    }

    private final ArtistaDAO artistaDAO;

    public ArtistaController() {
        artistaDAO = new ArtistaDAO();
    }

    public void crearNuevoArtista(String correo, String password, String nombre, String apellidos, String nombreUsuario) {
        Usuario artista = new Usuario(nombreUsuario, null, correo, password, nombre, apellidos);
        artistaDAO.crearNuevoArtista(artista);
    }

    @Override
    public boolean isArtista(String user) {
        return artistaDAO.isUser(user);
    }

    @Override
    public boolean login(String email, String pass) {
        return artistaDAO.login(email, pass);
    }

    @Override
    public void addEventos(String correo, List<Evento> eventos) {
        artistaDAO.addEventos(correo, eventos);
    }

    @Override
    public List<Evento> getEventos(String correo) {
        return artistaDAO.getEventos(correo);
    }

    @Override
    public Artista getUsuario(String correo) {
        return artistaDAO.getArtista(correo);
    }

    @Override
    public void removeUsuario(String correo) {
        artistaDAO.removeArtista(correo);
    }

    @Override
    public void updateUsuario(String correo, String password, String nombreUsuario, String nombre, String apellidos, String descripcion) {
        artistaDAO.updateArtista(correo, password, nombreUsuario, nombre, apellidos, descripcion);
    }

}
