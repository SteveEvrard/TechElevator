BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users(
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

insert into users (id, username, password, salt, role) values (2, 'test', 'test', 'test', 'user');
insert into users (id, username, password, salt, role) values (3, 'testAdmin', 'test', 'test', 'admin');

COMMIT;
