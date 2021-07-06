package Conexion;
import org.sql2o.Sql2o;

/**
 *
 * 
 * Conexion a la Base de datos con Singleton.
 */
public class Sql2oDAO {
    static Sql2o sql2o;

    public static Sql2o getSql2o() {
        if (sql2o == null) {
             sql2o = new Sql2o("jdbc:mysql://localhost:3306/comoencasadb", "root", "");
        }
        return sql2o;
    }
    
}