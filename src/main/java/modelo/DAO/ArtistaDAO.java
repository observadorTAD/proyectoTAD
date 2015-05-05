/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import modelo.entidades.Persona;
import modelo.entidades.Usuario;

/**
 *
 * @author Alberto Lo
 */
public class ArtistaDAO {

    private final MongoDBJDBC jdbc;
    private final DBCollection coll;

    public ArtistaDAO() {
        jdbc = new MongoDBJDBC();
        this.coll = jdbc.getCollection("artistas");
    }

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

    public boolean isUser(String user) {
        boolean res = false;
        BasicDBObject query = new BasicDBObject("_id", user);
        if (coll.find(query).hasNext()) {
            res = true;
        }
        return res;
    }

    public boolean login(String email, String pass) {
        boolean res = false;
        BasicDBObject query = new BasicDBObject("password", pass)
                .append("_id", email);
        if (coll.find(query).hasNext()) {
            res = true;
        }
        return res;
    }

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

    public void removeArtista(String correo) {
        BasicDBObject query = new BasicDBObject("_id", correo);

        coll.remove(query);
    }

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

    public void updateArtista(String correo, String password, String nombreUsuario, String nombre, String apellidos, String descripcion) {
        BasicDBObject query = new BasicDBObject("_id", correo);

        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("nombre", nombre)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("password", password)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("nombreArtistico", nombreUsuario)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("apellidos", apellidos)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("descripcion", descripcion)));
    }
}
