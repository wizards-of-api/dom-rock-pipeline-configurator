package com.domrock.configurator.Model.ConfigModel;

import com.domrock.configurator.Views;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.ColumnConfigDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "file_column")
public class ColumnConfig {

    @JsonIgnore
    @ManyToOne
    @JsonView(Views.LZ.class)
    @JoinColumn(name = "file_id")
    private LZMetadataConfig fileId;
    
    @Id
    @JsonView(Views.LZ.class)
    @Column(name = "column_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer columnId;

    @JsonView(Views.LZ.class)
    @Column(name = "column_name")
    private String columnName;

    @JsonView(Views.LZ.class)
    @Column(name = "column_number")
    private Integer columnNumber;

    @JsonView(Views.LZ.class)
    @Column(name = "column_type")
    private String type;

    @JsonView(Views.LZ.class)
    @Column(name = "column_empty")
    private Integer empty;

    @JsonView(Views.LZ.class)
    @Column(name = "column_description")
    private String description;

    @JsonView(Views.LZ.class)
    @Column(name = "column_active")
    private Integer status;

    // Configurações Bronze

    @JsonView(Views.Bronze.class)
    @Column(name = "column_is_hash")
    private Integer hash;

    @JsonView(Views.Bronze.class)
    @Column(name = "column_valid")
    private Integer valid;


    public ColumnConfig (LZMetadataConfig lzMetadataConfig, ColumnConfigDTO data){   
        this.fileId = lzMetadataConfig;    
        this.columnNumber = data.index();
        this.columnName = data.name();
        this.type = data.type();
        this.empty = data.CanBeNull() ? 1 : 0;
        this.description = data.description();
        this.status = data.status();
        this.hash = data.hash() ? 1 : 0;
        this.valid = data.valid() ? 1 : 0;
        
        lzMetadataConfig.getColumns().add(this);
    }

    public ColumnConfig() {}

}
