package com.domrock.configurator.Model.ConfigModel;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.ColumnConfigDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "coluna")
public class ColumnConfig {

    @ManyToOne
    @Column(name = "id_arquivo")
    private LZConfig archiveId;
    
    @Id
    @Column(name = "id_coluna")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer columnId;

    private Integer index;

    @Column(name = "nome_coluna")
    private String columnName;

    @Column(name = "tipo")
    private String type;

    @Column(name = "vazio")
    private Integer empty;

    @Column(name = "descricao")
    private String description;

    @Column(name = "ativo")
    private Integer status;

    public ColumnConfig (ColumnConfigDTO data){       
        this.index = data.index();
        this.columnName = data.name();
        this.type = data.type();
        this.empty = data.CanBeNull();
        this.description = data.description();
        this.status = data.status();
    }


}
