CREATE DATABASE Reislaufer;

CREATE TABLE Especie (
	IDespecie INT AUTO_INCREMENT,
	Nomeespecie VARCHAR(50) NOT NULL,
	Tipo VARCHAR(8) NOT NULL,
	Producao VARCHAR(13) NOT NULL,
	Origem VARCHAR(10) NOT NULL,
	PRIMARY KEY(IDespecie)
);

CREATE TABLE Plantio (
	IDplantio INT AUTO_INCREMENT,
	IDespecie INT NOT NULL,
	Quantidade INT NOT NULL,
	DataDePlantio DATE NOT NULL,
	PRIMARY KEY(IDplantio),
	FOREIGN KEY(IDespecie) REFERENCES especies(IDespecie)
);

CREATE TABLE Insumos (
	IDinsumo INT AUTO_INCREMENT,
	NomeDoInsumo VARCHAR(50) NOT NULL,
	TipoDeInsumo VARCHAR(50) NOT NULL,
	PRIMARY KEY(IDinsumo)
);

CREATE TABLE ApliacacaoDeInsumos (
	IDaplicacao INT AUTO_INCREMENT,
	IDinsumo INT NOT NULL,
	IDplantio INT NOT NULL,
	Quantidade INT NOT NULL,
	DataDeAplicacao DATE NOT NULL,
	PRIMARY KEY(IDaplicacao),
	FOREIGN KEY(IDinsumo) REFERENCES Insumos(IDinsumo),
	FOREIGN KEY(IDplantio) REFERENCES Plantio(IDplantio)
);

CREATE TABLE Colheita (
	IDcolheita INT AUTO_INCREMENT,
	IDplantio INT NOT NULL,
	Quantidade INT NOT NULL,
	Preco FLOAT NOT NULL,
	DataDeColheita DATE NOT NULL,
	PRIMARY KEY(IDaplicacao),
	FOREIGN KEY(IDplantio) REFERENCES Plantio(IDplantio)
);

CREATE TABLE Compras (
	IDcompra INT AUTO_INCREMENT,
	IDinsumo INT,
	IDespecie INT,
	Quantidadein INT,
	Quantidadep INT,
	DataDeCompra DATE NOT NULL,
	PRIMARY KEY(IDcompra),
	FOREIGN KEY(IDinsumo) REFERENCES Insumos(IDinsumo),
	FOREIGN KEY(IDespecie) REFERENCES especies(IDespecie)
);
