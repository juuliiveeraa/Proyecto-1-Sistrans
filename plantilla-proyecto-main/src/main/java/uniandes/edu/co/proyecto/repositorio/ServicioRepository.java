package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Servicio;

import org.springframework.data.jpa.repository.Modifying;

import java.util.Collection;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    @Query(value = "SELECT * FROM SERVICIO", nativeQuery = true)
    Collection<Servicio> darServicios();

    @Query(value = "SELECT * FROM SERVICIO WHERE ID_SERVICIO = :id", nativeQuery = true)
    Servicio darServicio(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SERVICIO (ID_SERVICIO, ID_USUARIO, ID_VEHICULO, ID_PUNTO_INICIO, FECHA, COSTO) " +
            "VALUES (SERVICIO_SEQ.NEXTVAL, :idUsuario, :idVehiculo, :idPuntoInicio, :fecha, :costo)", nativeQuery = true)
    void insertarServicio(@Param("idUsuario") Integer idUsuario,
                          @Param("idVehiculo") Integer idVehiculo,
                          @Param("idPuntoInicio") Integer idPuntoInicio,
                          @Param("fecha") String fecha,
                          @Param("costo") Double costo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SERVICIO SET ID_USUARIO = :idUsuario, ID_VEHICULO = :idVehiculo, ID_PUNTO_INICIO = :idPuntoInicio, FECHA = :fecha, COSTO = :costo WHERE ID_SERVICIO = :id", nativeQuery = true)
    void actualizarServicio(@Param("id") Integer id,
                            @Param("idUsuario") Integer idUsuario,
                            @Param("idVehiculo") Integer idVehiculo,
                            @Param("idPuntoInicio") Integer idPuntoInicio,
                            @Param("fecha") String fecha,
                            @Param("costo") Double costo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SERVICIO WHERE ID_SERVICIO = :id", nativeQuery = true)
    void eliminarServicio(@Param("id") Integer id);
}
