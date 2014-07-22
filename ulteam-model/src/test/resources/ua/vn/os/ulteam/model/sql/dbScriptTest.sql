/*create news table*/
CREATE TABLE IF NOT EXISTS news (
    id                   bigint auto_increment NOT NULL,
    title                TEXT NOT NULL,
    modification_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    views                INTEGER,
    news_content         TEXT,
    picture              BYTEA,
    PRIMARY KEY (id)
);