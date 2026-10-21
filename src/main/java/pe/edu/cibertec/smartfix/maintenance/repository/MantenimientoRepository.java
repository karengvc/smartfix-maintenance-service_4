package pe.edu.cibertec.smartfix.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.smartfix.maintenance.entity.Mantenimiento;

public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
}
