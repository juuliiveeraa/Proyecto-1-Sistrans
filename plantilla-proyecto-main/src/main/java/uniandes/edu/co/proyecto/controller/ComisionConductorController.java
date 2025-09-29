package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.repositorio.ComisionConductorRepository;
import uniandes.edu.co.proyecto.modelo.ComisionConductor;

@RestController
@RequestMapping("/comisiones-conductor")
public class ComisionConductorController {

    @Autowired
    private ComisionConductorRepository comisionConductorRepository;

    // Obtener todas las comisiones
    @GetMapping
    public ResponseEntity<Collection<ComisionConductor>> comisiones() {
        try {
            return ResponseEntity.ok(comisionConductorRepository.darComisiones());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener comisión por ID
    @GetMapping("/{id}")
    public ResponseEntity<ComisionConductor> comisionPorId(@PathVariable("id") Integer id) {
        try {
            ComisionConductor comision = comisionConductorRepository.darComision(id);
            return comision != null ? ResponseEntity.ok(comision) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Insertar nueva comisión
    @PostMapping("/new/save")
    public ResponseEntity<?> comisionGuardar(@RequestBody ComisionConductor comision) {
        try {
            comisionConductorRepository.insertarComision(
                    comision.getUsuarioConductor().getIdUsuario(),
                    comision.getMonto()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Comisión creada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la comisión", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar comisión existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<?> comisionEditarGuardar(@PathVariable("id") Integer id, @RequestBody ComisionConductor comision) {
        try {
            comisionConductorRepository.actualizarComision(
                    id,
                    comision.getUsuarioConductor().getIdUsuario(),
                    comision.getMonto()
            );
            return ResponseEntity.ok("Comisión actualizada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar la comisión", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar comisión
    @GetMapping("/{id}/delete")
    public ResponseEntity<?> comisionBorrar(@PathVariable("id") Integer id) {
        try {
            comisionConductorRepository.eliminarComision(id);
            return ResponseEntity.ok("Comisión eliminada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la comisión", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}