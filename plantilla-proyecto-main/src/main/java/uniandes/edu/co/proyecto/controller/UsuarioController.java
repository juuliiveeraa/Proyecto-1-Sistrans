package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository;

import java.util.Collection;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Collection<Usuario>> getUsuarios() {
        try {
            return ResponseEntity.ok(usuarioRepository.darUsuarios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Integer id) {
        try {
            Usuario usuario = usuarioRepository.darUsuario(id);
            return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioRepository.insertarUsuario(usuario.getNombre(), usuario.getCorreo(), usuario.getCelular(), usuario.getCedula(), usuario.getCalificacionPromedio());
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario");
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        try {
            usuarioRepository.actualizarUsuario(id, usuario.getNombre(), usuario.getCorreo(), usuario.getCelular(), usuario.getCedula(), usuario.getCalificacionPromedio());
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Integer id) {
        try {
            usuarioRepository.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario");
        }
    }
}
