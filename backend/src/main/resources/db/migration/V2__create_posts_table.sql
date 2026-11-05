-- Tabla de Publicaciones (Posts)
CREATE TABLE posts (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    url_pictures TEXT[] NOT NULL,
    event_date DATE NOT NULL
);
