package controlador;

import modelo.DAO.ArtistaDAO;
import modelo.entidades.Artista;
import modelo.entidades.Usuario;

/**
 *
 * @author Alberto Lo
 */
public class ArtistaController {
    
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
    
    public boolean isArtista(String user){
         return artistaDAO.isUser(user);
    }
    
    public boolean login(String email, String pass){
        return artistaDAO.login(email, pass);
    }
    
}
