create table usr_usuario (
  usr_id bigint generated always as identity,
  usr_nome varchar(20) not null,
  usr_senha varchar(150) not null,
  primary key (usr_id),
  unique (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint generated always as identity,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint not null,
  aut_id bigint not null,
  primary key (usr_id, aut_id),
  foreign key (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table ant_anotacao (
  ant_id bigint generated always as identity,
  ant_texto varchar(256) not null,
  ant_data_hora timestamp not null,
  ant_usr_id bigint not null,
  primary key (ant_id),
  foreign key (ant_usr_id) references usr_usuario(usr_id)
);

create table tra_trabalho (
  tra_id bigint generated always as identity,
  tra_titulo varchar(100) not null unique,
  tra_data_hora_entrega timestamp not null,
  tra_descricao varchar(200),
  tra_usr_id bigint not null,
  tra_nota int,
  primary key (tra_id),
  foreign key (tra_usr_id) references usr_usuario (usr_id) on delete restrict on update cascade
);

create table sec_secao (
  sec_id bigint generated always as identity,
  sec_titulo varchar(100) not null,
  sec_data_hora_criacao timestamp not null,
  sec_conteudo varchar(300),
  sec_tra_id bigint not null,
  primary key (sec_id),
  foreign key (sec_tra_id) references tra_trabalho (tra_id) on delete restrict on update cascade
);

insert into usr_usuario (usr_nome, usr_senha)
  values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C'),
         ('teste', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into aut_autorizacao (aut_nome)
  values ('ROLE_ADMIN'),
         ('ROLE_USER');
insert into uau_usuario_autorizacao (usr_id, aut_id) 
  values (1, 1),
         (2, 2);
insert into ant_anotacao(ant_texto, ant_data_hora, ant_usr_id)
  values ('Meu novo projeto', '2023-08-01 19:10', 1),
         ('Teste de anotação', '2025-08-01 19:10', 2);
insert into tra_trabalho (tra_titulo, tra_data_hora_entrega, tra_nota, tra_usr_id)
  values ('Teste 1', current_timestamp, 6, 1),
         ('Teste 2', '2023-08-03 20:30', null, 1);
insert into sec_secao (sec_titulo, sec_data_hora_criacao, sec_conteudo, sec_tra_id)
  values ('Capítulo 1', '2023-08-04 10:30', 'Meu nome é Post', 2),
         ('Capítulo 2', '2023-08-04 11:16', 'Trabalho com software', 2);

create user spring with password 'pass123';

-- Instruções SQL para a avaliações
-- Não esqueçam de executar o grant depois das instruções

create table com_comentario (
  com_id bigint generated always as identity,
  com_conteudo varchar(100) not null,
  com_data_hora timestamp not null,
  com_severidade int not null,
  com_tra_id bigint not null,
  primary key (com_id),
  foreign key (com_tra_id) references tra_trabalho (tra_id) on delete restrict on update cascade
);

insert into com_comentario (com_conteudo, com_data_hora, com_severidade, com_tra_id)
  values ('Falta conteúdo', '2025-10-20 11:00', 3, 2),
         ('Novo', current_timestamp, 1, 1);

create table rev_revisao (
  rev_id bigint generated always as identity,
  rev_feedback varchar(100) not null,
  rev_data_hora timestamp not null,
  rev_data_hora_correcao timestamp,
  rev_sec_id bigint not null,
  primary key (rev_id),
  foreign key (rev_sec_id) references sec_secao (sec_id) on delete restrict on update cascade
);


insert into rev_revisao (rev_feedback, rev_data_hora, rev_data_hora_correcao, rev_sec_id)
  values ('Somente título', '2025-10-20 11:00', '2025-10-27 23:05', 1),
         ('E o resto?', current_timestamp, null, 2);

create table chk_checklist (
  chk_id bigint generated always as identity,
  chk_descricao varchar(100) not null,
  chk_data_hora_finalizacao timestamp,
  chk_comentario_finalizacao varchar(100),
  chk_ant_id bigint not null,
  primary key (chk_id),
  foreign key (chk_ant_id) references ant_anotacao (ant_id) on delete restrict on update cascade
);

insert into chk_checklist (chk_descricao, chk_data_hora_finalizacao, chk_comentario_finalizacao, chk_ant_id)
  values ('Periféricos', '2023-08-13 11:20', 'Caro', 1),
         ('Montagem', null, null, 1);

create table fin_financeiro (
  fin_id bigint generated always as identity,
  fin_gasto varchar(150) not null,
  fin_valor numeric(10,2) not null,
  fin_desconto_porc int,
  fin_chk_id bigint not null,
  primary key (fin_id),
  foreign key (fin_chk_id) references chk_checklist (chk_id) on delete restrict on update cascade
);

insert into fin_financeiro (fin_gasto, fin_valor, fin_desconto_porc, fin_chk_id)
  values ('Motherboard', 999.9, null, 1),
         ('Processador', 1500.0, 10, 1);

grant update, delete, insert, select on all tables in schema public to spring;