package com.domrock.configurator.Model.ConfigModel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserCompanyId implements Serializable {
    private static final long serialVersionUID = 5356338148783792514L;
    @Size(max = 255)
    @NotNull
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Size(max = 14)
    @NotNull
    @Column(name = "company_cnpj", nullable = false, length = 14)
    private String companyCnpj;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserCompanyId entity = (UserCompanyId) o;
        return Objects.equals(this.companyCnpj, entity.companyCnpj) &&
                Objects.equals(this.userEmail, entity.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCnpj, userEmail);
    }

}