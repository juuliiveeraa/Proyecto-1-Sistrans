package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ServicioPasajeros;

import org.springframework.data.jpa.repository.Modifying;

import java.util.Collection;

public interface ServicioPasajerosRepository extends JpaRepository<ServicioPasajeros, Integer> {

    @Query(value = "SELECT * FROM SERVICIO_PASAJEROS", nativeQuery = true)
    Collection<ServicioPasajeros> darServiciosPasajeros();

    @Query(value = "SELECT * FROM SERVICIO_PASAJEROS WHERE ID_SERVICIO = :id", nativeQuery = true)
    ServicioPasajeros darServicioPasajero(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SERVICIO_PASAJEROS (ID_SERVICIO, NUMERO_PASAJEROS, DETALLE) " +
            "VALUES (:idServicio, :numeroPasajeros, :detalle)", nativeQuery = true)
    void insertarServicioPasajero(@Param("idServicio") Integer idServicio,
                                  @Param("numeroPasajeros") Integer numeroPasajeros,
                                  @Param("detalle") String detalle);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SERVICIO_PASAJEROS SET NUMERO_PASAJEROS = :numeroPasajeros, DETALLE = :detalle WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    void actualizarServicioPasajero(@Param("idServicio") Integer idServicio,
                                    @Param("numeroPasajeros") Integer numeroPasajeros,
                                    @Param("detalle") String detalle);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SERVICIO_PASAJEROS WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    void eliminarServicioPasajero(@Param("idServicio") Integer idServicio);
}