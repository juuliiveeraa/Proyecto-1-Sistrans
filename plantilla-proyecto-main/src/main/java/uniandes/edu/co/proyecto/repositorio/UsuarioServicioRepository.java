package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.UsuarioServicio;

import org.springframework.data.jpa.repository.Modifying;

import java.sql.Date;
import java.util.Collection;

public interface UsuarioServicioRepository extends JpaRepository<UsuarioServicio, Integer> {

    @Query(value = "SELECT * FROM USUARIO_SERVICIO", nativeQuery = true)
    Collection<UsuarioServicio> darUsuariosServicio();

    @Query(value = "SELECT * FROM USUARIO_SERVICIO WHERE ID_USUARIO = :id", nativeQuery = true)
    UsuarioServicio darUsuarioServicio(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIO_SERVICIO (ID_USUARIO, TARJETA_NUMERO, TARJETA_NOMBRE, TARJETA_VENCIMIENTO, TARJETA_CODIGO_SEGURIDAD) " +
                   "VALUES (:idUsuario, :tarjetaNumero, :tarjetaNombre, :tarjetaVencimiento, :tarjetaCodigoSeguridad)", 
           nativeQuery = true)
    void insertarUsuarioServicio(@Param("idUsuario") Integer idUsuario,
                                 @Param("tarjetaNumero") String tarjetaNumero,
                                 @Param("tarjetaNombre") String tarjetaNombre,
                                 @Param("tarjetaVencimiento") Date tarjetaVencimiento,
                                 @Param("tarjetaCodigoSeguridad") String tarjetaCodigoSeguridad);
    @Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO_SERVICIO SET TARJETA_NUMERO = :tarjetaNumero, TARJETA_NOMBRE = :tarjetaNombre, " +
                   "TARJETA_VENCIMIENTO = :tarjetaVencimiento, TARJETA_CODIGO_SEGURIDAD = :tarjetaCodigoSeguridad " +
                   "WHERE ID_USUARIO = :idUsuario",
           nativeQuery = true)
    void actualizarUsuarioServicio(@Param("idUsuario") Integer idUsuario,
                                        @Param("tarjetaNumero") String tarjetaNumero,
                                        @Param("tarjetaNombre") String tarjetaNombre,
                                        @Param("tarjetaVencimiento") Date tarjetaVencimiento,
                                        @Param("tarjetaCodigoSeguridad") String tarjetaCodigoSeguridad);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM USUARIO_SERVICIO WHERE ID_USUARIO = :idUsuario", nativeQuery = true)
    void eliminarUsuarioServicio(@Param("idUsuario") Integer idUsuario);

    // Buscar usuario servicio por número de tarjeta
    @Query(value = "SELECT * FROM USUARIO_SERVICIO WHERE TARJETA_NUMERO = :tarjetaNumero", nativeQuery = true)
    UsuarioServicio buscarPorTarjeta(@Param("tarjetaNumero") String tarjetaNumero);

}
