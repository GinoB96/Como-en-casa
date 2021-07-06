package Proveedores;
import Conexion.Sql2oDAO;
import java.util.List;
import lombok.Data;
import org.sql2o.Connection;

@Data
public class ProveedorDAO {
    private List<Proveedor> proveedores;
    
    public List<Proveedor> proveedoresXrubro(int id_rubro){
        String sql = "SELECT * FROM proveedor WHERE id_rubro=:id_rubro";
        
        try(Connection con = Sql2oDAO.getSql2o().open()){
            proveedores = con
                .createQuery(sql)
                .addParameter("id_rubro", id_rubro)
                .executeAndFetch(Proveedor.class);
                    
        }catch(Exception e){
            System.out.println("Error en ProveedorDAO:proveedoresXrubro con "+e);
        }
        return proveedores;
    }
    
    public List<Proveedor> getProveedorXid(int id_proveedor){
        String sql = "SELECT * FROM proveedor WHERE id_proveedor=:id_proveedor";
        
        try(Connection con = Sql2oDAO.getSql2o().open()){
            proveedores = con
                .createQuery(sql)
                .addParameter("id_proveedor", id_proveedor)
                .executeAndFetch(Proveedor.class);
                    
        }catch(Exception e){
            System.out.println("Error en ProveedorDAO:getProveedorXid con "+e);
        }
        return proveedores;
    }
    
}
