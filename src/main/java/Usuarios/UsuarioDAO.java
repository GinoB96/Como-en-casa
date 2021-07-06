package Usuarios;

import Conexion.Sql2oDAO;
import java.util.List;
import org.sql2o.Connection;

public class UsuarioDAO {
   private List<Usuario> registroUsuarios;
    
    
    public List<Usuario> verificarPersona( String email, String pass) {
        
        String sql = "SELECT id,tipo,email,pass,nombre_usuario FROM usuario WHERE email = :email and  pass = :pass";
        try (Connection con = Sql2oDAO.getSql2o().open()) {

            registroUsuarios = con
                .createQuery(sql)
                .addParameter("email", email)
                .addParameter("pass", pass)
                .executeAndFetch(Usuario.class);
            System.out.println("email: "+registroUsuarios.get(0).getEmail());
            System.out.println("pass: "+registroUsuarios.get(0).getPass());
            
        }
        catch(Exception e){
            System.out.println("Error en UsuarioDAO Con "+e);
        }
        return registroUsuarios;
    }
}
