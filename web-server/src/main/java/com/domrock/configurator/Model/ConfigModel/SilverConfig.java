package com.domrock.configurator.Model.ConfigModel;

import com.domrock.configurator.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "silver")
public class SilverConfig {

    @JsonIgnore
    @ManyToOne
    @JsonView(Views.LZ.class)
    @JoinColumn(name = "column_id")
    private ColumnConfig columnId;

    @Id
    @JsonView(Views.Silver.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_silver")
    private Integer silverId;

    @JsonView(Views.Silver.class)
    @Column(name = "column_from")
    private String from;
    
    @JsonView(Views.Silver.class)
    @Column(name = "column_to")
    private String to;
}
