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

    public List<Evento> getEventos() {
        return eventos;
    }

    @Override
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String getCorreo() {
        return correo;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getApellidos() {
        return apellidos;
    }
}
