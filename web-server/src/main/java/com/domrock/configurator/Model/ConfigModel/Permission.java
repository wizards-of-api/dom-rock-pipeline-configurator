package com.domrock.configurator.Model.ConfigModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "permission", schema = "domrock")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission", nullable = false)
    private Integer id;

    @Column(name = "type", length = 10)
    private String type;

}