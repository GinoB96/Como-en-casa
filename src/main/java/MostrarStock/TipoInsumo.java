package MostrarStock;

import lombok.Data;

@Data
public class TipoInsumo {
    private int id_TipoInsumo;
    private String nombreTI;

    public int getId_TipoInsumo() {
        return id_TipoInsumo;
    }

    public void setId_TipoInsumo(int id_TipoInsumo) {
        this.id_TipoInsumo = id_TipoInsumo;
    }

    public String getNombreTI() {
        return nombreTI;
    }

    public void setNombreTI(String nombreTI) {
        this.nombreTI = nombreTI;
    }
}
