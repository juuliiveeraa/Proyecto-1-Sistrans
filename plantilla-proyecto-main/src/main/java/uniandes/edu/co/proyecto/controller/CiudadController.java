package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Ciudad;
import uniandes.edu.co.proyecto.repositorio.CiudadRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping
    public ResponseEntity<Collection<Ciudad>> getCiudades() {
        try {
            return ResponseEntity.ok(ciudadRepository.darCiudades());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getCiudad(@PathVariable("id") Integer id) {
        try {
            Ciudad ciudad = ciudadRepository.darCiudad(id);
            return ciudad != null ? ResponseEntity.ok(ciudad) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

@PostMapping("/new/save")
public ResponseEntity<?> crearCiudad(@RequestBody Ciudad ciudad) {
    try {
        ciudadRepository.insertarCiudad(ciudad.getNombre());

        Ciudad nuevaCiudad = ciudadRepository.darCiudadNombre(ciudad.getNombre());

        if (nuevaCiudad == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.singletonMap("error", "No se pudo recuperar la ciudad creada"));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id_ciudad", nuevaCiudad.getIdCiudad());
        response.put("nombre", nuevaCiudad.getNombre());
        response.put("mensaje", "Ciudad creada exitosamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    } catch (Exception e) {
        e.printStackTrace(); 
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(Collections.singletonMap("error", e.getMessage()));
    }
}



    @PostMapping("/{id}/edit/save")
    public ResponseEntity<?> actualizarCiudad(@PathVariable("id") Integer id, @RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.actualizarCiudad(id, ciudad.getNombre());
            return ResponseEntity.ok("Ciudad actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la ciudad");
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> eliminarCiudad(@PathVariable("id") Integer id) {
        try {
            ciudadRepository.eliminarCiudad(id);
            return ResponseEntity.ok("Ciudad eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la ciudad");
        }
    }
}