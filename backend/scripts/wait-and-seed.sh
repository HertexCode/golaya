#!/bin/sh
# Seed automático para el entorno local (docker compose).
#
# 1. Espera a que Flyway aplique las 5 migraciones (el backend las ejecuta
#    al arrancar, así que hay que esperar a que las tablas existan).
# 2. Si la base aún no tiene el admin de prueba, aplica seed_data.sql.
#    Si ya existe, no hace nada (para no borrar datos de sesiones previas).
set -eu

MIGRATIONS_ESPERADAS=5
MAX_ESPERAS=150   # 150 * 2s = 5 minutos
esperas=0

aplicadas() {
  psql -tAc "SELECT COUNT(*) FROM flyway_schema_history WHERE success = true" 2>/dev/null || echo 0
}

echo "[seed] Esperando migraciones de Flyway..."
while [ "$(aplicadas)" -lt "$MIGRATIONS_ESPERADAS" ]; do
  esperas=$((esperas + 1))
  if [ "$esperas" -ge "$MAX_ESPERAS" ]; then
    echo "[seed] ERROR: no se aplicaron las migraciones a tiempo." >&2
    exit 1
  fi
  sleep 2
done
echo "[seed] Migraciones aplicadas."

if [ "$(psql -tAc "SELECT 1 FROM admins WHERE email = 'admin@admin.com' LIMIT 1" 2>/dev/null)" = "1" ]; then
  echo "[seed] Los datos ya existen; nada que hacer."
  exit 0
fi

echo "[seed] Aplicando seed_data.sql..."
psql -v ON_ERROR_STOP=1 -q -f /seed/seed_data.sql
echo "[seed] Listo. Admin de prueba: admin@admin.com / Admin1234."
