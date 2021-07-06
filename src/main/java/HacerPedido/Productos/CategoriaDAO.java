package HacerPedido.Productos;

import Conexion.Sql2oDAO;
import java.util.List;
import lombok.Data;
import org.sql2o.Connection;

@Data
public class CategoriaDAO {
    private List<Categoria> CList;
    
    /**
     * Retorna todas las categorias, junto con sus respectivas sub-categorias, platos y bebidas.
     * @return retorna un objeto que contiene categorias, sub-categorias, platos y bebidas
     */
    public List<Categoria> getAllItemAndHierarchy(){ /* Cambiar el modelo de disenio */
        String sql = "SELECT * FROM categoria WHERE id_padre_categoria= -1";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            CList = con
                .createQuery(sql)
                .executeAndFetch(Categoria.class);
            System.out.println("Categoria 1:"+CList.get(0).getNombre_categoria());
            System.out.println("Categoria 2:"+CList.get(1).getNombre_categoria());
            //System.out.println("Categoria 3:"+CList.get(2).getNombre_categoria());
        }
        catch(Exception e){
            System.out.println("Error en CategoriaDAO con "+e);
        }
        
        //A cada categoria se le carga su respectiva lista de sub-categorias
        int idPC;/*id del padre*/
        for(int i=0;i<CList.size();i++){
           idPC=CList.get(i).getId_categoria();
           
           System.out.println("id_padre: "+idPC);
           
           CList.get(i).setSubCategoriaList( this.getSubCategoriaListXCategoria(idPC) );
           
        }
        return CList;
    }
    
    private List<Categoria>getSubCategoriaListXCategoria(int id_padre_categoria){ 
        String sql = "SELECT id_categoria,nombre_categoria FROM categoria WHERE id_padre_categoria= :id_padre_categoria";
        List<Categoria> SCList;
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            SCList = con
                .createQuery(sql)
                .addParameter("id_padre_categoria", id_padre_categoria)
                .executeAndFetch(Categoria.class);
            
            //A cada sub-categoria se le carga su respectiva lista de Productos
            ProductoDAO PDAO=new ProductoDAO();
            int idSC;
            for(int i=0;i<SCList.size();i++){
               idSC=SCList.get(i).getId_categoria();
               
               SCList.get(i).setProductoList( PDAO.getProductoXSubCategoria(idSC) ); 
               //System.out.println(""+CList.get(i).getSubCategoriaList().get(0).getNombre_sub_categoria());
            }
            return SCList;      
        }
        catch(Exception e){
            System.out.println("Error en CategoriaDAO con "+e);
        }
        return null;
    }  
}

