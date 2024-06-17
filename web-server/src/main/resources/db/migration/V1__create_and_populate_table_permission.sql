-- create
CREATE TABLE permission
(
    id_permission INT AUTO_INCREMENT PRIMARY KEY,
    type          VARCHAR(20) CHECK (type IN ('ADMIN',
                                              'LZ',
                                              'LZ-BRONZE',
                                              'LZ-SILVER',
                                              'LZ-BRONZE-SILVER',
                                              'BRONZE',
                                              'BRONZE-SILVER',
                                              'SILVER')),
    UNIQUE (type)
);

-- populate
INSERT INTO permission (type)
VALUES
    ('ADMIN'),
    ('LZ'),
    ('LZ-BRONZE'),
    ('LZ-SILVER'),
    ('LZ-BRONZE-SILVER'),
    ('BRONZE'),
    ('BRONZE-SILVER'),
    ('SILVER');
