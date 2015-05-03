package modelo.entidades;

import java.util.Date;
import java.util.List;

/**
 * Esta clase contine los métodos y atributos que pertenecen a la entidad Evento
 */
public class Evento {
    private String titulo;
    private String descripcion;
    private String lugar;
    private Date fecha;
    private List<Usuario> usuarios;
    private Artista artista;
    private float precio;

    /**
     * Constructor que sirve para crear un objeto de tipo evento.
     * @param titulo
     * @param descripcion
     * @param lugar
     * @param fecha
     * @param usuarios
     * @param artista
     * @param precio 
     */
    public Evento(String titulo, String descripcion, String lugar, Date fecha, List<Usuario> usuarios, 
            Artista artista, float precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.usuarios = usuarios;
        this.artista = artista;
        this.precio = precio;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Artista getArtista() {
        return artista;
    }

    public float getPrecio() {
        return precio;
    }
    
}
