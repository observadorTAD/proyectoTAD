/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
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
                .append("nombreUsuario", usuario.getNombreUsuario())
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

}
