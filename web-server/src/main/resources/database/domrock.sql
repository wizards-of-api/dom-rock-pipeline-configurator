create database domrockbd;

use domrockBD;
create table lz_config(
id_arquivo integer auto_increment primary key,
nome_config varchar(50) not null,
tipo_arq ENUM('csv', 'excel') not null,
origem_arq varchar(50) not null,
periodo ENUM('dia','semana','mês','ano') not null,
frequencia int not null,
header boolean not null
);

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



