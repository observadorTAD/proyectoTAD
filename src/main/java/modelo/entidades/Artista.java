package modelo.entidades;

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
/**
 * Devuelve el correo del artista
 * @return Correo
 */
    public String getCorreo() {
        return correo;
    }
/**
 * Devuelve la contraseña del artista
 * @return Contraseña
 */
    public String getPassword() {
        return password;
    }
/**
 * Devuelve el nombre del artista
 * @return Nombre
 */
    public String getNombre() {
        return nombre;
    }
/**
 * Devuelve los apellidos del artista
 * @return Apellidos
 */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Devuelve la descripcion del artista en cuestión
     * @return Descripción
     */
    public String getDescripcion(){
        return descripcion;
    }

    /**
     * Devuelve el nombre artístico del artista
     * @return Nombre artístico
     */
    @Override
    public String getNombreUsuario() {
        return nombreArtistico;
    }

}
