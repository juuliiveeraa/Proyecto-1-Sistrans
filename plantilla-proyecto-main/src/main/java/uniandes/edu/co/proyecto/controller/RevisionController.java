package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Revision;
import uniandes.edu.co.proyecto.repositorio.RevisionRepository;

import java.util.Collection;

@RestController
@RequestMapping("/revisiones")
public class RevisionController {

    @Autowired
    private RevisionRepository revisionRepository;

    // Obtener todas las revisiones
    @GetMapping
    public ResponseEntity<Collection<Revision>> darRevisiones() {
        return ResponseEntity.ok(revisionRepository.darRevisiones());
    }

    // Obtener una revisión por ID
    @GetMapping("/{id}")
    public ResponseEntity<Revision> darRevision(@PathVariable Integer id) {
        Revision revision = revisionRepository.darRevision(id);
        if (revision != null) {
            return ResponseEntity.ok(revision);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Insertar una nueva revisión
    @PostMapping
    public ResponseEntity<String> insertarRevision(@RequestParam Integer idServicio,
                                                   @RequestParam Integer idEvaluador,
                                                   @RequestParam Integer idEvaluado,
                                                   @RequestParam Integer calificacion,
                                                   @RequestParam String comentario) {
        revisionRepository.insertarRevision(idServicio, idEvaluador, idEvaluado, calificacion, comentario);
        return ResponseEntity.ok("Revisión insertada correctamente");
    }

    // Actualizar una revisión existente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarRevision(@PathVariable Integer id,
                                                     @RequestParam Integer calificacion,
                                                     @RequestParam String comentario) {
        revisionRepository.actualizarRevision(id, calificacion, comentario);
        return ResponseEntity.ok("Revisión actualizada correctamente");
    }

    // Eliminar una revisión por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRevision(@PathVariable Integer id) {
        revisionRepository.eliminarRevision(id);
        return ResponseEntity.ok("Revisión eliminada correctamente");
    }
}