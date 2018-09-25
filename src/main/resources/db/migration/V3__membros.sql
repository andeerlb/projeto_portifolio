CREATE TABLE membros( 
	idprojeto bigserial NOT NULL,
	idpessoa bigint NOT NULL,
	CONSTRAINT pk_membros_projeto PRIMARY KEY (idprojeto),
	CONSTRAINT fk_membros_pessoa FOREIGN KEY (idpessoa)
	REFERENCES projeto (id) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_pessoa FOREIGN KEY (idpessoa)
	REFERENCES pessoa (id) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
);