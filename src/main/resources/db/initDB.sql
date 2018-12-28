DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP TABLE votes IF EXISTS;
DROP TABLE dishes IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ
  AS INTEGER
  START WITH 100000;


CREATE TABLE users
(
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name       VARCHAR(255)            NOT NULL,
  email      VARCHAR(255)            NOT NULL,
  password   VARCHAR(255)            NOT NULL,
  registered DATE DEFAULT now() NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON USERS (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id      INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  phone   VARCHAR(255) NOT NULL,
  CONSTRAINT adress_phone_idx UNIQUE (address, phone)
);

CREATE TABLE dishes
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name          VARCHAR(255)          NOT NULL,
  price         DECIMAL(10, 2)        NOT NULL,
  date          DATE DEFAULT now()    NOT NULL,
  restaurant_id INTEGER               NOT NULL,
  FOREIGN KEY (restaurant_id) references RESTAURANTS (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  user_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  date          DATE DEFAULT now() NOT NULL,
  PRIMARY KEY (user_id, date),
  FOREIGN KEY (user_id) references USERS (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) references RESTAURANTS (id) ON DELETE CASCADE
);