package modelo.DAO;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.entidades.Evento;
import modelo.entidades.Usuario;

/**
 * Clase DAO encargada de la persistencia de la entidad usuario.
 * @author racede
 */
public class UsuarioDAO {

    private final MongoDBJDBC jdbc;
    private final DBCollection coll;

    /**
     * Inicialización del driver JDBC para MongoDB
     */
    public UsuarioDAO() {
        jdbc = new MongoDBJDBC();
        this.coll = jdbc.getCollection("usuarios");
    }

    /**
     * Guarda un nuevo usuario en la base de datos
     * @param usuario 
     */
    public void crearNuevoUsuario(Usuario usuario) {
        BasicDBObject doc = new BasicDBObject("_id", usuario.getCorreo())
                .append("password", usuario.getPassword())
                .append("nombre", usuario.getNombre())
                .append("apellidos", usuario.getApellidos())
                .append("nombreUsuario", usuario.getNombreUsuario())
                .append("eventos", new BasicDBList());
        coll.insert(doc);
    }
/**
 * Devuelve si la combinación correo/password proveida es correcta de acuerdo a los registros.
 * @param correo
 * @param password
 * @return true/false
 */
    public boolean isUser(String correo, String password) {
        boolean res = false;
        BasicDBObject query = new BasicDBObject("password", password)
                .append("_id", correo);
        if (coll.find(query).hasNext()) {
            res = true;
        }
        return res;
    }

    /**
     * Devuelve null si el usuario no existe, o el usuario que se pide.
     * @param correo
     * @return Usuario
     */
    public Usuario getUsuario(String correo) {
        Usuario usuario = null;
        BasicDBObject query = new BasicDBObject("_id", correo);
        DBCursor cursor = coll.find(query);
        if (cursor.hasNext()) {
            DBObject aux = cursor.next();
            List<Evento> l = (List<Evento>) aux.get("eventos");
            usuario = new Usuario((String) aux.get("nombreUsuario"), l,
                    (String) aux.get("_id"), (String) aux.get("password"),
                    (String) aux.get("nombre"), (String) aux.get("apellidos"));
        }
        return usuario;
    }
/**
 * Actualiza un usuario con los datos proveidos por el usuario
 * @param correo
 * @param password
 * @param nombreUsuario
 * @param nombre
 * @param apellidos 
 */
    public void updateUsuario(String correo, String password, String nombreUsuario, String nombre, String apellidos) {
        BasicDBObject query = new BasicDBObject("_id", correo);

        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("nombre", nombre)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("password", password)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("nombreUsuario", nombreUsuario)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("apellidos", apellidos)));
    }

    /**
     * Borra el usuario pasado por parámetros
     * @param correo 
     */
    public void removeUsuario(String correo) {
        BasicDBObject query = new BasicDBObject("_id", correo);

        coll.remove(query);
    }
/**
 * Añade un evento a un determinado usuario.
 * @param correo
 * @param eventos 
 */
    public void addEventos(String correo, List<Evento> eventos) {
        BasicDBObject query = new BasicDBObject("_id", correo);
        coll.update(query, new BasicDBObject("$unset", new BasicDBObject("eventos", 1)));

        for (Evento evento : eventos) {
            BasicDBObject eventoMongo = new BasicDBObject("titulo", evento.getTitulo())
                    .append("artista", evento.getArtista())
                    .append("fecha", evento.getFecha())
                    .append("lugar", evento.getLugar())
                    .append("precio", evento.getPrecio())
                    .append("descripcion", evento.getDescripcion());

            coll.update(query, new BasicDBObject("$push", new BasicDBObject("eventos", eventoMongo)));
        }

    }
/**
 * Devuelve de la persistencia los eventos asignados a un usuario
 * @param correo
 * @return Eventos
 */
    public List<Evento> getEventos(String correo) {
        List<Evento> eventos = new ArrayList<>();
        BasicDBObject query = new BasicDBObject("_id", correo);
        DBCursor cursor = coll.find(query);
        BasicDBList misEventos = (BasicDBList) cursor.next().get("eventos");
        if (misEventos != null) {
            for (Object misEvento : misEventos) {
                BasicDBObject aux = (BasicDBObject) misEvento;
                Evento evento = new Evento((String) aux.get("titulo"), (String) aux.get("descripcion"),
                        (String) aux.get("lugar"), (Date) aux.get("fecha"), (String) aux.get("artista"),
                        (Double) aux.get("precio"));
                eventos.add(evento);
            }
        }
        return eventos;
    }
}
