-- Seed data: locations + canchas (fields) + posts (galeria) + metodos de pago
-- Ejecutar contra la base de datos golaya_db
-- Re-ejecutable: limpia datos anteriores antes de insertar

-- Limpiar datos existentes (respetar orden de FK)
DELETE FROM pagos;
DELETE FROM metodos_pago;
DELETE FROM reservations;
DELETE FROM posts;
DELETE FROM fields;
DELETE FROM locations;
DELETE FROM clients;

-- ============================================
-- ADMINISTRADOR DE PRUEBA
-- Credencial: admin@admin.com / Admin1234.
-- ============================================
INSERT INTO admins (
  name,
  identity_document,
  phone_number,
  email,
  password_hash,
  role,
  state
)
VALUES (
  'Administrador',
  '123456789',
  '321123456',
  'admin@admin.com',
  '$2a$10$enk/3.V5v36Mnm4sNJDxJOU5AcbKwdBx1klpAoTWTjqTWW7oB9BLC',
  'ADMIN',
  true
)
ON CONFLICT (email) DO UPDATE SET
  name = EXCLUDED.name,
  identity_document = EXCLUDED.identity_document,
  phone_number = EXCLUDED.phone_number,
  password_hash = EXCLUDED.password_hash,
  role = EXCLUDED.role,
  state = EXCLUDED.state;

-- ============================================
-- CLIENTES DE PRUEBA (2)
-- ============================================
-- Hash BCrypt reutilizado del usuario de prueba existente.
-- Credencial de ambos clientes: Clave1234.
INSERT INTO clients (
  name,
  identity_document,
  phone_number,
  email,
  password_hash,
  reservation_amount,
  classification,
  role,
  state
)
VALUES
  (
    'Valentina Torres',
    'TEST-RES-1001',
    '3000001001',
    'cliente.reservas.1@test.com',
    '$2a$10$enk/3.V5v36Mnm4sNJDxJOU5AcbKwdBx1klpAoTWTjqTWW7oB9BLC',
    0,
    'ESTANDAR',
    'CLIENTE',
    true
  ),
  (
    'Mateo Ramirez',
    'TEST-RES-1002',
    '3000001002',
    'cliente.reservas.2@test.com',
    '$2a$10$enk/3.V5v36Mnm4sNJDxJOU5AcbKwdBx1klpAoTWTjqTWW7oB9BLC',
    0,
    'ESTANDAR',
    'CLIENTE',
    true
  )
ON CONFLICT (email) DO UPDATE SET
  name = EXCLUDED.name,
  identity_document = EXCLUDED.identity_document,
  phone_number = EXCLUDED.phone_number,
  password_hash = EXCLUDED.password_hash,
  classification = EXCLUDED.classification,
  role = EXCLUDED.role,
  state = EXCLUDED.state;

-- ============================================
-- MÉTODOS DE PAGO (4)
-- ============================================
INSERT INTO metodos_pago (name, description, state)
VALUES
  ('NEQUI', 'Pago móvil a través de Nequi', true),
  ('Daviplata', 'Pago móvil a través de Daviplata', true),
  ('Transferencia', 'Transferencia bancaria a cuenta de GOLAYA', true),
  ('Efectivo', 'Pago en efectivo directo en sede', true);

-- ============================================
-- LOCATIONS (3 sedes)
-- ============================================
INSERT INTO locations (
  name,
  headquarters,
  address,
  url_qr_address,
  url_address,
  description,
  state,
  visible
)
VALUES
  (
    'Sede Chapinero',
    'Sede Principal',
    'Cra 7 #45-12, Bogota',
    'https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=https%3A%2F%2Fmaps.app.goo.gl%2FpCpq1o52AK65H3Gq9',
    'https://maps.app.goo.gl/pCpq1o52AK65H3Gq9',
    'Complejo deportivo ubicado en el corazon de Chapinero, con multiples canchas y zonas de descanso.',
    true,
    true
  ),
  (
    'Sede Usaquen',
    'Sede Norte',
    'Cra 19 #145-30, Bogota',
    'https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=https%3A%2F%2Fmaps.app.goo.gl%2F1enRUeajQB1WzYRSA',
    'https://maps.app.goo.gl/1enRUeajQB1WzYRSA',
    'Espacio deportivo en la zona norte de la ciudad, ideal para familias y grupos de amigos.',
    true,
    true
  ),
  (
    'Sede Norte',
    'Sede Deportiva',
    'Cra 15 #80-20, Bogota',
    'https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=https%3A%2F%2Fwww.google.com%2Fmaps%2Fsearch%2F%3Fapi%3D1%26query%3DCra%2B15%2B%252380-20%252C%2BBogota',
    'https://www.google.com/maps/search/?api=1&query=Cra+15+%2380-20%2C+Bogota',
    'Centro deportivo con instalaciones techadas y al aire libre para toda clase de deportes.',
    true,
    true
  );

-- ============================================
-- FIELDS (10 canchas)
-- ============================================

-- Cancha 1: Estadio Principal - Futbol 11 (Chapinero)
INSERT INTO fields (name, capacity, sport, surface, description, hourly_rate, state, details, url_pictures, location_id)
VALUES (
  'Estadio Principal',
  '22',
  'Futbol 11',
  'Grama Natural Pro',
  'Nuestra joya del complejo. Una cancha con medidas oficiales optima para partidos grandes.',
  60000,
  'DISPONIBLE',
  ARRAY['Capacidad ideal: 22 jugadores', 'Graderias laterales para acompanantes', 'Incluye petos de entrenamiento y balones oficiales'],
  ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/canchas/futbol-estadio-principal.webp'],
  (SELECT id FROM locations WHERE name = 'Sede Chapinero')
);

-- Cancha 2: Coliseo Multi-deporte - Futbol Sala / Baloncesto (Norte)
INSERT INTO fields (name, capacity, sport, surface, description, hourly_rate, state, details, url_pictures, location_id)
VALUES (
  'Coliseo Multi-deporte',
  '10',
  'Futbol Sala',
  'Madera Pulida / PVC',
  'Espacio totalmente techado y protegido del clima.',
  45000,
  'DISPONIBLE',
  ARRAY['Tableros de baloncesto hidraulicos ajustables', 'Excelente ventilacion e iluminacion cenital', 'Arcos de futsal con mallas reforzadas'],
  ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/canchas/baloncesto-coliseo.webp'],
  (SELECT id FROM locations WHERE name = 'Sede Norte')
);

-- Cancha 3: Club de Tenis Las Palmas - Tenis (Usaquen)
INSERT INTO fields (name, capacity, sport, surface, description, hourly_rate, state, details, url_pictures, location_id)
VALUES (
  'Club de Tenis Las Palmas',
  '4',
  'Tenis',
  'Superficie Sintetica Rapida',
  'Disenada para amantes de la velocidad y precision.',
  35000,
  'DISPONIBLE',
  ARRAY['Excelente rebote controlado de bola', 'Entorno libre de ruidos disruptivos', 'Alquiler disponible de raquetas y tubos de bolas'],
  ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/canchas/tenis-las-palmas.webp'],
  (SELECT id FROM locations WHERE name = 'Sede Usaquen')
);

-- Cancha 4: Padel Arena Celeste - Padel (Chapinero)
INSERT INTO fields (name, capacity, sport, surface, description, hourly_rate, state, details, url_pictures, location_id)
VALUES (
  'Padel Arena Celeste',
  '4',
  'Padel',
  'Vidrio Templado Panoramico',
  'Disfruta del deporte con mayor crecimiento mundial.',
  40000,
  'DISPONIBLE',
  ARRAY['Estructura panoramica de alta visibilidad', 'Iluminacion LED antideslumbrante orientada al cielo', 'Zona de descanso integrada para hidratacion'],
  ARRAY['https://res.cloudinary.com/skohqf7m/image/upload/v1789941319/canchas/qv0savwxsdrposawltde.avif'],
  (SELECT id FROM locations WHERE name = 'Sede Chapinero')
);

-- Cancha 5: Zona de Entrenamiento - Indoor (Norte)
INSERT INTO fields (name, capacity, sport, surface, description, hourly_rate, state, details, url_pictures, location_id)
VALUES (
  'Zona de Entrenamiento',
  '12',
  'Indoor',
  'Piso de Concreto',
  'Disenada especialmente para sesiones enfocadas en la tecnica.',
  25000,
  'DISPONIBLE',
  ARRAY['Excelente acustica y concentracion', 'Redes de aro en nylon de alta densidad', 'Ideal para practicas libres o rutinas fisicas'],
  ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/canchas/indoor-entrenamiento.webp'],
  (SELECT id FROM locations WHERE name = 'Sede Norte')
);

-- Cancha 6: La Catedral del Basket - Baloncesto / 3x3 (Norte)
INSERT INTO fields (name, capacity, sport, surface, description, hourly_rate, state, details, url_pictures, location_id)
VALUES (
  'La Catedral del Basket',
  '10',
  'Baloncesto',
  'Madera Deportiva',
  'Espacio techado con pista reglamentaria y zona 3x3 para partidos rapidos.',
  30000,
  'DISPONIBLE',
  ARRAY['Tableros homologados con red reglamentaria', 'Iluminacion LED de alta intensidad', 'Marcador electronico digital integrado'],
  ARRAY['https://res.cloudinary.com/skohqf7m/image/upload/v1789941820/canchas/kusxalb9wtoxch9a8yc0.avif'],
  (SELECT id FROM locations WHERE name = 'Sede Norte')
);

-- Cancha 7: Olas del Norte - Voley Playa / Indoor (Norte)
INSERT INTO fields (name, capacity, sport, surface, description, hourly_rate, state, details, url_pictures, location_id)
VALUES (
  'Olas del Norte',
  '12',
  'Voley Playa',
  'Arena Sintetica',
  'Cancha de voley con arena sintetica de alta calidad y red reglamentaria.',
  28000,
  'DISPONIBLE',
  ARRAY['Arena sintetica certificada para competencia', 'Red ajustable para playa o indoor', 'Sector de calentamiento lateral'],
  ARRAY['https://res.cloudinary.com/skohqf7m/image/upload/v1789941428/canchas/vquc9y0okipged6gzzkm.avif'],
  (SELECT id FROM locations WHERE name = 'Sede Norte')
);

-- Cancha 8: El Potrero Sintetico - Futbol 7 / Futsal (Chapinero) - MANTENIMIENTO
INSERT INTO fields (name, capacity, sport, surface, description, hourly_rate, state, details, url_pictures, location_id)
VALUES (
  'El Potrero Sintetico',
  '14',
  'Futbol 7',
  'Cesped Sintetico 4G',
  'Cancha sintetica con dimensiones oficiales de futbol 7, en mantenimiento preventivo.',
  38000,
  'MANTENIMIENTO',
  ARRAY['Cesped sintetico de ultima generacion 4G', 'Dimensiones reglamentarias FIFA', 'Actualmente en mantenimiento preventivo del cesped'],
  ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/canchas/futbol-estadio-principal.webp'],
  (SELECT id FROM locations WHERE name = 'Sede Chapinero')
);

-- Cancha 9: Los Cristales Padel Club - Padel / Padel Cross (Usaquen)
INSERT INTO fields (name, capacity, sport, surface, description, hourly_rate, state, details, url_pictures, location_id)
VALUES (
  'Los Cristales Padel Club',
  '4',
  'Padel',
  'Cristal Templado Panoramico',
  'Club de padel con estructura panoramica y zona para padel cross.',
  42000,
  'DISPONIBLE',
  ARRAY['Muro panoramico sin perfiles verticales', 'Iluminacion cenital homologada', 'Zona adaptada para padel cross y entrenamiento'],
  ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/canchas/padel-arena.webp'],
  (SELECT id FROM locations WHERE name = 'Sede Usaquen')
);

-- Cancha 10: Tierra y Red - Tenis / Tenis de Mesa (Usaquen) - MANTENIMIENTO
INSERT INTO fields (name, capacity, sport, surface, description, hourly_rate, state, details, url_pictures, location_id)
VALUES (
  'Tierra y Red',
  '4',
  'Tenis',
  'Polvo de Ladrillo',
  'Cancha de tenis con superficie de polvo de ladrillo en renovacion.',
  32000,
  'MANTENIMIENTO',
  ARRAY['Superficie de arcilla roja natural', 'Red reglamentaria con poste de acero', 'Actualmente en proceso de nivelacion del court'],
  ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/canchas/tenis-las-palmas.webp'],
  (SELECT id FROM locations WHERE name = 'Sede Usaquen')
);

-- ============================================
-- POSTS (6 publicaciones de galería)
-- ============================================
INSERT INTO posts (name, description, url_pictures, event_date)
VALUES
  (
    'Torneo tapitas',
    'Torneo realizado el 2025 de abril a las 18:00 horas donde el campeon fue el equipo "Los Campeones"',
    ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/torneodefutbol.jpg'],
    '2025-04-20'
  ),
  (
    'Eliminaciones FutbolClub',
    'En las eliminaciones del FutbolClub se enfrentaron los mejores equipos de la nacion.',
    ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/eliminacionesfutbolclub.jpg'],
    '2025-05-12'
  ),
  (
    'Torneo de tenis 2026',
    'El torneo de tenis 2026 se reunieron los mejores jugadores del país y solo un jugador logró la victoria y fue del país de Estados Unidos.',
    ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/torneotenis.jpg'],
    '2026-03-10'
  ),
  (
    'Torneo de pádel 2024',
    'Torneo de pádel compitiendo por el primer lugar entre muchos participantes.',
    ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/padeljugando.jpg'],
    '2024-11-18'
  ),
  (
    'Zona de Entrenamiento',
    'Prepárate y mejora tus habilidades en nuestras modernas instalaciones.',
    ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/entrenandoengym.jpg'],
    '2026-01-15'
  ),
  (
    'Torneo de Baloncesto',
    'En este torneo los mejores deportistas y estrellas del país compitieron en un entorno de alto nivel para ganar su primer lugar y título a nivel nacional.',
    ARRAY['https://raw.githubusercontent.com/CamiloBermeo/devPortes/refs/heads/main/assets/img/torneodebaloncesto.jpg'],
    '2026-04-15'
  );

-- ============================================
-- RESERVAS DE PRUEBA (20)
-- Septiembre y octubre de 2026, 10 por cliente
-- ============================================
-- Horarios válidos para la interfaz de reservas:
-- 08:00, 09:00, 10:00, 16:00, 17:00, 18:00, 19:00, 20:00 y 21:00.
INSERT INTO reservations (
  user_id,
  field_id,
  reservation_date,
  start_time,
  end_time,
  total_hours,
  total_pay,
  remaining_payment,
  state
)
SELECT
  c.id,
  f.id,
  datos.reservation_date::date,
  datos.start_time::time,
  (datos.start_time::time + INTERVAL '1 hour')::time,
  1,
  f.hourly_rate,
  ROUND(f.hourly_rate * 0.5, 2),
  'PENDIENTE'
FROM (
  VALUES
    ('cliente.reservas.1@test.com', '2026-09-03', '08:00', 'Estadio Principal'),
    ('cliente.reservas.1@test.com', '2026-09-10', '16:00', 'Coliseo Multi-deporte'),
    ('cliente.reservas.1@test.com', '2026-09-17', '18:00', 'Club de Tenis Las Palmas'),
    ('cliente.reservas.1@test.com', '2026-09-24', '19:00', 'Padel Arena Celeste'),
    ('cliente.reservas.1@test.com', '2026-09-29', '20:00', 'La Catedral del Basket'),
    ('cliente.reservas.1@test.com', '2026-10-02', '09:00', 'Zona de Entrenamiento'),
    ('cliente.reservas.1@test.com', '2026-10-09', '17:00', 'Los Cristales Padel Club'),
    ('cliente.reservas.1@test.com', '2026-10-16', '10:00', 'Olas del Norte'),
    ('cliente.reservas.1@test.com', '2026-10-23', '21:00', 'Estadio Principal'),
    ('cliente.reservas.1@test.com', '2026-10-30', '08:00', 'Padel Arena Celeste'),
    ('cliente.reservas.2@test.com', '2026-09-05', '09:00', 'Coliseo Multi-deporte'),
    ('cliente.reservas.2@test.com', '2026-09-12', '17:00', 'Zona de Entrenamiento'),
    ('cliente.reservas.2@test.com', '2026-09-19', '19:00', 'Los Cristales Padel Club'),
    ('cliente.reservas.2@test.com', '2026-09-26', '20:00', 'Estadio Principal'),
    ('cliente.reservas.2@test.com', '2026-09-30', '10:00', 'Club de Tenis Las Palmas'),
    ('cliente.reservas.2@test.com', '2026-10-04', '16:00', 'Padel Arena Celeste'),
    ('cliente.reservas.2@test.com', '2026-10-11', '18:00', 'Olas del Norte'),
    ('cliente.reservas.2@test.com', '2026-10-18', '21:00', 'Coliseo Multi-deporte'),
    ('cliente.reservas.2@test.com', '2026-10-25', '08:00', 'La Catedral del Basket'),
    ('cliente.reservas.2@test.com', '2026-10-28', '09:00', 'Estadio Principal')
) AS datos(email, reservation_date, start_time, field_name)
JOIN clients c ON c.email = datos.email
JOIN fields f ON f.name = datos.field_name;

-- ============================================
-- 20 RESERVAS ADICIONALES: ESTADIO PRINCIPAL
-- Septiembre de 2026, sin solapamientos en la misma cancha.
-- ============================================
INSERT INTO reservations (
  user_id,
  field_id,
  reservation_date,
  start_time,
  end_time,
  total_hours,
  total_pay,
  remaining_payment,
  state
)
SELECT
  c.id,
  f.id,
  datos.reservation_date::date,
  datos.start_time::time,
  (datos.start_time::time + INTERVAL '1 hour')::time,
  1,
  f.hourly_rate,
  ROUND(f.hourly_rate * 0.5, 2),
  'PENDIENTE'
FROM (
  VALUES
    ('cliente.reservas.1@test.com', '2026-09-01', '08:00'),
    ('cliente.reservas.2@test.com', '2026-09-02', '09:00'),
    ('cliente.reservas.1@test.com', '2026-09-04', '10:00'),
    ('cliente.reservas.2@test.com', '2026-09-05', '16:00'),
    ('cliente.reservas.1@test.com', '2026-09-06', '17:00'),
    ('cliente.reservas.2@test.com', '2026-09-07', '18:00'),
    ('cliente.reservas.1@test.com', '2026-09-08', '19:00'),
    ('cliente.reservas.2@test.com', '2026-09-09', '20:00'),
    ('cliente.reservas.1@test.com', '2026-09-10', '21:00'),
    ('cliente.reservas.2@test.com', '2026-09-11', '08:00'),
    ('cliente.reservas.1@test.com', '2026-09-12', '09:00'),
    ('cliente.reservas.2@test.com', '2026-09-13', '10:00'),
    ('cliente.reservas.1@test.com', '2026-09-14', '16:00'),
    ('cliente.reservas.2@test.com', '2026-09-15', '17:00'),
    ('cliente.reservas.1@test.com', '2026-09-16', '18:00'),
    ('cliente.reservas.2@test.com', '2026-09-17', '19:00'),
    ('cliente.reservas.1@test.com', '2026-09-18', '20:00'),
    ('cliente.reservas.2@test.com', '2026-09-19', '21:00'),
    ('cliente.reservas.1@test.com', '2026-09-20', '08:00'),
    ('cliente.reservas.2@test.com', '2026-09-21', '09:00')
) AS datos(email, reservation_date, start_time)
JOIN clients c ON c.email = datos.email
JOIN fields f ON f.name = 'Estadio Principal';
