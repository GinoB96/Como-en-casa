package MostrarStock;
import lombok.Data;

@Data
public class OrdenInsumo {
    private int id_ordenInsumo;
    private int id_pedidoProveedor;
    private int id_insumo;
    private int cantidad;
    private String notaEspecial;

    public int getId_ordenInsumo() {
        return id_ordenInsumo;
    }

    public void setId_ordenInsumo(int id_ordenInsumo) {
        this.id_ordenInsumo = id_ordenInsumo;
    }

    public int getId_pedidoProveedor() {
        return id_pedidoProveedor;
    }

    public void setId_pedidoProveedor(int id_pedidoProveedor) {
        this.id_pedidoProveedor = id_pedidoProveedor;
    }

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNotaEspecial() {
        return notaEspecial;
    }

    public void setNotaEspecial(String notaEspecial) {
        this.notaEspecial = notaEspecial;
    }
}
