package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicio_mercancias")
public class ServicioMercancias {
    @Id
    @Column(name = "ID_SERVICIO")
    private Integer idServicio;

    @OneToOne
    @JoinColumn(
        name = "ID_SERVICIO",
        referencedColumnName = "ID_SERVICIO",
        nullable = false,
        insertable = false,
        updatable = false
    )
    private Servicio servicio;

    @Column(name = "DESCRIPCION", nullable = false, length = 200)
    private String descripcion;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
