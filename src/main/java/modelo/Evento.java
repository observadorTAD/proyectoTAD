package modelo;

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
    private List<Persona> usuarios;
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
    public Evento(String titulo, String descripcion, String lugar, Date fecha, List<Persona> usuarios, 
            Artista artista, float precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.usuarios = usuarios;
        this.artista = artista;
        this.precio = precio;
        this.fecha = fecha;
    }
    
}
