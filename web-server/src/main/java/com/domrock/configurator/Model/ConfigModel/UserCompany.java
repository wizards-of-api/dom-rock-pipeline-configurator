package com.domrock.configurator.Model.ConfigModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_company", schema = "domrock")
public class UserCompany {
    @EmbeddedId
    private UserCompanyId id;

    @MapsId("userEmail")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_email", nullable = false)
    private User userEmail;

    @MapsId("companyCnpj")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_cnpj", nullable = false)
    private Company companyCnpj;

}