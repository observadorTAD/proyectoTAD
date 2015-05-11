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
     *
     * @param correo
     * @param eventos
     */
    void addEventos(String correo, List<Evento> eventos);

    /**
     *
     * @param correo
     * @return
     */
    List<Evento> getEventos(String correo);

    /**
     *
     * @param correo
     * @return
     */
    Persona getUsuario(String correo);

    /**
     *
     * @param correo
     * @return
     */
    boolean isArtista(String correo);

    /**
     *
     * @param correo
     */
    void removeUsuario(String correo);

    /**
     *
     * @param correo
     * @param password
     * @return
     */
    boolean login(String correo, String password);

    /**
     *
     * @param correo
     * @param password
     * @param nombreUsuario
     * @param nombre
     * @param apellidos
     * @param descripcion
     */
    public void updateUsuario(String correo, String password, String nombreUsuario, String nombre, String apellidos, String descripcion);
}
