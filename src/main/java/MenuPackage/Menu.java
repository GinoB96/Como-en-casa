/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPackage;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap; 

public class Menu {
    List<HashMap> menu = new ArrayList();
    public void init() {                                            //MENU SIN LOGEO
            HashMap item = new HashMap();
            item.put("label", "Home"); //nombre de la primera NAV.
            item.put("url", "index");  //URL de la primera NAV.
            menu.add(item);            
            item = new HashMap();
            item.put("label", "Login"); //nombre de la segunda NAV.
            item.put("url", "login");   //URL de la segunda NAV.
            menu.add(item);
    }
    public void initLog() {                                         //MENU CON LOGEO
            HashMap item = new HashMap();
            item.put("label", "Home");    //nombre de la primera NAV.
            item.put("url", "index");      //URL de la primera NAV. 
            menu.add(item);
            item = new HashMap();
            item.put("label", "Hacer Pedido"); //nombre de la segunda NAV.
            item.put("url", "hacerPedido");   //URL de la segunda NAV.
            menu.add(item);
            item = new HashMap();
            item.put("label", "Logout"); //nombre de la tercera NAV.
            item.put("url", "logout");   //URL de la tercera NAV.
            menu.add(item);
    }
    
    public void initLogAdmin() {                                   //MENU CON LOGEO de ADMIN
            HashMap item = new HashMap();
            item.put("label", "Home");    //nombre de la primera NAV.
            item.put("url", "index");      //URL de la primera NAV. 
            menu.add(item);
            item = new HashMap();
            item.put("label", "Hacer Pedido"); //nombre de la segunda NAV.
            item.put("url", "hacerPedido");   //URL de la segunda NAV.
            menu.add(item);
            item = new HashMap();
            item.put("label", "Stock"); //nombre de la tercera NAV.
            item.put("url", "mostrarStockInsumo");   //URL de la tercera NAV.
            menu.add(item);
            item = new HashMap();
            item.put("label", "Pedido a Proveedores"); //nombre de la cuarta NAV.
            item.put("url", "registrarPedidoProveedor");   //URL de la cuarta NAV.
            menu.add(item);
            item = new HashMap();
            item.put("label", "Logout"); //nombre de la quinta NAV.
            item.put("url", "logout");   //URL de la quinta NAV.
            menu.add(item);
            
    }
    
    public List<HashMap> getMenu() {
        init();
        return menu;
    }
    public List<HashMap> getMenuLog() {
        initLog();
        return menu;
    }
    
    public List<HashMap> getMenuLogAdmin() {
        initLogAdmin();
        return menu;
    }
}
