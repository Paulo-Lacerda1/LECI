CREATE SCHEMA Conferencias;
GO

CREATE TABLE Conferencias.Conferencia (
	ID			INT				NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE Conferencias.ArtigoCientifico (
	NReg		INT	UNIQUE		NOT NULL,
	Titulo		VARCHAR(40),
	ID_C		INT,
	PRIMARY KEY (NReg),
	FOREIGN KEY (ID_C)		REFERENCES Conferencias.Conferencia(ID)
);

CREATE TABLE Conferencias.Instituicao (
	Nome		VARCHAR(15)		NOT NULL,
	Endereco	VARCHAR(60),
	PRIMARY KEY (Nome)
);

CREATE TABLE Conferencias.Autor (
	Email				VARCHAR(15)		NOT NULL,
	Nome				VARCHAR(15),
	NomeInstituicao		VARCHAR(15)		NOT NULL,
	PRIMARY KEY (Email),
	FOREIGN KEY (NomeInstituicao) REFERENCES Conferencias.Instituicao (Nome)
);

CREATE TABLE Conferencias.Participantes (
	Email				VARCHAR(15)		NOT NULL,
	Nome				VARCHAR(15),
	Morada				VARCHAR(60),
	DataInscricao		Date,
	ID_C				INT				NOT NULL,
	NomeInstituicao		VARCHAR(15)		NOT NULL,
	PRIMARY KEY (Email),
	FOREIGN KEY (ID_C)				REFERENCES	Conferencias.Conferencia(ID),
	FOREIGN KEY (NomeInstituicao)	REFERENCES Conferencias.Instituicao (Nome)
);

CREATE TABLE Conferencias.Estudante (
	Email			VARCHAR(15)			NOT NULL,
	Comprovativo	VARCHAR(20),
	PRIMARY KEY (Email),
	FOREIGN KEY (Email) REFERENCES Conferencias.Participantes (Email)
);

CREATE TABLE Conferencias.NaoEstudante (
	Email			VARCHAR(15)			NOT NULL,
	Comprovativo	VARCHAR(20),
	PRIMARY KEY (Email),
	FOREIGN KEY (Email) REFERENCES Conferencias.Participantes (Email)
);