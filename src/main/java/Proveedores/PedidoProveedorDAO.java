package Proveedores;
import Conexion.Sql2oDAO;
import java.util.List;
import org.sql2o.Connection;

public class PedidoProveedorDAO {
    List<PedidoProveedor> pedidos;
    
    public void insertarNuevoPedido(int id_proveedor){
        String sql = "INSERT INTO pedidoproveedor (id_pedidoProveedor, id_proveedor, fecha) VALUES (NULL,"+id_proveedor+",current_timestamp())";
        try(Connection con = Sql2oDAO.getSql2o().open()){
            con
                .createQuery(sql)
                .executeUpdate();
        }catch(Exception e){
            System.out.println("Error en PedidoProveedorDAO con "+e);
        }
    }
    
    public int idUltimoPedido(){
        String sql = "SELECT MAX(id_pedidoProveedor) AS id_pedidoProveedor FROM pedidoproveedor";
        try(Connection con = Sql2oDAO.getSql2o().open()){
            pedidos = con
                .createQuery(sql)
                .executeAndFetch(PedidoProveedor.class);
        }catch(Exception e){
            System.out.println("Error en PedidoProveedorDAO con "+e);
        }
        return pedidos.get(0).getId_pedidoProveedor();
    }
}
