# devPortes - Backend

API REST desarrollada con Spring Boot para la administración y gestión de complejos deportivos.

## Deployments

- **Servidor Backend:** https://devportes-backend-yvbj.onrender.com/
- **Repositorio Frontend:** [Enlace al repositorio frontend](https://github.com/CamiloBermeo/devPortes)

## Arquitectura

El proyecto sigue un patrón de **Clean Architecture** organizado por dominios. Cada módulo se divide en capas independientes:

```
Controller → Service (Use Case) → Repository (Adapter) → JPA Repository
```

- **Controller:** Expone los endpoints REST y valida las peticiones entrantes.
- **Service:** Contiene la lógica de negocio encapsulada en casos de uso.
- **Repository:** Actúa como adaptador entre el dominio y la persistencia (Spring Data JPA).
- **Model/Entity:** Los modelos de dominio son puros (sin anotaciones de framework), mientras que las entidades manejan el mapeo JPA.
- **DTO:** Objetos de transferencia de datos (Java records) para las peticiones y respuestas.
- **Mapper:** Transforman datos entre capas de forma estática.
- **Exceptions:** Excepciones específicas por módulo con handlers `@ControllerAdvice`.

## Funcionalidades Implementadas

### Autenticación y Usuarios
- Registro de nuevos usuarios con rol `CLIENTE`.
- Inicio de sesión con generación de tokens JWT (expiración de 30 minutos).
- Consulta del perfil del usuario autenticado.
- Contraseñas encriptadas con BCrypt.
- Autenticación stateless (sin sesiones HTTP).

### Gestión de Sedes (Locations)
- Creación de nuevas sedes deportivas (solo admin).
- Listado de todas las sedes activas (público).
- Actualización de información de sedes (solo admin).
- Activación/desactivación de sedes (solo admin).

### Gestión de Canchas (Fields)
- Creación de canchas con carga de imágenes en Cloudinary (solo admin).
- Edición de canchas existentes con actualización de imágenes (solo admin).
- Listado de todas las canchas disponibles (público).

### Seguridad
- Tokens JWT con algoritmo HMAC256 (Auth0 java-jwt).
- Roles: `ROLE_CLIENTE` y `ROLE_ADMIN`.
- CORS configurable mediante variable de entorno.
- Filtro personalizado `OncePerRequestFilter` que valida el token en cada petición.

## Funcionalidades Planeadas

- Reservaciones: verificación de disponibilidad, creación, cancelación y detalle.
- Pagos: integración con MercadoPago y procesamiento de pagos.
- Gestión de usuarios: edición de perfil, estadísticas de usuario (admin).
- Galería: CRUD de publicaciones.
- Generación de códigos QR.

## Tecnologías Utilizadas

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA / Hibernate
- Spring Security + Auth0 java-jwt 4.4.0
- PostgreSQL (Supabase en producción)
- Flyway 13.5.0 (migraciones de base de datos)
- Cloudinary (almacenamiento de imágenes)
- Lombok
- Jakarta Bean Validation
- dotenv-java 2.2.4
- Docker (multi-stage build)
- Maven
- Render.com (deployment)

## Endpoints API

### Autenticación

| Método | Endpoint | Auth | Descripción |
|--------|----------|------|-------------|
| POST | `/api/v1/auth/register` | No | Registrar nuevo usuario |
| POST | `/api/v1/auth/login` | No | Iniciar sesión (retorna JWT) |
| GET | `/api/v1/auth/profile` | JWT | Obtener perfil del usuario autenticado |

### Canchas (Fields)

| Método | Endpoint | Auth | Descripción |
|--------|----------|------|-------------|
| GET | `/api/v1/field/all` | No | Listar todas las canchas |
| POST | `/api/v1/field/new` | ADMIN | Crear cancha (multipart: imágenes) |
| PUT | `/api/v1/field/edit/{id}` | ADMIN | Editar cancha (multipart: imágenes) |

### Sedes (Locations)

| Método | Endpoint | Auth | Descripción |
|--------|----------|------|-------------|
| GET | `/api/v1/location/locations` | No | Listar todas las sedes |
| POST | `/api/v1/location/new-location` | ADMIN | Crear nueva sede |
| PUT | `/api/v1/location/{id}` | ADMIN | Actualizar sede |
| PATCH | `/api/v1/location/{id}/state` | ADMIN | Activar/desactivar sede |
| PATCH | `/api/v1/field/{id}/state` | ADMIN | Activar/desactivar cancha |

## Base de datos

El esquema contiene 7 tablas principales:

- `clients` — Usuarios clientes del sistema.
- `admins` — Usuarios administradores.
- `locations` — Sedes deportivas (3 sedes: Chapinero, Usaquén, Norte).
- `fields` — Canchas deportivas (10 canchas: fútbol, baloncesto, tenis, pádel, vóley, futsal).
- `reservations` — Reservaciones de canchas.
- `metodos_pago` — Métodos de pago disponibles.
- `pagos` — Pagos asociados a reservaciones.

Las migraciones se gestionan con **Flyway** y se ejecutan automáticamente al iniciar la aplicación.

## Variables de Entorno

| Variable | Descripción |
|----------|-------------|
| `URL_DB` | URL de conexión a PostgreSQL |
| `USERNAME_DB` | Usuario de la base de datos |
| `PASSWORD_DB` | Contraseña de la base de datos |
| `JWT_SECRET` | Secreto para firmar tokens JWT |
| `CORS_URLS` | Orígenes permitidos para CORS (separados por coma) |
| `SPRING_PROFILE_ACTIVE` | Perfil de Spring (`dev` o `prod`) |
| `CLOUDINARY_CLOUD_NAME` | Nombre del cloud de Cloudinary |
| `CLOUDINARY_API_KEY` | API key de Cloudinary |
| `CLOUDINARY_API_SECRET` | API secret de Cloudinary |

Crea un archivo `.env` en la raíz del backend basado en `.env.example`.

## Instalación y Ejecución

### Requisitos
- Java 21
- Maven (o usar el Maven Wrapper incluido)

### Con Maven Wrapper
```bash
cd backend
# Configurar variables de entorno en .env
./mvnw spring-boot:run
```

### Con Docker
```bash
cd backend
docker build -t devportes-backend .
docker run -p 8080:8080 \
  -e URL_DB=jdbc:postgresql://tu-host:5432/postgres \
  -e USERNAME_DB=tu-usuario \
  -e PASSWORD_DB=tu-password \
  -e JWT_SECRET=tu-secreto \
  -e CORS_URLS=http://localhost:5500 \
  -e SPRING_PROFILE_ACTIVE=dev \
  -e CLOUDINARY_CLOUD_NAME=tu-cloud \
  -e CLOUDINARY_API_KEY=tu-key \
  -e CLOUDINARY_API_SECRET=tu-secret \
  devportes-backend
```

### Scripts de Maven
```bash
./mvnw spring-boot:run          # Ejecutar la aplicación
./mvnw package -DskipTests       # Empaquetar JAR
./mvnw test                      # Ejecutar tests
```

La aplicación inicia en el puerto **8080**.

## Perfiles de Spring

- **dev:** `ddl-auto: update` (Hibernate actualiza el esquema automáticamente), logging detallado de SQL.
- **prod:** `ddl-auto: validate` (solo valida el esquema), Flyway habilitado para migraciones, logging reducido.
