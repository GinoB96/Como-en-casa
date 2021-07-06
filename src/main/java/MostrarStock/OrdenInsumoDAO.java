package MostrarStock;
import Conexion.Sql2oDAO;
import java.util.List;
import org.sql2o.Connection;

public class OrdenInsumoDAO {
    List<OrdenInsumo> ordenes;
    
    public void insertarNuevaOrdenInsumo(OrdenInsumo orden){
        String sql = "INSERT INTO ordeninsumo (id_ordenInsumo, id_pedidoProveedor, id_insumo, cantidad, notaEspecial) VALUES (NULL,:id_pedidoProveedor,:id_insumo,:cantidad,:notaEspecial)";
        
        try(Connection con = Sql2oDAO.getSql2o().open()){
            con
                .createQuery(sql)
                .addParameter("id_pedidoProveedor", orden.getId_pedidoProveedor())
                .addParameter("id_insumo", orden.getId_insumo())
                .addParameter("cantidad", orden.getCantidad())
                .addParameter("notaEspecial", orden.getNotaEspecial())
                .executeUpdate();
        }catch(Exception e){
            System.out.println("Error en OrdenInsumoDAO con "+e);
        }
    }
}
