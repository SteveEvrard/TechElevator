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
);

DROP TABLE IF EXISTS jointable;

CREATE TABLE jointable
(
id SERIAL REFERENCES users(id),
event_id SERIAL REFERENCES event(event_id),
whiskey_id SERIAL REFERENCES whiskey(whiskey_id)
);

DROP TABLE IF EXISTS whiskey;

CREATE TABLE whiskey
(
whiskey_id SERIAL PRIMARY KEY,
brand varchar(255),
price INT,
taste_rating INT,
nose_rating INT,
color_rating INT,
body_rating INT,
finish_rating INT,
price__rating INT,
overall_rating INT
);
COMMIT;
