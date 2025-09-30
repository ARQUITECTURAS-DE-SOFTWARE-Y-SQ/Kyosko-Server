-- Insertar datos iniciales solo si no existen

-- Ciudadanos de prueba
INSERT INTO kyosko.citizen (name) VALUES ('Juan Pérez') ON CONFLICT DO NOTHING;
INSERT INTO kyosko.citizen (name) VALUES ('María González') ON CONFLICT DO NOTHING;
INSERT INTO kyosko.citizen (name) VALUES ('Carlos Rodríguez') ON CONFLICT DO NOTHING;

-- Estados genéricos
INSERT INTO kyosko.generic_state (name, description, discriminator)
VALUES ('Activo', 'Estado activo', 'benefit') ON CONFLICT DO NOTHING;

INSERT INTO kyosko.generic_state (name, description, discriminator)
VALUES ('Pendiente', 'Estado pendiente', 'benefit') ON CONFLICT DO NOTHING;

INSERT INTO kyosko.generic_state (name, description, discriminator)
VALUES ('Aprobado', 'Estado aprobado', 'benefit') ON CONFLICT DO NOTHING;

INSERT INTO kyosko.generic_state (name, description, discriminator)
VALUES ('Activo', 'Estado activo', 'subsidy') ON CONFLICT DO NOTHING;

INSERT INTO kyosko.generic_state (name, description, discriminator)
VALUES ('Pendiente', 'Estado pendiente', 'subsidy') ON CONFLICT DO NOTHING;

-- Managers de prueba
INSERT INTO kyosko.manager (name, cargo, active)
VALUES ('Admin Principal', 'Administrador', true) ON CONFLICT DO NOTHING;

INSERT INTO kyosko.manager (name, cargo, active)
VALUES ('Gestor 1', 'Gestor', true) ON CONFLICT DO NOTHING;