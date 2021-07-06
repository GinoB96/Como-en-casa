package MostrarStock;

import Conexion.Sql2oDAO;
import HacerPedido.Productos.Categoria;
import java.util.List;
import org.sql2o.Connection;

public class InsumoDAO {
    private List<Insumo> IList;
     
    public List<Insumo> getAllInsumos(){ /* Cambiar el modelo de disenio */
        String sql = "SELECT id_insumo,imagen,nombre,nombreTI,stock,stock_minimo,costo "
                + "FROM insumo INNER JOIN tipoinsumo ON insumo.id_tipoInsumo=tipoinsumo.id_tipoInsumo "
                + "ORDER BY nombreTI DESC, stock ASC";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            IList = con
                .createQuery(sql)
                .executeAndFetch(Insumo.class);
        }
        catch(Exception e){
            System.out.println("Error en InsumoDAO con "+e);
        }
        return IList;
    }
    
    public List<Insumo> getInsumosByTipo(int id_tipoInsumo){ /* Cambiar el modelo de disenio */
        String sql = "SELECT id_insumo,imagen,nombre,nombreTI,stock,stock_minimo,costo "
                + "FROM insumo INNER JOIN tipoinsumo ON insumo.id_tipoInsumo=tipoinsumo.id_tipoInsumo "
                + "AND insumo.id_tipoInsumo = :id_tipoInsumo "
                + "ORDER BY nombreTI DESC, stock ASC";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            IList = con
                .createQuery(sql)
                .addParameter("id_tipoInsumo", id_tipoInsumo)
                .executeAndFetch(Insumo.class);
        }
        catch(Exception e){
            System.out.println("Error en InsumoDAO con "+e);
        }
        return IList;
    }
    
    public List<Insumo> getInsumosByCodigo(int id_insumo){ /* Cambiar el modelo de disenio */
        String sql = "SELECT id_insumo,imagen,nombre,nombreTI,stock,stock_minimo,costo "
                + "FROM insumo INNER JOIN tipoinsumo ON insumo.id_tipoInsumo=tipoinsumo.id_tipoInsumo "
                + "AND insumo.id_insumo = :id_insumo "
                + "ORDER BY nombreTI DESC, stock ASC";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            IList = con
                .createQuery(sql)
                .addParameter("id_insumo", id_insumo)
                .executeAndFetch(Insumo.class);
        }
        catch(Exception e){
            System.out.println("Error en InsumoDAO con "+e);
        }
        return IList;
    }
    
    public List<Insumo> getInsumosByNombre(String nombre){ /* Cambiar el modelo de disenio */
        String sql = "SELECT id_insumo,imagen,nombre,nombreTI,stock,stock_minimo,costo "
                + "FROM insumo INNER JOIN tipoinsumo ON insumo.id_tipoInsumo=tipoinsumo.id_tipoInsumo "
                + "AND insumo.nombre = :nombre "
                + "ORDER BY nombreTI DESC, stock ASC";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            IList = con
                .createQuery(sql)
                .addParameter("nombre", nombre)
                .executeAndFetch(Insumo.class);
        }
        catch(Exception e){
            System.out.println("Error en InsumoDAO con "+e);
        }
        return IList;
    }
    
}
