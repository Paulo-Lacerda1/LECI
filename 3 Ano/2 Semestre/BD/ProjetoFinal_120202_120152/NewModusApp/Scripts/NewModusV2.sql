--CREATE DATABASE NewModus;
--GO
--USE NewModus;
--GO

--CREATE SCHEMA NM;
--GO

CREATE TABLE NM.Fornecedor (
	id_fornecedor		INT				NOT NULL,
	nome				VARCHAR(40)		NOT NULL,
	telefone			VARCHAR(20)		NOT NULL,
	email				VARCHAR(255)	NOT NULL,
	morada				VARCHAR(50)		NOT NULL,
	
	CONSTRAINT PK_Fornecedor PRIMARY KEY (id_fornecedor)
);

CREATE TABLE NM.Tecido (
	id_tecido			INT				NOT NULL,
	nome				VARCHAR(30)		NOT NULL,
	preco_metro			DECIMAL(6,2)	NOT NULL,
	quantidade_stock	INT,
	codigo				INT				NOT NULL,
	cor					VARCHAR(10),
	tipo				VARCHAR(15),
	padrao				VARCHAR(15),
	fornecedor			INT				NOT NULL,
	
	CONSTRAINT PK_Tecido PRIMARY KEY (id_tecido),
	CONSTRAINT UQ_Tecido_Codigo UNIQUE (codigo),
	CONSTRAINT FK_Tecido_Fornecedor
		FOREIGN KEY (fornecedor) REFERENCES NM.Fornecedor(id_fornecedor)
);

CREATE TABLE NM.Material (
	id_material			INT				NOT NULL,
	nome				VARCHAR(30)		NOT NULL,
	custo_unitario		DECIMAL(6,2)	NOT NULL,
	quantidade_stock	INT,
	unidade_medida		VARCHAR(15)		NOT NULL,
	tipo				VARCHAR(15),
	fornecedor			INT				NOT NULL,
	
	CONSTRAINT PK_Material PRIMARY KEY (id_material),
	CONSTRAINT FK_Material_Fornecedor
		FOREIGN KEY (fornecedor) REFERENCES NM.Fornecedor(id_fornecedor)
);

CREATE TABLE NM.Modelo (
	id_modelo			INT				NOT NULL,
	nome_modelo			VARCHAR(40)		NOT NULL,
	tipo_peca			VARCHAR(15)		NOT NULL,
	descricao			VARCHAR(100),
	origem_modelo		VARCHAR(30),
	
	CONSTRAINT PK_Modelo PRIMARY KEY (id_modelo)
);

CREATE TABLE NM.Cliente (
	id_cliente			INT				NOT NULL,
	nome				VARCHAR(40)		NOT NULL,
	telefone			VARCHAR(20)		NOT NULL,
	email				VARCHAR(255),

	CONSTRAINT PK_Cliente PRIMARY KEY (id_cliente)
);

CREATE TABLE NM.Perfil_Medida (
	id_perfil			INT				NOT NULL,
	nome_perfil			VARCHAR(40)		NOT NULL,
	braco				INT				NOT NULL,
	costas				INT				NOT NULL,
	peito				INT				NOT NULL,
	cinta				INT				NOT NULL,
	anca				INT				NOT NULL,
	data_atualizacao	DATE			NOT NULL,
	cliente				INT				NOT NULL,

	CONSTRAINT PK_Perfil_Medida PRIMARY KEY (id_perfil),
	CONSTRAINT FK_Perfil_Cliente
		FOREIGN KEY (cliente) REFERENCES NM.Cliente(id_cliente)
);

CREATE TABLE NM.Encomenda (
	id_encomenda			INT				NOT NULL,
	data_encomenda			DATE			NOT NULL,
	data_prevista_entrega	DATE,
	estado					VARCHAR(15)		NOT NULL,
	data_pronto				DATE,
	data_real_entrega		DATE,
	valor_total				DECIMAL(10,2)	NOT NULL,
	cliente					INT				NOT NULL,

	CONSTRAINT PK_Encomenda PRIMARY KEY (id_encomenda),
	CONSTRAINT CHK_Encomenda_Estado
		CHECK (estado IN ('Pendente', 'Em Produção', 'Pronta', 'Entregue', 'Cancelada')),
	CONSTRAINT FK_Encomenda_Cliente
		FOREIGN KEY (cliente) REFERENCES NM.Cliente(id_cliente)
);

CREATE TABLE NM.Item_Encomenda (
	id_item_encomenda			INT				NOT NULL,
	tamanho						INT				NOT NULL,
	preco						DECIMAL(6,2)	NOT NULL,
	tipo_peca					VARCHAR(15)		NOT NULL,
	custo_producao				DECIMAL(10,2),
	descricao_personalizacao	VARCHAR(50),
	perfil_medida				INT				NOT NULL,
	modelo						INT				NULL,
	encomenda					INT				NOT NULL,
	
	CONSTRAINT PK_Item_Encomenda PRIMARY KEY (id_item_encomenda),
	CONSTRAINT FK_Item_Perfil_Medida
		FOREIGN KEY (perfil_medida) REFERENCES NM.Perfil_Medida(id_perfil),
	CONSTRAINT FK_Item_Modelo
		FOREIGN KEY (modelo) REFERENCES NM.Modelo(id_modelo),
	CONSTRAINT FK_Item_Encomenda
		FOREIGN KEY (encomenda) REFERENCES NM.Encomenda(id_encomenda)
);

CREATE TABLE NM.Pagamento_Encomenda (
	id_pagamento_encomenda	INT				NOT NULL,
	data_pagamento			DATE			NOT NULL,
	valor					DECIMAL(10,2)	NOT NULL,
	metodo_pagamento		VARCHAR(20)		NOT NULL,
	morada					VARCHAR(50),
	encomenda				INT				NOT NULL,

	CONSTRAINT PK_Pagamento_Encomenda PRIMARY KEY (id_pagamento_encomenda),
	CONSTRAINT FK_Pagamento_Encomenda
		FOREIGN KEY (encomenda) REFERENCES NM.Encomenda(id_encomenda)
);

CREATE TABLE NM.ItemEnc_Tecido (
	tecido				INT				NOT NULL,
	item_encomenda		INT				NOT NULL,
	metros_usados		DECIMAL(6,2),
	
	CONSTRAINT PK_ItemEnc_Tecido PRIMARY KEY (tecido, item_encomenda),
	CONSTRAINT FK_ItemEncTecido_Tecido
		FOREIGN KEY (tecido) REFERENCES NM.Tecido(id_tecido),
	CONSTRAINT FK_ItemEncTecido_Item
		FOREIGN KEY (item_encomenda) REFERENCES NM.Item_Encomenda(id_item_encomenda)
);

CREATE TABLE NM.ItemEnc_Material (
	material			INT				NOT NULL,
	item_encomenda		INT				NOT NULL,
	quantidade_usada	INT,
	
	CONSTRAINT PK_ItemEnc_Material PRIMARY KEY (material, item_encomenda),
	CONSTRAINT FK_ItemEncMat_Material
		FOREIGN KEY (material) REFERENCES NM.Material(id_material),
	CONSTRAINT FK_ItemEncMat_Item
		FOREIGN KEY (item_encomenda) REFERENCES NM.Item_Encomenda(id_item_encomenda)
);

CREATE TABLE NM.Compra (
	id_compra			INT				NOT NULL,
	data_compra			DATE			NOT NULL,
	valor_total			DECIMAL(10,2)	NOT NULL,
	metodo_pagamento	VARCHAR(20)		NOT NULL,
	cliente				INT				NULL,

	CONSTRAINT PK_Compra PRIMARY KEY (id_compra),
	CONSTRAINT FK_Compra_Cliente
		FOREIGN KEY (cliente) REFERENCES NM.Cliente(id_cliente)
);

CREATE TABLE NM.Categoria_Produto_Pronto (
	id_categoria_produto	INT			NOT NULL,
	nome_categoria			VARCHAR(20)	NOT NULL,

	CONSTRAINT PK_Categoria_Produto PRIMARY KEY (id_categoria_produto)
);

CREATE TABLE NM.Produto_Pronto (
	id_produto_pronto		INT				NOT NULL,
	codigo					INT				NOT NULL,
	nome					VARCHAR(30)		NOT NULL,
	tamanho					VARCHAR(10)		NOT NULL,
	cor						VARCHAR(10)		NOT NULL,
	preco					DECIMAL(6,2)	NOT NULL,
	quantidade_stock		INT,
	classificacao_produto	INT,

	CONSTRAINT PK_Produto_Pronto PRIMARY KEY (id_produto_pronto),
	CONSTRAINT UQ_Produto_Pronto_Codigo UNIQUE (codigo),
	CONSTRAINT FK_Produto_Categoria
		FOREIGN KEY (classificacao_produto) REFERENCES NM.Categoria_Produto_Pronto(id_categoria_produto)
);

CREATE TABLE NM.Detalhe_Compra (
	id_detalhes				INT				NOT NULL,
	quantidade				INT				NOT NULL,
	preco_unitario			DECIMAL(6,2)	NOT NULL,
	compra					INT				NOT NULL,
	produto_pronto			INT				NOT NULL,

	CONSTRAINT PK_Detalhe_Compra PRIMARY KEY (id_detalhes),
	CONSTRAINT FK_Detalhe_Compra
		FOREIGN KEY (compra) REFERENCES NM.Compra(id_compra),
	CONSTRAINT FK_Detalhe_Produto
		FOREIGN KEY (produto_pronto) REFERENCES NM.Produto_Pronto(id_produto_pronto)
);

CREATE TABLE NM.Ajuste (
	id_ajuste				INT				NOT NULL,
	estado					VARCHAR(15)		NOT NULL,
	custo					DECIMAL(6,2)	NOT NULL,
	data_ajuste				DATE			NOT NULL,
	descricao				VARCHAR(50),
	braco_ajuste			INT				NULL,
	costas_ajuste			INT				NULL,
	peito_ajuste			INT				NULL,
	cinta_ajuste			INT				NULL,
	anca_ajuste				INT				NULL,
	detalhe_compra			INT				NULL,
	item_encomenda			INT				NULL,

	CONSTRAINT PK_Ajuste PRIMARY KEY (id_ajuste),
	CONSTRAINT CHK_Ajuste_Estado
		CHECK (estado IN ('Pendente', 'Em Produção', 'Pronta', 'Entregue', 'Cancelada')),
	CONSTRAINT CHK_Ajuste_Exclusivo
		CHECK (
			(detalhe_compra IS NOT NULL AND item_encomenda IS NULL) OR
			(detalhe_compra IS NULL AND item_encomenda IS NOT NULL)
		),
	CONSTRAINT FK_Ajuste_Detalhe
		FOREIGN KEY (detalhe_compra) REFERENCES NM.Detalhe_Compra(id_detalhes),
	CONSTRAINT FK_Ajuste_Item_Encomenda
		FOREIGN KEY (item_encomenda) REFERENCES NM.Item_Encomenda(id_item_encomenda)
);