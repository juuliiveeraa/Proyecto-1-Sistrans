package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Vehiculo;
import uniandes.edu.co.proyecto.repositorio.VehiculoRepository;

import java.util.Collection;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

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

    @PostMapping("/new/save")
    public ResponseEntity<?> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
            vehiculoRepository.insertarVehiculo(
                    vehiculo.getIdVehiculo(),
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
            return ResponseEntity.status(HttpStatus.CREATED).body("Vehiculo creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el vehiculo");
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

    @GetMapping("/{id}/delete")
    public ResponseEntity<?> eliminarVehiculo(@PathVariable("id") Integer id) {
        try {
            vehiculoRepository.eliminarVehiculo(id);
            return ResponseEntity.ok("Vehiculo eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el vehiculo");
        }
    }
}
