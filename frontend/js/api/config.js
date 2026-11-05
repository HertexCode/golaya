// URL base de la API
// - Local (localhost): backend en Docker, puerto 8080.
// - Producción: backend desplegado en Render.
//
// Cuando frontend y backend compartan dominio (Dokploy / home lab),
// cambia la URL de producción a '/api/v1' (mismo origen, sin CORS).
const isLocal =
  window.location.hostname === 'localhost' ||
  window.location.hostname === '127.0.0.1';

export const API_URL = isLocal
  ? 'http://localhost:8080/api/v1'
  : 'https://devportes-backend.onrender.com/api/v1';
