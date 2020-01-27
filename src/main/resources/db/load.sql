INSERT INTO app_role(id, name)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

INSERT INTO app_user (id, name, password, active)
VALUES (1, 'testovik1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', TRUE),
       (2, 'testovik2', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', TRUE);

INSERT INTO user_role (id, app_user, role)
VALUES (1, 1, 2),
       (2, 2, 2);

INSERT INTO album (id, name, description, owner, path_preview)
VALUES (1, 'Первый', 'Первый тестовый альбом', 2, 'a001.jpg'),
    (2, 'Второй', 'Второй тестовый альбом', 2, 'a002.jpg');

INSERT INTO photo (id, name, description, album, sort, path_preview, path_full)
VALUES (1, '001.jpg', '', 1, 1, 's001.jpg', 'f001.jpg'),
       (2, '002.jpg', '', 1, 2, 's002.jpg', 'f002.jpg'),
       (3, '003.jpg', '', 2, 3, 's003.jpg', 'f003.jpg');