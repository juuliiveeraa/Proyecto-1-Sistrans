package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository;

import java.util.Collection;

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
    @PostMapping
    public ResponseEntity<String> insertarServicio(@RequestParam Integer idUsuario,
                                                   @RequestParam Integer idVehiculo,
                                                   @RequestParam Integer idPuntoInicio,
                                                   @RequestParam String fecha,
                                                   @RequestParam Double costo) {
        servicioRepository.insertarServicio(idUsuario, idVehiculo, idPuntoInicio, fecha, costo);
        return ResponseEntity.ok("Servicio insertado correctamente");
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
