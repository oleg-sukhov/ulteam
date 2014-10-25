/*check if database exist*/
DO
$do$
BEGIN
IF EXISTS (SELECT 1 FROM pg_database WHERE datname = 'ulteam') THEN
   RAISE NOTICE 'Database <ulteam> exist';
ELSE
   CREATE DATABASE ulteam ENCODING 'UTF-8';
   RAISE NOTICE 'Database was created';
END IF;

--/*create news table*/
CREATE TABLE IF NOT EXISTS news (
    id                                  BIGSERIAL NOT NULL,
    title                               VARCHAR(255) NOT NULL,
    short_description    TEXT NOT NULL,
    modification_date   BYTEA NOT NULL,
    views                           BIGINT NOT NULL,
    news_content          TEXT NOT NULL,
    picture                        BYTEA NOT NULL,
    PRIMARY KEY(id)
);54=-09876НКУЦФЙ ІВауп ро

/*create photo album table for storing information about all photo albums*/
CREATE TABLE IF NOT EXISTS PhotoAlbum (
    id                                 BIGSERIAL NOT NULL,
    title                              VARCHAR(255) NOT NULL,
    description               TEXT NOT NULL,
    creation_date           BYTEA NOT NULL,
    --file system location path
    fs_location_path     VARCHAR(255) NOT NULL,
    author                       VARCHAR(255),
    PRIMARY KEY(id)
);

/*create table for storing information about photo, but not photo*/
CREATE TABLE IF NOT EXISTS Photo (
    id                                    BIGSERIAL NOT NULL,
    photo_name               VARCHAR(255) NOT NULL,
    photo_description    TEXT,
    album_id                      BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(album_id) REFERENCES PhotoAlbum(id)
);

/*create table for storing information about season*/
CREATE TABLE IF NOT EXISTS Season (
    id BIGSERIAL NOT NULL,
    name VARCHAR(10) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

/*create table for storing information about football team*/
CREATE TABLE IF NOT EXISTS Team (
    id BIGSERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    town VARCHAR(100) NOT NULL,
    logo VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

/*create table for storing information about tournaments like cup or liga*/
CREATE TABLE IF NOT EXISTS Tournament (
    id BIGSERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    season BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(season) REFERENCES Season(id)
);

/*create table for storing information about game details*/
CREATE TABLE IF NOT EXISTS Game_details (
    id BIGSERIAL NOT NULL,
    PRIMARY KEY(id)
);

/*create table for storing information about games*/
CREATE TABLE IF NOT EXISTS Game (
    id                                     BIGSERIAL NOT NULL,
    owner_team                BIGINT NOT NULL,
    guest_team                  BIGINT NOT NULL,
    owner_team_goals SMALLINT,
    guest_team_goals      SMALLINT,
    game_date                   DATE NOT NULL,
    tournament                 BIGINT NOT NULL,
		tour                                VARCHAR(50),
    game_details               BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY(owner_team)     REFERENCES Team(id),
    FOREIGN KEY(guest_team)       REFERENCES Team(id),
    FOREIGN KEY(tournament)     REFERENCES Tournament(id),
    FOREIGN KEY(game_details)    REFERENCES Game_details(id)
);
END
$do$