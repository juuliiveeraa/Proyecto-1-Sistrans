package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    @ManyToOne
    @JoinColumn(name = "id_conductor", referencedColumnName = "id_usuario", nullable = false)
    private UsuarioConductor conductor;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "color", nullable = false, length = 30)
    private String color;

    @Column(name = "placa", nullable = false, unique = true, length = 20)
    private String placa;

    @Column(name = "ciudad_expedicion", nullable = false, length = 50)
    private String ciudadExpedicion;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "nivel", nullable = false, length = 20)
    private String nivel;

    public Vehiculo() {}

    public Vehiculo(Integer idVehiculo, UsuarioConductor conductor, String tipo, String marca, 
                    String modelo, String color, String placa, String ciudadExpedicion, 
                    Integer capacidad, String nivel) {
        this.idVehiculo = idVehiculo;
        this.conductor = conductor;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.placa = placa;
        this.ciudadExpedicion = ciudadExpedicion;
        this.capacidad = capacidad;
        this.nivel = nivel;
    }

    // Getters y Setters
    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public UsuarioConductor getConductor() {
        return conductor;
    }

    public void setConductor(UsuarioConductor conductor) {
        this.conductor = conductor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCiudadExpedicion() {
        return ciudadExpedicion;
    }

    public void setCiudadExpedicion(String ciudadExpedicion) {
        this.ciudadExpedicion = ciudadExpedicion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
