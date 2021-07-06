package MostrarStock;

import MenuPackage.Menu;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class ControladorMostrarStock {
   
   public static Route allInsumo = (Request req, Response res) -> {
        Menu menu = new Menu();
        HashMap model=new HashMap();
        
        model.put("menu", menu.getMenuLogAdmin());
        model.put("menuActivo", "mostrarStockInsumo");
        
        InsumoDAO idao= new InsumoDAO();
        model.put("insumos",idao.getAllInsumos());
        TipoInsumoDAO tidao =new TipoInsumoDAO();
        model.put("tipoI",tidao.getAllTipoInsumo());
        
        model.put("template","templates/mostrarStockInsumo.vsl");
        return new VelocityTemplateEngine().render(new ModelAndView(model,"templates/cuerpo.vsl"));  
    };
    
   public static Route actualizarTabla = (Request request,Response res)->{

       /*Si es 0, entonces se hizo una busqueda por rubro.
        *Si es 1, entonces se hizo una busqueda por codigo.
        *Si es 2, entonces se hizo una busqueda por nombre
       */
       System.out.println("ENTRANDO A ACTUALIZAR TABLA");
       
       int op = Integer.parseInt(request.queryParams("op"));
       InsumoDAO idao= new InsumoDAO();
       List<Insumo> list=null;
       int id;
       
       switch(op){
           case 0: 
                   id = Integer.parseInt(request.queryParams("id"));
                   if(id==0){
                       list=idao.getAllInsumos();
                   }
                   else{
                       list=idao.getInsumosByTipo(id);
                   }
                   break;
           case 1: 
                   id = Integer.parseInt(request.queryParams("id"));
                   list=idao.getInsumosByCodigo(id);
                   break;
           case 2: 
                   String nombre=request.queryParams("id");
                   list=idao.getInsumosByNombre(nombre);
                   break;
       }
                  
       String rta="";
       for(int i=0;i<list.size();i++){
           if(list.get(i).getStock() < list.get(i).getStock_minimo()){
               rta+="<tr style=\"background-color: #b0201b\">";
           }
           else{
               rta+="<tr>";
           }
           rta+="<td class=\"text-center\">"+list.get(i).getId_insumo()+"</td>";
           rta+="<td class=\"text-center\"> <img class='img-rounded' src='"+list.get(i).getImagen()
                   +"'style=\"width:80px;\"> </td>";
           rta+="<td>"+list.get(i).getNombre()+"</td>";
           rta+="<td>"+list.get(i).getNombreTI()+"</td>";
           rta+="<td class=\"text-center\">"+list.get(i).getStock()+"</td>";
           rta+="<td class=\"text-center\">"+list.get(i).getStock_minimo()+"</td>";
           rta+="<td class=\"text-center\">"+list.get(i).getCosto()+"</td>";
           rta+=" </tr>";
       }
      
       return rta;
       
    };
}
