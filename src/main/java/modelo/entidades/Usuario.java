package modelo.entidades;

import java.util.List;

/**
 * Esta clase contine los métodos y atributos que pertenecen a la entidad Usuario
 */
public class Usuario {

    private final String email;
    private final String password;
    private final String nombre;
    private final String apellidos;
    private final List<Evento> eventos;
    private final String nombreUsuario;

    /**
     * Consctructor que crea un objeto de tipo usuario
     * @param nombreUsuario
     * @param eventos
     * @param email
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Usuario(String nombreUsuario, List<Evento> eventos, String email, String password,
            String nombre, String apellidos) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.eventos = eventos;
        this.nombreUsuario = nombreUsuario;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
        public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

}
