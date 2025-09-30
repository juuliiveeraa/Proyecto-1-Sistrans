package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    // Obtener todos los servicios
    @GetMapping
    public ResponseEntity<Collection<Servicio>> darServicios() {
        return ResponseEntity.ok(servicioRepository.darServicios());
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> darServicio(@PathVariable Integer id) {
        Servicio servicio = servicioRepository.darServicio(id);
        if (servicio != null) {
            return ResponseEntity.ok(servicio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Insertar un nuevo servicio
    @PostMapping("/{idUsuario}/new/save")
    public ResponseEntity<?> solicitarServicio(
            @PathVariable Integer idUsuario,
            @RequestBody Servicio datosServicio) {

        try {
            // Insertar el servicio
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Convertir LocalDateTime a String
            String fechaSolicitudStr = datosServicio.getFechaSolicitud().format(formatter);

            // Llamar al repositorio con String en lugar de LocalDateTime
            servicioRepository.insertarServicio(
                idUsuario,
                datosServicio.getVehiculo().getIdVehiculo(),
                datosServicio.getPuntoInicio().getIdPunto(),
                fechaSolicitudStr,
                datosServicio.getCosto()
                , datosServicio.getDistancia(),
                datosServicio.getHoraInicio().format(formatter),
                datosServicio.getHoraFin().format(formatter),
                (Long) datosServicio.getDuracion()
            );

            // Recuperar el servicio recién insertado
            Servicio nuevoServicio = servicioRepository.buscarUltimoServicioPorUsuario(idUsuario);

            if (nuevoServicio == null) {
                return ResponseEntity.status(500)
                        .body(Collections.singletonMap("error", "No se pudo recuperar el servicio creado"));
            }

            // Construir JSON de respuesta
           Map<String, Object> response = new HashMap<>();
            response.put("id_servicio", nuevoServicio.getIdServicio());
            response.put("id_usuario", nuevoServicio.getUsuario().getIdUsuario());
            response.put("id_vehiculo", nuevoServicio.getVehiculo().getIdVehiculo());
            response.put("id_punto_inicio", nuevoServicio.getPuntoInicio().getIdPunto());
            // Convertir LocalDateTime a String antes de ponerlo en el Map
            response.put("fecha", nuevoServicio.getFechaSolicitud().format(formatter));
            response.put("costo", nuevoServicio.getCosto());
            response.put("mensaje", "Servicio creado con éxito");

            return ResponseEntity.status(201).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    // Actualizar un servicio
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarServicio(@PathVariable Integer id,
                                                     @RequestParam Integer idUsuario,
                                                     @RequestParam Integer idVehiculo,
                                                     @RequestParam Integer idPuntoInicio,
                                                     @RequestParam String fecha,
                                                     @RequestParam Double costo) {
        servicioRepository.actualizarServicio(id, idUsuario, idVehiculo, idPuntoInicio, fecha, costo);
        return ResponseEntity.ok("Servicio actualizado correctamente");
    }

    // Eliminar un servicio
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable Integer id) {
        servicioRepository.eliminarServicio(id);
        return ResponseEntity.ok("Servicio eliminado correctamente");
    }
}
