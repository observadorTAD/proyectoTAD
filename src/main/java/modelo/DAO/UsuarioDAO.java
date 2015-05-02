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
        BasicDBObject doc = new BasicDBObject("_id", usuario.getEmail())
                .append("password", usuario.getPassword())
                .append("nombre", usuario.getNombre())
                .append("apellidos", usuario.getApellidos())
                .append("nombreUsuario", usuario.getNombreUsuario())
                .append("evetnos", new BasicDBList());
        coll.insert(doc);
    }
    
}
