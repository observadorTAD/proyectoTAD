package modelo.entidades;

/**
 * Esta clase contine los métodos y atributos que pertenecen a la entidad
 * Artista
 */
public class Artista {

    private final String email;
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
     * @param email
     * @param password
     * @param nombre
     * @param apellidos
     */
    public Artista(String nombreArtistico, String descripcion, String email,
            String password, String nombre, String apellidos) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreArtistico = nombreArtistico;
        this.descripcion = descripcion;
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
