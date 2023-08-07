BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO video_games (game_name, description, developer, publisher, release_date) VALUES
('')
('')
('')
('')
('')
('')
('')
('')
('')
('')
('')
('')

INSERT INTO game_genre (game_id, genre_id) VALUES
(1, 1)
(1, 1)
(1, 1)
(1, 1)
(1, 1)
(1, 1)
(1, 1)
(1, 1)
(1, 1)
(1, 1)
(1, 1)

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

COMMIT TRANSACTION;
