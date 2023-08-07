

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO video_games (game_name, description, developer, publisher, release_date) VALUES
('Destiny', '', 'Bungie Inc.', 'Activision', '2014-09-09'),
('Destiny 2', '', 'Bungie Inc.', 'Activision', '2017-09-06'),
('Grand Theft Auto V', '', 'Rockstar Games', 'Rockstar Games', '2013-09-17'),
('World of Warcraft', '', 'Blizzard Entertainment', 'Blizzard Entertainment', '2004-11-23'),
('Fallout: New Vegas', '', 'Obsidian Entertainment', 'Bethesda Softworks', '2010-10-19');

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