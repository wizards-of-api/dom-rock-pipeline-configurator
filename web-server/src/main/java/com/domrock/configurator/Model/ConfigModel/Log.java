package com.domrock.configurator.Model.ConfigModel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "log")
public class Log {

    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "log_user_email")
    private String userEmail;

    @Column(name = "log_company_cnpj", length = 14)
    private String companyCnpj;

    @Column(name = "log_datetime")
    private LocalDateTime logDateTime;

    @Column(name = "log_action")
    private String action;
}
