
CREATE TABLE file_column
(
    column_id          INT auto_increment PRIMARY KEY,
    file_id            INT          NOT NULL,
    column_number      INT          NOT NULL,
    column_name        varchar(50)  NOT NULL,
    column_type        varchar(50)  NOT NULL,
    column_empty       INT          NOT NULL,
    column_description VARCHAR(225) NOT NULL,
    column_active      INT          NOT NULL,
    column_is_hash     INT          DEFAULT -1,
    column_valid       INT          DEFAULT -1,
    CONSTRAINT fk_file_id FOREIGN KEY (file_id) REFERENCES lz_config (file_id)
);