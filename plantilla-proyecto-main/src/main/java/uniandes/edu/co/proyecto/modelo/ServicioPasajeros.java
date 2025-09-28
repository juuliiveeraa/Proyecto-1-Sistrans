package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicio_pasajeros")
public class ServicioPasajeros {
    @Id
    @Column(name = "ID_SERVICIO")
    private Integer idServicio;

    @OneToOne
    @JoinColumn(name = "ID_SERVICIO", referencedColumnName = "ID_SERVICIO", nullable = false, insertable = false, updatable = false)
    private Servicio servicio;

    @Column(name = "NIVEL_TRANSPORTE", nullable = false, length = 20)
    private String nivelTransporte;

    @Column(name = "PASAJEROS", nullable = false)
    private Integer pasajeros;

    // Getters y Setters
    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getNivelTransporte() {
        return nivelTransporte;
    }

    public void setNivelTransporte(String nivelTransporte) {
        this.nivelTransporte = nivelTransporte;
    }

    public Integer getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(Integer pasajeros) {
        this.pasajeros = pasajeros;
    }
}
