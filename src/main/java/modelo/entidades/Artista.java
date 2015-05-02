package modelo.entidades;

/**
 * Esta clase contine los métodos y atributos que pertenecen a la entidad Artista
 */
public class Artista extends Persona {

    private String nombreArtistico;
    private String descripcion;

    /**
     * Constructor que sirve par crear un objeto de tipo Artista.
     * @param nombreArtistico
     * @param descripcion
     * @param email
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Artista(String nombreArtistico, String descripcion, String email,
            String password, String nombre, String apellidos) {
        super(email, password, nombre, apellidos);
        this.nombreArtistico = nombreArtistico;
        this.descripcion = descripcion;
    }

}
