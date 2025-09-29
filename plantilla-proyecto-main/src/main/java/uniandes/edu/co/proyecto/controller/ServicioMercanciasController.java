package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.ServicioMercancias;
import uniandes.edu.co.proyecto.repositorio.ServicioMercanciasRepository;

import java.util.Collection;

@RestController
@RequestMapping("/servicios-mercancias")
public class ServicioMercanciasController {

    @Autowired
    private ServicioMercanciasRepository servicioMercanciasRepository;

    // Obtener todos los servicios de mercancías
    @GetMapping
    public ResponseEntity<Collection<ServicioMercancias>> darServiciosMercancias() {
        return ResponseEntity.ok(servicioMercanciasRepository.darServiciosMercancias());
    }

    // Obtener un servicio de mercancías por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicioMercancias> darServicioMercancia(@PathVariable Integer id) {
        ServicioMercancias servicio = servicioMercanciasRepository.darServicioMercancia(id);
        if (servicio != null) {
            return ResponseEntity.ok(servicio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Insertar un nuevo servicio de mercancías
    @PostMapping
    public ResponseEntity<String> insertarServicioMercancia(@RequestParam Integer idServicio,
                                                            @RequestParam String tipoMercancia,
                                                            @RequestParam Double peso) {
        servicioMercanciasRepository.insertarServicioMercancia(idServicio, tipoMercancia, peso);
        return ResponseEntity.ok("Servicio de mercancías insertado correctamente");
    }

    // Actualizar un servicio de mercancías
    @PutMapping("/{idServicio}")
    public ResponseEntity<String> actualizarServicioMercancia(@PathVariable Integer idServicio,
                                                              @RequestParam String tipoMercancia,
                                                              @RequestParam Double peso) {
        servicioMercanciasRepository.actualizarServicioMercancia(idServicio, tipoMercancia, peso);
        return ResponseEntity.ok("Servicio de mercancías actualizado correctamente");
    }

    // Eliminar un servicio de mercancías
    @DeleteMapping("/{idServicio}")
    public ResponseEntity<String> eliminarServicioMercancia(@PathVariable Integer idServicio) {
        servicioMercanciasRepository.eliminarServicioMercancia(idServicio);
        return ResponseEntity.ok("Servicio de mercancías eliminado correctamente");
    }
}
