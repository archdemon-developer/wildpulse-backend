CREATE TABLE IF NOT EXISTS wp_schema.tbl_users (
    id VARCHAR(200) NOT NULL,
    name VARCHAR(200),
    username VARCHAR(200),
    email VARCHAR(200) NOT NULL,
    email_verified BOOLEAN,
    photo_url VARCHAR(500),
    is_subscribed BOOLEAN,
    PRIMARY KEY (id)
);
