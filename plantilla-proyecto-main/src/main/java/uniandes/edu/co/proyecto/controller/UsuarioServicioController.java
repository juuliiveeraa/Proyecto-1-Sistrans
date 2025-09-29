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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> convertirEnUsuarioServicio(
            @PathVariable Integer idUsuario,
            @RequestBody UsuarioServicio datosTarjeta) {

        try {
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Insertar usuario servicio
            usuarioServicioRepository.insertarUsuarioServicio(
                    usuario.getIdUsuario(),
                    datosTarjeta.getTarjetaNumero(),
                    datosTarjeta.getTarjetaNombre(),
                    datosTarjeta.getTarjetaVencimiento(),
                    datosTarjeta.getTarjetaCodigoSeguridad()
            );

            // Recuperar el usuario servicio recién insertado
            UsuarioServicio nuevoUsuarioServicio = usuarioServicioRepository.buscarPorTarjeta(datosTarjeta.getTarjetaNumero());

            if (nuevoUsuarioServicio == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("error", "No se pudo recuperar el usuario servicio creado"));
            }

            // Construir respuesta en JSON
            Map<String, Object> response = new HashMap<>();
            response.put("id_usuario", nuevoUsuarioServicio.getIdUsuario());
            response.put("tarjeta_numero", nuevoUsuarioServicio.getTarjetaNumero());
            response.put("tarjeta_nombre", nuevoUsuarioServicio.getTarjetaNombre());
            response.put("tarjeta_vencimiento", nuevoUsuarioServicio.getTarjetaVencimiento());
            response.put("tarjeta_codigo_seguridad", nuevoUsuarioServicio.getTarjetaCodigoSeguridad());
            response.put("mensaje", "Usuario convertido en UsuarioServicio con éxito");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }



    // Actualizar un usuario servicio existente
    @PutMapping("/{idUsuario}/update")
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
