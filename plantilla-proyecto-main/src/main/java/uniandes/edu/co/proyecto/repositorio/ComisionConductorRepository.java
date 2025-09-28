package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ComisionConductor;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ComisionConductorRepository extends JpaRepository<ComisionConductor, Integer> {

    // Obtener todas las comisiones
    @Query(value = "SELECT * FROM COMISION_CONDUCTOR", nativeQuery = true)
    Collection<ComisionConductor> darComisiones();

    // Obtener comisión por ID
    @Query(value = "SELECT * FROM COMISION_CONDUCTOR WHERE ID_COMISION = :id", nativeQuery = true)
    ComisionConductor darComision(@Param("id") Integer id);

    // Insertar comisión
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO COMISION_CONDUCTOR (ID_COMISION, ID_USUARIO_CONDUCTOR, MONTO) " +
                   "VALUES (COMISION_CONDUCTOR_SEQ.nextval, :idConductor, :monto)", nativeQuery = true)
    void insertarComision(@Param("idConductor") Integer idConductor,
                          @Param("monto") Double monto);

    // Actualizar comisión
    @Modifying
    @Transactional
    @Query(value = "UPDATE COMISION_CONDUCTOR SET ID_USUARIO_CONDUCTOR = :idConductor, MONTO = :monto " +
                   "WHERE ID_COMISION = :id", nativeQuery = true)
    void actualizarComision(@Param("id") Integer id,
                            @Param("idConductor") Integer idConductor,
                            @Param("monto") Double monto);

    // Eliminar comisión
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM COMISION_CONDUCTOR WHERE ID_COMISION = :id", nativeQuery = true)
    void eliminarComision(@Param("id") Integer id);
}
