package controlador;

import java.util.List;
import modelo.entidades.Evento;
import modelo.entidades.Persona;

/**
 * Interfaz del controlador de persona
 * @author Alberto Lo
 */
public interface IPersonaController {

    /**
     * Método que añade a un correo electronico una serie de eventos
     * @param correo
     * @param eventos
     */
    void addEventos(String correo, List<Evento> eventos);

    /**
     * Metodo que devuelve todos los eventos vinculados a un correo electrónico
     * @param correo
     * @return eventos
     */
    List<Evento> getEventos(String correo);

    /**
     * Método que devuelve un objeto de tipo usuario de la persistencia a partir de un correo electrónico.
     * @param correo
     * @return
     */
    Persona getUsuario(String correo);

    /**
     * Indica si un correo está registrado como artista
     * @param correo
     * @return true/false
     */
    boolean isArtista(String correo);

    /**
     * Elimina un usuario de la persistencia
     * @param correo
     */
    void removeUsuario(String correo);

    /**
     * Indica si una combinacion de usuario y contraseña es valida
     * @param correo
     * @param password
     * @return true/false
     */
    boolean login(String correo, String password);

    /**
     * Actualiza los valores en persistencia de un usuario
     * @param correo
     * @param password
     * @param nombreUsuario
     * @param nombre
     * @param apellidos
     * @param descripcion
     */
    public void updateUsuario(String correo, String password, String nombreUsuario, String nombre, String apellidos, String descripcion);
}
