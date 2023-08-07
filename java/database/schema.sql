DROP TABLE IF EXISTS users, video_games, game_genre, genre, publishers, developers, game_developers, game_publishers;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE genre (
	genre_id SERIAL,
	genre_name varchar(100),
	CONSTRAINT PK_genre PRIMARY KEY (genre_id)
);	

CREATE TABLE publishers (
	publisher_id SERIAL,
	publisher_name varchar(100),
	CONSTRAINT PK_publisher PRIMARY KEY (publisher_id)
);

CREATE TABLE developers (
	developer_id SERIAL,
	developer_name varchar(100),
	CONSTRAINT PK_developer PRIMARY KEY (developer_id)
);

CREATE TABLE video_games (
	game_id SERIAL,
	game_name varchar(100) NOT NULL UNIQUE,
	description varchar(500) NOT NULL,
	release_date date NOT NULL,
	game_logo varchar(128) NOT NULL,
	CONSTRAINT PK_game PRIMARY KEY (game_id)
);

CREATE TABLE game_publishers (
	game_id INT NOT NULL, 
	publisher_id INT NOT NULL,
	CONSTRAINT PK_publishers PRIMARY KEY (game_id, publisher_id),
	CONSTRAINT FK_game_publishers FOREIGN KEY (game_id) REFERENCES video_games (game_id),
	CONSTRAINT FK_publisher FOREIGN KEY (publisher_id) REFERENCES publishers (publisher_id)
);

CREATE TABLE game_developers (
	game_id INT NOT NULL,
	developer_id INT NOT NULL,
	CONSTRAINT PK_developers PRIMARY KEY (game_id, developer_id),
	CONSTRAINT FK_game_developers FOREIGN KEY (game_id) REFERENCES video_games (game_id),
	CONSTRAINT FK_developer FOREIGN KEY (developer_id) REFERENCES developers (developer_id)
);

CREATE TABLE game_genre (
	game_id int NOT NULL,
	genre_id int NOT NULL,
	game_genre_id SERIAL,
	CONSTRAINT PK_game_genre PRIMARY KEY (game_genre_id),
	CONSTRAINT FK_game FOREIGN KEY (game_id) REFERENCES video_games (game_id),
	CONSTRAINT FK_genre FOREIGN KEY (genre_id) REFERENCES genre (genre_id)
);

