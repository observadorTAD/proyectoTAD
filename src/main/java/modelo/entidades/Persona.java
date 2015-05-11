package modelo.entidades;

/**
 * Interfaz que contiene los metodos de artista y de usuario.
 * Consultar las clases especificadas para más información
 * @author Alberto Lo
 */
public interface Persona {

    /**
     * Devuelve los apellidos de una persona
     * @return apellidos
     */
    String getApellidos();

    /**
     * Devuelve el correo electronico de una persona
     * @return correo
     */
    String getCorreo();

    /**
     * Devuelve el nombre de una persona
     * @return nombre
     */
    String getNombre();

    /** 
     * Devuelve el nombre de usuario de una persona
     * @return nombreusuario
     */
    String getNombreUsuario();

    /**
     * Devuelve la contraseña de una persona
     * @return contraseña
     */
    String getPassword();

}
