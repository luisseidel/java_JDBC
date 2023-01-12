create table usuarios(
	id BIGSERIAL primary key unique not null,
	nome varchar(200) null,
	email varchar(100) null
);

insert into usuarios (id, nome, email) values (
	(select coalesce(max(id)+1, '1') from usuarios), 'luis', 'luis.seidel@celk.net'
);

create table telefones_usuarios(
	id SERIAL primary key not null,
	numero varchar(14) not null,
	tipo varchar(1) not null,
	usuario integer references usuarios(id) not null
);

insert into telefones_usuarios (numero, tipo, usuario) values (
	5551998882898, 2, 1
);

insert into telefones_usuarios (numero, tipo, usuario) values (
	555137487288, 1, 1
);