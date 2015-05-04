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

}
