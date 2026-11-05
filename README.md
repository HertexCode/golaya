# Golaya — Sistema de Gestión de Reservas Deportivas

**Golaya** es una plataforma web *full-stack* diseñada para optimizar la administración y reserva de complejos deportivos. Este sistema ofrece una solución completa y segura para gestionar espacios, usuarios y horarios de manera eficiente.

Este repositorio está estructurado como un **monorepo**, integrando tanto el cliente como la API REST en un único entorno orquestado con Docker, garantizando una experiencia de desarrollo y despliegue sin fricciones.

<img width="920" height="500" alt="image" src="https://github.com/user-attachments/assets/be4cc77c-7f73-4ba2-8cc7-605c156cd721" />


## Inicio rápido (local)

Único requisito: [Docker](https://docs.docker.com/get-docker/).

```bash
https://github.com/HertexCode/golaya.git
cd golaya
docker compose up --build
```

Abre **http://localhost:8000** y listo:

| Credencial | Contraseña |
|---|---|
| `admin@admin.com` | `Admin1234.` |

La base de datos se crea y siembra sola la primera vez (Flyway + seed automático).

### Comandos útiles

```bash
docker compose logs -f backend   # ver logs del backend
docker compose down              # detener (los datos se conservan)
docker compose down -v           # detener y BORRAR los datos
docker compose up -d             # arrancar en segundo plano
```

### Puertos

| Servicio | Puerto |
|---|---|
| Frontend | http://localhost:8000 |
| API backend | http://localhost:8080/api/v1 |
| PostgreSQL | interno (no expuesto) |

> Para conectar un cliente de bases de datos (DBeaver, pgAdmin), agrega
> `- "5432:5432"` al servicio `db` en `docker-compose.yml`. No está expuesto
> por defecto para evitar conflictos con un Postgres local.

## Estructura

```
golaya/
├── docker-compose.yml        # entorno local completo (db + backend + frontend + seed)
├── docker-compose.prod.yml   # override para producción (Dokploy/VPS)
├── backend/                  # API Spring Boot
│   ├── DATABASE/seed_data.sql
│   └── scripts/wait-and-seed.sh
├── frontend/                 # HTML/CSS/JS estático (nginx en Docker)
├── devPortes/                # respaldo del repo frontend original (ignorado por git)
└── progress.md               # registro de avance del proyecto
```

## Tecnologías

- **Frontend:** HTML5, CSS3, JavaScript (ES6+), nginx
- **Backend:** Java 21, Spring Boot, Spring Security (JWT), Spring Data JPA, Flyway, PostgreSQL
- **Infra:** Docker, Docker Compose

Documentación técnica:

- [`backend/README.md`](backend/README.md) — arquitectura, endpoints API, variables de entorno
- [`frontend/README.md`](frontend/README.md) — funcionalidades y vista previa

## Deploy

- **Frontend:** [Vercel](https://golaya.vercel.app/)
- **Backend:** [Render](https://devportes-backend.onrender.com/) 

### Nota sobre el cold start

Render en plan free apaga el servicio tras 15 min sin tráfico; la primera
visita posterior tarda ~1 minuto en despertarlo.
`frontend/js/api/config.js` la URL de producción a `/api/v1` (mismo origen,
sin CORS).
