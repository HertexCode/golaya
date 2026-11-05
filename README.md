# Golaya — Sistema de gestión de reservas

Sistema web para la administración y reserva de complejos deportivos. Proyecto integrador del bootcamp Generation.

Este repositorio es un **monorepo** con el frontend y el backend juntos.

## Inicio rápido (local)

Único requisito: [Docker](https://docs.docker.com/get-docker/).

```bash
git clone git@github.com:HertexCode/golaya.git
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
| PostgreSQL | interno (no expuesto; ver nota abajo) |

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

- **Frontend:** [Vercel](https://golaya.vercel.app/) (estático, sin build)
- **Backend:** [Render](https://devportes-backend.onrender.com/) (free tier)

> ⚠️ **Importante tras el monorepo:** en los ajustes de ambos servicios hay que
> indicar la subcarpeta, o el deploy fallará:
> - **Vercel →** Root Directory = `frontend`
> - **Render →** Root Directory = `backend`

### Nota sobre el cold start

Render en plan free apaga el servicio tras 15 min sin tráfico; la primera
visita posterior tarda ~1 minuto en despertarlo. El frontend (Vercel) siempre
es inmediato.

### Variables de entorno en Render

| Variable | Descripción |
|----------|-------------|
| `SPRING_PROFILE_ACTIVE` | `prod` |
| `URL_DB` | URL de conexión a PostgreSQL |
| `USERNAME_DB` / `PASSWORD_DB` | Credenciales de la base |
| `JWT_SECRET` | Secreto JWT (mínimo 32 caracteres) |
| `CORS_URLS` | Orígenes permitidos, separados por coma (sin espacios) |
| `CLOUDINARY_CLOUD_NAME` / `CLOUDINARY_API_KEY` / `CLOUDINARY_API_SECRET` | Subida de imágenes |

## Producción con Docker (Dokploy / home lab)

```bash
cp .env.example .env   # editar valores reales
docker compose -f docker-compose.yml -f docker-compose.prod.yml up -d --build
```

Diferencias con el modo dev: sin seed automático, `restart: unless-stopped`,
perfil `prod` y secretos obligatorios (el compose falla si faltan).
Para sembrar los datos de fábrica en producción:

```bash
docker compose -f docker-compose.yml -f docker-compose.prod.yml --profile seed run --rm seed
```

En Dokploy puedes apuntar directamente a este `docker-compose.yml` y definir
las variables desde su UI. Si frontend y backend comparten dominio, cambia en
`frontend/js/api/config.js` la URL de producción a `/api/v1` (mismo origen,
sin CORS).
