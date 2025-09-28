package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "revision")
public class Revision {
    @Id
    @Column(name = "ID_REVISION")
    private Integer idRevision;

    @ManyToOne
    @JoinColumn(name = "ID_EVALUADOR", referencedColumnName = "ID_USUARIO", nullable = false)
    private Usuario evaluador;

    @ManyToOne
    @JoinColumn(name = "ID_EVALUADO", referencedColumnName = "ID_USUARIO", nullable = false)
    private Usuario evaluado;

    @ManyToOne
    @JoinColumn(name = "ID_SERVICIO", referencedColumnName = "ID_SERVICIO", nullable = false)
    private Servicio servicio;

    @Column(name = "CALIFICACION")
    private Double calificacion;

    @Column(name = "COMENTARIO", length = 500)
    private String comentario;

    // Getters y Setters
    public Integer getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(Integer idRevision) {
        this.idRevision = idRevision;
    }

    public Usuario getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(Usuario evaluador) {
        this.evaluador = evaluador;
    }

    public Usuario getEvaluado() {
        return evaluado;
    }

    public void setEvaluado(Usuario evaluado) {
        this.evaluado = evaluado;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
