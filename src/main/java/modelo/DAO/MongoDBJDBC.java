
package modelo.DAO;

/**
 *
 * @author racede
 */
import com.mongodb.MongoClient;
import com.mongodb.DB;

public class MongoDBJDBC {

    private MongoClient mongoClient;
    private DB db;
    private UsuarioDAO usuarioDAO;

    public MongoDBJDBC() {
        try {
            mongoClient = new MongoClient("localhost", 27017); // Now connect to your databases
            db = mongoClient.getDB("myEvent");
            usuarioDAO = new UsuarioDAO(db.getCollection("usuarios"));
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    public void crearNuevoUsuario(String correo, String password, String nombre, String apellidos, String nombreUsuario){
        usuarioDAO.crearNuevoUsuario(correo, password, nombre, apellidos, nombreUsuario);
    }
}
