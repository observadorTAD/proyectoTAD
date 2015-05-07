package modelo.DAO;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.entidades.Artista;
import modelo.entidades.Evento;
import modelo.entidades.Usuario;

/**
 * Clase DAO para la entidad Artista
 * @author Alberto Lo
 */
public class ArtistaDAO {

    private final MongoDBJDBC jdbc;
    private final DBCollection coll;
/**
 * Inicialización del driver JDBC para MongoDB
 */
    public ArtistaDAO() {
        jdbc = new MongoDBJDBC();
        this.coll = jdbc.getCollection("artistas");
    }
/**
 * Guarda un artista en la persistencia
 * @param usuario 
 */
    public void crearNuevoArtista(Usuario usuario) {
        BasicDBObject doc = new BasicDBObject("_id", usuario.getCorreo())
                .append("password", usuario.getPassword())
                .append("nombre", usuario.getNombre())
                .append("apellidos", usuario.getApellidos())
                .append("nombreArtistico", usuario.getNombreUsuario())
                .append("eventos", new BasicDBList())
                .append("descripcion", "");
        coll.insert(doc);
    }
/**
 * Devuelve si un usuario está ya en la BD o no
 * @param user
 * @return resultado
 */
    public boolean isUser(String user) {
        boolean res = false;
        BasicDBObject query = new BasicDBObject("_id", user);
        if (coll.find(query).hasNext()) {
            res = true;
        }
        return res;
    }
/**
 * Devuelve si una combinación de contraseña e email vinculada a un artista.
 * @param email
 * @param pass
 * @return resultado
 */
    public boolean login(String email, String pass) {
        boolean res = false;
        BasicDBObject query = new BasicDBObject("password", pass)
                .append("_id", email);
        if (coll.find(query).hasNext()) {
            res = true;
        }
        return res;
    }
/**
 * Devuelve un artista, con todos sus valores de la persistencia dada la clave principal proveida.
 * @param correo
 * @return artista
 */
    public Artista getArtista(String correo) {
        Artista artista = null;
        BasicDBObject query = new BasicDBObject("_id", correo);
        DBCursor cursor = coll.find(query);
        if (cursor.hasNext()) {
            DBObject aux = cursor.next();
            artista = new Artista((String) aux.get("nombreArtistico"), (String) aux.get("descripcion"),
                    (String) aux.get("_id"), (String) aux.get("password"),
                    (String) aux.get("nombre"), (String) aux.get("apellidos"));
        }
        return artista;
    }
/**
 * Borra un artista de la persistencia.
 * @param correo 
 */
    public void removeArtista(String correo) {
        BasicDBObject query = new BasicDBObject("_id", correo);

        coll.remove(query);
    }
/**
 * Devuelve todos los eventos organizados por un artista.
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
/**
 * Añade a un determinado artista una lista de eventos.
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
 * Actualiza los valores de un artista.
 * @param correo
 * @param password
 * @param nombreUsuario
 * @param nombre
 * @param apellidos
 * @param descripcion 
 */
    public void updateArtista(String correo, String password, String nombreUsuario, String nombre, String apellidos, String descripcion) {
        BasicDBObject query = new BasicDBObject("_id", correo);

        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("nombre", nombre)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("password", password)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("nombreArtistico", nombreUsuario)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("apellidos", apellidos)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("descripcion", descripcion)));
    }
/**
 * Devuelve el conjunto de artistas registrados en la persistencia.
 * @return artistas
 */
    public List<Artista> getArtistas() {
        List<Artista> artistas = new ArrayList<>();
        DBCursor cursor = coll.find();
        while (cursor.hasNext()) {
            DBObject aux = cursor.next();
            Artista artista = new Artista((String) aux.get("nombreArtistico"), (String) aux.get("descripcion"),
                    (String) aux.get("_id"), (String) aux.get("password"), (String) aux.get("nombre"),
                    (String) aux.get("apellidos"));
            artistas.add(artista);
        }
        return artistas;
    }
/**
 * Devuelve el número de artistas registrados en el sistema
 * @return numero de artistas
 */
    public int countArtistas() {
        return coll.find().count();
    }
}
