create table coluna(
id_arquivo integer not null,
id_coluna integer primary key,
nome_coluna varchar(50) not null,
tipo varchar(50) not null,
vazio boolean not null,
descricao varchar(225) not null,
ativo boolean not null,
FOREIGN key (id_arquivo) references lz_config(id_arquivo)
);