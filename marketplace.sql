create table usuario(
usuarioId int primary key AUTO_INCREMENT,
nombreCompleto varchar(250) not null,
username  varchar(30) not null UNIQUE,
email varchar(250) not null,
password varchar(500) NOT NULL
);

create table file(
 fileId int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  fileName varchar(250) NOT NULL,
  path varchar(500) NOT NULL,
  temporal int(1) NOT NULL DEFAULT 1
);

create table anuncio(
anuncioId int primary key AUTO_INCREMENT,
usuarioId int,
titulo varchar(250) not null,
categoria varchar(20) not null,
descripcion varchar(350) not null,
telefono int not null,
precio int not null,
estado int not null,
imagenFileId int DEFAULT NULL,
FOREIGN KEY (imagenFileId) REFERENCES file(fileId),
FOREIGN KEY (usuarioId) REFERENCES usuario(usuarioId)
);