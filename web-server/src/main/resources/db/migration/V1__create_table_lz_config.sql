create table lz_config(
id_arquivo integer auto_increment primary key,
nome_config varchar(50) not null,
tipo_arq varchar(50) not null,
origem_arq varchar(50) not null,
periodo ENUM('DIA','SEMANA','MES','ANO') not null,
frequencia int not null,
header boolean not null
);