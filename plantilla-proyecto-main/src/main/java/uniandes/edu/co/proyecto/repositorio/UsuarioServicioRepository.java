package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.UsuarioServicio;

import org.springframework.data.jpa.repository.Modifying;

import java.util.Collection;

public interface UsuarioServicioRepository extends JpaRepository<UsuarioServicio, Integer> {

    @Query(value = "SELECT * FROM USUARIO_SERVICIO", nativeQuery = true)
    Collection<UsuarioServicio> darUsuariosServicio();

    @Query(value = "SELECT * FROM USUARIO_SERVICIO WHERE ID_USUARIO = :id", nativeQuery = true)
    UsuarioServicio darUsuarioServicio(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIO_SERVICIO (ID_USUARIO, TIPO_SERVICIO, HISTORIAL) " +
            "VALUES (:idUsuario, :tipoServicio, :historial)", nativeQuery = true)
    void insertarUsuarioServicio(@Param("idUsuario") Integer idUsuario,
                                 @Param("tipoServicio") String tipoServicio,
                                 @Param("historial") String historial);

    @Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO_SERVICIO SET TIPO_SERVICIO = :tipoServicio, HISTORIAL = :historial " +
            "WHERE ID_USUARIO = :idUsuario", nativeQuery = true)
    void actualizarUsuarioServicio(@Param("idUsuario") Integer idUsuario,
                                   @Param("tipoServicio") String tipoServicio,
                                   @Param("historial") String historial);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM USUARIO_SERVICIO WHERE ID_USUARIO = :idUsuario", nativeQuery = true)
    void eliminarUsuarioServicio(@Param("idUsuario") Integer idUsuario);
}
