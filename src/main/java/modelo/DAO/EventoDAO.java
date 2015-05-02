/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import com.mongodb.DBCollection;

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
    
}
