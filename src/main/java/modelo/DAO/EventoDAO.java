/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
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
                .append("artista", evento.getArtista().getNombre())
                .append("fecha", evento.getFecha())
                .append("lugar", evento.getLugar())
                .append("precio", evento.getPrecio())
                .append("descripcion", evento.getDescripcion())
                .append("usuarios", new BasicDBList());
        coll.insert(doc);
    }
    
}
