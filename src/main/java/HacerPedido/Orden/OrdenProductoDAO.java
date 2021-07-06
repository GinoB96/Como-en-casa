package HacerPedido.Orden;

import Conexion.Sql2oDAO;
import java.util.List;
import org.sql2o.Connection;

public class OrdenProductoDAO {
    List<OrdenProducto> OP;
    
    public void agregarOrden (OrdenProducto producto){
        String sql = "INSERT INTO ordenproducto (idOP,id_producto,cantidad,nota_especial,id_carrito) VALUES (NULL,:id_producto,:cantidad,:nota_especial,:id_carrito)";
        try (Connection con = Sql2oDAO.getSql2o().open()){
            con
                .createQuery(sql)
                .addParameter("id_producto",producto.getId_producto())
                .addParameter("cantidad",producto.getCantidad())
                .addParameter("nota_especial",producto.getNota_especial())
                .addParameter("id_carrito",producto.getId_carrito())
                .executeUpdate();
        }catch(Exception e){
            System.out.println("Error en OrdenBebidaDAO con "+e);
        }
    }
    
    public List<OrdenProducto> ordenesActuales(int id_carrito){
        String sql = "SELECT * FROM ordenproducto WHERE id_carrito=:id_carrito";
        try (Connection con = Sql2oDAO.getSql2o().open()){
            OP = con
                .createQuery(sql)
                .addParameter("id_carrito",id_carrito)
                .executeAndFetch(OrdenProducto.class);
        }catch(Exception e){
            System.out.println("Error en OrdenBebidaDAO con "+e);
        }
        return OP;
    }  
}
