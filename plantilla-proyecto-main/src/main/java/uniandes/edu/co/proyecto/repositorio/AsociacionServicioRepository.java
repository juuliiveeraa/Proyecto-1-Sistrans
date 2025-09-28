package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.AsociacionServicio;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AsociacionServicioRepository extends JpaRepository<AsociacionServicio, Integer> {

    // Obtener todas las asociaciones
    @Query(value = "SELECT * FROM ASOCIACION_SERVICIO", nativeQuery = true)
    Collection<AsociacionServicio> darAsociaciones();

    // Obtener una asociación por ID
    @Query(value = "SELECT * FROM ASOCIACION_SERVICIO WHERE ID_ASOCIACION = :id", nativeQuery = true)
    AsociacionServicio darAsociacion(@Param("id") Integer id);

    // Insertar una asociación
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ASOCIACION_SERVICIO (ID_ASOCIACION, USUARIO, SERVICIO) " +
                   "VALUES (ASOCIACION_SERVICIO_SEQ.nextval, :usuario, :servicio)", nativeQuery = true)
    void insertarAsociacion(@Param("usuario") Integer usuario,
                            @Param("servicio") Integer servicio);

    // Actualizar una asociación
    @Modifying
    @Transactional
    @Query(value = "UPDATE ASOCIACION_SERVICIO SET USUARIO = :usuario, SERVICIO = :servicio " +
                   "WHERE ID_ASOCIACION = :id", nativeQuery = true)
    void actualizarAsociacion(@Param("id") Integer id,
                              @Param("usuario") Integer usuario,
                              @Param("servicio") Integer servicio);

    // Eliminar una asociación
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ASOCIACION_SERVICIO WHERE ID_ASOCIACION = :id", nativeQuery = true)
    void eliminarAsociacion(@Param("id") Integer id);
}
