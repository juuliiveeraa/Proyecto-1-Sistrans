package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.AsociacionServicio;
import uniandes.edu.co.proyecto.repositorio.AsociacionServicioRepository;

@RestController
@RequestMapping("/asociaciones-servicio")
public class AsociacionServicioController {

    @Autowired
    private AsociacionServicioRepository asociacionServicioRepository;

    // Obtener todas las asociaciones
    @GetMapping
    public ResponseEntity<Collection<AsociacionServicio>> asociaciones() {
        try {
            return ResponseEntity.ok(asociacionServicioRepository.darAsociaciones());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una asociación por ID
    @GetMapping("/{id}")
    public ResponseEntity<AsociacionServicio> asociacionPorId(@PathVariable("id") Integer id) {
        try {
            AsociacionServicio asociacion = asociacionServicioRepository.darAsociacion(id);
            return asociacion != null ? ResponseEntity.ok(asociacion) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Insertar nueva asociación
    @PostMapping("/new/save")
    public ResponseEntity<?> asociacionGuardar(@RequestBody AsociacionServicio asociacion) {
        try {
            asociacionServicioRepository.insertarAsociacion(
                    asociacion.getUsuario().getIdUsuario(),
                    asociacion.getServicio().getIdServicio()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Asociación creada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la asociación", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar asociación existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<?> asociacionEditarGuardar(@PathVariable("id") Integer id, @RequestBody AsociacionServicio asociacion) {
        try {
            asociacionServicioRepository.actualizarAsociacion(
                    id,
                    asociacion.getUsuario().getIdUsuario(),
                    asociacion.getServicio().getIdServicio()
            );
            return ResponseEntity.ok("Asociación actualizada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar la asociación", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar asociación
    @GetMapping("/{id}/delete")
    public ResponseEntity<?> asociacionBorrar(@PathVariable("id") Integer id) {
        try {
            asociacionServicioRepository.eliminarAsociacion(id);
            return ResponseEntity.ok("Asociación eliminada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la asociación", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}