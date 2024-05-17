CREATE TABLE user_company
(
    user_email   VARCHAR(255),
    company_cnpj VARCHAR(14),
    PRIMARY KEY (user_email, company_cnpj),
    CONSTRAINT fk_user_email_join_company FOREIGN KEY (user_email) REFERENCES user (email),
    CONSTRAINT fk_company_cnpj FOREIGN KEY (company_cnpj) REFERENCES company (cnpj)
);