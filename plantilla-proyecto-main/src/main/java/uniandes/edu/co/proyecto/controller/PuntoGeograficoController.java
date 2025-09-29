package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
            // Insertar punto
            puntoGeograficoRepository.insertarPunto(
                    punto.getNombre(),
                    punto.getDireccion(),
                    punto.getCoordenadas(),
                    punto.getCiudad().getIdCiudad()
            );

            // Recuperar el punto recién insertado por su nombre
            PuntoGeografico nuevoPunto = puntoGeograficoRepository.buscarPorNombre(punto.getNombre());

            if (nuevoPunto == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("error", "No se pudo recuperar el punto creado"));
            }

            // Construir la respuesta JSON
            Map<String, Object> response = new HashMap<>();
            response.put("id_punto", nuevoPunto.getIdPunto());
            response.put("nombre", nuevoPunto.getNombre());
            response.put("direccion", nuevoPunto.getDireccion());
            response.put("coordenadas", nuevoPunto.getCoordenadas());
            response.put("id_ciudad", nuevoPunto.getCiudad().getIdCiudad());
            response.put("mensaje", "Punto creado exitosamente");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
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
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> puntoBorrar(@PathVariable("id") Integer id) {
        try {
            puntoGeograficoRepository.eliminarPunto(id);
            return ResponseEntity.ok("Punto eliminado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el punto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
