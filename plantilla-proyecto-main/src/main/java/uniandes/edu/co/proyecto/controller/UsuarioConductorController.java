package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.UsuarioConductor;
import uniandes.edu.co.proyecto.repositorio.UsuarioConductorRepository;

import java.util.Collection;

@RestController
@RequestMapping("/usuariosConductores")
public class UsuarioConductorController {

    @Autowired
    private UsuarioConductorRepository usuarioConductorRepository;

    // Obtener todos los usuarios conductores
    @GetMapping
    public ResponseEntity<Collection<UsuarioConductor>> listarUsuariosConductores() {
        return ResponseEntity.ok(usuarioConductorRepository.darUsuariosConductores());
    }

    // Obtener un usuario conductor por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioConductor> obtenerUsuarioConductor(@PathVariable Integer id) {
        UsuarioConductor usuario = usuarioConductorRepository.darUsuarioConductor(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // Crear un nuevo usuario conductor
    @PostMapping
    public ResponseEntity<String> crearUsuarioConductor(
            @RequestParam Integer idUsuario,
            @RequestParam String licencia,
            @RequestParam Integer experiencia) {
        usuarioConductorRepository.insertarUsuarioConductor(idUsuario, licencia, experiencia);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario conductor creado con éxito");
    }

    // Actualizar un usuario conductor existente
    @PutMapping("/{idUsuario}")
    public ResponseEntity<String> actualizarUsuarioConductor(
            @PathVariable Integer idUsuario,
            @RequestParam String licencia,
            @RequestParam Integer experiencia) {
        usuarioConductorRepository.actualizarUsuarioConductor(idUsuario, licencia, experiencia);
        return ResponseEntity.ok("Usuario conductor actualizado con éxito");
    }

    // Eliminar un usuario conductor
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> eliminarUsuarioConductor(@PathVariable Integer idUsuario) {
        usuarioConductorRepository.eliminarUsuarioConductor(idUsuario);
        return ResponseEntity.ok("Usuario conductor eliminado con éxito");
    }
}
