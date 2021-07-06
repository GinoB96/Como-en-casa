package Web;


import MenuPackage.Menu;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class ControladorWeb {
    
    public static Route index = (Request req, Response response) -> {
       
       HashMap model=new HashMap();
       String email = req.session().attribute("email");//Si la sesion no esta abieta da NULL.
       String nombre = req.session().attribute("nombre");//Si la sesion no esta abieta da NULL.
       int tipoU;
       try{
            tipoU=req.session().attribute("tipo");
            System.out.println("Tipo Usuario: "+tipoU);
       }catch(Exception  err){
            tipoU=0;
       }
       Menu menu = new Menu();
       
       if(email == null){   //SI NO ESTA LOGEADO
            model.put("menu", menu.getMenu());     //Menu sin logeo
            model.put("menuActivo", "index");   
            model.put("template", "templates/index.vsl"); //index es la primera pagina del sin logeo
       }else{               //SI ESTA LOGEADO   
            if(tipoU==1){
                model.put("menu", menu.getMenuLogAdmin()); //Menu con logeo Admin
                model.put("email-usuario", email);    //Sirve para en el Home.vsl mostrar el Bienvenido usuario
                model.put("nombre-usuario", nombre);    //Sirve para en el Home.vsl mostrar el Bienvenido usuario
                model.put("menuActivo", "index");     
                model.put("template", "templates/home.vsl");  //home es la primera pagina del con logeo
            }
            else{
                model.put("menu", menu.getMenuLog()); //Menu con logeo
                model.put("email-usuario", email);    //Sirve para en el Home.vsl mostrar el Bienvenido usuario
                model.put("nombre-usuario", nombre);    //Sirve para en el Home.vsl mostrar el Bienvenido usuario
                model.put("menuActivo", "index");     
                model.put("template", "templates/home.vsl");  //home es la primera pagina del con logeo   
            }
        }
       
       return new VelocityTemplateEngine().render(new ModelAndView(model,"templates/cuerpo.vsl"));
    };
    
    
}
