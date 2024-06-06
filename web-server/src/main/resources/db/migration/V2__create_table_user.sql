CREATE TABLE user
(
    name          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) PRIMARY KEY,
    password      VARCHAR(255) NOT NULL,
    permission_id INT,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,

    CONSTRAINT fk_permission_id FOREIGN KEY (permission_id) REFERENCES permission (id_permission)
);