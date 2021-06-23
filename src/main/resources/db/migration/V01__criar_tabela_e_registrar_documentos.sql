CREATE TABLE IF NOT EXISTS documento (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data VARCHAR(50) NOT NULL,
    ano INT,
    resumo VARCHAR(150) NOT NULL,
    solicitante VARCHAR(30) NOT NULL
);

INSERT INTO documento(data, ano, resumo,solicitante)
VALUES ('2020-08-10 09:15:44', 2020, 'OFÍCIO CIRCULAR Nº 0012/2020 Encaminhamento continuação Kit – de Trabalho', 'Marcos Pinheiro');