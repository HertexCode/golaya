INSERT INTO admins (
    name, identity_document, phone_number, email, password_hash, role, state
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
ON CONFLICT (email) DO NOTHING;
