BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user')
);

DROP TABLE IF EXISTS event;

Create Table event(
event_id SERIAL PRIMARY KEY,
date_time TIMESTAMP,
location varchar(255),
title varchar(255),
is_blind boolean,
has_occurred boolean,
is_private boolean
)

COMMIT;
