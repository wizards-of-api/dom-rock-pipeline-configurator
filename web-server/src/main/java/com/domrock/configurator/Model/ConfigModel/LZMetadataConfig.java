package com.domrock.configurator.Model.ConfigModel;

import java.util.ArrayList;
import java.util.List;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.MetadataConfigDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "lz_config")
public class LZMetadataConfig {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    @OneToMany(mappedBy = "fileId")
    private List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

    @Column(name = "file_config_name")
    private String name;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_origin")
    private String fileOrigin;

    @Column(name = "file_frequency")
    private Integer frequency;

    @Column(name = "file_has_header")
    private Integer hasHeader;

    @Column(name = "file_period")
    private String filePeriod;

    @Column(name = "file_name")
    private String fileName;

    public LZMetadataConfig(MetadataConfigDTO file){
        this.fileName = file.fileName();
        this.name = file.name();
        this.fileType = file.fileExtension();
        this.fileOrigin = file.fileOrigin();
        this.frequency = file.frequencyNumber();
        this.filePeriod = file.frequencyType();
        this.hasHeader = file.hasHeader() ? 1: 0;
    }
    public LZMetadataConfig(){}

    public void updateFields(MetadataConfigDTO data){
        if (data.name() != null) name = data.name();
        if (data.fileName() != null) fileName = data.fileName();
        if (data.frequencyNumber() != null) frequency = data.frequencyNumber();
        if (data.frequencyType() != null) frequency = Integer.valueOf(data.frequencyType());
        hasHeader = data.hasHeader() ? 1: 0;
    }
}
