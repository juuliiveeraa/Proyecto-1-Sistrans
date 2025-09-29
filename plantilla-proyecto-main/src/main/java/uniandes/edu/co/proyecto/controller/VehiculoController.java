package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.UsuarioConductor;
import uniandes.edu.co.proyecto.modelo.Vehiculo;
import uniandes.edu.co.proyecto.repositorio.VehiculoRepository;
import uniandes.edu.co.proyecto.repositorio.UsuarioConductorRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UsuarioConductorRepository usuarioConductorRepository;

    @GetMapping
    public ResponseEntity<Collection<Vehiculo>> getVehiculos() {
        try {
            return ResponseEntity.ok(vehiculoRepository.darVehiculos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculo(@PathVariable("id") Integer id) {
        try {
            Vehiculo vehiculo = vehiculoRepository.darVehiculo(id);
            return vehiculo != null ? ResponseEntity.ok(vehiculo) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{id}/new/save")
    public ResponseEntity<?> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
            if (vehiculo.getConductor() == null || vehiculo.getConductor().getIdUsuario() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "El vehículo debe tener un conductor válido"));
            }

            vehiculoRepository.insertarVehiculo(
                    vehiculo.getConductor().getIdUsuario(),
                    vehiculo.getTipo(),
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                    vehiculo.getColor(),
                    vehiculo.getPlaca(),
                    vehiculo.getCiudadExpedicion(),
                    vehiculo.getCapacidad(),
                    vehiculo.getNivel()
            );

            Vehiculo vehiculoCreado = vehiculoRepository.darVehiculoPorPlaca(vehiculo.getPlaca());

            if (vehiculoCreado == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("error", "No se pudo recuperar el vehículo creado"));
            }

            Map<String, Object> response = new HashMap<>();
            response.put("id_vehiculo", vehiculoCreado.getIdVehiculo());
            response.put("mensaje", "Vehículo creado exitosamente");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<?> actualizarVehiculo(@PathVariable("id") Integer id, @RequestBody Vehiculo vehiculo) {
        try {
            vehiculoRepository.actualizarVehiculo(
                    id,
                    vehiculo.getConductor().getIdUsuario(),
                    vehiculo.getTipo(),
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                    vehiculo.getColor(),
                    vehiculo.getPlaca(),
                    vehiculo.getCiudadExpedicion(),
                    vehiculo.getCapacidad(),
                    vehiculo.getNivel()
            );
            return ResponseEntity.ok("Vehiculo actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el vehiculo");
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> eliminarVehiculo(@PathVariable("id") Integer id) {
        try {
            vehiculoRepository.eliminarVehiculo(id);
            return ResponseEntity.ok("Vehiculo eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el vehiculo");
        }
    }
}
