package MostrarStock;
import lombok.Data;

@Data
public class Insumo {
    private int id_insumo;
    private String imagen;
    private String nombre;
    private String nombreTI;
    private int stock;
    private int stock_minimo;
    private double costo;
    
    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNombreTI() {
        return nombreTI;
    }

    public void setNombreTI(String nombreTI) {
        this.nombreTI = nombreTI;
    }
}
