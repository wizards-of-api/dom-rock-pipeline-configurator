package com.domrock.configurator.Model.ConfigModel;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.ColumnConfigDTO;

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

    @ManyToOne
    @JoinColumn(name = "file_id")
    private LZConfig fileId;
    
    @Id
    @Column(name = "column_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer columnId;

    private Integer index;

    @Column(name = "column_name")
    private String columnName;

    @Column(name = "column_type")
    private String type;

    @Column(name = "column_empty")
    private Integer empty;

    @Column(name = "column_description")
    private String description;

    @Column(name = "column_active")
    private Integer status;

    public ColumnConfig (ColumnConfigDTO data){       
        this.index = data.index();
        this.columnName = data.name();
        this.type = data.type();
        this.empty = data.CanBeNull() == true? 1: 0;
        this.description = data.description();
        this.status = data.status();
    }


}
