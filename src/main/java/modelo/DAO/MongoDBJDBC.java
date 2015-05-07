package modelo.DAO;

/**
 * Clase genérica DAO encargada de gestionar la conexión con la persistencia con las otras clases DAO.
 * @author racede
 */
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class MongoDBJDBC {

    private MongoClient mongoClient;
    private DB db;
/**
 * Inicialización de los parámetros de conexión
 */
    public MongoDBJDBC() {
        try {
            mongoClient = new MongoClient("localhost", 27017); // Now connect to your databases
            db = mongoClient.getDB("myEvent");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
/**
 * Devuelve una colección de la persistencia. Si no existe, la crea.
 * @param collection
 * @return Colección
 */
    public DBCollection getCollection(String collection) {
        DBCollection coll = null;
        if (db.collectionExists(collection)) {
            coll = db.getCollection(collection);
        } else {
            coll = db.createCollection(collection, null);
        }
        return coll;
    }
}
