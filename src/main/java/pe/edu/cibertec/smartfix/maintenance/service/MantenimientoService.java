package pe.edu.cibertec.smartfix.maintenance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.smartfix.maintenance.dto.MantenimientoRequest;
import pe.edu.cibertec.smartfix.maintenance.entity.Mantenimiento;
import pe.edu.cibertec.smartfix.maintenance.exception.RecursoNoEncontradoException;
import pe.edu.cibertec.smartfix.maintenance.repository.MantenimientoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MantenimientoService {
    private final MantenimientoRepository repository;

    @Transactional(readOnly = true)
    public List<Mantenimiento> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Mantenimiento obtenerPorId(Long id) {
        return buscar(id);
    }

    public Mantenimiento crear(MantenimientoRequest request) {
        Mantenimiento mantenimiento = new Mantenimiento();
        aplicarDatos(mantenimiento, request);
        return repository.save(mantenimiento);
    }

    public Mantenimiento actualizar(Long id, MantenimientoRequest request) {
        Mantenimiento mantenimiento = buscar(id);
        aplicarDatos(mantenimiento, request);
        return repository.save(mantenimiento);
    }

    public void eliminar(Long id) {
        Mantenimiento mantenimiento = buscar(id);
        repository.delete(mantenimiento);
    }

    private Mantenimiento buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un mantenimiento con id " + id));
    }

    private void aplicarDatos(Mantenimiento entity, MantenimientoRequest request) {
        entity.setTipo(request.tipo());
        entity.setFecha(request.fecha());
        entity.setDescripcion(request.descripcion().trim());
        entity.setResultado(request.resultado().trim());
        entity.setEquipoId(request.equipoId());
        entity.setIncidenciaId(request.incidenciaId());
        entity.setTecnicoId(request.tecnicoId());
    }
}
