package modelo;

import java.util.List;

/**
 * Esta clase contine los métodos y atributos que pertenecen a la entidad Usuario
 */
public class Usuario extends Persona {

    private List<Evento> eventos;
    private String usuario;

    /**
     * Consctructor que crea un objeto de tipo usuario
     * @param usuario
     * @param eventos
     * @param email
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Usuario(String usuario, List<Evento> eventos, String email, String password,
            String nombre, String apellidos) {
        super(email, password, nombre, apellidos);
        this.eventos = eventos;
        this.usuario = usuario;
    }

}
