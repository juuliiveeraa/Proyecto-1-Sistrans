package uniandes.edu.co.proyecto.controller;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.DisponibilidadVehiculo;
import uniandes.edu.co.proyecto.repositorio.DisponibilidadVehiculoRepository;

@RestController
@RequestMapping("/disponibilidades-vehiculo")
public class DisponibilidadVehiculoController {

    @Autowired
    private DisponibilidadVehiculoRepository disponibilidadVehiculoRepository;

    // Obtener todas las disponibilidades
    @GetMapping
    public ResponseEntity<Collection<DisponibilidadVehiculo>> disponibilidades() {
        try {
            return ResponseEntity.ok(disponibilidadVehiculoRepository.darDisponibilidades());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener disponibilidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadVehiculo> disponibilidadPorId(@PathVariable("id") Integer id) {
        try {
            DisponibilidadVehiculo disponibilidad = disponibilidadVehiculoRepository.darDisponibilidad(id);
            return disponibilidad != null ? ResponseEntity.ok(disponibilidad) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Insertar nueva disponibilidad
    @PostMapping("/{id}/new/save")
    public ResponseEntity<?> disponibilidadGuardar(@RequestBody DisponibilidadVehiculo disponibilidad) {
        try {
            // Insertar disponibilidad
            disponibilidadVehiculoRepository.insertarDisponibilidad(
                    disponibilidad.getVehiculo().getIdVehiculo(),
                    disponibilidad.getDia(),
                    Timestamp.valueOf(disponibilidad.getHoraInicio()),
                    Timestamp.valueOf(disponibilidad.getHoraFin())
            );

            // Recuperar la disponibilidad recién insertada
            DisponibilidadVehiculo nuevaDisponibilidad = disponibilidadVehiculoRepository.buscarPorVehiculoDiaHora(
                    disponibilidad.getVehiculo().getIdVehiculo(),
                    disponibilidad.getDia(),
                    Timestamp.valueOf(disponibilidad.getHoraInicio())
            );

            if (nuevaDisponibilidad == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("error", "No se pudo recuperar la disponibilidad creada"));
            }

            // Construir JSON de respuesta
            Map<String, Object> response = new HashMap<>();
            response.put("id_disponibilidad", nuevaDisponibilidad.getIdDisponibilidad());
            response.put("id_vehiculo", nuevaDisponibilidad.getVehiculo().getIdVehiculo());
            response.put("dia", nuevaDisponibilidad.getDia());
            response.put("hora_inicio", nuevaDisponibilidad.getHoraInicio().toString());
            response.put("hora_fin", nuevaDisponibilidad.getHoraFin().toString());
            response.put("mensaje", "Disponibilidad creada con éxito");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    // Actualizar disponibilidad existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<?> disponibilidadEditarGuardar(@PathVariable("id") Integer id,
                                                         @RequestBody DisponibilidadVehiculo disponibilidad) {
        try {
            disponibilidadVehiculoRepository.actualizarDisponibilidad(
                    id,
                    disponibilidad.getVehiculo().getIdVehiculo(),
                    disponibilidad.getDia(),
                    Timestamp.valueOf(disponibilidad.getHoraInicio()),
                    Timestamp.valueOf(disponibilidad.getHoraFin())
            );
            return ResponseEntity.ok("Disponibilidad actualizada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar la disponibilidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar disponibilidad
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> disponibilidadBorrar(@PathVariable("id") Integer id) {
        try {
            disponibilidadVehiculoRepository.eliminarDisponibilidad(id);
            return ResponseEntity.ok("Disponibilidad eliminada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la disponibilidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
