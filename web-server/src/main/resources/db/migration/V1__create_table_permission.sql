CREATE TABLE permission (
id_permission INT PRIMARY KEY,
description VARCHAR(255) NOT NULL,
type VARCHAR(10) CHECK(type IN ('lz', 'bronze', 'silver'))
);