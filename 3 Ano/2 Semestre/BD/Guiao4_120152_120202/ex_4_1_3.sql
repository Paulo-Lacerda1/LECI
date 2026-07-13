CREATE SCHEMA Stocks;
GO

CREATE TABLE Stocks.Produto (
	ID			INT	UNIQUE		NOT NULL,
	Nome		VARCHAR(15)		NOT NULL,
	IVA			INT,
	Preco		INT,
	PRIMARY KEY (ID)
);

CREATE TABLE Stocks.MetodoPagamento (
	ID			INT				NOT NULL,
	Designacao	VARCHAR(40),
	PRIMARY KEY (ID)
);

CREATE TABLE Stocks.TipoFornecedor (
	ID			INT				NOT NULL,
	Designacao	VARCHAR(40)		NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE Stocks.Fornecedor (
	NIF			INT				NOT NULL,
	Nome		VARCHAR(15),
	Endereco	VARCHAR(60),
	FAX			INT,
	ID_TF		INT,
	ID_MP		INT,
	PRIMARY KEY (NIF),
	FOREIGN KEY (ID_TF) REFERENCES Stocks.TipoFornecedor(ID),
	FOREIGN KEY (ID_MP) REFERENCES Stocks.MetodoPagamento(ID)
);

CREATE TABLE Stocks.Encomenda (
	Num			INT		UNIQUE		NOT NULL,
	_Data		DATE,
	NIF_F		INT,
	PRIMARY KEY (Num),
	FOREIGN KEY (NIF_F) REFERENCES Stocks.Fornecedor(NIF)
);

CREATE TABLE Stocks.Contem(
	Num_Encomenda		INT				NOT NULL,
	ID_Produto			INT				NOT NULL,
	Stock				INT,
	PRIMARY KEY (Num_Encomenda, ID_Produto),
	FOREIGN KEY (Num_Encomenda) REFERENCES Stocks.Encomenda(Num),
	FOREIGN KEY (ID_Produto)	REFERENCES Stocks.Produto(ID)
);