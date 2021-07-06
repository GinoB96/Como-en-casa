/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HacerPedido.Productos;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Ing. Matias
 */
@Data
public class Categoria {
    private int id_categoria; /* Cambiar el modelo de disenio */
    private String nombre_categoria;
    private List<Categoria> subCategoriaList;
    private List<Producto> productoList; /* Cambiar el modelo de disenio */
    private int id_padre_categoria; /* Cambiar el modelo de disenio */
    
    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public List<Categoria> getSubCategoriaList() {
        return subCategoriaList;
    }

    public void setSubCategoriaList(List<Categoria> subCategoriaList) {
        this.subCategoriaList = subCategoriaList;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public int getId_padre_categoria() {
        return id_padre_categoria;
    }

    public void setId_padre_categoria(int id_padre_categoria) {
        this.id_padre_categoria = id_padre_categoria;
    }
    
    
    
}
