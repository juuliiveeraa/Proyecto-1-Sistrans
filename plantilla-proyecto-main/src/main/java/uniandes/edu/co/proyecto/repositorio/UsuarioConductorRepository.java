package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.UsuarioConductor;

import org.springframework.data.jpa.repository.Modifying;

import java.util.Collection;

public interface UsuarioConductorRepository extends JpaRepository<UsuarioConductor, Integer> {

    @Query(value = "SELECT * FROM USUARIO_CONDUCTOR", nativeQuery = true)
    Collection<UsuarioConductor> darUsuariosConductores();

    @Query(value = "SELECT * FROM USUARIO_CONDUCTOR WHERE ID_USUARIO = :id", nativeQuery = true)
    UsuarioConductor darUsuarioConductor(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIO_CONDUCTOR (ID_USUARIO, LICENCIA, EXPERIENCIA) " +
            "VALUES (:idUsuario, :licencia, :experiencia)", nativeQuery = true)
    void insertarUsuarioConductor(@Param("idUsuario") Integer idUsuario,
                                  @Param("licencia") String licencia,
                                  @Param("experiencia") Integer experiencia);

    @Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO_CONDUCTOR SET LICENCIA = :licencia, EXPERIENCIA = :experiencia WHERE ID_USUARIO = :idUsuario", nativeQuery = true)
    void actualizarUsuarioConductor(@Param("idUsuario") Integer idUsuario,
                                    @Param("licencia") String licencia,
                                    @Param("experiencia") Integer experiencia);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM USUARIO_CONDUCTOR WHERE ID_USUARIO = :idUsuario", nativeQuery = true)
    void eliminarUsuarioConductor(@Param("idUsuario") Integer idUsuario);
}
