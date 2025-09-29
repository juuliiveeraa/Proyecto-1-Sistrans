package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.ServicioDomicilios;
import uniandes.edu.co.proyecto.repositorio.ServicioDomiciliosRepository;

import java.util.Collection;

@RestController
@RequestMapping("/servicios-domicilios")
public class ServicioDomiciliosController {

    @Autowired
    private ServicioDomiciliosRepository servicioDomiciliosRepository;

    // Obtener todos los servicios de domicilios
    @GetMapping
    public ResponseEntity<Collection<ServicioDomicilios>> darServiciosDomicilios() {
        return ResponseEntity.ok(servicioDomiciliosRepository.darServiciosDomicilios());
    }

    // Obtener un servicio de domicilio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicioDomicilios> darServicioDomicilio(@PathVariable Integer id) {
        ServicioDomicilios servicio = servicioDomiciliosRepository.darServicioDomicilio(id);
        if (servicio != null) {
            return ResponseEntity.ok(servicio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Insertar un nuevo servicio de domicilio
    @PostMapping
    public ResponseEntity<String> insertarServicioDomicilio(@RequestParam Integer idServicio,
                                                            @RequestParam String direccionEntrega,
                                                            @RequestParam String nombreReceptor) {
        servicioDomiciliosRepository.insertarServicioDomicilio(idServicio, direccionEntrega, nombreReceptor);
        return ResponseEntity.ok("Servicio de domicilio insertado correctamente");
    }

    // Actualizar un servicio de domicilio
    @PutMapping("/{idServicio}")
    public ResponseEntity<String> actualizarServicioDomicilio(@PathVariable Integer idServicio,
                                                              @RequestParam String direccionEntrega,
                                                              @RequestParam String nombreReceptor) {
        servicioDomiciliosRepository.actualizarServicioDomicilio(idServicio, direccionEntrega, nombreReceptor);
        return ResponseEntity.ok("Servicio de domicilio actualizado correctamente");
    }

    // Eliminar un servicio de domicilio
    @DeleteMapping("/{idServicio}")
    public ResponseEntity<String> eliminarServicioDomicilio(@PathVariable Integer idServicio) {
        servicioDomiciliosRepository.eliminarServicioDomicilio(idServicio);
        return ResponseEntity.ok("Servicio de domicilio eliminado correctamente");
    }
}
