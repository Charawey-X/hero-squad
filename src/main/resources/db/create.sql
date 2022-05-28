CREATE DATABASE hero_squad;
\c hero_squad;
CREATE TABLE heroes (id SERIAL PRIMARY KEY, name VARCHAR, age INTEGER, superpower VARCHAR, weakness VARCHAR, squad_id INTEGER);
CREATE TABLE squads (id SERIAL PRIMARY KEY, name VARCHAR, maxSize INTEGER, cause VARCHAR);
CREATE DATABASE hero_squad_test WITH TEMPLATE hero_squad;