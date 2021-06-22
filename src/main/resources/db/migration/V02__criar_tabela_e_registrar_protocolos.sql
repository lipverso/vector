CREATE TABLE IF NOT EXISTS protocolo (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data DATETIME NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    id_documento BIGINT(20) NOT NULL,
    FOREIGN KEY (id_documento) REFERENCES documento(id)
);

INSERT INTO protocolo(data, descricao, id_documento)
VALUES ('2021-01-04 12:25:21', ' PROTOCOLO DE OFÍCIO CIRCULAR Nº 0012/2020', 1);