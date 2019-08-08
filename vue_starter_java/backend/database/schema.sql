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
event_date date,
event_time time,
description varchar(255),
location varchar(255),
title varchar(255),
is_blind boolean,
has_occurred boolean,
is_private boolean,
event_image_name varchar(255)
);



DROP TABLE IF EXISTS whiskey;

CREATE TABLE whiskey
(
whiskey_id SERIAL PRIMARY KEY,
brand varchar(255),
price INT
);
DROP TABLE IF EXISTS whiskeyscores;

CREATE TABLE whiskeyrating
(
whiskey_rating_id SERIAL PRIMARY KEY,
whiskey_id SERIAL REFERENCES whiskey(whiskey_id),
event_id SERIAL REFERENCES event(event_id),
user_id SERIAL REFERENCES users(id),
taste_rating INT,
nose_rating INT,
color_rating INT,
body_rating INT,
finish_rating INT,
price__rating INT,
overall_rating INT
);
DROP TABLE IF EXISTS jointable;

CREATE TABLE jointable
(
id SERIAL REFERENCES users(id),
event_id SERIAL REFERENCES event(event_id)
);

DROP TABLE IF EXISTS whiskeyToUser;

CREATE TABLE whiskeyToUser(
whiskey_id serial References whiskey(whiskey_id),
id serial References users(id)
);

DROP TABLE IF EXISTS whiskeyToEvent;

CREATE TABLE whiskeyToEvent(
whiskey_id serial References whiskey(whiskey_id),
event_id serial References event(event_id)
);

DROP TABLE IF EXISTS previousquestions;

CREATE TABLE previousquestions
(
question_id SERIAL PRIMARY KEY,
question VARCHAR (255)
);

COMMIT;
