package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.DisponibilidadVehiculo;

import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;

@Repository
public interface DisponibilidadVehiculoRepository extends JpaRepository<DisponibilidadVehiculo, Integer> {

    // Obtener todas las disponibilidades
    @Query(value = "SELECT * FROM DISPONIBILIDAD_VEHICULO", nativeQuery = true)
    Collection<DisponibilidadVehiculo> darDisponibilidades();

    // Obtener disponibilidad por ID
    @Query(value = "SELECT * FROM DISPONIBILIDAD_VEHICULO WHERE ID_DISPONIBILIDAD = :id", nativeQuery = true)
    DisponibilidadVehiculo darDisponibilidad(@Param("id") Integer id);

    // Insertar disponibilidad
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO DISPONIBILIDAD_VEHICULO (ID_DISPONIBILIDAD, ID_VEHICULO, DIA, HORA_INICIO, HORA_FIN) " +
                   "VALUES (DISPONIBILIDAD_VEHICULO_SEQ.nextval, :idVehiculo, :dia, :horaInicio, :horaFin)", nativeQuery = true)
    void insertarDisponibilidad(@Param("idVehiculo") Integer idVehiculo,
                                @Param("dia") String dia,
                                @Param("horaInicio") Timestamp horaInicio,
                                @Param("horaFin") Timestamp horaFin);

    // Actualizar disponibilidad
    @Modifying
    @Transactional
    @Query(value = "UPDATE DISPONIBILIDAD_VEHICULO SET ID_VEHICULO = :idVehiculo, DIA = :dia, " +
                   "HORA_INICIO = :horaInicio, HORA_FIN = :horaFin WHERE ID_DISPONIBILIDAD = :id", nativeQuery = true)
    void actualizarDisponibilidad(@Param("id") Integer id,
                                  @Param("idVehiculo") Integer idVehiculo,
                                  @Param("dia") String dia,
                                  @Param("horaInicio") Timestamp horaInicio,
                                  @Param("horaFin") Timestamp horaFin);

    // Eliminar disponibilidad
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM DISPONIBILIDAD_VEHICULO WHERE ID_DISPONIBILIDAD = :id", nativeQuery = true)
    void eliminarDisponibilidad(@Param("id") Integer id);
}
