CREATE TABLE user
(
    name          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) PRIMARY KEY,
    password      VARCHAR(255) NOT NULL,
    permission_id INT,
    company_cnpj  VARCHAR(14) REFERENCES company(cnpj),
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,

    CONSTRAINT fk_permission_id FOREIGN KEY (permission_id) REFERENCES permission (id_permission),
    CONSTRAINT fk_company_cnpj FOREIGN KEY (company_cnpj) REFERENCES company (cnpj) ON DELETE cascade
);