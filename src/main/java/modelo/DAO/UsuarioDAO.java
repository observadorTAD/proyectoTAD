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
import modelo.entidades.Evento;
import modelo.entidades.Usuario;

/**
 *
 * @author racede
 */
public class UsuarioDAO {

    private final MongoDBJDBC jdbc;
    private final DBCollection coll;

    public UsuarioDAO() {
        jdbc = new MongoDBJDBC();
        this.coll = jdbc.getCollection("usuarios");
    }

    public void crearNuevoUsuario(Usuario usuario) {
        BasicDBObject doc = new BasicDBObject("_id", usuario.getCorreo())
                .append("password", usuario.getPassword())
                .append("nombre", usuario.getNombre())
                .append("apellidos", usuario.getApellidos())
                .append("nombreUsuario", usuario.getNombreUsuario())
                .append("eventos", new BasicDBList());
        coll.insert(doc);
    }

    public boolean isUser(String correo, String password) {
        boolean res = false;
        BasicDBObject query = new BasicDBObject("password", password)
                .append("_id", correo);
        if (coll.find(query).hasNext()) {
            res = true;
        }
        return res;
    }

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

    public void updateUsuario(String correo, String password, String nombreUsuario, String nombre, String apellidos) {

        BasicDBObject searchQuery = new BasicDBObject().append("_id", correo);

        coll.update(searchQuery, new BasicDBObject().append("$set", new BasicDBObject().append("nombre", nombre)));
        coll.update(searchQuery, new BasicDBObject().append("$set", new BasicDBObject().append("password", password)));
        coll.update(searchQuery, new BasicDBObject().append("$set", new BasicDBObject().append("nombreUsuario", nombreUsuario)));
        coll.update(searchQuery, new BasicDBObject().append("$set", new BasicDBObject().append("apellidos", apellidos)));
    }
}
