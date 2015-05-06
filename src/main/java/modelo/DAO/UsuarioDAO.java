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
        BasicDBObject query = new BasicDBObject("_id", correo);

        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("nombre", nombre)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("password", password)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("nombreUsuario", nombreUsuario)));
        coll.update(query, new BasicDBObject().append("$set", new BasicDBObject().append("apellidos", apellidos)));
    }

    public void removeUsuario(String correo) {
        BasicDBObject query = new BasicDBObject("_id", correo);

        coll.remove(query);
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

    public List<Evento> getEventos(String correo) {
        List<Evento> eventos = new ArrayList<>();
        BasicDBObject query = new BasicDBObject("_id", correo);
        DBCursor cursor = coll.find(query);
        if (cursor.hasNext()) {
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
        }
        return eventos;
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        DBCursor cursor = coll.find();
        while (cursor.hasNext()) {
            DBObject aux = cursor.next();
            Usuario usuario = new Usuario((String) aux.get("nombreUsuario"), null, (String) aux.get("_id"),
                    (String) aux.get("password"), (String) aux.get("nombre"), (String) aux.get("apellidos"));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public int countUsuarios() {
        return coll.find().count();
    }
}
