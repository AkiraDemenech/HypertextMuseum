create database unovis;

use unovis;

CREATE TABLE obra (
  id integer AUTO_INCREMENT primary key,
  titulo varchar (511),
  url varchar (1023),
  ano integer
);

CREATE TABLE artista (
  id integer AUTO_INCREMENT primary key,
  nome varchar (511),
  assinatura varchar (511)
);

CREATE TABLE suporte (
  id integer AUTO_INCREMENT primary key,
  suporte varchar (255)
);

CREATE TABLE linguagem (
  id integer AUTO_INCREMENT primary key,
  id_suporte integer,
  FOREIGN KEY (id_suporte) REFERENCES suporte(id),
  id_obra integer,
  FOREIGN KEY (id_obra) REFERENCES obra(id)
);

CREATE TABLE autoria (
  id integer AUTO_INCREMENT primary key,
  id_artista integer,
  FOREIGN KEY (id_artista) REFERENCES artista(id),
  id_obra integer,
  FOREIGN KEY (id_obra) REFERENCES obra(id)
);