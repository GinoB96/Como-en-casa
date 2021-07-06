package HacerPedido.Carrito;

import HacerPedido.Orden.OrdenProducto;
import HacerPedido.Orden.OrdenProductoDAO;
import HacerPedido.Productos.CategoriaDAO;
import HacerPedido.Productos.ProductoDAO;
import HacerPedido.Productos.Producto;
import MenuPackage.Menu;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class ControladorCarrito {
    
    public static Route agregar = (Request request,Response res)->{

       int id_producto = Integer.parseInt(request.queryParams("idP"));
       int cantidad = Integer.parseInt(request.queryParams("cantidad"));
       String nota=request.queryParams("nota");
       
       System.out.println("idP: "+id_producto+" Cantidad: "+cantidad+" Nota: "+nota);
       
       int id_usuario=request.session().attribute("id");
       System.out.println("id_usuario AgregarOrden: "+id_usuario);
       CarritoDAO carritodao = new CarritoDAO();
       int id_carrito=carritodao.getIdCarritoActual(id_usuario);
       
       OrdenProducto op = new OrdenProducto(id_producto,cantidad,nota,id_carrito);
       OrdenProductoDAO ordenproductodao = new OrdenProductoDAO();
       ordenproductodao.agregarOrden(op);
       
       ProductoDAO pDAO = new ProductoDAO();
       double precio=pDAO.getPrecioProducto(id_producto);
       double subtotal=(precio*cantidad)+ carritodao.getSubTotal(id_carrito);
       System.out.println("Sub-Total: "+subtotal);
       carritodao.actualizarSubTotal(subtotal,id_carrito);
      
       return "OK";
   };
   
   public static Route confirmarCarrito = (Request req, Response res) -> {
        Menu menu = new Menu();
        HashMap model=new HashMap();
        
        int tipoU=req.session().attribute("tipo");
        if(tipoU==1){
           model.put("menu", menu.getMenuLogAdmin()); 
        }
        else{
           model.put("menu", menu.getMenuLog());   
        }
        model.put("menuActivo", "home");
        
        int id_usuario=req.session().attribute("id");
        System.out.println("id_usuario: "+id_usuario);
        
        CarritoDAO cdao=new CarritoDAO();
        int id_carrito=cdao.getIdCarritoActual(id_usuario);
        System.out.println("id_carrito: "+id_carrito);
        cdao.confirmarCarrito(id_carrito);
        cdao.crearCarritoNuevoVacio(id_usuario);
        
        
        model.put("template","templates/Home.vsl");
        model.put("nombre-usuario",req.session().attribute("nombre"));
        return new VelocityTemplateEngine().render(new ModelAndView(model,"templates/cuerpo.vsl"));  
    };
   
    public static Route actualizarCarrito = (Request request,Response res)->{

       
       /*llamada asincrona actualizar mostrador carrito by Maty*/
       System.out.println("ENTRANDO A ACTUALIZAR CARRITO");
       ProductoDAO pDAO = new ProductoDAO();
       int id_usuario=request.session().attribute("id");
       CarritoDAO carritodao = new CarritoDAO();
       int id_carrito=carritodao.getIdCarritoActual(id_usuario);
       List<Producto> LP=pDAO.getProductoListXCarrito(id_carrito);
       
       String rta="<center> <table style=\"width:100%\">\n <tr> ";
       for(int i=0;i<LP.size();i++){
           rta+=" <td>"+LP.get(i).getNombre()+"</td>";
           rta+=" <td> X </td>";
           rta+=" <td>"+LP.get(i).getCantidad()+"</td>";
           rta+=" <td>$"+LP.get(i).getPrecio()+"</td>";
           rta+=" </tr> <tr>";
       }
       rta+="</table> </center>";
       
       return rta;
       
    };
    
    public static Route totalCarrito = (Request req,Response res)->{
        System.out.println("ENTRANDO A ACTUALIZAR TOTAL CARRITO");
        // Inicio de Actualizar Carrito Provisorio
        CarritoDAO carritodao = new CarritoDAO();
        ProductoDAO pDAO= new ProductoDAO();
        int id_usuario=req.session().attribute("id");
        int id_carrito=carritodao.getIdCarritoActual(id_usuario);
        
        // Fin de Actualizar Carrito Provisorio
      
        return carritodao.getSubTotal(id_carrito);
   };
}
