package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.repositorio.PuntoGeograficoRepository;
import uniandes.edu.co.proyecto.modelo.PuntoGeografico;

@RestController
@RequestMapping("/puntos-geograficos")
public class PuntoGeograficoController {

    @Autowired
    private PuntoGeograficoRepository puntoGeograficoRepository;

    // Obtener todos los puntos
    @GetMapping
    public ResponseEntity<Collection<PuntoGeografico>> puntos() {
        try {
            return ResponseEntity.ok(puntoGeograficoRepository.darPuntos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener un punto por ID
    @GetMapping("/{id}")
    public ResponseEntity<PuntoGeografico> puntoPorId(@PathVariable("id") Integer id) {
        try {
            PuntoGeografico punto = puntoGeograficoRepository.darPunto(id);
            return punto != null ? ResponseEntity.ok(punto) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Insertar un nuevo punto
    @PostMapping("/new/save")
    public ResponseEntity<?> puntoGuardar(@RequestBody PuntoGeografico punto) {
        try {
            puntoGeograficoRepository.insertarPunto(
                    punto.getNombre(),
                    punto.getDireccion(),
                    punto.getCoordenadas(),
                    punto.getCiudad().getIdCiudad()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Punto creado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el punto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un punto existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<?> puntoEditarGuardar(@PathVariable("id") Integer id, @RequestBody PuntoGeografico punto) {
        try {
            puntoGeograficoRepository.actualizarPunto(
                    id,
                    punto.getNombre(),
                    punto.getDireccion(),
                    punto.getCoordenadas(),
                    punto.getCiudad().getIdCiudad()
            );
            return ResponseEntity.ok("Punto actualizado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar el punto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un punto
    @GetMapping("/{id}/delete")
    public ResponseEntity<?> puntoBorrar(@PathVariable("id") Integer id) {
        try {
            puntoGeograficoRepository.eliminarPunto(id);
            return ResponseEntity.ok("Punto eliminado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el punto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
