package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Vehiculo;

import org.springframework.data.jpa.repository.Modifying;

import java.util.Collection;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    @Query(value = "SELECT * FROM VEHICULO", nativeQuery = true)
    Collection<Vehiculo> darVehiculos();

    @Query(value = "SELECT * FROM VEHICULO WHERE ID_VEHICULO = :id", nativeQuery = true)
    Vehiculo darVehiculo(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO VEHICULO (ID_VEHICULO, ID_CONDUCTOR, TIPO, MARCA, MODELO, COLOR, PLACA, CIUDAD_EXPEDICION, CAPACIDAD, NIVEL) " +
        "VALUES (VEHICULO_SEQ.NEXTVAL, :idConductor, :tipo, :marca, :modelo, :color, :placa, :ciudadExpedicion, :capacidad, :nivel)", 
        nativeQuery = true)
    void insertarVehiculo(@Param("idConductor") Integer idConductor,
        @Param("tipo") String tipo,
        @Param("marca") String marca,
        @Param("modelo") String modelo,
        @Param("color") String color,
        @Param("placa") String placa,
        @Param("ciudadExpedicion") String ciudadExpedicion,
        @Param("capacidad") Integer capacidad,
        @Param("nivel") String nivel);


    @Modifying
    @Transactional
    @Query(value = "UPDATE VEHICULO SET ID_CONDUCTOR = :idConductor, TIPO = :tipo, MARCA = :marca, MODELO = :modelo, COLOR = :color, " +
            "PLACA = :placa, CIUDAD_EXPEDICION = :ciudadExpedicion, CAPACIDAD = :capacidad, NIVEL = :nivel WHERE ID_VEHICULO = :idVehiculo", nativeQuery = true)
    void actualizarVehiculo(@Param("idVehiculo") Integer idVehiculo,
                            @Param("idConductor") Integer idConductor,
                            @Param("tipo") String tipo,
                            @Param("marca") String marca,
                            @Param("modelo") String modelo,
                            @Param("color") String color,
                            @Param("placa") String placa,
                            @Param("ciudadExpedicion") String ciudadExpedicion,
                            @Param("capacidad") Integer capacidad,
                            @Param("nivel") String nivel);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM VEHICULO WHERE ID_VEHICULO = :idVehiculo", nativeQuery = true)
    void eliminarVehiculo(@Param("idVehiculo") Integer idVehiculo);

    @Query(value = "SELECT * FROM VEHICULO WHERE PLACA = :placa", nativeQuery = true)
    Vehiculo darVehiculoPorPlaca(@Param("placa") String placa);

}
