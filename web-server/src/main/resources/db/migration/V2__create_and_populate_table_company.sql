CREATE TABLE company
(
    cnpj         VARCHAR(14) PRIMARY KEY,
    fantasy_name VARCHAR(255) NOT NULL
);

INSERT INTO company (cnpj, fantasy_name)
VALUES ('12345678901234', 'Wiz');