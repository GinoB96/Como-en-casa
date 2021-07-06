package Usuarios;

import MenuPackage.Menu;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class ControladorUsuario {
    public static Route login = (Request req, Response res) -> {
        
       HashMap model = new HashMap();
       Menu menu = new Menu();
       model.put("menu", menu.getMenu());  
       model.put("menuActivo", "login");   
       
       if(req.queryParams("pass")!=null && req.queryParams("email")!=null) //Si hay datos en el input de email y pass entra
            {
                UsuarioDAO uDAO = new UsuarioDAO(); 
                List<Usuario> user = uDAO.verificarPersona(req.queryParams("email"),req.queryParams("pass")); //retorna los usuarios que concuerden con el email y pass recibidos
                if(user.size() > 0){  //si la cantidad de usuarios que se trajo de la base de datos es mayor a 1
                    //CREAR SEASION/COOKIE
                    model.put("template", "templates/home.vsl");
                    Usuario usuarioLogeado = user.get(0);
                    req.session(true);                                             // Crear y retornar la sesion
                    req.session().attribute("id", usuarioLogeado.getId() );       // Seteamos atributo
                    req.session().attribute("tipo", usuarioLogeado.getTipo() );  //Usuario cliente o empleado del negocio
                    req.session().attribute("email", usuarioLogeado.getEmail() );// Seteamos atributo
                    req.session().attribute("nombre", usuarioLogeado.getNombre_usuario());
                    res.redirect("/index");
                }else{ // si el size es 0 quiere decir que no existe un usuario con esos datos, por lo tanto...
                    model.put("template", "templates/login.vsl");
                    model.put("request",req);
                    model.put("error", "La contraseña o el email es incorrecto.");
                }
                
            }else{ //En caso de que sea la primera vez que se entra al login
                model.put("email","");
                model.put("template", "templates/login.vsl");   
            }
            
       
       return new VelocityTemplateEngine().render(new ModelAndView(model,"templates/cuerpo.vsl"));
    };
    
    public static Route logout = (Request req, Response res) -> {
        req.session().removeAttribute("id");
        req.session().removeAttribute("email");
        res.redirect("/index");
        return null;
    };
}
