package modelo.entidades;

import java.util.List;
import modelo.entidades.Persona;

/**
 * Esta clase contine los métodos y atributos que pertenecen a la entidad
 * Artista
 */
public class Artista implements Persona{

    private final String correo;
    private final String password;
    private final String nombre;
    private final String apellidos;
    private final String nombreArtistico;
    private final String descripcion;

    /**
     * Constructor que sirve par crear un objeto de tipo Artista.
     *
     * @param nombreArtistico
     * @param descripcion
     * @param correo
     * @param password
     * @param nombre
     * @param apellidos
     */
    public Artista(String nombreArtistico, String descripcion, String correo,
            String password, String nombre, String apellidos) {
        this.correo = correo;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreArtistico = nombreArtistico;
        this.descripcion = descripcion;
    }

    public String getCorreo() {
        return correo;
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

    @Override
    public List<Evento> getEventos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNombreUsuario() {
        return nombreArtistico;
    }

}
