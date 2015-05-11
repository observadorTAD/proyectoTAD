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

    /**
     * Obtiene el titulo del evento
     * @return Titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene la descripción del evento
     * @return Descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el lugar en el que el evento tendrá lugar
     * @return Lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Obtiene la fecha del evento
     * @return Fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Devuelve el artista principal del evento
     * @return Artista
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Devuelve el precio de asistencia al evento
     * @return 
     */
    public Double getPrecio() {
        return precio;
    }
    
    /**
     * Metodo equals de la clase Evento
     * @param obj
     * @return boolean
     */
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

    /**
     * Metodo haschode de objetos evento
     * @return hascode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.titulo);
        hash = 29 * hash + Objects.hashCode(this.descripcion);
        hash = 29 * hash + Objects.hashCode(this.lugar);
        hash = 29 * hash + Objects.hashCode(this.fecha);
        hash = 29 * hash + Objects.hashCode(this.artista);
        hash = 29 * hash + Objects.hashCode(this.precio);
        return hash;
    }
    
    

}
