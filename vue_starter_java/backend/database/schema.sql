BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users(
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user')
);

DROP TABLE IF EXISTS userdetails;

CREATE TABLE userdetails(
	username varchar(255)  REFERENCES users(username),
	full_name varchar(255),
	phone varchar(255),
	city varchar(255),
	fav_brands varchar(1047),
	fav_types varchar(1047)
);

DROP TABLE IF EXISTS event;

Create Table event(
event_id SERIAL PRIMARY KEY,
event_date date,
event_time varchar(255),
description varchar(255),
location varchar(255),
title varchar(255),
is_blind boolean,
is_private boolean
);


DROP TABLE IF EXISTS whiskey;

CREATE TABLE whiskey
(
whiskey_id SERIAL PRIMARY KEY,
brand varchar(255),
price INT
);
DROP TABLE IF EXISTS whiskeyrating;

CREATE TABLE whiskeyrating
(
whiskey_rating_id SERIAL PRIMARY KEY,
whiskey_id SERIAL REFERENCES whiskey(whiskey_id),
event_id SERIAL REFERENCES event(event_id),
id SERIAL REFERENCES users(id),
taste_rating INT,
nose_rating INT,
color_rating INT,
body_rating INT,
finish_rating INT,
price__rating INT,
overall_rating INT
);
DROP TABLE IF EXISTS usersToEvent;

CREATE TABLE usersToEvent
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

DROP TABLE IF EXISTS surveyQuestions;

CREATE TABLE surveyQuestions
(
question_id SERIAL PRIMARY KEY,
event_id SERIAL REFERENCES event(event_id),
question VARCHAR (255),
question_type VARCHAR (255)
);

DROP TABLE IF EXISTS surveyAnswers;

CREATE TABLE surveyAnswers
(
answer_id SERIAL PRIMARY KEY,
question_id SERIAL REFERENCES surveyQuestions(question_id),
event_id SERIAL REFERENCES event(event_id),
id SERIAL REFERENCES users(id),
answer VARCHAR (255)
);

DROP TABLE IF EXISTS surveyAnswersToSurveyQuestions;

CREATE TABLE surveyAnwersToSurveyQuestions
(
question_id SERIAL REFERENCES surveyQuestions(question_id),
answer_id SERIAL REFERENCES surveyAnswers(answer_id)

);


Insert Into event Values (default, '08/08/2019', '7:00 PM', 'All-you-can-taste!', '104 Farrow Ave Suite 7', 'Blue Blind Paralytic Drunk', true, false);

Insert Into event Values (default, '10/31/2019', '1:00 PM', 'A soul-stirring taste of whiskey that will haunt you.', '31 All Hallows Blvd', 'Terrifying Tastes', true, false);

Insert Into whiskey (brand, price) Values ('Jamisons 18 Year Limited reserve', 200);
Insert Into whiskey (brand, price) Values ('Colonel Taylor Small Batch KY Bourbon', 40);

insert into users values (default, 'whiskey', '8mR22I72p+QlLz/aOpVgKg==', 'Shb49EyESa/+y5mT5sRgLlNLF9PNyze+78RnsO5ZwJdZ9GhUle+VQct1rxAW9abWm6ygjxENS4QHkDiCJQgYEO1gGw6w6ODTJLeqBwPy7M4QDXPssH4k6jyLxfcKQzPzv191kbbMRQuJr7QKRjecNFi7miz4d2PGrwW0wu55C9I=', 'admin');
insert into users (id, username, password, salt, role) values (default, 'test', 'test', 'test', 'user');

insert into whiskeytoevent values (1, 1);
insert into whiskeytoevent values (1, 2);
insert into whiskeytoevent values (2, 1);
insert into whiskeytoevent values (2, 2);


COMMIT;
