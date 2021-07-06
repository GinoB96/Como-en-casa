
package HacerPedido;

import HacerPedido.Carrito.CarritoDAO;
import HacerPedido.Productos.CategoriaDAO;
import MenuPackage.Menu;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;


public class ControladorHacerPedido {
    public static Route inicioHacerPedido = (Request req, Response res) -> {
        Menu menu = new Menu();
        HashMap model=new HashMap();
        
        int tipoU=req.session().attribute("tipo");
        if(tipoU==1){
           model.put("menu", menu.getMenuLogAdmin()); 
        }
        else{
           model.put("menu", menu.getMenuLog());   
        }
        model.put("menuActivo", "hacerPedido");
        
        CategoriaDAO cDAO=new CategoriaDAO();
        CarritoDAO carritodao = new CarritoDAO();
        
        /*// Inicio de Actualizar Carrito Provisorio
        ProductoDAO pDAO= new ProductoDAO();
        int id_usuario=req.session().attribute("id");
        int id_carrito=carritodao.getIdCarritoActual(id_usuario);
        List<Producto> listaOrdenes=pDAO.getProductoListXCarrito(id_carrito);
        double total=0;
        for(int i=0;i<listaOrdenes.size();i++){
            total=total+listaOrdenes.get(i).getPrecio();
        }
        model.put("ordenes",listaOrdenes);
        model.put("total",total);
        */// Fin de Actualizar Carrito Provisorio
        
        model.put("categoria",cDAO.getAllItemAndHierarchy());
        model.put("template","templates/hacerPedido.vsl");
        return new VelocityTemplateEngine().render(new ModelAndView(model,"templates/cuerpo.vsl"));  
    };
}
