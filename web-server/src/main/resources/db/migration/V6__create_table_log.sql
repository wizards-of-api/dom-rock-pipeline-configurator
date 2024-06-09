CREATE TABLE log
(
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    log_user_email VARCHAR(255),
    log_company_cnpj VARCHAR(14),
    log_datetime DATETIME,
    log_action VARCHAR(255)
)