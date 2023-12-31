DROP TABLE IF EXISTS users, video_games, game_genre, genre, publishers, developers, game_developers, game_publishers, reviews, lists, games_list, average_rating;

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
	game_name varchar(100) UNIQUE,
	description varchar(500),
	release_date date,
	game_logo varchar(300),
	CONSTRAINT PK_game PRIMARY KEY (game_id)
);

CREATE TABLE lists (
	list_id SERIAL,
	list_name varchar(50),
	user_id INT,
	CONSTRAINT PK_lists PRIMARY KEY (list_id),
	CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE reviews (
	review_id SERIAL,
	game_id INT,
	user_id INT,
	rating INT,
	review_title VARCHAR(50),
	review_body VARCHAR(500),
	CONSTRAINT PK_review PRIMARY KEY (review_id),
	CONSTRAINT FK_review_game FOREIGN KEY (game_id) REFERENCES video_games (game_id),
	CONSTRAINT FK_review_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE average_rating (
	review_id INT,
	user_id INT,
	thumb_up_down BOOLEAN, 
	CONSTRAINT PK_average_rating PRIMARY KEY (review_id, user_id),
	CONSTRAINT FK_review FOREIGN KEY (review_id) REFERENCES reviews (review_id),
	CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE games_list (
	game_id INT,
	list_id INT,
	CONSTRAINT PK_games_list PRIMARY KEY (game_id, list_id),
	CONSTRAINT FK_game FOREIGN KEY (game_id) REFERENCES video_games (game_id),
	CONSTRAINT FK_list FOREIGN KEY (list_id) REFERENCES lists (list_id)
);

CREATE TABLE game_publishers (
	game_id INT, 
	publisher_id INT,
	CONSTRAINT PK_publishers PRIMARY KEY (game_id, publisher_id),
	CONSTRAINT FK_game_publishers FOREIGN KEY (game_id) REFERENCES video_games (game_id),
	CONSTRAINT FK_publisher FOREIGN KEY (publisher_id) REFERENCES publishers (publisher_id)
);

CREATE TABLE game_developers (
	game_id INT,
	developer_id INT,
	CONSTRAINT PK_developers PRIMARY KEY (game_id, developer_id),
	CONSTRAINT FK_game_developers FOREIGN KEY (game_id) REFERENCES video_games (game_id),
	CONSTRAINT FK_developer FOREIGN KEY (developer_id) REFERENCES developers (developer_id)
);

CREATE TABLE game_genre (
	game_id int,
	genre_id int,
	game_genre_id SERIAL,
	CONSTRAINT PK_game_genre PRIMARY KEY (game_genre_id),
	CONSTRAINT FK_game FOREIGN KEY (game_id) REFERENCES video_games (game_id),
	CONSTRAINT FK_genre FOREIGN KEY (genre_id) REFERENCES genre (genre_id)
);

