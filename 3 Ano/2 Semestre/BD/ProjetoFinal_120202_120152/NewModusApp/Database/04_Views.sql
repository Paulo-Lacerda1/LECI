USE NewModus;
GO

-- Remove a view para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.vwClientes', N'V') IS NOT NULL
    DROP VIEW NM.vwClientes;
GO

-- View simples com as colunas usadas no DataGridView de clientes.
CREATE VIEW NM.vwClientes
AS
    SELECT
        id_cliente AS ID,
        nome AS Nome,
        telefone AS Telefone,
        email AS Email
    FROM NM.Cliente;
GO

-- Remove a view para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.vwPerfisMedida', N'V') IS NOT NULL
    DROP VIEW NM.vwPerfisMedida;
GO

-- Junta os perfis de medida ao respetivo cliente para uso na interface.
CREATE VIEW NM.vwPerfisMedida
AS
    SELECT
        PM.id_perfil AS ID,
        PM.nome_perfil AS Perfil,
        PM.cliente AS ClienteID,
        C.nome AS Cliente,
        PM.data_atualizacao AS Data,
        PM.braco AS Braco,
        PM.costas AS Costas,
        PM.peito AS Peito,
        PM.cinta AS Cinta,
        PM.anca AS Anca
    FROM NM.Perfil_Medida PM
    INNER JOIN NM.Cliente C ON PM.cliente = C.id_cliente;
GO

-- Remove a view para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.vwEncomendas', N'V') IS NOT NULL
    DROP VIEW NM.vwEncomendas;
GO

-- Junta as encomendas ao respetivo cliente para uso na interface.
CREATE VIEW NM.vwEncomendas
AS
    SELECT
        E.id_encomenda AS ID,
        E.cliente AS ClienteID,
        C.nome AS Cliente,
        E.data_encomenda AS Data,
        E.data_prevista_entrega AS DataPrevista,
        E.estado AS Estado,
        E.data_pronto AS DataPronto,
        E.data_real_entrega AS DataEntrega,
        E.valor_total AS ValorTotal
    FROM NM.Encomenda E
    INNER JOIN NM.Cliente C ON E.cliente = C.id_cliente;
GO

-- Remove a view para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.vwItensEncomenda', N'V') IS NOT NULL
    DROP VIEW NM.vwItensEncomenda;
GO

-- Junta os itens da encomenda ao perfil de medida e ao modelo escolhido.
CREATE VIEW NM.vwItensEncomenda
AS
    SELECT
        IE.id_item_encomenda AS ID,
        IE.encomenda AS EncomendaID,
        IE.perfil_medida AS PerfilID,
        PM.nome_perfil AS Perfil,
        IE.modelo AS ModeloID,
        M.nome_modelo AS Modelo,
        IE.tamanho AS Tamanho,
        IE.preco AS Preco,
        IE.tipo_peca AS TipoPeca,
        NM.fnCalcularCustoRealItem(IE.id_item_encomenda) AS CustoProducao,
        IE.descricao_personalizacao AS Descricao
    FROM NM.Item_Encomenda IE
    INNER JOIN NM.Perfil_Medida PM ON IE.perfil_medida = PM.id_perfil
    LEFT JOIN NM.Modelo M ON IE.modelo = M.id_modelo;
GO

-- Remove a view para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.vwComprasProntoVestir', N'V') IS NOT NULL
    DROP VIEW NM.vwComprasProntoVestir;
GO

-- Lista as compras de produtos prontos com cliente opcional.
CREATE VIEW NM.vwComprasProntoVestir
AS
    SELECT
        CP.id_compra AS ID,
        CP.cliente AS ClienteID,
        ISNULL(C.nome, 'Cliente ocasional') AS Cliente,
        CP.data_compra AS Data,
        CP.valor_total AS ValorTotal,
        CP.metodo_pagamento AS MetodoPagamento
    FROM NM.Compra CP
    LEFT JOIN NM.Cliente C ON CP.cliente = C.id_cliente;
GO

-- Remove a view para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.vwDetalhesCompraProntoVestir', N'V') IS NOT NULL
    DROP VIEW NM.vwDetalhesCompraProntoVestir;
GO

-- Junta as linhas da compra aos produtos prontos.
CREATE VIEW NM.vwDetalhesCompraProntoVestir
AS
    SELECT
        DC.id_detalhes AS ID,
        DC.compra AS CompraID,
        DC.produto_pronto AS ProdutoID,
        PP.nome AS Produto,
        PP.codigo AS Codigo,
        PP.tamanho AS Tamanho,
        PP.cor AS Cor,
        CP.nome_categoria AS Categoria,
        DC.quantidade AS Quantidade,
        DC.preco_unitario AS PrecoUnitario,
        CAST(DC.quantidade * DC.preco_unitario AS DECIMAL(10,2)) AS Subtotal
    FROM NM.Detalhe_Compra DC
    INNER JOIN NM.Produto_Pronto PP ON DC.produto_pronto = PP.id_produto_pronto
    LEFT JOIN NM.Categoria_Produto_Pronto CP ON PP.classificacao_produto = CP.id_categoria_produto;
GO


-- Remove a view para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.vwListarTecidos', N'V') IS NOT NULL
    DROP VIEW NM.vwListarTecidos;
GO

-- Lista os tecidos disponíveis com o respetivo fornecedor 
CREATE VIEW NM.vwListarTecidos AS
SELECT 
    T.id_tecido, 
    T.nome AS Tecido, 
    T.preco_metro AS PrecoPorMetro, 
    T.quantidade_stock AS StockTecido,
    T.codigo AS CodigoTecido,
    T.cor AS CorTecido,
    T.tipo AS TipoTecido,
    T.padrao as PadraoTecido,
    T.fornecedor AS FornecedorID,
    F.nome + ' (' + CAST(T.fornecedor AS VARCHAR(10)) + ')' AS FornecedorExibicao
FROM NM.Tecido T
JOIN NM.Fornecedor F ON T.fornecedor = F.id_fornecedor;
GO


-- ===========================================================================
-- VIEWS DO DASHBOARD
-- ===========================================================================

IF OBJECT_ID(N'NM.vwDashboard_FaturacaoMesAtual', N'V') IS NOT NULL
    DROP VIEW NM.vwDashboard_FaturacaoMesAtual;
GO

-- Faturacao total do mes corrente (encomendas concluidas ou entregues).
-- Usa a data de fecho real: data_real_entrega > data_pronto > data_encomenda (fallback).
CREATE VIEW NM.vwDashboard_FaturacaoMesAtual
AS
    SELECT ISNULL(SUM(ie.preco), 0) AS Total
    FROM NM.Item_Encomenda ie
    INNER JOIN NM.Encomenda e ON ie.encomenda = e.id_encomenda
    WHERE e.estado IN ('Concluída', 'Entregue')
      AND MONTH(ISNULL(e.data_real_entrega, ISNULL(e.data_pronto, e.data_encomenda))) = MONTH(GETDATE())
      AND YEAR(ISNULL(e.data_real_entrega, ISNULL(e.data_pronto, e.data_encomenda)))  = YEAR(GETDATE());
GO

IF OBJECT_ID(N'NM.vwDashboard_EncomendasAtivas', N'V') IS NOT NULL
    DROP VIEW NM.vwDashboard_EncomendasAtivas;
GO

-- Numero de encomendas em estado activo (Pendente ou Em Producao).
CREATE VIEW NM.vwDashboard_EncomendasAtivas
AS
    SELECT COUNT(*) AS Total
    FROM NM.Encomenda
    WHERE estado IN ('Pendente', 'Em Produção');
GO

IF OBJECT_ID(N'NM.vwDashboard_StockTotal', N'V') IS NOT NULL
    DROP VIEW NM.vwDashboard_StockTotal;
GO

-- Total de pecas em stock de produtos prontos.
CREATE VIEW NM.vwDashboard_StockTotal
AS
    SELECT ISNULL(SUM(quantidade_stock), 0) AS Total
    FROM NM.Produto_Pronto;
GO

IF OBJECT_ID(N'NM.vwDashboard_ReceitaProntoVestir', N'V') IS NOT NULL
    DROP VIEW NM.vwDashboard_ReceitaProntoVestir;
GO

-- Receita total acumulada de vendas de pronto a vestir.
CREATE VIEW NM.vwDashboard_ReceitaProntoVestir
AS
    SELECT ISNULL(SUM(valor_total), 0) AS Total
    FROM NM.Compra;
GO

IF OBJECT_ID(N'NM.vwDashboard_ReceitaPorMedida', N'V') IS NOT NULL
    DROP VIEW NM.vwDashboard_ReceitaPorMedida;
GO

-- Receita total acumulada de encomendas por medida concluidas ou entregues.
CREATE VIEW NM.vwDashboard_ReceitaPorMedida
AS
    SELECT ISNULL(SUM(valor_total), 0) AS Total
    FROM NM.Encomenda
    WHERE estado IN ('Concluída', 'Entregue');
GO


-- ===========================================================================
-- VIEWS DE PRODUTOS PRONTOS
-- ===========================================================================

IF OBJECT_ID(N'NM.vwProdutosProntos', N'V') IS NOT NULL
    DROP VIEW NM.vwProdutosProntos;
GO

-- Lista completa de produtos prontos com a categoria associada.
CREATE VIEW NM.vwProdutosProntos
AS
    SELECT
        p.id_produto_pronto  AS ID,
        p.codigo             AS Codigo,
        p.nome               AS Nome,
        p.tamanho            AS Tamanho,
        p.cor                AS Cor,
        p.preco              AS Preco,
        p.quantidade_stock   AS Stock,
        c.nome_categoria     AS Categoria
    FROM NM.Produto_Pronto p
    LEFT JOIN NM.Categoria_Produto_Pronto c
        ON p.classificacao_produto = c.id_categoria_produto;
GO

IF OBJECT_ID(N'NM.vwProdutosDetalhados', N'V') IS NOT NULL
    DROP VIEW NM.vwProdutosDetalhados;
GO

-- Lista de produtos prontos com coluna calculada Produto (nome + codigo).
CREATE VIEW NM.vwProdutosDetalhados
AS
    SELECT
        id_produto_pronto                               AS ID,
        nome                                            AS Nome,
        nome + ' - ' + CAST(codigo AS VARCHAR(20))      AS Produto,
        tamanho                                         AS Tamanho,
        cor                                             AS Cor,
        codigo                                          AS Codigo,
        preco                                           AS Preco
    FROM NM.Produto_Pronto;
GO

IF OBJECT_ID(N'NM.vwCategoriasProdutoPronto', N'V') IS NOT NULL
    DROP VIEW NM.vwCategoriasProdutoPronto;
GO

-- Categorias disponiveis para produto pronto.
CREATE VIEW NM.vwCategoriasProdutoPronto
AS
    SELECT
        id_categoria_produto AS ID,
        nome_categoria       AS Nome
    FROM NM.Categoria_Produto_Pronto;
GO


-- ===========================================================================
-- VIEWS AUXILIARES PARA ENCOMENDAS
-- ===========================================================================

IF OBJECT_ID(N'NM.vwTecidosParaEncomenda', N'V') IS NOT NULL
    DROP VIEW NM.vwTecidosParaEncomenda;
GO

-- Tecidos formatados para a combo box de seleccao em encomendas.
CREATE VIEW NM.vwTecidosParaEncomenda
AS
    SELECT
        id_tecido AS ID,
        nome + ' - ' + cor + ' (' + CAST(codigo AS VARCHAR(20)) + ')' AS Nome
    FROM NM.Tecido;
GO

IF OBJECT_ID(N'NM.vwMateriaisParaEncomenda', N'V') IS NOT NULL
    DROP VIEW NM.vwMateriaisParaEncomenda;
GO

-- Materiais formatados para a combo box de seleccao em encomendas.
CREATE VIEW NM.vwMateriaisParaEncomenda
AS
    SELECT
        id_material AS ID,
        nome + ' - ' + unidade_medida AS Nome
    FROM NM.Material;
GO


-- ===========================================================================
-- VIEWS DO INVENTARIO (MATERIAIS E FORNECEDORES)
-- ===========================================================================

IF OBJECT_ID(N'NM.vwMateriais', N'V') IS NOT NULL
    DROP VIEW NM.vwMateriais;
GO

-- Lista completa de materiais com o fornecedor associado.
CREATE VIEW NM.vwMateriais
AS
    SELECT
        M.id_material       AS ID,
        M.nome              AS Nome,
        M.custo_unitario    AS CustoUnitario,
        M.quantidade_stock  AS Stock,
        M.unidade_medida    AS UnidadeMedida,
        M.tipo              AS Tipo,
        M.fornecedor        AS FornecedorID,
        F.nome              AS FornecedorNome
    FROM NM.Material M
    INNER JOIN NM.Fornecedor F ON F.id_fornecedor = M.fornecedor;
GO

IF OBJECT_ID(N'NM.vwFornecedores', N'V') IS NOT NULL
    DROP VIEW NM.vwFornecedores;
GO

-- Lista simples de fornecedores para combos.
CREATE VIEW NM.vwFornecedores
AS
    SELECT
        id_fornecedor AS ID,
        nome          AS Nome
    FROM NM.Fornecedor;
GO