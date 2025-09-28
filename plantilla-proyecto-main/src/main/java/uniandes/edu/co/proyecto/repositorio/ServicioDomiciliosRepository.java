package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ServicioDomicilios;

import org.springframework.data.jpa.repository.Modifying;

import java.util.Collection;

public interface ServicioDomiciliosRepository extends JpaRepository<ServicioDomicilios, Integer> {

    @Query(value = "SELECT * FROM SERVICIO_DOMICILIOS", nativeQuery = true)
    Collection<ServicioDomicilios> darServiciosDomicilios();

    @Query(value = "SELECT * FROM SERVICIO_DOMICILIOS WHERE ID_SERVICIO = :id", nativeQuery = true)
    ServicioDomicilios darServicioDomicilio(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SERVICIO_DOMICILIOS (ID_SERVICIO, DIRECCION_ENTREGA, NOMBRE_RECEPTOR) " +
            "VALUES (:idServicio, :direccionEntrega, :nombreReceptor)", nativeQuery = true)
    void insertarServicioDomicilio(@Param("idServicio") Integer idServicio,
                                   @Param("direccionEntrega") String direccionEntrega,
                                   @Param("nombreReceptor") String nombreReceptor);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SERVICIO_DOMICILIOS SET DIRECCION_ENTREGA = :direccionEntrega, NOMBRE_RECEPTOR = :nombreReceptor WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    void actualizarServicioDomicilio(@Param("idServicio") Integer idServicio,
                                     @Param("direccionEntrega") String direccionEntrega,
                                     @Param("nombreReceptor") String nombreReceptor);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SERVICIO_DOMICILIOS WHERE ID_SERVICIO = :idServicio", nativeQuery = true)
    void eliminarServicioDomicilio(@Param("idServicio") Integer idServicio);
}
