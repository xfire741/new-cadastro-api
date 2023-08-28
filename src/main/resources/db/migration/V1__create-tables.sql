CREATE TABLE person(
	id BIGINT  AUTO_INCREMENT NOT NULL,
	name VARCHAR(100) NOT NULL,
	cpf VARCHAR(11) NOT NULL UNIQUE,
	CONSTRAINT pk_person PRIMARY KEY (id)

) ENGINE=InnoDB default charset=utf8;

CREATE TABLE address(
	id BIGINT  AUTO_INCREMENT NOT NULL,
	cep VARCHAR(8) NOT NULL UNIQUE,
	logradouro VARCHAR(30) NOT NULL,
	bairro VARCHAR(80) NOT NULL,
	cidade VARCHAR(30) NOT NULL,
	uf VARCHAR(2) NOT NULL,
	complemento VARCHAR(100),
	person_id BIGINT NOT NULL,
	CONSTRAINT pk_address PRIMARY KEY (id)

)ENGINE=InnoDB default charset=utf8;

ALTER TABLE address ADD CONSTRAINT fk_address_person
FOREIGN KEY (person_id) REFERENCES person(id);

CREATE TABLE phone (
	id BIGINT AUTO_INCREMENT NOT NULL,
	number VARCHAR(11) NOT NULL UNIQUE,
	phone_type VARCHAR(15) NOT NULL,
	person_id BIGINT NOT NULL,
	CONSTRAINT pk_phone PRIMARY KEY (id)

)ENGINE=InnoDB default charset=utf8;

ALTER TABLE phone ADD CONSTRAINT fk_phone_person
FOREIGN KEY (person_id) REFERENCES person(id);

CREATE TABLE email(
	id BIGINT AUTO_INCREMENT NOT NULL,
	email VARCHAR(30) NOT NULL UNIQUE,
	person_id BIGINT NOT NULL,
	CONSTRAINT pk_email PRIMARY KEY (id)

)ENGINE=InnoDB default charset=utf8;

ALTER TABLE email ADD CONSTRAINT fk_email_person
FOREIGN KEY (person_id) REFERENCES person(id);
