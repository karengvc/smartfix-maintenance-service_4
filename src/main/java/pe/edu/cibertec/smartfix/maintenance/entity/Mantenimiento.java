package pe.edu.cibertec.smartfix.maintenance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "mantenimientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoMantenimiento tipo;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Column(nullable = false, length = 1000)
    private String resultado;

    @Column(name = "equipo_id", nullable = false)
    private Long equipoId;

    @Column(name = "incidencia_id")
    private Long incidenciaId;

    @Column(name = "tecnico_id", nullable = false)
    private Long tecnicoId;
}
