create table wp_schema.tbl_users if not exists(
    id serial not null,
    name varchar(200),
    username varchar(200),
    email varchar(200) not null,
    primary key(id)
);