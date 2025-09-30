package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Duration;


@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @Column(name = "ID_SERVICIO")
    private Integer idServicio;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID_VEHICULO", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "ID_PUNTO_INICIO", referencedColumnName = "ID_PUNTO", nullable = false)
    private PuntoGeografico puntoInicio;

    @Column(name = "FECHA_SOLICITUD", nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(name = "DISTANCIA", nullable = false)
    private Double distancia;

    @Column(name = "COSTO", nullable = false)
    private Double costo;

    @Column(name = "HORA_INICIO", nullable = false)
    private LocalDateTime horaInicio;

    @Column(name = "HORA_FIN", nullable = false)
    private LocalDateTime horaFin;

    @Column(name = "DURACION", nullable = false)
    private Long duracion;

    // Getters y Setters
    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public PuntoGeografico getPuntoInicio() {
        return puntoInicio;
    }

    public void setPuntoInicio(PuntoGeografico puntoInicio) {
        this.puntoInicio = puntoInicio;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }
}
