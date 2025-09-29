package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.modelo.UsuarioServicio;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository;
import uniandes.edu.co.proyecto.repositorio.UsuarioServicioRepository;

import java.sql.Date;
import java.util.Collection;

@RestController
@RequestMapping("/usuariosServicio")
public class UsuarioServicioController {

    @Autowired
    private UsuarioServicioRepository usuarioServicioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


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
    @PostMapping("/{idUsuario}/new/save")
    public ResponseEntity<String> convertirEnUsuarioServicio(
            @PathVariable Integer idUsuario,
            @RequestBody UsuarioServicio datosTarjeta) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioServicioRepository.insertarUsuarioServicio(
                usuario.getIdUsuario(),
                datosTarjeta.getTarjetaNumero(),
                datosTarjeta.getTarjetaNombre(),
                datosTarjeta.getTarjetaVencimiento(),
                datosTarjeta.getTarjetaCodigoSeguridad()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario convertido en UsuarioServicio con éxito");
    }


    // Actualizar un usuario servicio existente
    @PutMapping("/{idUsuario}")
    public ResponseEntity<String> actualizarUsuarioServicio(
            @PathVariable Integer idUsuario,
            @RequestParam String tarjetaNumero,
            @RequestParam String tarjetaNombre,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date tarjetaVencimiento,
            @RequestParam String tarjetaCodigoSeguridad) {

        usuarioServicioRepository.actualizarUsuarioServicio(
                idUsuario, tarjetaNumero, tarjetaNombre, tarjetaVencimiento, tarjetaCodigoSeguridad);

        return ResponseEntity.ok("Usuario servicio actualizado con éxito");
    }

    // Eliminar un usuario servicio
    @DeleteMapping("/{idUsuario}/delete")
    public ResponseEntity<String> eliminarUsuarioServicio(@PathVariable Integer idUsuario) {
        usuarioServicioRepository.eliminarUsuarioServicio(idUsuario);
        return ResponseEntity.ok("Usuario servicio eliminado con éxito");
    }
}
