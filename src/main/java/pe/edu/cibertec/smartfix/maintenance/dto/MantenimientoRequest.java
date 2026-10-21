package pe.edu.cibertec.smartfix.maintenance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import pe.edu.cibertec.smartfix.maintenance.entity.TipoMantenimiento;

import java.time.LocalDate;

public record MantenimientoRequest(
        @NotNull(message = "El tipo es obligatorio")
        TipoMantenimiento tipo,

        @NotNull(message = "La fecha es obligatoria")
        LocalDate fecha,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(max = 1000, message = "La descripción no puede superar 1000 caracteres")
        String descripcion,

        @NotBlank(message = "El resultado es obligatorio")
        @Size(max = 1000, message = "El resultado no puede superar 1000 caracteres")
        String resultado,

        @NotNull(message = "El equipoId es obligatorio")
        @Positive(message = "El equipoId debe ser mayor que cero")
        Long equipoId,

        @Positive(message = "El incidenciaId debe ser mayor que cero")
        Long incidenciaId,

        @NotNull(message = "El tecnicoId es obligatorio")
        @Positive(message = "El tecnicoId debe ser mayor que cero")
        Long tecnicoId
) {}
