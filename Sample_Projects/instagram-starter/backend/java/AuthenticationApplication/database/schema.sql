BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user'),
  image varchar(200)
);

DROP TABLE IF EXISTS event;

CREATE TABLE event
(
event_id SERIAL PRIMARY KEY,
date_time    TIMESTAMP,
location VARCHAR(255),
title varchar(255),
has_occured boolean,
is_private boolean
);

DROP TABLE IF EXISTS joinTable;

CREATE TABLE joinTable
(
event_id serial REFERENCES     event(event_id),
id SERIAL REFERENCES users(id)
)
;

COMMIT TRANSACTION;