--CREATE DATABASE RentACarDB;
--GO
--USE RentACarDB;
--GO
-- Apenas Local

CREATE SCHEMA RentACar;
GO

CREATE TABLE RentACar.Cliente (
	NIF				INT				NOT NULL,
	Nome			VARCHAR(10)		NOT NULL,
	Endereco		VARCHAR(60),
	NumCarta		INT				NOT NULL,
	PRIMARY KEY (NIF)
);

CREATE TABLE RentACar.Balcao (
	BalcaoID		INT				NOT NULL,
	Endereco		VARCHAR(60),
	Nome			VARCHAR(15),
	PRIMARY KEY (BalcaoID)
);

CREATE TABLE RentACar.TipoVeiculo(
	Codigo			INT				NOT NULL,
	AC				BIT,
	Designacao		VARCHAR(10),
	PRIMARY KEY (Codigo)
);

CREATE TABLE RentACar.Veiculo (
	Matricula			VARCHAR(8)		UNIQUE	NOT NULL,
	Marca				VARCHAR(15),
	CategVeiculo		INT,
	PRIMARY KEY (Matricula),
	FOREIGN KEY (CategVeiculo) REFERENCES RentACar.TipoVeiculo(Codigo)
);

CREATE TABLE RentACar.Aluguer (
	AluguerID					INT				NOT NULL,
	Duracao						TIME,
	DataAluguer					DATE,
	Nome						VARCHAR(10),
	NIF							INT,
	Balcao						INT,
	Veiculo						VARCHAR(8),
	PRIMARY KEY (AluguerID),
	FOREIGN KEY (NIF)			REFERENCES RentACar.Cliente(NIF),
	FOREIGN KEY (Balcao)		REFERENCES RentACar.Balcao(BalcaoID),
	FOREIGN KEY (Veiculo)		REFERENCES RentACar.Veiculo(Matricula)
);

CREATE TABLE RentACar.Similaridade (
	CodigoVeiculo				INT				NOT NULL,
	Similar						INT				NOT NULL,
	PRIMARY KEY	(CodigoVeiculo, Similar),
	FOREIGN KEY (CodigoVeiculo) REFERENCES RentACar.TipoVeiculo(Codigo),
	FOREIGN KEY (Similar)		REFERENCES RentACar.TipoVeiculo(Codigo)
);

CREATE TABLE RentACar.Ligeiro (
	CategLigeiro				INT				NOT NULL,
	NumLugares					INT,
	Portas						INT,
	Combustivel					VARCHAR(5),
	PRIMARY KEY (CategLigeiro),
	FOREIGN KEY (CategLigeiro)	REFERENCES RentACar.TipoVeiculo(Codigo),
);

CREATE TABLE RentACar.Pesado (
	CategPesado					INT				NOT NULL,
	Peso						INT,
	Passageiros					INT,
	PRIMARY KEY (CategPesado),
	FOREIGN KEY (CategPesado)	REFERENCES RentACar.TipoVeiculo(Codigo)
);