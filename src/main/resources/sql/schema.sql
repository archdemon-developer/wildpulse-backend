CREATE TABLE IF NOT EXISTS wp_schema.tbl_users (
    id integer NOT NULL,
    name VARCHAR(200),
    username VARCHAR(200),
    email VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)
);
