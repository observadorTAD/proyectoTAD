package modelo.DAO;

/**
 * Clase genérica DAO encargada de gestionar la conexión con la persistencia con las otras clases DAO.
 * @author racede
 */
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClientURI;
import java.util.Map;
import org.cloudfoundry.runtime.env.CloudEnvironment;

/**
 *
 * @author racede
 */
public class MongoDBJDBC {

    private MongoClient mongoClient;
    private DB db;
/**
 * Inicialización de los parámetros de conexión
 */
    public MongoDBJDBC() {
        try {
            mongoClient = getMongoClient(); // Now connect to your databases
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
    /**
     * Conexión a mongo para Bluemix.
     * @return
     * @throws Exception 
     */
    public static MongoClient getMongoClient() throws Exception {
        CloudEnvironment environment = new CloudEnvironment();
        MongoClient mongoClient;
        if (environment.getServiceDataByLabels("mongodb").size() == 0) {
// To connect to mongodb server
            mongoClient = new MongoClient("localhost", 27017);
        } else {
            Map credential = (Map) ((Map) environment.getServiceDataByLabels("mongodb").get(0)).get("credentials");
            String connURL = (String) credential.get("url");
            mongoClient = new MongoClient(new MongoClientURI(connURL));
        }
        return mongoClient;
    }
}
