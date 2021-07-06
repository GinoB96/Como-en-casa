package RubroProveedor;
import lombok.Data;

@Data
public class RubroProveedor {
    private int id_rubro;
    private String nombre;

    public int getId_rubro() {
        return id_rubro;
    }

    public void setId_rubro(int id_rubro) {
        this.id_rubro = id_rubro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
