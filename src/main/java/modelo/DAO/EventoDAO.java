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
import modelo.entidades.Evento;

/**
 *
 * @author Alberto Lo
 */
public class EventoDAO {

    private final MongoDBJDBC jdbc;
    private final DBCollection coll;

    public EventoDAO() {
        jdbc = new MongoDBJDBC();
        this.coll = jdbc.getCollection("eventos");
    }

    public void crearEvento(Evento evento) {
        BasicDBObject doc = new BasicDBObject("titulo", evento.getTitulo())
                .append("artista", evento.getArtista())
                .append("fecha", evento.getFecha())
                .append("lugar", evento.getLugar())
                .append("precio", evento.getPrecio())
                .append("descripcion", evento.getDescripcion());
        coll.insert(doc);
    }

    public List<Evento> getEventos() {
        List<Evento> eventos = new ArrayList<>();
        DBCursor cursor = coll.find();
        while (cursor.hasNext()) {
            DBObject aux = cursor.next();
            Evento evento = new Evento((String) aux.get("titulo"), (String) aux.get("descripcion"),
                    (String) aux.get("lugar"), (Date) aux.get("fecha"), (String) aux.get("artista"),
                    (Double) aux.get("precio"));
            eventos.add(evento);
        }
        return eventos;
    }

    public void removeEvent(String titulo, String artista, Date fecha) {
        BasicDBObject query = new BasicDBObject("titulo", titulo)
                .append("artista", artista)
                .append("fecha", fecha);

        coll.remove(query);
    }

    public void updateEvent(String tituloAnt, String artistaAnt, Date fechaAnt, String titulo, String lugar, Date fecha, Double precio, String descripcion, String artista) {
        BasicDBObject query = new BasicDBObject("titulo", tituloAnt)
                .append("artista", artistaAnt)
                .append("fecha", fechaAnt);

        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("titulo", titulo)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("descripcion", descripcion)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("lugar", lugar)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("fecha", fecha)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("artista", artista)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("precio", precio)));
    }

    public int countEventos() {
        return coll.find().count();
    }

}
