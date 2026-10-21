# SmartFix Maintenance Service — Integrante 4

Microservicio REST para registrar y administrar mantenimientos preventivos y correctivos.

## Requisitos

- Java 25
- Maven 3.9+
- MySQL 8+
- IntelliJ IDEA (recomendado)

## Paquetes

- `controller`: endpoints REST
- `service`: lógica de negocio
- `repository`: acceso a datos con Spring Data JPA
- `entity`: entidad `Mantenimiento` y enum `TipoMantenimiento`
- `dto`: solicitud de entrada con validaciones
- `exception`: respuestas de error

## Ejecución local

1. Crea la base de datos ejecutando `sql/schema.sql` en MySQL.
2. Ajusta `DB_USERNAME` y `DB_PASSWORD` o configura `spring.datasource.*` en `application.properties`.
3. Ejecuta `mvn spring-boot:run` o inicia `MaintenanceServiceApplication` desde IntelliJ.
4. El servicio escucha en `http://localhost:8084`.

Por defecto, la URL JDBC crea la base de datos si el usuario tiene permisos. No uses credenciales de ejemplo fuera del entorno local.

## Ejecución con Docker Compose

```bash
docker compose up --build
```

Se publican el servicio en `http://localhost:8084` y MySQL en el puerto local `3307`.
Para cambiar la contraseña local, define `DB_PASSWORD` en el entorno antes de iniciar.

## API

| Método | Ruta | Resultado |
|---|---|---|
| GET | `/api/mantenimientos` | Lista todos |
| GET | `/api/mantenimientos/{id}` | Consulta uno |
| POST | `/api/mantenimientos` | Registra (201 Created) |
| PUT | `/api/mantenimientos/{id}` | Actualiza |
| DELETE | `/api/mantenimientos/{id}` | Elimina (204 No Content) |

## JSON de ejemplo

```json
{
  "tipo": "PREVENTIVO",
  "fecha": "2026-10-20",
  "descripcion": "Limpieza y revisión general del equipo",
  "resultado": "Equipo operativo; se recomienda revisión en seis meses",
  "equipoId": 1,
  "incidenciaId": null,
  "tecnicoId": 2
}
```

`tipo` acepta `PREVENTIVO` o `CORRECTIVO`. `incidenciaId` es opcional. `equipoId` y `tecnicoId` son obligatorios y deben ser IDs existentes en los servicios correspondientes. Este microservicio no crea entidades duplicadas para esos módulos; la comprobación de existencia requiere integración entre servicios y queda pendiente de acordar con el equipo.

## Validaciones y errores

- `tipo`, `fecha`, `descripcion`, `resultado`, `equipoId` y `tecnicoId` son obligatorios.
- `equipoId` y `tecnicoId` deben ser positivos.
- `incidenciaId`, si se envía, debe ser positivo.
- Descripción y resultado tienen un máximo de 1000 caracteres.
- Un ID inexistente devuelve `404 Not Found`.
- Una solicitud inválida devuelve `400 Bad Request`.

## Pruebas

```bash
mvn test
```

Incluye pruebas unitarias básicas del servicio para registrar y consultar un ID inexistente. Completa también pruebas de integración con MySQL y pruebas HTTP en Postman antes de la entrega.

## Notas de integración

- Confirmar con el equipo el puerto y los nombres exactos de los campos/IDs.
- `equipoId`, `incidenciaId` y `tecnicoId` son referencias escalares, no relaciones JPA.
- `spring.jpa.hibernate.ddl-auto=update` facilita el desarrollo académico; para producción se recomienda gestionar cambios con migraciones.
