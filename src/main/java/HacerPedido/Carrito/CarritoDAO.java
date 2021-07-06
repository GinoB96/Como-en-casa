package HacerPedido.Carrito;

import Conexion.Sql2oDAO;
import java.util.List;
import org.sql2o.Connection;

public class CarritoDAO {
    private List<Carrito> carritos;
    
    public String getEstadoActualCarrito(int id_usuario){
        String sql = "SELECT MAX(id_carrito) AS id_carrito FROM carrito WHERE id_usuario="+id_usuario;
        String estado="";
        try (Connection con = Sql2oDAO.getSql2o().open()){
            carritos = con
                .createQuery(sql)
                .executeAndFetch(Carrito.class);
        }catch(Exception e){
            System.out.println("Error en CarritoDAO con "+e);
        }
        return carritos.get(0).getEstado();
    }
    
    public void crearCarritoNuevoVacio(int id_usuario){
        String sql = "INSERT INTO carrito (id_carrito, fecha, subtotal, estado, id_usuario) VALUES (NULL,NULL,0,\"NoConfirmado\","+id_usuario+")";
        try(Connection con = Sql2oDAO.getSql2o().open()){
            con
                .createQuery(sql)
                .executeUpdate();
        }catch(Exception e){
            System.out.println("Error en CarritoDAO con "+e);
        }
    }
    
    public int getIdCarritoActual(int id_usuario){
        String sql = "SELECT MAX(id_carrito) AS id_carrito FROM carrito WHERE id_usuario="+id_usuario;
        try (Connection con = Sql2oDAO.getSql2o().open()){
            carritos = con
                .createQuery(sql)
                .executeAndFetch(Carrito.class);
        }catch(Exception e){
            System.out.println("Error en CarritoDAO con "+e);
        }
        return carritos.get(0).getId_carrito();
    }
    
    public void confirmarCarrito(int id_carrito){
        String sql = "UPDATE carrito SET estado= \"Confirmado\" WHERE id_carrito = :id_carrito";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql).addParameter("id_carrito", id_carrito).executeUpdate();
        }
        catch(Exception e){
            System.out.println("Error en EquipoDAO con "+e);
        }
    } 
    
    public double getSubTotal(int id_carrito){
        String sql = "SELECT subtotal FROM carrito WHERE id_carrito= :id_carrito";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            carritos= con
                .createQuery(sql)
                .addParameter("id_carrito", id_carrito)
                .executeAndFetch(Carrito.class);
        }
        catch(Exception e){
            System.out.println("Error en ProductoDAO con "+e);
        }
        
        return carritos.get(0).getSubtotal();
    }  
    
    public void actualizarSubTotal(double subtotal,int id_carrito){
        String sql = "UPDATE carrito SET subtotal= :subtotal WHERE id_carrito = :id_carrito";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
                    .addParameter("id_carrito", id_carrito)
                    .addParameter("subtotal", subtotal)
                    .executeUpdate();
        }
        catch(Exception e){
            System.out.println("Error en EquipoDAO con "+e);
        }
    }
    
}