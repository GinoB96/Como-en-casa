package HacerPedido.Orden;

public class Orden {
    protected int cantidad;
    protected String nota_especial;
    protected int id_carrito;
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNota_especial() {
        return nota_especial;
    }

    public void setNota_especial(String nota_especial) {
        this.nota_especial = nota_especial;
    }

    public int getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(int id_carrito) {
        this.id_carrito = id_carrito;
    }
}
