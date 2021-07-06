package HacerPedido.Orden;

public class OrdenProducto extends Orden {
    private int idOP;
    private int id_producto;

    public OrdenProducto(int id_producto,int cantidad,String nota,int id_carrito){
        this.id_producto=id_producto;
        this.cantidad=cantidad;
        this.nota_especial=nota;
        this.id_carrito=id_carrito;
    }
    
    public int getIdOP() {
        return idOP;
    }

    public void setIdOP(int idOP) {
        this.idOP = idOP;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

}