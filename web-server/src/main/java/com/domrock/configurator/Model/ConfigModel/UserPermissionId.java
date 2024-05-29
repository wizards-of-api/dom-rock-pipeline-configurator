package com.domrock.configurator.Model.ConfigModel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class UserPermissionId implements Serializable {
    private static final long serialVersionUID = 4750891206293258515L;
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "permission_id", nullable = false)
    private Integer permissionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserPermissionId entity = (UserPermissionId) o;
        return Objects.equals(this.permissionId, entity.permissionId) &&
                Objects.equals(this.userEmail, entity.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, userEmail);
    }

}