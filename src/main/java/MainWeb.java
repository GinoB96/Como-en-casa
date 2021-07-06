
import Usuarios.ControladorUsuario;
import Web.ControladorWeb;
import HacerPedido.Carrito.ControladorCarrito;
import HacerPedido.ControladorHacerPedido;
import MostrarStock.ControladorMostrarStock;
import Proveedores.ControladorProveedor;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;


public class MainWeb {
    public static void main(String args[]){
        staticFiles.location("/public");
        get("/index",ControladorWeb.index);
        get("/login",ControladorUsuario.login);
        post("/login",ControladorUsuario.login);
        get("/logout",ControladorUsuario.logout);
        get("/hacerPedido",ControladorHacerPedido.inicioHacerPedido);
        get("/agregar",ControladorCarrito.agregar);
        get("/actualizarCarrito",ControladorCarrito.actualizarCarrito);
        get("/confirmarCarrito",ControladorCarrito.confirmarCarrito);
        get("/totalCarrito",ControladorCarrito.totalCarrito);
        get("/mostrarStockInsumo",ControladorMostrarStock.allInsumo);
        get("/actualizarTabla",ControladorMostrarStock.actualizarTabla);
        get("/registrarPedidoProveedor",ControladorProveedor.mostrarRubros);
        get("/actualizarContactos",ControladorProveedor.AC);
        get("/actualizarModal",ControladorProveedor.AM);
        get("/actualizarSelectInsumo",ControladorProveedor.ASI);
        get("/agregarFilas",ControladorProveedor.agregarFilas);
        get("/confirmarPedidoProveedor",ControladorProveedor.registrarPedidoProveedor);
    }
}
