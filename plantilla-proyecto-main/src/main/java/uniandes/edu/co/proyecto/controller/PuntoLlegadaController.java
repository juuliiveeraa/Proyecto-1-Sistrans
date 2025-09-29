package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.PuntoLlegada;
import uniandes.edu.co.proyecto.repositorio.PuntoLlegadaRepository;

@RestController
@RequestMapping("/puntos-llegada")
public class PuntoLlegadaController {

    @Autowired
    private PuntoLlegadaRepository puntoLlegadaRepository;

    // Obtener todos los puntos de llegada
    @GetMapping
    public ResponseEntity<Collection<PuntoLlegada>> puntosLlegada() {
        try {
            return ResponseEntity.ok(puntoLlegadaRepository.darPuntosLlegada());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener un punto de llegada por IDs
    @GetMapping("/servicio/{idServicio}/punto/{idPunto}")
    public ResponseEntity<PuntoLlegada> puntoLlegadaPorId(@PathVariable("idServicio") Integer idServicio,
                                                          @PathVariable("idPunto") Integer idPunto) {
        try {
            PuntoLlegada puntoLlegada = puntoLlegadaRepository.darPuntoLlegada(idServicio, idPunto);
            return puntoLlegada != null ? ResponseEntity.ok(puntoLlegada) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Insertar nuevo punto de llegada
    @PostMapping("/new/save")
    public ResponseEntity<?> puntoLlegadaGuardar(@RequestBody PuntoLlegada puntoLlegada) {
        try {
            puntoLlegadaRepository.insertarPuntoLlegada(
                    puntoLlegada.getPk().getServicio().getIdServicio(),
                    puntoLlegada.getPk().getPunto().getIdPunto()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Punto de llegada creado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el punto de llegada", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un punto de llegada
    @GetMapping("/servicio/{idServicio}/punto/{idPunto}/delete")
    public ResponseEntity<?> puntoLlegadaBorrar(@PathVariable("idServicio") Integer idServicio,
                                                @PathVariable("idPunto") Integer idPunto) {
        try {
            puntoLlegadaRepository.eliminarPuntoLlegada(idServicio, idPunto);
            return ResponseEntity.ok("Punto de llegada eliminado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el punto de llegada", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
