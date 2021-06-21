CREATE TABLE IF NOT EXISTS documento (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data DATETIME NOT NULL,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    ano INT(10) NOT NULL,
    resumo VARCHAR(150) NOT NULL,
    solicitante VARCHAR(30) NOT NULL
);

INSERT INTO documento(data, codigo, ano, resumo, solicitante)
VALUES ('2020-08-10 09:15:44', '005', 2020, 'OFÍCIO CIRCULAR Nº 0012/2020 Encaminhamento continuação Kit – de Trabalho', 'Marcos Pinheiro');