package pe.edu.cibertec.smartfix.maintenance.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.cibertec.smartfix.maintenance.dto.MantenimientoRequest;
import pe.edu.cibertec.smartfix.maintenance.entity.Mantenimiento;
import pe.edu.cibertec.smartfix.maintenance.entity.TipoMantenimiento;
import pe.edu.cibertec.smartfix.maintenance.exception.RecursoNoEncontradoException;
import pe.edu.cibertec.smartfix.maintenance.repository.MantenimientoRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MantenimientoServiceTest {
    @Mock
    MantenimientoRepository repository;

    @InjectMocks
    MantenimientoService service;

    private MantenimientoRequest request() {
        return new MantenimientoRequest(
                TipoMantenimiento.PREVENTIVO,
                LocalDate.of(2026, 10, 20),
                "Limpieza y revisión general",
                "Equipo operativo",
                1L,
                null,
                2L
        );
    }

    @Test
    void crearDebeGuardarMantenimiento() {
        when(repository.save(any(Mantenimiento.class))).thenAnswer(inv -> {
            Mantenimiento m = inv.getArgument(0);
            m.setId(10L);
            return m;
        });

        Mantenimiento resultado = service.crear(request());

        assertEquals(10L, resultado.getId());
        assertEquals(TipoMantenimiento.PREVENTIVO, resultado.getTipo());
        assertEquals(1L, resultado.getEquipoId());
        verify(repository).save(any(Mantenimiento.class));
    }

    @Test
    void obtenerIdInexistenteDebeLanzarExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () -> service.obtenerPorId(99L));
    }
}
