package RubroProveedor;

import Conexion.Sql2oDAO;
import java.util.List;
import org.sql2o.Connection;

public class RubroProveedorDAO {
    private List<RubroProveedor> rubros;
    
    public List<RubroProveedor> allRubros(){
        String sql = "SELECT * FROM rubroproveedor";
        
        try(Connection con = Sql2oDAO.getSql2o().open()){
            rubros = con
                .createQuery(sql)
                .executeAndFetch(RubroProveedor.class);
        }catch(Exception e){
            System.out.println("Error en OrdenBebidaDAO con "+e);
        }
        return rubros;
    }
}
