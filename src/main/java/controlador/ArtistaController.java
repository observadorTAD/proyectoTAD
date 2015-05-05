package controlador;

import java.util.List;
import modelo.DAO.ArtistaDAO;
import modelo.entidades.Artista;
import modelo.entidades.Evento;
import modelo.entidades.Persona;
import modelo.entidades.Usuario;

/**
 *
 * @author Alberto Lo
 */
public class ArtistaController implements IPersonaController{
    
    public Artista getArtista(String artista){
        return new Artista(artista, artista, artista, artista, artista, artista);
    }
    
     private final ArtistaDAO artistaDAO;
    
    public ArtistaController(){
        artistaDAO = new ArtistaDAO();
    }
    public void crearNuevoArtista(String correo, String password, String nombre, String apellidos, String nombreUsuario) {
        Usuario artista = new Usuario(nombreUsuario, null, correo, password, nombre, apellidos);
        artistaDAO.crearNuevoArtista(artista);
    }
    @Override
    public boolean isArtista(String user){
         return artistaDAO.isUser(user);
    }
    
    public boolean login(String email, String pass){
        return artistaDAO.login(email, pass);
    }

    @Override
    public void addEventos(String correo, List<Evento> eventos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evento> getEventos(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Artista getUsuario(String correo) {
        return artistaDAO.getArtista(correo);
    }

    @Override
    public boolean isUser(String correo, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUsuario(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
