package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PuntoLlegadaPK implements Serializable{
     @ManyToOne
    @JoinColumn(name = "ID_SERVICIO", referencedColumnName = "ID_SERVICIO", nullable = false)
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "ID_PUNTO", referencedColumnName = "ID_PUNTO", nullable = false)
    private PuntoGeografico punto;

    public PuntoLlegadaPK() {}

    public PuntoLlegadaPK(Servicio servicio, PuntoGeografico punto) {
        this.servicio = servicio;
        this.punto = punto;
    }

    // Getters y Setters
    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public PuntoGeografico getPunto() {
        return punto;
    }

    public void setPunto(PuntoGeografico punto) {
        this.punto = punto;
    }

    // hashCode y equals son OBLIGATORIOS en una PK embebida
    @Override
    public int hashCode() {
        return servicio.hashCode() + punto.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PuntoLlegadaPK)) return false;
        PuntoLlegadaPK other = (PuntoLlegadaPK) obj;
        return servicio.equals(other.servicio) && punto.equals(other.punto);
    }
}
