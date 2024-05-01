package com.domrock.configurator.Model.ConfigModel;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import com.domrock.configurator.Views;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.MetadataConfigDTO;
import com.fasterxml.jackson.annotation.JsonView;

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
    @JsonView(Views.LZ.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;
    @NotBlank
    @JsonView(Views.LZ.class)
    @Column(name = "file_config_name")
    private String name;
    @NotBlank
    @JsonView(Views.LZ.class)
    @Column(name = "file_type")
    private String fileType;
    @NotBlank
    @JsonView(Views.LZ.class)
    @Column(name = "file_origin")
    private String fileOrigin;
    @NotNull
    @JsonView(Views.LZ.class)
    @Column(name = "file_frequency")
    private Integer frequency;
    @JsonView(Views.LZ.class)
    @Column(name = "file_has_header")
    private Integer hasHeader;
    @NotBlank
    @JsonView(Views.LZ.class)
    @Column(name = "file_period")
    private String filePeriod;
    @NotBlank
    @JsonView(Views.LZ.class)
    @Column(name = "file_name")
    private String fileName;
    @JsonView(Views.LZ.class)
    @OneToMany(mappedBy = "fileId", cascade = CascadeType.ALL)
    private List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

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
}
