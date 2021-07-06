package Proveedores;
import java.util.Date;
import lombok.Data;

@Data
public class PedidoProveedor {
    private int id_pedidoProveedor;
    private int id_proveedor;
    private Date fecha;

    public int getId_pedidoProveedor() {
        return id_pedidoProveedor;
    }

    public void setId_pedidoProveedor(int id_pedidoProveedor) {
        this.id_pedidoProveedor = id_pedidoProveedor;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
