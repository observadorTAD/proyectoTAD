package modelo.entidades;

import java.util.List;

/**
 * Esta clase contine los métodos y atributos que pertenecen a la entidad
 * Usuario
 */
public class Usuario implements Persona {

    private final String correo;
    private final String password;
    private final String nombre;
    private final String apellidos;
    private final List<Evento> eventos;
    private final String nombreUsuario;

    /**
     * Consctructor que crea un objeto de tipo usuario
     *
     * @param nombreUsuario
     * @param eventos
     * @param correo
     * @param password
     * @param nombre
     * @param apellidos
     */
    public Usuario(String nombreUsuario, List<Evento> eventos, String correo, String password,
            String nombre, String apellidos) {
        this.correo = correo;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.eventos = eventos;
        this.nombreUsuario = nombreUsuario;
    }
/**
 * Metodo que devuelve los eventos a los que asistirá el usuario
 * @return Eventos
 */
    public List<Evento> getEventos() {
        return eventos;
    }

    /**
     * Devuelve el nombre de usuario
     * @return Nombre de usuario
     */
    @Override
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Devuelve el correo del usuario
     * @return Correo electronico
     */
    @Override
    public String getCorreo() {
        return correo;
    }

    /**
     * Devuelve la contraseña del usuario
     * @return Contraseña
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Devuelve el nombre real del usuario
     * @return nombre
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve los apellidos del usuario
     * @return Apellidos
     */
    @Override
    public String getApellidos() {
        return apellidos;
    }
}
