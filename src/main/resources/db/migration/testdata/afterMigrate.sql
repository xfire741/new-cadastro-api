set foreign_key_checks = 0;

delete from address;
delete from email;
delete from person;
delete from phone;
delete from users;

set foreign_key_checks = 1;

alter table address auto_increment = 1;
alter table email auto_increment = 1;
alter table person auto_increment = 1;
alter table phone auto_increment = 1;
alter table users auto_increment = 1;

INSERT into person(id, name, cpf) values(1, "Eduardo", "58397387005");
INSERT into person(id, name, cpf) values(2, "Maria", "72746180081");

insert into address(id, cep, logradouro, bairro, cidade, uf, complemento, person_id) values(1, "16900538", "Rua B", "Loteamento Residencial Nova Esperança","Andradina", "SP", "Ao lado do mercado", 1);

INSERT into phone(id, number, phone_type, person_id) values (1, "61984542321", "CELULAR",  1);
INSERT into phone(id, number, phone_type, person_id) values (2, "6133355186", "RESIDENCIAL",  1);

INSERT INTO email(id, email, person_id) values (1, "eduardo@gmail.com", 1);
INSERT INTO email(id, email, person_id) values (2, "eduardovictor@gmail.com", 1);

INSERT INTO email(id, email, person_id) values (3, "maria@gmail.com", 2);
