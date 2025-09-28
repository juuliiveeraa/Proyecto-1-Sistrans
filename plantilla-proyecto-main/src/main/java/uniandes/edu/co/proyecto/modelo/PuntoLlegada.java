package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "punto_llegada")
public class PuntoLlegada {
     @EmbeddedId
    private PuntoLlegadaPK pk;

    public PuntoLlegada() {}

    public PuntoLlegada(Servicio servicio, PuntoGeografico punto) {
        this.pk = new PuntoLlegadaPK(servicio, punto);
    }

    public PuntoLlegadaPK getPk() {
        return pk;
    }

    public void setPk(PuntoLlegadaPK pk) {
        this.pk = pk;
    }
}
