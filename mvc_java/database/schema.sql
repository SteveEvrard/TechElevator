-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL
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

COMMIT;