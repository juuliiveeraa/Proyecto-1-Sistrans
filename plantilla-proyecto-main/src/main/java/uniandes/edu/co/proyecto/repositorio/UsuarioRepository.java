package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Usuario;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Obtener todos los usuarios
    @Query(value = "SELECT * FROM USUARIO", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    // Obtener un usuario por ID
    @Query(value = "SELECT * FROM USUARIO WHERE ID_USUARIO = :id", nativeQuery = true)
    Usuario darUsuario(@Param("id") Integer id);

    // Insertar un usuario
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIO (ID_USUARIO, NOMBRE, CORREO, CLAVE) " +
                   "VALUES (USUARIO_SEQ.nextval, :nombre, :correo, :clave)", nativeQuery = true)
    void insertarUsuario(@Param("nombre") String nombre,
                         @Param("correo") String correo,
                         @Param("clave") String clave);

    // Actualizar un usuario
    @Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO SET NOMBRE = :nombre, CORREO = :correo, CLAVE = :clave " +
                   "WHERE ID_USUARIO = :id", nativeQuery = true)
    void actualizarUsuario(@Param("id") Integer id,
                           @Param("nombre") String nombre,
                           @Param("correo") String correo,
                           @Param("clave") String clave);

    // Eliminar un usuario
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM USUARIO WHERE ID_USUARIO = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") Integer id);
}