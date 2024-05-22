INSERT INTO user (email, name, password, is_super)
VALUES ('superuser@wiz.com', 'super', 'super', true);


INSERT INTO company (cnpj, fantasy_name)
VALUES ('12345678901234', 'Wiz');


INSERT INTO permission (type) VALUES ('lz');
INSERT INTO permission (type) VALUES ('bronze');
INSERT INTO permission (type) VALUES ('silver');


INSERT INTO user_company (user_email, company_cnpj)
VALUES ('superuser@wiz.com', '12345678901234');


INSERT INTO user_permission (user_email, permission_id)
SELECT 'superuser@wiz.com', id_permission FROM permission;