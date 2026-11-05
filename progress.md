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

- [ ] 7. `docker-compose.yml`: `db` (Postgres 16 + healthcheck + volumen), `backend` (defaults de dev), `frontend` (nginx :8000), `seed` (espera Flyway → `DATABASE/seed_data.sql`)
- [ ] 8. `frontend/Dockerfile`: reemplazar copia Maven errónea por `nginx:alpine` + `nginx.conf` (gzip, caché `assets/`, HTML `no-cache`)
- [ ] 9. `frontend/js/api/config.js`: auto-detección localhost → `http://localhost:8080/api/v1`, si no → producción. Comentario para migrar a `/api/v1` (Dokploy)
- [ ] 10. `backend/.../application-dev.yaml`: defaults `${DB_HOST:localhost}`, `${POSTGRES_PASSWORD:postgres}`, `${JWT_SECRET:dev-secret-local}`, `${CORS_URLS:http://localhost:8000,...}`, Cloudinary vacío
- [ ] 11. Seed automático: script `psql` con reintentos que espera las 5 migraciones de Flyway antes de insertar
- [ ] 12. URLs relativas: reemplazar ~20+ referencias absolutas a `golaya.vercel.app` (CSS/HTML/JS)
- [ ] 13. `docker-compose.prod.yml`: override Dokploy (sin `seed`, `restart: unless-stopped`, secrets desde `.env`)
- [ ] 14. `backend/.env.example`: corregir typos (`PASSWORD-DB`, `URL_DB =`) + aclarar que en local no se necesita
- [ ] 15. README raíz `golaya/`: quickstart Docker, credenciales, nota cold start Render, deploy Vercel/Render, migración a Dokploy

## Fase 3 — Verificación

- [ ] 16. `docker compose up --build` → probar login admin, canchas, sedes, galería en navegador
- [ ] 17. `docker compose down -v` → subir de nuevo → arranque limpio desde cero
- [ ] 18. `git log --follow backend/pom.xml` → historia preservada

## Pasos manuales (usuario)

- [ ] **Render:** poner Root Directory = `backend/` en el dashboard del servicio (sin esto, el próximo deploy falla)
- [ ] **Repo frontend original (`HertexCode/devPortes`):** el usuario decide archivar/borrar cuando quiera
- [ ] **Vercel:** verificar que el deploy siga apuntando bien tras el reordenamiento (no debería cambiar, el frontend no cambia de estructura interna)

## Log de cambios

- **2026-11-04:** Creado `progress.md`. Plan v2 aprobado (monorepo simétrico baseado en el repo del backend).
- **2026-11-04 — Fase 1 completa:** monorepo estructurado (`backend/` + `frontend/`) con la historia de git preservada, frontend importado, repo renombrado a `HertexCode/golaya` y todo pusheado. Commits: `0784779` (formato), `84c0dc5` (mv a backend/), `1be9d65` (import frontend).
