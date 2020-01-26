INSERT INTO app_role(id, name)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

INSERT INTO app_user (id, name, password, active)
VALUES (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', TRUE),
       (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', TRUE);

INSERT INTO user_role (id, app_user, role)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 2, 2);

INSERT INTO album (id, name, description, owner)
VALUES (1, 'Первый', 'Первый тестовый альбом', 2),
    (2, 'Второй', 'Второй тестовый альбом', 2),
    (3, 'Третий', 'Третий тестовый альбом', 2);

INSERT INTO photo (id, description, album, path_small, path_full)
VALUES (1, '001', 1, 's001', 'f001'),
       (2, '002', 1, 's002', 'f002');