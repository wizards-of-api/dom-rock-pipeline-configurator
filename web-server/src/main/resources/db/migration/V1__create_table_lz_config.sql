create table lz_config(
id_file integer auto_increment primary key,
file_config_name varchar(50) not null,
file_type varchar(50) not null,
file_origin varchar(50) not null,
file_period varchar(50) not null,
file_frequency int not null,
file_has_header int not null
);