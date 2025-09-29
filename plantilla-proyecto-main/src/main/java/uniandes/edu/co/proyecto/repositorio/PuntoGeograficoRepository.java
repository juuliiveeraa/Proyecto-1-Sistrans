package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.PuntoGeografico;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PuntoGeograficoRepository extends JpaRepository<PuntoGeografico, Integer> {

    // Obtener todos los puntos
    @Query(value = "SELECT * FROM PUNTO_GEOGRAFICO", nativeQuery = true)
    Collection<PuntoGeografico> darPuntos();

    // Obtener punto por ID
    @Query(value = "SELECT * FROM PUNTO_GEOGRAFICO WHERE ID_PUNTO = :id", nativeQuery = true)
    PuntoGeografico darPunto(@Param("id") Integer id);

    // Insertar punto
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PUNTO_GEOGRAFICO (ID_PUNTO, NOMBRE, DIRECCION, COORDENADAS, ID_CIUDAD) " +
                   "VALUES (PUNTO_GEOGRAFICO_SEQ.NEXTVAL, :nombre, :direccion, :coordenadas, :idCiudad)", nativeQuery = true)
    void insertarPunto(@Param("nombre") String nombre,
                       @Param("direccion") String direccion,
                       @Param("coordenadas") String coordenadas,
                       @Param("idCiudad") Integer idCiudad);

    // Actualizar punto
    @Modifying
    @Transactional
    @Query(value = "UPDATE PUNTO_GEOGRAFICO SET NOMBRE = :nombre, DIRECCION = :direccion, " +
                   "COORDENADAS = :coordenadas, ID_CIUDAD = :idCiudad WHERE ID_PUNTO = :id", nativeQuery = true)
    void actualizarPunto(@Param("id") Integer id,
                         @Param("nombre") String nombre,
                         @Param("direccion") String direccion,
                         @Param("coordenadas") String coordenadas,
                         @Param("idCiudad") Integer idCiudad);

    // Eliminar punto
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PUNTO_GEOGRAFICO WHERE ID_PUNTO = :id", nativeQuery = true)
    void eliminarPunto(@Param("id") Integer id);

    // Buscar por nombre único
    @Query(value = "SELECT * FROM PUNTO_GEOGRAFICO WHERE NOMBRE = :nombre", nativeQuery = true)
    PuntoGeografico buscarPorNombre(@Param("nombre") String nombre);

}
