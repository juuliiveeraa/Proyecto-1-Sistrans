package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "USUARIO_SERVICIO")
public class UsuarioServicio {
    @Id
    private Integer idUsuario;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO ")
    private Usuario usuario;


    private String tarjetaNumero;

    private String tarjetaNombre;

    @Temporal(TemporalType.DATE)
    private Date tarjetaVencimiento;

    private String tarjetaCodigoSeguridad;

    public UsuarioServicio() {}

    public UsuarioServicio(Integer idUsuario, Usuario usuario, String tarjetaNumero, 
                           String tarjetaNombre, Date tarjetaVencimiento, 
                           String tarjetaCodigoSeguridad) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.tarjetaNumero = tarjetaNumero;
        this.tarjetaNombre = tarjetaNombre;
        this.tarjetaVencimiento = tarjetaVencimiento;
        this.tarjetaCodigoSeguridad = tarjetaCodigoSeguridad;
    }

    // Getters y setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTarjetaNumero() {
        return tarjetaNumero;
    }

    public void setTarjetaNumero(String tarjetaNumero) {
        this.tarjetaNumero = tarjetaNumero;
    }

    public String getTarjetaNombre() {
        return tarjetaNombre;
    }

    public void setTarjetaNombre(String tarjetaNombre) {
        this.tarjetaNombre = tarjetaNombre;
    }

    public Date getTarjetaVencimiento() {
        return tarjetaVencimiento;
    }

    public void setTarjetaVencimiento(Date tarjetaVencimiento) {
        this.tarjetaVencimiento = tarjetaVencimiento;
    }

    public String getTarjetaCodigoSeguridad() {
        return tarjetaCodigoSeguridad;
    }

    public void setTarjetaCodigoSeguridad(String tarjetaCodigoSeguridad) {
        this.tarjetaCodigoSeguridad = tarjetaCodigoSeguridad;
    } 
}
