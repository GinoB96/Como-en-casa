package Proveedores;

import MenuPackage.Menu;
import MostrarStock.Insumo;
import MostrarStock.InsumoDAO;
import MostrarStock.OrdenInsumo;
import MostrarStock.OrdenInsumoDAO;
import MostrarStock.TipoInsumo;
import MostrarStock.TipoInsumoDAO;
import RubroProveedor.RubroProveedorDAO;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class ControladorProveedor {
    public static Route mostrarRubros = (Request req, Response res) -> {
        HashMap model = new HashMap();
        Menu menu = new Menu();
        model.put("menu",menu.getMenuLogAdmin());
        model.put("menuActivo","Pedido a Proveedores");
        
        RubroProveedorDAO rDAO = new RubroProveedorDAO();
        model.put("rubros",rDAO.allRubros());
        TipoInsumoDAO tidao =new TipoInsumoDAO();
        model.put("tipoI",tidao.getAllTipoInsumo());
        
        model.put("template","templates/registrarPedidoProveedor.vsl");
        return new VelocityTemplateEngine().render(new ModelAndView(model,"templates/cuerpo.vsl"));
    };
    
    public static Route AC = (Request request,Response res)->{

       System.out.println("ENTRO A ACTUALIZAR CONTACTOS");
       int id = Integer.parseInt(request.queryParams("id"));
       System.out.println("id: "+id);
       ProveedorDAO pdao=new ProveedorDAO();
       List<Proveedor> list=pdao.proveedoresXrubro(id);
       
       String rta="";
       for(int i=0;i<list.size();i++){
           rta+="<option value='"+list.get(i).getId_proveedor()+"'>"
                   +list.get(i).getApellido()+" "+list.get(i).getNombre()+"---"+list.get(i).getNombre_negocio()+
                   "</option>";        
       }
       System.out.println("rta: "+rta);
       return rta;    
    };
    
    public static Route AM = (Request request,Response res)->{

       System.out.println("ENTRO A ACTUALIZAR MODAL");
       int id = Integer.parseInt(request.queryParams("id"));
       System.out.println("id: "+id);
       
       ProveedorDAO pdao=new ProveedorDAO();
       List<Proveedor> list=pdao.getProveedorXid(id);
       
       String rta="";
       for(int i=0;i<list.size();i++){
           rta+="<center><h2><i>"+list.get(i).getNombre_negocio()+"</i></h2></center>"; 
           rta+="<br>";
           rta+="<h5><b>Nombre: </b>"+list.get(i).getNombre()+" "+list.get(i).getApellido()+"</h5>";
           rta+="<br>";
           rta+="<h5><b>Teléfono: </b>"+list.get(i).getTelefono()+"</h5>";
           rta+="<br>";
           rta+="<h5><b>Dirección: </b>"+list.get(i).getDireccion()+"</h5>";
       }
       System.out.println("rta: "+rta);
       return rta;    
    };
    
    public static Route ASI = (Request request,Response res)->{

       System.out.println("ENTRO A ACTUALIZAR SELECT INSUMOS");
       int id = Integer.parseInt(request.queryParams("id"));
       //System.out.println("id: "+id);
       InsumoDAO idao= new InsumoDAO();
       List<Insumo> list=idao.getInsumosByTipo(id);
       
       String rta="";
       for(int i=0;i<list.size();i++){
           rta+="<option value='"+list.get(i).getId_insumo()+"'>"+list.get(i).getNombre()+"</option>";        
       }
       //System.out.println("rta: "+rta);
       return rta;    
    };
    
    public static Route agregarFilas = (Request request,Response res)->{

       System.out.println("ENTRO A AGREGAR FILAS");
       int contF = Integer.parseInt(request.queryParams("contadorFilas"));
       int cantF = Integer.parseInt(request.queryParams("cantidadFilas"));
       //System.out.println("id: "+id);
       
       TipoInsumoDAO tidao =new TipoInsumoDAO();
       List<TipoInsumo> list=tidao.getAllTipoInsumo();
       
       String rta="";
       cantF++;
       for(int i=0;i<contF;i++){
           rta+="<tr align='center' id='fila"+cantF+"'>" +
            "                            <th>" +
            "                                <div class='searchFormat'> " +
            "                                    <select onChange='getIdTI("+cantF+")' id='selectTI"+cantF+"'>" +
            "                                        <option value='' disabled selected style='display:none;'></option>";
           for(int j=0;j<list.size();j++){
              rta+="<option value='"+list.get(j).getId_TipoInsumo()+"'>"+list.get(j).getNombreTI()+"</option>"; 
           }
           rta+=" </select>" +
            "                                </div>" +
            "                            </th>" +
            "                            <th>" +
            "                                <select  id='selectInsumo"+cantF+"'style='width:310px;'><option value='' disabled selected style='display:none;'></option>";
           rta+="      </select>\n" +
            "                            </th>" +
            "                            <th><input id='cant"+cantF+"'type='' name='' size='2'></th>" +
            "                            <th><input id='notaE"+cantF+"' type='' name='' size='70'></th>" +
            "               </tr>";
           cantF++;
       }
       //System.out.println("rta: "+rta);
       return rta;    
    };
    
    
    public static Route registrarPedidoProveedor = (Request request,Response res)->{
       System.out.println("ENTRO A REGISTRAR PEDIDO");
       
       int cantF = Integer.parseInt(request.queryParams("cantidadFilas")); //Cantidad total de Ordenes
       int idContacto = Integer.parseInt(request.queryParams("contacto")); //id del Proveedor
       String stringJson=request.queryParams("rta");
       
       System.out.println("idContacto: "+idContacto);
       JSONObject json = new JSONObject(stringJson);
       
       //Gino
       PedidoProveedorDAO pedidoDAO = new PedidoProveedorDAO();
        pedidoDAO.insertarNuevoPedido(idContacto);
        int pedido = pedidoDAO.idUltimoPedido();
        OrdenInsumo orden;
        OrdenInsumoDAO ordenDAO = new OrdenInsumoDAO();
        //
       
       for(int i=1;i<cantF+1;i++){
           
           orden = new OrdenInsumo();
           orden.setCantidad(Integer.parseInt( ((JSONObject)json.get("o"+i)).get("c").toString() ));
           orden.setId_insumo(Integer.parseInt( ((JSONObject)json.get("o"+i)).get("i").toString() ));
           orden.setId_pedidoProveedor(pedido);
           orden.setNotaEspecial( ((JSONObject)json.get("o"+i)).get("ne").toString() );
           
           ordenDAO.insertarNuevaOrdenInsumo(orden);
           
           
           /*obtiene el id_TipoInsumo de la Orden+i del JSON
             Integer.parseInt( ((JSONObject)json.get("o"+i)).get("ti").toString() )
           
             obtiene el id_Insumo de la Orden+i del JSON
             Integer.parseInt( ((JSONObject)json.get("o"+i)).get("i").toString() )
           
             obtiene la cantidad de la Orden+i del JSON
             Integer.parseInt( ((JSONObject)json.get("o"+i)).get("c").toString() )
           
             obtiene la nota especial de la Orden+i del JSON
             (JSONObject)json.get("o"+i)).get("ne").toString()
           */
           System.out.println("ti: "+Integer.parseInt( ((JSONObject)json.get("o"+i)).get("ti").toString() ));
           System.out.println("i: "+Integer.parseInt( ((JSONObject)json.get("o"+i)).get("i").toString() ));
           System.out.println("c: "+Integer.parseInt( ((JSONObject)json.get("o"+i)).get("c").toString() ));
           System.out.println("ne: "+ ( (JSONObject)json.get("o"+i)).get("ne").toString() );
       }
       
       return "En Proceso";    
    };
}
