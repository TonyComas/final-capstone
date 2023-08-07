
DROP TABLE IF EXISTS users, video_games, game_genre, genre CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE video_games (
	game_id SERIAL,
	game_name varchar(100) NOT NULL UNIQUE,
	description varchar(500) NOT NULL,
	release_date varchar(50) NOT NULL,
	developer varchar(100) NOT NULL,
	publisher varchar(100),
	CONSTRAINT PK_game PRIMARY KEY (game_id)
);

CREATE TABLE game_genre (
	game_id int NOT NULL,
	genre_id int NOT NULL,
	game_genre_id SERIAL,
	CONSTRAINT PK_genre PRIMARY KEY (game_genre_id),
	CONSTRAINT FK_game FOREIGN KEY (game_id) REFERENCES video_games (game_id),
	CONSTRAINT FK_genre FOREIGN KEY (genre_id) REFERENCES genre (genre_id)
);

CREATE TABLE genre (
	genre_id SERIAL,
	genre_name varchar(100),
	CONSTRAINT PK_genre PRIMARY KEY (genre_id)
);

