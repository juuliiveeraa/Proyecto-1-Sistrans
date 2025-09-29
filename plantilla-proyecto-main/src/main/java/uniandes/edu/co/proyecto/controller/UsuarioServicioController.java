package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.UsuarioServicio;
import uniandes.edu.co.proyecto.repositorio.UsuarioServicioRepository;

import java.util.Collection;

@RestController
@RequestMapping("/usuariosServicio")
public class UsuarioServicioController {

    @Autowired
    private UsuarioServicioRepository usuarioServicioRepository;

    // Obtener todos los usuarios servicio
    @GetMapping
    public ResponseEntity<Collection<UsuarioServicio>> listarUsuariosServicio() {
        return ResponseEntity.ok(usuarioServicioRepository.darUsuariosServicio());
    }

    // Obtener un usuario servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioServicio> obtenerUsuarioServicio(@PathVariable Integer id) {
        UsuarioServicio usuario = usuarioServicioRepository.darUsuarioServicio(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // Crear un nuevo usuario servicio
    @PostMapping
    public ResponseEntity<String> crearUsuarioServicio(
            @RequestParam Integer idUsuario,
            @RequestParam String tipoServicio,
            @RequestParam String historial) {
        usuarioServicioRepository.insertarUsuarioServicio(idUsuario, tipoServicio, historial);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario servicio creado con éxito");
    }

    // Actualizar un usuario servicio existente
    @PutMapping("/{idUsuario}")
    public ResponseEntity<String> actualizarUsuarioServicio(
            @PathVariable Integer idUsuario,
            @RequestParam String tipoServicio,
            @RequestParam String historial) {
        usuarioServicioRepository.actualizarUsuarioServicio(idUsuario, tipoServicio, historial);
        return ResponseEntity.ok("Usuario servicio actualizado con éxito");
    }

    // Eliminar un usuario servicio
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> eliminarUsuarioServicio(@PathVariable Integer idUsuario) {
        usuarioServicioRepository.eliminarUsuarioServicio(idUsuario);
        return ResponseEntity.ok("Usuario servicio eliminado con éxito");
    }
}
