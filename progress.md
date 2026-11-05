# Golaya — Progreso

Registro de avance del monorepo. Ir actualizando este archivo en cada paso.

## Contexto

- **Objetivo local:** `git clone` → `docker compose up --build` → `http://localhost:8000` → login `admin@admin.com / Admin1234.` funcionando. Único requisito: Docker.
- **Deploy actual:** Vercel (frontend) + Render free (backend), **aceptando el cold start** (sin keep-alive).
- **Futura:** Dokploy en home lab (el compose queda listo para migrar).

## Decisiones tomadas

| Tema | Decisión |
|------|----------|
| Estructura | Monorepo simétrico: `backend/` + `frontend/` |
| Base del repo | El repo `DevPortes-Backend` (historia conservada) pasa a llamarse `golaya` |
| Historia git | Se conserva la historia del backend (`git mv` + renames detectados) |
| Requisito local | Solo Docker |
| Seed | Automático al levantar (admin de prueba incluido) |
| Cloudinary | Vacío por defecto, opcional en local (solo falla al subir imágenes) |
| Puerto frontend local | 8000 (backend 8080) |
| Repo frontend original | Se queda en `golaya/devPortes/` intacto e ignorado por git. El usuario decide después |
| Rename GitHub | `HertexCode/DevPortes-Backend` → `HertexCode/golaya` con `gh` |
| Velocidad | URLs de imágenes relativas + gzip/caché en nginx |

## Fase 1 — Migración de estructura (git)

- [x] 1. Commit del cambio pendiente en backend (`DevPortesBackendApplication.java`, formato) — `0784779`
- [x] 2. `git mv` de todo lo trackeado a `backend/` (209 renames detectados) + commit — `84c0dc5`
- [x] 3. `.git` movido a la raíz `golaya/` con `backend/` + ignorados locales (`target/`, Eclipse files → `backend/`); carpeta `DevPortes-Backend/` eliminada
- [x] 4. `devPortes/*` copiado a `frontend/` con `rsync --exclude .git`; `.github/` a la raíz; commit — `1be9d65`
- [x] 5. `.gitignore` raíz creado (`devPortes/`, `.env`, `target/`, IDE)
- [x] 6. Repo renombrado a `HertexCode/golaya` con `gh` (remote actualizado automáticamente); push `b8aba07..1be9d65`

## Fase 2 — Trabajo técnico

- [x] 7. `docker-compose.yml`: `db` (Postgres 16 + healthcheck + volumen), `backend` (defaults de dev), `frontend` (nginx :8000), `seed` (espera Flyway → `DATABASE/seed_data.sql`)
- [x] 8. `frontend/Dockerfile`: reemplazar copia Maven errónea por `nginx:alpine` + `nginx.conf` (gzip, caché `assets/`, HTML `no-cache`)
- [x] 9. `frontend/js/api/config.js`: auto-detección localhost → `http://localhost:8080/api/v1`, si no → producción. Comentario para migrar a `/api/v1` (Dokploy)
- [x] 10. `backend/.../application-dev.yaml`: defaults `${DB_HOST:localhost}`, `${POSTGRES_PASSWORD:postgres}`, `${JWT_SECRET:...}`, `${CORS_URLS:http://localhost:8000,...}`, Cloudinary vacío
- [x] 11. Seed automático: `backend/scripts/wait-and-seed.sh` (espera las 5 migraciones, salta si el admin ya existe)
- [x] 12. URLs relativas: 23 referencias a `golaya.vercel.app/assets/` → `/assets/` (meta tags SEO dejados absolutos)
- [x] 13. `docker-compose.prod.yml`: override Dokploy (sin `seed` por defecto, `restart: unless-stopped`, secrets desde `.env` con `:?`)
- [x] 14. `.env.example` corregidos (backend + uno nuevo en la raíz para el compose de producción)
- [x] 15. README raíz `golaya/`: quickstart Docker, credenciales, cold start Render, deploy Vercel/Render, migración a Dokploy
- [x] 15b. *(extra)* Wiring de `dotenv-java` en `main()` — el README prometía leer `.env` y nunca estaba implementado

## Fase 3 — Verificación

- [x] 16. `docker compose up --build` → login admin, canchas, sedes y perfil probados en navegador (Playwright): homepage con datos reales, login E2E → perfil "Administrador" con 20 reservas
- [x] 17. `docker compose down -v` → arranque limpio desde cero: Flyway V1-V5 + seed + API/frontend en 200. Seed re-ejecutado: salta ("Los datos ya existen")
- [x] 18. `git log --follow backend/pom.xml` → historia preservada cruza el rename

## Pasos manuales (usuario)

- [ ] **Render:** poner Root Directory = `backend/` en el dashboard del servicio (sin esto, el próximo deploy falla)
- [ ] **Repo frontend original (`HertexCode/devPortes`):** el usuario decide archivar/borrar cuando quiera
- [ ] **Vercel:** poner Root Directory = `frontend` en la configuración del proyecto (el repo ya no tiene `index.html` en la raíz; sin esto el deploy sirve 404)

## Log de cambios

- **2026-11-04:** Creado `progress.md`. Plan v2 aprobado (monorepo simétrico baseado en el repo del backend).
- **2026-11-04 — Fase 1 completa:** monorepo estructurado (`backend/` + `frontend/`) con la historia de git preservada, frontend importado, repo renombrado a `HertexCode/golaya` y todo pusheado. Commits: `0784779` (formato), `84c0dc5` (mv a backend/), `1be9d65` (import frontend).
- **2026-11-04 — Fase 2+3 completas:** compose local (db/backend/frontend/seed), Dockerfile+nginx del frontend, config.js auto-detectable, defaults en dev, seed idempotente, URLs relativas, compose de producción, .env.example y README. Verificado E2E en navegador y arranque limpio `down -v` → `up`. Correcciones durante el camino: `.dockerignore` excluía `nginx.conf` (build roto) y `Dotenv.builder()` no existe en dotenv-java 2.2.4 (es `Dotenv.configure()`).
