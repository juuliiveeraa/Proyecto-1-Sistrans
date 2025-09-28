package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.PuntoLlegada;
import uniandes.edu.co.proyecto.modelo.PuntoLlegadaPK;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PuntoLlegadaRepository extends JpaRepository<PuntoLlegada, PuntoLlegadaPK> {

    // Obtener todos los puntos de llegada
    @Query(value = "SELECT * FROM PUNTO_LLEGADA", nativeQuery = true)
    Collection<PuntoLlegada> darPuntosLlegada();

    // Obtener un punto de llegada por IDs
    @Query(value = "SELECT * FROM PUNTO_LLEGADA WHERE ID_SERVICIO = :idServicio AND ID_PUNTO = :idPunto", nativeQuery = true)
    PuntoLlegada darPuntoLlegada(@Param("idServicio") Integer idServicio,
                                 @Param("idPunto") Integer idPunto);

    // Insertar punto de llegada
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PUNTO_LLEGADA (ID_SERVICIO, ID_PUNTO) " +
                   "VALUES (:idServicio, :idPunto)", nativeQuery = true)
    void insertarPuntoLlegada(@Param("idServicio") Integer idServicio,
                              @Param("idPunto") Integer idPunto);

    // Eliminar punto de llegada
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PUNTO_LLEGADA WHERE ID_SERVICIO = :idServicio AND ID_PUNTO = :idPunto", nativeQuery = true)
    void eliminarPuntoLlegada(@Param("idServicio") Integer idServicio,
                              @Param("idPunto") Integer idPunto);
}
