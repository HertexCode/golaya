-- 1. Tabla de Clientes
CREATE TABLE clients (
                         id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         identity_document VARCHAR(100) NOT NULL UNIQUE,
                         phone_number VARCHAR(50),
                         email VARCHAR(255) NOT NULL UNIQUE,
                         password_hash VARCHAR(255) NOT NULL,
                         reservation_amount INT NOT NULL,
                         classification VARCHAR(50),
                         role VARCHAR(50) NOT NULL,
                         state BOOLEAN NOT NULL
);

-- 2. Tabla de Administradores
CREATE TABLE admins (
                        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        identity_document VARCHAR(100) NOT NULL UNIQUE,
                        phone_number VARCHAR(50),
                        email VARCHAR(255) NOT NULL UNIQUE,
                        password_hash VARCHAR(255) NOT NULL,
                        role VARCHAR(50) NOT NULL,
                        state BOOLEAN NOT NULL
);

-- 3. Tabla de Ubicaciones
CREATE TABLE locations (
                           id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           name VARCHAR(255),
                           headquarters VARCHAR(255),
                           address VARCHAR(255),
                           url_qr_address VARCHAR(500),
                           description TEXT,
                           state BOOLEAN NOT NULL
);

-- 4. Tabla de Canchas
CREATE TABLE fields (
                        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        location_id BIGINT NOT NULL REFERENCES locations(id) ON DELETE RESTRICT,
                        url_pictures TEXT[] NOT NULL,
                        name VARCHAR(255) NOT NULL,
                        capacity VARCHAR(50) NOT NULL,
                        sport VARCHAR(100) NOT NULL,
                        description TEXT NOT NULL,
                        surface VARCHAR(100) NOT NULL,
                        details TEXT[] NOT NULL,
                        hourly_rate NUMERIC(10, 2) NOT NULL,
                        state VARCHAR(50) NOT NULL
);

-- 5. Tabla de Reservas
CREATE TABLE reservations (
                              id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              user_id BIGINT NOT NULL REFERENCES clients(id) ON DELETE RESTRICT,
                              field_id BIGINT NOT NULL REFERENCES fields(id) ON DELETE RESTRICT,
                              reservation_date TIMESTAMP NOT NULL,
                              start_time TIME NOT NULL,
                              end_time TIME NOT NULL,
                              total_hours INT NOT NULL,
                              total_pay NUMERIC(10, 2) NOT NULL,
                              remaining_payment NUMERIC(10, 2) NOT NULL,
                              state VARCHAR(50) NOT NULL
);

-- 6. Tabla de Métodos de Pago
CREATE TABLE metodos_pago (
                              id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              name VARCHAR(100) NOT NULL,
                              description TEXT NOT NULL,
                              state BOOLEAN NOT NULL
);

-- 7. Tabla de Pagos
CREATE TABLE pagos (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       payment_method_id BIGINT NOT NULL REFERENCES metodos_pago(id) ON DELETE RESTRICT,
                       reservation_id BIGINT NOT NULL REFERENCES reservations(id) ON DELETE RESTRICT,
                       total_amount NUMERIC(10, 2),
                       payment_date TIMESTAMP,
                       fixed_percentage DOUBLE PRECISION,
                       state VARCHAR(50) NOT NULL,
                       notes TEXT NOT NULL
);