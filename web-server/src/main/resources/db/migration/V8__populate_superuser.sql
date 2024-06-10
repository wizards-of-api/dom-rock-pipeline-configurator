INSERT INTO company (cnpj, fantasy_name)
VALUES ('12345678901234', 'Wiz');

INSERT INTO user (email, name, password, permission_id, company_cnpj, created_at, updated_at)
VALUES ('superuser@wiz.com', 'super', 'super', 1, '12345678901234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);