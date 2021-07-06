package MostrarStock;

import Conexion.Sql2oDAO;
import java.util.List;
import org.sql2o.Connection;

public class TipoInsumoDAO {
    private List<TipoInsumo> TIList;
    
    public List<TipoInsumo> getAllTipoInsumo(){ /* Cambiar el modelo de disenio */
        String sql = "SELECT * FROM tipoinsumo";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            TIList = con
                .createQuery(sql)
                .executeAndFetch(TipoInsumo.class);
        }
        catch(Exception e){
            System.out.println("Error en InsumoDAO con "+e);
        }
        return TIList;
    }
}
