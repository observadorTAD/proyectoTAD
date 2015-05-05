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
import java.util.List;
import modelo.entidades.Artista;
import modelo.entidades.Evento;
import modelo.entidades.Persona;
import modelo.entidades.Usuario;

/**
 *
 * @author Alberto Lo
 */
public class ArtistaDAO{

    private final MongoDBJDBC jdbc;
    private final DBCollection coll;

    public ArtistaDAO() {
        jdbc = new MongoDBJDBC();
        this.coll = jdbc.getCollection("artista");
    }

    public void crearNuevoArtista(Usuario usuario) {
        BasicDBObject doc = new BasicDBObject("_id", usuario.getCorreo())
                .append("password", usuario.getPassword())
                .append("nombre", usuario.getNombre())
                .append("apellidos", usuario.getApellidos())
                .append("nombreArtistico", usuario.getNombreUsuario())
                .append("eventos", new BasicDBList());
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
            artista = new Artista((String) aux.get("nombreArtistico"),(String) aux.get("descripcion"),
                    (String) aux.get("_id"), (String) aux.get("password"),
                    (String) aux.get("nombre"), (String) aux.get("apellidos"));
        }
        return artista;
    }
}
