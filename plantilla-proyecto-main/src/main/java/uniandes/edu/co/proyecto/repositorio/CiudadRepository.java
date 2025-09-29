package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Ciudad;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

    // Obtener todas las ciudades
    @Query(value = "SELECT * FROM CIUDAD", nativeQuery = true)
    Collection<Ciudad> darCiudades();

    // Obtener ciudad por ID
    @Query(value = "SELECT * FROM CIUDAD WHERE NOMBRE = :nombre", nativeQuery = true)
    Ciudad darCiudadNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT * FROM CIUDAD WHERE ID_CIUDAD = :id", nativeQuery = true)
    Ciudad darCiudad(@Param("id") Integer id);

    // Insertar ciudad
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CIUDAD (ID_CIUDAD, NOMBRE) " +
                   "VALUES (CIUDAD_SEQ.nextval, :nombre)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre);

    // Actualizar ciudad
    @Modifying
    @Transactional
    @Query(value = "UPDATE CIUDAD SET NOMBRE = :nombre WHERE ID_CIUDAD = :id", nativeQuery = true)
    void actualizarCiudad(@Param("id") Integer id,
                          @Param("nombre") String nombre);

    // Eliminar ciudad
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CIUDAD WHERE ID_CIUDAD = :id", nativeQuery = true)
    void eliminarCiudad(@Param("id") Integer id);
}