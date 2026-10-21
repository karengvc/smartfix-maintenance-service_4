package pe.edu.cibertec.smartfix.maintenance.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.smartfix.maintenance.dto.MantenimientoRequest;
import pe.edu.cibertec.smartfix.maintenance.entity.Mantenimiento;
import pe.edu.cibertec.smartfix.maintenance.service.MantenimientoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {
    private final MantenimientoService service;

    @GetMapping
    public List<Mantenimiento> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Mantenimiento obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Mantenimiento> crear(@Valid @RequestBody MantenimientoRequest request) {
        Mantenimiento creado = service.crear(request);
        return ResponseEntity.created(URI.create("/api/mantenimientos/" + creado.getId()))
                .body(creado);
    }

    @PutMapping("/{id}")
    public Mantenimiento actualizar(@PathVariable Long id,
                                    @Valid @RequestBody MantenimientoRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
