package modelo.entidades;

import java.util.List;

/**
 * Esta clase contine los métodos y atributos que pertenecen a la entidad Usuario
 */
public class Usuario extends Persona {

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
        super(email, password, nombre, apellidos);
        this.eventos = eventos;
        this.nombreUsuario = nombreUsuario;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    

}
