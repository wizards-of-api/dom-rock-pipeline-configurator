CREATE TABLE permission (
id_permission INT PRIMARY KEY,
type VARCHAR(10) CHECK(type IN ('lz', 'bronze', 'silver')),
UNIQUE (type)
);