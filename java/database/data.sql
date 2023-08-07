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
('Destiny', 'Destiny is an online first-person shooter video game developed by Bungie. It was released worldwide on September 9, 2014, for the PlayStation 3, PlayStation 4, Xbox 360, and Xbox One consoles. Destiny marked Bungies first new console franchise since the Halo series, and it was the first game in a ten-year agreement between Bungie and Activision. Set in a "mythological science fiction" world, the game features a multiplayer "shared-world" environment with elements of role-playing games', '2014-09-09'),
('Destiny 2', 'Set in a "mythic science fiction" world, Destiny 2 features a multiplayer first-person shooter "shared-world" environment with elements of role-playing games. Like the original, activities in Destiny 2 are divided among player versus environment (PvE) and player versus player (PvP) game types. In addition to normal story missions, PvE features three-player "strikes" and dungeons and six-player raids.', '2017-09-06'),
('Grand Theft Auto V', 'GTA V (Grand Theft Auto V) is an action-adventure video game developed by Rockstar Games. Set in the fictional city of Los Santos, players explore a vast open-world, assuming the roles of three protagonists: Michael, a retired bank robber; Franklin, a hustler; and Trevor, a violent psychopath. They undertake daring heists, engage in car chases, shootouts, and various criminal activities, immersing players in a compelling narrative and unrestricted gameplay.', '2013-09-17'),
('World of Warcraft', 'World of Warcraft (WoW) is a massively multiplayer online role-playing game (MMORPG) by Blizzard Entertainment. Players venture into the fantastical realm of Azeroth, choosing from various races and classes. They embark on quests, battle fierce creatures, and engage in epic raids and player-versus-player combat. With a rich lore, frequent content updates, and a vibrant community, WoW offers a captivating gaming experience in a constantly evolving virtual world.', '2004-11-23'),
('Fallout: New Vegas', 'Fallout: New Vegas is a post-apocalyptic action role-playing game developed by Obsidian Entertainment. Set in a nuclear-blasted Mojave Desert, players assume the role of a courier on a mission for revenge and retrieval of a stolen package. They encounter factions vying for control, make impactful choices, and navigate a morally grey world. Through engaging storytelling, exploration, and tactical combat, players shape the outcome of the wasteland, creating a unique and immersive experience.', '2010-10-19');

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