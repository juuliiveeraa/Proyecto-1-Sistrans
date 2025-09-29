package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.UsuarioConductor;

import org.springframework.data.jpa.repository.Modifying;

import java.util.Collection;

public interface UsuarioConductorRepository extends JpaRepository<UsuarioConductor, Integer> {

    // Obtener todos los registros de usuario_conductor
    @Query(value = "SELECT * FROM USUARIO_CONDUCTOR", nativeQuery = true)
    Collection<UsuarioConductor> darUsuariosConductores();

    // Obtener un usuario_conductor por ID de usuario
    @Query(value = "SELECT * FROM USUARIO_CONDUCTOR WHERE ID_USUARIO = :id", nativeQuery = true)
    UsuarioConductor darUsuarioConductor(@Param("id") Integer id);

    // Insertar un usuario_conductor vinculando un usuario existente
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIO_CONDUCTOR (ID_USUARIO) VALUES (:idUsuario)", nativeQuery = true)
    void insertarUsuarioConductor(@Param("idUsuario") Integer idUsuario);

    // Actualizar un usuario_conductor -> sin campos adicionales, este método puede ser innecesario
    // Podrías usarlo si en el futuro agregas más atributos
    @Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO_CONDUCTOR SET ID_USUARIO = :idUsuario WHERE ID_USUARIO = :idUsuario", nativeQuery = true)
    void actualizarUsuarioConductor(@Param("idUsuario") Integer idUsuario);

    // Eliminar un usuario_conductor
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM USUARIO_CONDUCTOR WHERE ID_USUARIO = :idUsuario", nativeQuery = true)
    void eliminarUsuarioConductor(@Param("idUsuario") Integer idUsuario);
}
