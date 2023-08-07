INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO genre (genre_name) VALUES
('Action'),
('Adventure'),
('Role-playing game'),
('Fantasy'),
('Simulation'),
('Strategy'),
('First-person shooter'),
('Sports'),
('Puzzle'),
('Horror'),
('Fighting'),
('Survival'),
('Massively multiplayer');

INSERT INTO developers (developer_name) VALUES
('Blizzard Entertainment'),
('Bungie Inc.'),
('High Moon Studios'),
('Radical Entertainment'),
('Rockstar Games'),
('Rockstar North'),
('Obsidian Entertainment');

INSERT INTO publishers (publisher_name) VALUES
('Blizzard Entertainment'),
('Activision'),
('Bungie Inc.'),
('Rockstar Games'),
('Bethesda Softworks');

INSERT INTO video_games (game_name, description, release_date) VALUES
('Destiny', '', '2014-09-09'),
('Destiny 2', '', '2017-09-06'),
('Grand Theft Auto V', '', '2013-09-17'),
('World of Warcraft', '', '2004-11-23'),
('Fallout: New Vegas', '', '2010-10-19');

INSERT INTO game_genre (game_id, genre_id) VALUES
(1, 7),
(1, 13),
(1, 1),
(1, 2),
(2, 7),
(2, 13),
(2, 1),
(2, 2),
(3, 1),
(3, 3),
(3, 5),
(4, 2),
(4, 4),
(4, 13),
(4, 3),
(5, 2),
(5, 3),
(5, 7),
(5, 12);

INSERT INTO game_developers (game_id, developer_id) VALUES
(1, 2),
(1, 3),
(1, 4),
(2, 2),
(3, 5),
(3, 6),
(4, 1),
(5, 7);

INSERT INTO game_publishers (game_id, publisher_id) VALUES
(1, 2),
(1, 3),
(2, 3),
(3, 4),
(4, 1),
(5, 5);