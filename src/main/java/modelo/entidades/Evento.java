package modelo.entidades;

import java.util.Date;
import java.util.Objects;

/**
 * Esta clase contine los métodos y atributos que pertenecen a la entidad Evento
 */
public class Evento {

    private String titulo;
    private String descripcion;
    private String lugar;
    private Date fecha;
    private String artista;
    private Double precio;

    /**
     * Constructor que sirve para crear un objeto de tipo evento.
     *
     * @param titulo
     * @param descripcion
     * @param lugar
     * @param fecha
     * @param artista
     * @param precio
     */
    public Evento(String titulo, String descripcion, String lugar, Date fecha,
            String artista, Double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.lugar = lugar;
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

    public String getArtista() {
        return artista;
    }

    public Double getPrecio() {
        return precio;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.lugar, other.lugar)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.artista, other.artista)) {
            return false;
        }
        if (!Objects.equals(this.precio, other.precio)) {
            return false;
        }
        return true;
    }
    
    

}
