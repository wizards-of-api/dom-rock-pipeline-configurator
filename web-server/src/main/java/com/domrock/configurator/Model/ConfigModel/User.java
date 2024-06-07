package com.domrock.configurator.Model.ConfigModel;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "domrock")
public class User {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_super", nullable = false, columnDefinition = "0")
    private Byte isSuper;

}

