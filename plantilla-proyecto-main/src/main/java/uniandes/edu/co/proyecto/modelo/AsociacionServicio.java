package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "asociacion_servicio")
public class AsociacionServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // porque en SQL está definido con IDENTITY
    @Column(name = "ID_ASOCIACION")
    private Long idAsociacion;

    @ManyToOne
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "SERVICIO", referencedColumnName = "ID_SERVICIO", nullable = false)
    private Servicio servicio;

    public AsociacionServicio() {}

    public AsociacionServicio(Usuario usuario, Servicio servicio) {
        this.usuario = usuario;
        this.servicio = servicio;
    }

    // Getters y Setters
    public Long getIdAsociacion() {
        return idAsociacion;
    }

    public void setIdAsociacion(Long idAsociacion) {
        this.idAsociacion = idAsociacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
