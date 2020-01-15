DROP TABLE IF EXISTS user_role CASCADE;
DROP TABLE IF EXISTS app_user CASCADE;
DROP TABLE IF EXISTS app_role CASCADE;

CREATE TABLE app_user
(
  id       SERIAL4      NOT NULL PRIMARY KEY,
  name     VARCHAR(20)  NOT NULL UNIQUE CHECK ( name <> '' ),
  password VARCHAR(100) NOT NULL CHECK (password <> ''),
  active   BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE app_role
(
  id   SERIAL2     NOT NULL PRIMARY KEY,
  name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE user_role
(
  id       SERIAL4 NOT NULL PRIMARY KEY,
  app_user BIGINT  NOT NULL REFERENCES app_user (id),
  role     INT     NOT NULL REFERENCES app_role (id),
  CONSTRAINT unique_user_role_constraint UNIQUE (app_user, role)
);