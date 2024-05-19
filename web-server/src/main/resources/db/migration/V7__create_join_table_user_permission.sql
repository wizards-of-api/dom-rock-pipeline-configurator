CREATE TABLE user_permission
(
    user_email    VARCHAR(255),
    permission_id INT,
    PRIMARY KEY (user_email, permission_id),
    CONSTRAINT fk_user_email_join_permission FOREIGN KEY (user_email) REFERENCES user (email),
    CONSTRAINT fk_permission_id FOREIGN KEY (permission_id) REFERENCES permission (id_permission)
);