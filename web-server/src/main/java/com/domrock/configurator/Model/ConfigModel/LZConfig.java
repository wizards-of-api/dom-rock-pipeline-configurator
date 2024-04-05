package com.domrock.configurator.Model.ConfigModel;


import com.domrock.configurator.Model.ConfigModel.DTOConfig.MetadataConfigDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "lz_config")
public class LZConfig {
    
    @Id
    @Column(name = "id_file")
    private Integer fileId;

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

    public LZConfig(MetadataConfigDTO file){
        this.name = file.name();
        this.fileType = file.fileExtension();
        this.fileOrigin = file.fileOrigin();
        this.frequency = file.frequencyNumber();
        this.filePeriod = file.frequencyType();
        this.hasHeader = file.hasHeader() == true? 1: 0;
    }

}
