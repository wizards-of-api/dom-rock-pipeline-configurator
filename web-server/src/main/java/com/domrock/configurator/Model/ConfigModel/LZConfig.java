package com.domrock.configurator.Model.ConfigModel;

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
    @Column(name = "id_arquivo")
    private Integer archiveId;

    @Column(name = "nome_config")
    private String name;

    @Column(name = "tipo_arq")
    private String archiveType;

    @Column(name = "origem_arq")
    private String archiveOrigin;

    @Column(name="frequencia")
    private Integer frequency;

    private Integer header;
    
}
