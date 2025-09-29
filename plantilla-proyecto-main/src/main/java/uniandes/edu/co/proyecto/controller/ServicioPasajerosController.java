package uniandes.edu.co.proyecto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.ServicioPasajeros;
import uniandes.edu.co.proyecto.repositorio.ServicioPasajerosRepository;

import java.util.Collection;

@RestController
@RequestMapping("/servicios-pasajeros")
public class ServicioPasajerosController {

    @Autowired
    private ServicioPasajerosRepository servicioPasajerosRepository;

    // Obtener todos los servicios de pasajeros
    @GetMapping
    public ResponseEntity<Collection<ServicioPasajeros>> darServiciosPasajeros() {
        return ResponseEntity.ok(servicioPasajerosRepository.darServiciosPasajeros());
    }

    // Obtener un servicio de pasajeros por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicioPasajeros> darServicioPasajero(@PathVariable Integer id) {
        ServicioPasajeros servicio = servicioPasajerosRepository.darServicioPasajero(id);
        if (servicio != null) {
            return ResponseEntity.ok(servicio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Insertar un nuevo servicio de pasajeros
    @PostMapping
    public ResponseEntity<String> insertarServicioPasajero(@RequestParam Integer idServicio,
                                                           @RequestParam Integer numeroPasajeros,
                                                           @RequestParam String detalle) {
        servicioPasajerosRepository.insertarServicioPasajero(idServicio, numeroPasajeros, detalle);
        return ResponseEntity.ok("Servicio de pasajeros insertado correctamente");
    }

    // Actualizar un servicio de pasajeros
    @PutMapping("/{idServicio}")
    public ResponseEntity<String> actualizarServicioPasajero(@PathVariable Integer idServicio,
                                                             @RequestParam Integer numeroPasajeros,
                                                             @RequestParam String detalle) {
        servicioPasajerosRepository.actualizarServicioPasajero(idServicio, numeroPasajeros, detalle);
        return ResponseEntity.ok("Servicio de pasajeros actualizado correctamente");
    }

    // Eliminar un servicio de pasajeros
    @DeleteMapping("/{idServicio}")
    public ResponseEntity<String> eliminarServicioPasajero(@PathVariable Integer idServicio) {
        servicioPasajerosRepository.eliminarServicioPasajero(idServicio);
        return ResponseEntity.ok("Servicio de pasajeros eliminado correctamente");
    }
}
