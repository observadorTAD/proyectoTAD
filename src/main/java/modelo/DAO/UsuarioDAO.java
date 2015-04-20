/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

/**
 *
 * @author racede
 */
public class UsuarioDAO {

    private DBCollection coll;

    public UsuarioDAO(DBCollection coll) {
        this.coll = coll;
    }

    public void crearNuevoUsuario(String correo, String password, String nombre, String apellidos, String nombreUsuario) {
        BasicDBObject doc = new BasicDBObject("_id", correo)
                .append("password", password)
                .append("nombre", nombre)
                .append("apellidos", apellidos)
                .append("nombreUsuario", nombreUsuario);
        coll.insert(doc);
    }
}
