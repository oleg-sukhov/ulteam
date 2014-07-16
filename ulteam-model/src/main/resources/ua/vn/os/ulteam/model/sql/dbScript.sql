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
    id                   INTEGER NOT NULL,
    title                TEXT NOT NULL,
    modification_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    views                INTEGER,
    new_content          TEXT,
    picture              BYTEA,
    PRIMARY KEY (id)           
);
END
$do$
