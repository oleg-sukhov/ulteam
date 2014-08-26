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

/*create news table*/
CREATE TABLE IF NOT EXISTS news (
    id                   BIGSERIAL NOT NULL,
    title                VARCHAR(255) NOT NULL,
    short_description    TEXT NOT NULL,
    modification_date    BYTEA NOT NULL,
    views                BIGINT NOT NULL,
    news_content         TEXT NOT NULL,
    picture              BYTEA NOT NULL,
    PRIMARY KEY (id)           
);
END
$do$
