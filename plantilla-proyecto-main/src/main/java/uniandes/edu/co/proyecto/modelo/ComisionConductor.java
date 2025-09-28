package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "comision_conductor")
public class ComisionConductor {
    @Id
    @Column(name = "id_comision")
    private Integer idComision;

    @ManyToOne
    @JoinColumn(name = "id_usuario_conductor", referencedColumnName = "id_usuario", nullable = false)
    private UsuarioConductor usuarioConductor;

    @Column(name = "monto", nullable = false)
    private Integer monto;

    public ComisionConductor() {}

    public ComisionConductor(Integer idComision, UsuarioConductor usuarioConductor, Integer monto) {
        this.idComision = idComision;
        this.usuarioConductor = usuarioConductor;
        this.monto = monto;
    }

    // Getters y setters
    public Integer getIdComision() {
        return idComision;
    }

    public void setIdComision(Integer idComision) {
        this.idComision = idComision;
    }

    public UsuarioConductor getUsuarioConductor() {
        return usuarioConductor;
    }

    public void setUsuarioConductor(UsuarioConductor usuarioConductor) {
        this.usuarioConductor = usuarioConductor;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }
}
