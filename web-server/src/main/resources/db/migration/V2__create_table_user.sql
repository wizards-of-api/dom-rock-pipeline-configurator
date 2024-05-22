CREATE TABLE user
(
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    is_super TINYINT      NOT NULL
);