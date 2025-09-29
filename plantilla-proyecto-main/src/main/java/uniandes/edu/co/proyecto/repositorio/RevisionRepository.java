package uniandes.edu.co.proyecto.repositorio;



import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Revision;

public interface RevisionRepository extends JpaRepository<Revision, Integer> {

    @Query(value = "SELECT * FROM REVISION", nativeQuery = true)
    Collection<Revision> darRevisiones();

    @Query(value = "SELECT * FROM REVISION WHERE ID_REVISION = :id", nativeQuery = true)
    Revision darRevision(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO REVISION (ID_REVISION, ID_SERVICIO, ID_EVALUADOR, ID_EVALUADO, CALIFICACION, COMENTARIO) " +
            "VALUES (REVISION_SEQ.NEXTVAL, :idServicio, :idEvaluador, :idEvaluado, :calificacion, :comentario)", nativeQuery = true)
    void insertarRevision(@Param("idServicio") Integer idServicio,
                          @Param("idEvaluador") Integer idEvaluador,
                          @Param("idEvaluado") Integer idEvaluado,
                          @Param("calificacion") Integer calificacion,
                          @Param("comentario") String comentario);

    @Modifying
    @Transactional
    @Query(value = "UPDATE REVISION SET CALIFICACION = :calificacion, COMENTARIO = :comentario WHERE ID_REVISION = :id", nativeQuery = true)
    void actualizarRevision(@Param("id") Integer id,
                            @Param("calificacion") Integer calificacion,
                            @Param("comentario") String comentario);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM REVISION WHERE ID_REVISION = :id", nativeQuery = true)
    void eliminarRevision(@Param("id") Integer id);
}
