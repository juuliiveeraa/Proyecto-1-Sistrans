package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ServicioMercancias;

import org.springframework.data.jpa.repository.Modifying;

import java.util.Collection;

public interface ServicioMercanciasRepository extends JpaRepository<ServicioMercancias, Integer> {

    @Query(value = "SELECT * FROM SERVICIO_MERCANCIAS", nativeQuery = true)
    Collection<ServicioMercancias> darServiciosMercancias();

    @Query(value = "SELECT * FROM SERVICIO_MERCANCIAS WHERE ID_SERVICIO = :id", nativeQuery = true)
    ServicioMercancias darServicioMercancia(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SERVICIO_MERCANCIAS (ID_SERVICIO, TIPO_MERCANCIA, PESO) " +
            "VALUES (:idServicio, :tipoMercancia, :peso)", nativeQuery = true)
    void insertarServicioMercancia(@Param("idServicio") Integer idServicio,
                                   @Param("tipoMercancia") String tipoMercancia,
                                   @Param("peso") Double peso);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SERVICIO_MERCANCIAS SET TIPO_MERCANCIA = :tipoMercancia, PESO = :peso WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    void actualizarServicioMercancia(@Param("idServicio") Integer idServicio,
                                     @Param("tipoMercancia") String tipoMercancia,
                                     @Param("peso") Double peso);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SERVICIO_MERCANCIAS WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    void eliminarServicioMercancia(@Param("idServicio") Integer idServicio);
}
