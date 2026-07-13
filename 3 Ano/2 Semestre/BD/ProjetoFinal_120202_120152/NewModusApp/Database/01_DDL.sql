-- Cria a base de dados se ainda nao existir.
IF DB_ID(N'NewModus') IS NULL
    CREATE DATABASE NewModus;
GO

USE NewModus;
GO

-- Cria o schema principal da aplicacao.
IF SCHEMA_ID(N'NM') IS NULL
    EXEC(N'CREATE SCHEMA NM');
GO

-- Guarda os fornecedores de tecidos e materiais.
CREATE TABLE NM.Fornecedor (
	id_fornecedor		INT				NOT NULL,
	nome				VARCHAR(40)		NOT NULL,
	telefone			VARCHAR(20)		NOT NULL,
	email				VARCHAR(255)	NOT NULL,
	morada				VARCHAR(50)		NOT NULL,
	
	CONSTRAINT PK_Fornecedor PRIMARY KEY (id_fornecedor)
);

-- Guarda os tecidos disponiveis em stock.
CREATE TABLE NM.Tecido (
	id_tecido			INT				NOT NULL,
	nome				VARCHAR(30)		NOT NULL,
	preco_metro			DECIMAL(6,2)	NOT NULL,
	quantidade_stock	DECIMAL(10,2)	NOT NULL DEFAULT 0, -- Força a não aceitar NULL, começa em 0 e impede valores negativos
	codigo				INT				NOT NULL,
	cor					VARCHAR(10),
	tipo				VARCHAR(15),
	padrao				VARCHAR(15),
	fornecedor			INT				NOT NULL,
	
	CONSTRAINT PK_Tecido PRIMARY KEY (id_tecido),
	CONSTRAINT UQ_Tecido_Codigo UNIQUE (codigo),
	CONSTRAINT CHK_Tecido_Stock CHECK (quantidade_stock >= 0), -- Bloqueio defensivo
	CONSTRAINT FK_Tecido_Fornecedor
		FOREIGN KEY (fornecedor) REFERENCES NM.Fornecedor(id_fornecedor)
);

-- Guarda os materiais disponiveis em stock.
CREATE TABLE NM.Material (
	id_material			INT				NOT NULL,
	nome				VARCHAR(30)		NOT NULL,
	custo_unitario		DECIMAL(6,2)	NOT NULL,
	quantidade_stock	INT				NOT NULL DEFAULT 0,
	unidade_medida		VARCHAR(15)		NOT NULL,
	tipo				VARCHAR(15),
	fornecedor			INT				NOT NULL,
	
	CONSTRAINT PK_Material PRIMARY KEY (id_material),
	CONSTRAINT CHK_Material_Stock CHECK (quantidade_stock >= 0),
	CONSTRAINT FK_Material_Fornecedor
		FOREIGN KEY (fornecedor) REFERENCES NM.Fornecedor(id_fornecedor)
);

-- Guarda os modelos de pecas.
CREATE TABLE NM.Modelo (
	id_modelo			INT				NOT NULL,
	nome_modelo			VARCHAR(40)		NOT NULL,
	tipo_peca			VARCHAR(15)		NOT NULL,
	descricao			VARCHAR(100),
	origem_modelo		VARCHAR(30),
	
	CONSTRAINT PK_Modelo PRIMARY KEY (id_modelo)
);

-- Guarda os clientes da loja.
CREATE TABLE NM.Cliente (
	id_cliente			INT				NOT NULL,
	nome				VARCHAR(40)		NOT NULL,
	telefone			VARCHAR(20)		NOT NULL,
	email				VARCHAR(255),

	CONSTRAINT PK_Cliente PRIMARY KEY (id_cliente)
);

-- Guarda as medidas associadas a cada cliente.
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

-- Guarda as encomendas feitas pelos clientes.
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

-- Guarda os itens/pecas de cada encomenda.
CREATE TABLE NM.Item_Encomenda (
	id_item_encomenda			INT				NOT NULL,
	tamanho						INT				NOT NULL,
	preco						DECIMAL(6,2)	NOT NULL,
	tipo_peca					VARCHAR(15)		NOT NULL,
	custo_producao				DECIMAL(10,2),
	custo_mao_obra				DECIMAL(6,2)	NOT NULL DEFAULT 0,
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
		ON DELETE CASCADE
);

-- Guarda os pagamentos associados a encomendas.
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
		ON DELETE CASCADE
);

-- Liga os tecidos usados a cada item de encomenda.
CREATE TABLE NM.ItemEnc_Tecido (
	tecido				INT				NOT NULL,
	item_encomenda		INT				NOT NULL,
	metros_usados		DECIMAL(6,2),
	preco_cobrado		DECIMAL(6,2)	NOT NULL,
	
	CONSTRAINT PK_ItemEnc_Tecido PRIMARY KEY (tecido, item_encomenda),
	CONSTRAINT FK_ItemEncTecido_Tecido
		FOREIGN KEY (tecido) REFERENCES NM.Tecido(id_tecido),
	CONSTRAINT FK_ItemEncTecido_Item
		FOREIGN KEY (item_encomenda) REFERENCES NM.Item_Encomenda(id_item_encomenda)
		ON DELETE CASCADE
);

-- Liga os materiais usados a cada item de encomenda.
CREATE TABLE NM.ItemEnc_Material (
	material			INT				NOT NULL,
	item_encomenda		INT				NOT NULL,
	quantidade_usada	INT,
	preco_cobrado		DECIMAL(6,2)	NOT NULL,
	
	CONSTRAINT PK_ItemEnc_Material PRIMARY KEY (material, item_encomenda),
	CONSTRAINT FK_ItemEncMat_Material
		FOREIGN KEY (material) REFERENCES NM.Material(id_material),
	CONSTRAINT FK_ItemEncMat_Item
		FOREIGN KEY (item_encomenda) REFERENCES NM.Item_Encomenda(id_item_encomenda)
		ON DELETE CASCADE
);

-- Guarda compras feitas por clientes na loja.
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

-- Guarda categorias de produtos prontos.
CREATE TABLE NM.Categoria_Produto_Pronto (
	id_categoria_produto	INT			NOT NULL,
	nome_categoria			VARCHAR(20)	NOT NULL,

	CONSTRAINT PK_Categoria_Produto PRIMARY KEY (id_categoria_produto)
);

-- Guarda produtos prontos para venda.
CREATE TABLE NM.Produto_Pronto (
	id_produto_pronto		INT				NOT NULL,
	codigo					INT				NOT NULL,
	nome					VARCHAR(30)		NOT NULL,
	tamanho					VARCHAR(10)		NOT NULL,
	cor						VARCHAR(10)		NOT NULL,
	preco					DECIMAL(6,2)	NOT NULL,
	quantidade_stock		INT				NOT NULL DEFAULT 0,
	classificacao_produto	INT,

	CONSTRAINT PK_Produto_Pronto PRIMARY KEY (id_produto_pronto),
	CONSTRAINT UQ_Produto_Pronto_Codigo UNIQUE (codigo),
	CONSTRAINT CHK_ProdutoPronto_Stock CHECK (quantidade_stock >= 0),
	CONSTRAINT FK_Produto_Categoria
		FOREIGN KEY (classificacao_produto) REFERENCES NM.Categoria_Produto_Pronto(id_categoria_produto)
);

-- Guarda os detalhes de cada compra.
CREATE TABLE NM.Detalhe_Compra (
	id_detalhes				INT				NOT NULL,
	quantidade				INT				NOT NULL,
	preco_unitario			DECIMAL(6,2)	NOT NULL,
	compra					INT				NOT NULL,
	produto_pronto			INT				NOT NULL,

	CONSTRAINT PK_Detalhe_Compra PRIMARY KEY (id_detalhes),
	CONSTRAINT FK_Detalhe_Compra
		FOREIGN KEY (compra) REFERENCES NM.Compra(id_compra)
		ON DELETE CASCADE,
	CONSTRAINT FK_Detalhe_Produto
		FOREIGN KEY (produto_pronto) REFERENCES NM.Produto_Pronto(id_produto_pronto)
);

-- Guarda ajustes feitos a itens de encomenda ou detalhes de compra.
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
		FOREIGN KEY (detalhe_compra) REFERENCES NM.Detalhe_Compra(id_detalhes)
		ON DELETE CASCADE,
	CONSTRAINT FK_Ajuste_Item_Encomenda
		FOREIGN KEY (item_encomenda) REFERENCES NM.Item_Encomenda(id_item_encomenda)
		ON DELETE CASCADE
);

-- Guarda o histórico de movimentos de stock
CREATE TABLE NM.Movimentos_Stock (
	id_movimento		INT				NOT NULL IDENTITY(1,1), -- Auto incremento
	tipo_item			VARCHAR(20)		NOT NULL, -- Tipo de item: 'Tecido', 'Material' ou 'Produto_Pronto'
	id_item				INT				NOT NULL, -- ID do tecido, material ou produto
	quantidade			DECIMAL(10,2)	NOT NULL, -- Quantidade movimentada (positiva para entradas, negativa para saidas)
	tipo_movimento		VARCHAR(20)		NOT NULL, -- Ex: 'Entrada', 'Venda', 'Produção', 'Ajuste', 'Quebra'
	data_movimento		DATETIME		NOT NULL DEFAULT GETDATE(),
	observacoes			VARCHAR(100)	NULL, -- Util para justificar ajustes manuais ou quebras

	CONSTRAINT PK_Movimentos_Stock PRIMARY KEY (id_movimento),
	CONSTRAINT CHK_Movimento_TipoItem 
		CHECK (tipo_item IN ('Tecido', 'Material', 'Produto_Pronto')),
	CONSTRAINT CHK_Movimento_Tipo 
		CHECK (tipo_movimento IN ('Entrada', 'Saida', 'Ajuste', 'Quebra', 'Produção', 'Venda'))
);

-- Liga os tecidos usados a um Ajuste (para gastar material extra no caso de erros ou alterações)
-- A tabela Ajuste apenas regista custo financeiro e o estado do ajuste. Para que um ajuste 
-- possa "gastar" material ou tecido, precisamos de criar tabelas de ligação, tal como implementado para o Item_Encomenda
CREATE TABLE NM.Ajuste_Tecido (
	tecido				INT				NOT NULL,
	ajuste				INT				NOT NULL,
	metros_usados		DECIMAL(6,2)	NOT NULL,
	
	CONSTRAINT PK_Ajuste_Tecido PRIMARY KEY (tecido, ajuste),
	CONSTRAINT FK_AjusteTecido_Tecido
		FOREIGN KEY (tecido) REFERENCES NM.Tecido(id_tecido),
	CONSTRAINT FK_AjusteTecido_Ajuste
		FOREIGN KEY (ajuste) REFERENCES NM.Ajuste(id_ajuste)
		ON DELETE CASCADE
);

-- Liga os materiais usados a um Ajuste
CREATE TABLE NM.Ajuste_Material (
	material			INT				NOT NULL,
	ajuste				INT				NOT NULL,
	quantidade_usada	INT				NOT NULL,
	
	CONSTRAINT PK_Ajuste_Material PRIMARY KEY (material, ajuste),
	CONSTRAINT FK_AjusteMat_Material
		FOREIGN KEY (material) REFERENCES NM.Material(id_material),
	CONSTRAINT FK_AjusteMat_Ajuste
		FOREIGN KEY (ajuste) REFERENCES NM.Ajuste(id_ajuste)
		ON DELETE CASCADE
);