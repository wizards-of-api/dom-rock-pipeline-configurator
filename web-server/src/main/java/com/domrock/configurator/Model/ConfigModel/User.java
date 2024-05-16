package com.domrock.configurator.Model.ConfigModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_super", nullable = false)
    private boolean isSuper;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_permission",
            joinColumns = @JoinColumn(name = "user_email"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_company",
            joinColumns = @JoinColumn(name = "user_email"),
            inverseJoinColumns = @JoinColumn(name = "company_cnpj")
    )
    private Set<Company> companies;
}
