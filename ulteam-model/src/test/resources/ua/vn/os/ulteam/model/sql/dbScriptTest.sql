/*create news table*/
CREATE TABLE IF NOT EXISTS news (
    id                   SERIAL NOT NULL,
    title                TEXT NOT NULL,
    modification_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    views                INTEGER,
    news_content         TEXT,
    picture              BYTEA,
    PRIMARY KEY (id)
);
