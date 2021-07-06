/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HacerPedido.Productos;

import Conexion.Sql2oDAO;
import java.util.List;
import org.sql2o.Connection;

/**
 *
 * @author Ing. Matias
 */
public class ProductoDAO {
    private List<Producto> PList; 
   
   /**
    * recupera todas las bebidas referente a una subcategoria
    * @param id_sub_categoria
    * @return retorna una lista de bebidas referente al id_subcategoria pasado por parametro
    */
   public List<Producto> getProductoXSubCategoria(int id_categoria){
        String sql = "SELECT id_producto,nombre,descripcion,precio,sabor,litros FROM producto WHERE id_categoria= :id_categoria";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            PList = con
                .createQuery(sql)
                .addParameter("id_categoria", id_categoria)
                .executeAndFetch(Producto.class);
        }
        catch(Exception e){
            System.out.println("Error en ProductoDAO con "+e);
        }
        
        return PList;
    }  
   
    public double getPrecioProducto(int id_producto){
        String sql = "SELECT precio FROM producto WHERE id_producto= :id_producto";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            PList = con
                .createQuery(sql)
                .addParameter("id_producto", id_producto)
                .executeAndFetch(Producto.class);
        }
        catch(Exception e){
            System.out.println("Error en ProductoDAO con "+e);
        }
        
        return PList.get(0).getPrecio();
    } 
    
    public List<Producto> getProductoListXCarrito(int id_carrito){
        String sql = "SELECT nombre,precio,cantidad FROM producto,"
                + "(SELECT id_producto,cantidad FROM ordenproducto WHERE id_carrito = :id_carrito) AS orden "
                + "WHERE producto.id_producto=orden.id_producto ";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            PList = con
                .createQuery(sql)
                .addParameter("id_carrito", id_carrito)
                .executeAndFetch(Producto.class);
        }
        catch(Exception e){
            System.out.println("Error en ProductoDAO con "+e);
        }
        
        return PList;
    } 
}
