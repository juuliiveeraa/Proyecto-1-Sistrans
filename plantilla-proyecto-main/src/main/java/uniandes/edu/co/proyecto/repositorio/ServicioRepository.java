package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Servicio;

import org.springframework.data.jpa.repository.Modifying;

import java.util.Collection;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    @Query(value = "SELECT * FROM SERVICIO", nativeQuery = true)
    Collection<Servicio> darServicios();

    @Query(value = "SELECT * FROM SERVICIO WHERE ID_SERVICIO = :id", nativeQuery = true)
    Servicio darServicio(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SERVICIO " +
        "(ID_SERVICIO, ID_USUARIO, ID_VEHICULO, ID_PUNTO_INICIO, FECHA_SOLICITUD, DISTANCIA, COSTO, HORA_INICIO, HORA_FIN, DURACION) " +
        "VALUES (" +
        "SERVICIO_SEQ.NEXTVAL, :idUsuario, :idVehiculo, :idPuntoInicio, " +
        "TO_TIMESTAMP(:fechaSolicitud, 'YYYY-MM-DD HH24:MI:SS'), " +
        ":distancia, :costo, :horaInicio, :horaFin, :duracion)", nativeQuery = true)
        void insertarServicio(
                @Param("idUsuario") Integer idUsuario,
                @Param("idVehiculo") Integer idVehiculo,
                @Param("idPuntoInicio") Integer idPuntoInicio,
                @Param("fechaSolicitud") String fechaSolicitud,
                @Param("distancia") Double distancia,
                @Param("costo") Double costo,
                @Param("horaInicio") String horaInicio,
                @Param("horaFin") String horaFin,
                @Param("duracion") Long duracion
        );


    @Modifying
    @Transactional
    @Query(value = "UPDATE SERVICIO SET ID_USUARIO = :idUsuario, ID_VEHICULO = :idVehiculo, ID_PUNTO_INICIO = :idPuntoInicio, FECHA = :fecha, COSTO = :costo WHERE ID_SERVICIO = :id", nativeQuery = true)
    void actualizarServicio(@Param("id") Integer id,
                            @Param("idUsuario") Integer idUsuario,
                            @Param("idVehiculo") Integer idVehiculo,
                            @Param("idPuntoInicio") Integer idPuntoInicio,
                            @Param("fecha") String fecha,
                            @Param("costo") Double costo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SERVICIO WHERE ID_SERVICIO = :id", nativeQuery = true)
    void eliminarServicio(@Param("id") Integer id);

    @Query(value = "SELECT * FROM SERVICIO WHERE ID_USUARIO = :idUsuario ORDER BY ID_SERVICIO DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    Servicio buscarUltimoServicioPorUsuario(@Param("idUsuario") Integer idUsuario);

}
