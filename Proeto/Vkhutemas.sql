drop schema vkhutemas;
create database vkhutemas;
use vkhutemas;

CREATE TABLE artista (
  id integer AUTO_INCREMENT primary key,
  nome varchar (511),
  assinatura varchar (511)
);

CREATE TABLE suporte (
  id integer AUTO_INCREMENT primary key,
  suporte varchar (255)
);

CREATE TABLE obra (
   id integer AUTO_INCREMENT primary key,
   id_artista integer,
   id_suporte integer,
   FOREIGN KEY (id_suporte) REFERENCES suporte(id),
   FOREIGN KEY (id_artista) REFERENCES artista(id),
   titulo varchar (511),
   url varchar (1023),
   ano integer
);