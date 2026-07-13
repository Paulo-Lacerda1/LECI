USE NewModus;
GO

-- Remove as procedures para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.spListarClientes', N'P') IS NOT NULL DROP PROCEDURE NM.spListarClientes;
IF OBJECT_ID(N'NM.spPesquisarClientes', N'P') IS NOT NULL DROP PROCEDURE NM.spPesquisarClientes;
IF OBJECT_ID(N'NM.spCriarCliente', N'P') IS NOT NULL DROP PROCEDURE NM.spCriarCliente;
IF OBJECT_ID(N'NM.spAtualizarCliente', N'P') IS NOT NULL DROP PROCEDURE NM.spAtualizarCliente;
IF OBJECT_ID(N'NM.spObterResumoEliminacaoCliente', N'P') IS NOT NULL DROP PROCEDURE NM.spObterResumoEliminacaoCliente;
IF OBJECT_ID(N'NM.spEliminarClienteCompleto', N'P') IS NOT NULL DROP PROCEDURE NM.spEliminarClienteCompleto;
IF OBJECT_ID(N'NM.spListarPerfisMedida', N'P') IS NOT NULL DROP PROCEDURE NM.spListarPerfisMedida;
IF OBJECT_ID(N'NM.spPesquisarPerfisMedida', N'P') IS NOT NULL DROP PROCEDURE NM.spPesquisarPerfisMedida;
IF OBJECT_ID(N'NM.spCriarPerfilMedida', N'P') IS NOT NULL DROP PROCEDURE NM.spCriarPerfilMedida;
IF OBJECT_ID(N'NM.spAtualizarPerfilMedida', N'P') IS NOT NULL DROP PROCEDURE NM.spAtualizarPerfilMedida;
IF OBJECT_ID(N'NM.spEliminarPerfilMedida', N'P') IS NOT NULL DROP PROCEDURE NM.spEliminarPerfilMedida;
IF OBJECT_ID(N'NM.spListarEncomendas', N'P') IS NOT NULL DROP PROCEDURE NM.spListarEncomendas;
IF OBJECT_ID(N'NM.spPesquisarEncomendas', N'P') IS NOT NULL DROP PROCEDURE NM.spPesquisarEncomendas;
IF OBJECT_ID(N'NM.spCriarEncomenda', N'P') IS NOT NULL DROP PROCEDURE NM.spCriarEncomenda;
IF OBJECT_ID(N'NM.spAtualizarEncomenda', N'P') IS NOT NULL DROP PROCEDURE NM.spAtualizarEncomenda;
IF OBJECT_ID(N'NM.spObterResumoEliminacaoEncomenda', N'P') IS NOT NULL DROP PROCEDURE NM.spObterResumoEliminacaoEncomenda;
IF OBJECT_ID(N'NM.spEliminarEncomendaCompleta', N'P') IS NOT NULL DROP PROCEDURE NM.spEliminarEncomendaCompleta;
IF OBJECT_ID(N'NM.spListarModelosEncomenda', N'P') IS NOT NULL DROP PROCEDURE NM.spListarModelosEncomenda;
IF OBJECT_ID(N'NM.spListarPerfisMedidaCombo', N'P') IS NOT NULL DROP PROCEDURE NM.spListarPerfisMedidaCombo;
IF OBJECT_ID(N'NM.spListarItensEncomenda', N'P') IS NOT NULL DROP PROCEDURE NM.spListarItensEncomenda;
IF OBJECT_ID(N'NM.spCriarItemEncomenda', N'P') IS NOT NULL DROP PROCEDURE NM.spCriarItemEncomenda;
IF OBJECT_ID(N'NM.spAtualizarItemEncomenda', N'P') IS NOT NULL DROP PROCEDURE NM.spAtualizarItemEncomenda;
IF OBJECT_ID(N'NM.spEliminarItemEncomenda', N'P') IS NOT NULL DROP PROCEDURE NM.spEliminarItemEncomenda;
IF OBJECT_ID(N'NM.spListarProdutosProntosCombo', N'P') IS NOT NULL DROP PROCEDURE NM.spListarProdutosProntosCombo;
IF OBJECT_ID(N'NM.spPesquisarComprasProntoVestir', N'P') IS NOT NULL DROP PROCEDURE NM.spPesquisarComprasProntoVestir;
IF OBJECT_ID(N'NM.spCriarCompraProntoVestir', N'P') IS NOT NULL DROP PROCEDURE NM.spCriarCompraProntoVestir;
IF OBJECT_ID(N'NM.spAtualizarCompraProntoVestir', N'P') IS NOT NULL DROP PROCEDURE NM.spAtualizarCompraProntoVestir;
IF OBJECT_ID(N'NM.spObterResumoEliminacaoCompraProntoVestir', N'P') IS NOT NULL DROP PROCEDURE NM.spObterResumoEliminacaoCompraProntoVestir;
IF OBJECT_ID(N'NM.spEliminarCompraProntoVestirCompleta', N'P') IS NOT NULL DROP PROCEDURE NM.spEliminarCompraProntoVestirCompleta;
IF OBJECT_ID(N'NM.spListarDetalhesCompraProntoVestir', N'P') IS NOT NULL DROP PROCEDURE NM.spListarDetalhesCompraProntoVestir;
IF OBJECT_ID(N'NM.spCriarDetalheCompraProntoVestir', N'P') IS NOT NULL DROP PROCEDURE NM.spCriarDetalheCompraProntoVestir;
IF OBJECT_ID(N'NM.spAtualizarDetalheCompraProntoVestir', N'P') IS NOT NULL DROP PROCEDURE NM.spAtualizarDetalheCompraProntoVestir;
IF OBJECT_ID(N'NM.spEliminarDetalheCompraProntoVestir', N'P') IS NOT NULL DROP PROCEDURE NM.spEliminarDetalheCompraProntoVestir;
-- Dashboard
IF OBJECT_ID(N'NM.spObterFaturacaoMesAtual',     N'P') IS NOT NULL DROP PROCEDURE NM.spObterFaturacaoMesAtual;
IF OBJECT_ID(N'NM.spObterEncomendasAtivas',       N'P') IS NOT NULL DROP PROCEDURE NM.spObterEncomendasAtivas;
IF OBJECT_ID(N'NM.spObterTotalPecasStock',        N'P') IS NOT NULL DROP PROCEDURE NM.spObterTotalPecasStock;
IF OBJECT_ID(N'NM.spObterAlertasStock',           N'P') IS NOT NULL DROP PROCEDURE NM.spObterAlertasStock;
IF OBJECT_ID(N'NM.spObterReceitaProntoVestir',    N'P') IS NOT NULL DROP PROCEDURE NM.spObterReceitaProntoVestir;
IF OBJECT_ID(N'NM.spObterReceitaPorMedida',       N'P') IS NOT NULL DROP PROCEDURE NM.spObterReceitaPorMedida;
IF OBJECT_ID(N'NM.spObterFaturacaoPorIntervalo',       N'P') IS NOT NULL DROP PROCEDURE NM.spObterFaturacaoPorIntervalo;
-- Produtos Prontos CRUD
IF OBJECT_ID(N'NM.spListarProdutosProntos',       N'P') IS NOT NULL DROP PROCEDURE NM.spListarProdutosProntos;
IF OBJECT_ID(N'NM.spListarProdutosDetalhados',    N'P') IS NOT NULL DROP PROCEDURE NM.spListarProdutosDetalhados;
IF OBJECT_ID(N'NM.spListarCategoriasProduto',     N'P') IS NOT NULL DROP PROCEDURE NM.spListarCategoriasProduto;
IF OBJECT_ID(N'NM.spObterProdutoPorCodigo',       N'P') IS NOT NULL DROP PROCEDURE NM.spObterProdutoPorCodigo;
IF OBJECT_ID(N'NM.spVerificarBloqueiosProduto',   N'P') IS NOT NULL DROP PROCEDURE NM.spVerificarBloqueiosProduto;
IF OBJECT_ID(N'NM.spEliminarProduto',             N'P') IS NOT NULL DROP PROCEDURE NM.spEliminarProduto;
IF OBJECT_ID(N'NM.spInserirProduto',              N'P') IS NOT NULL DROP PROCEDURE NM.spInserirProduto;
IF OBJECT_ID(N'NM.spAtualizarProduto',            N'P') IS NOT NULL DROP PROCEDURE NM.spAtualizarProduto;
IF OBJECT_ID(N'NM.spAtualizarProdutoProntoBasico',N'P') IS NOT NULL DROP PROCEDURE NM.spAtualizarProdutoProntoBasico;
-- Encomendas auxiliares
IF OBJECT_ID(N'NM.spListarTecidosParaEncomenda',  N'P') IS NOT NULL DROP PROCEDURE NM.spListarTecidosParaEncomenda;
IF OBJECT_ID(N'NM.spListarMateriaisParaEncomenda',N'P') IS NOT NULL DROP PROCEDURE NM.spListarMateriaisParaEncomenda;
IF OBJECT_ID(N'NM.spListarPedidosPendentes',      N'P') IS NOT NULL DROP PROCEDURE NM.spListarPedidosPendentes;
IF OBJECT_ID(N'NM.spListarHistoricoEncomendas',   N'P') IS NOT NULL DROP PROCEDURE NM.spListarHistoricoEncomendas;
IF OBJECT_ID(N'NM.spAtualizarEstadoEncomenda',    N'P') IS NOT NULL DROP PROCEDURE NM.spAtualizarEstadoEncomenda;
IF OBJECT_ID(N'NM.spObterPrecoAtualTecido',       N'P') IS NOT NULL DROP PROCEDURE NM.spObterPrecoAtualTecido;
IF OBJECT_ID(N'NM.spObterPrecoAtualMaterial',     N'P') IS NOT NULL DROP PROCEDURE NM.spObterPrecoAtualMaterial;
-- Materiais e Fornecedores
IF OBJECT_ID(N'NM.spListarMateriais',             N'P') IS NOT NULL DROP PROCEDURE NM.spListarMateriais;
IF OBJECT_ID(N'NM.spListarFornecedores',          N'P') IS NOT NULL DROP PROCEDURE NM.spListarFornecedores;
IF OBJECT_ID(N'NM.spObterMaterialPorNomeETipo',   N'P') IS NOT NULL DROP PROCEDURE NM.spObterMaterialPorNomeETipo;
IF OBJECT_ID(N'NM.spInserirMaterial',             N'P') IS NOT NULL DROP PROCEDURE NM.spInserirMaterial;
IF OBJECT_ID(N'NM.spAtualizarMaterial',           N'P') IS NOT NULL DROP PROCEDURE NM.spAtualizarMaterial;
-- Tecidos
IF OBJECT_ID(N'NM.spObterTecidoPorCodigo',        N'P') IS NOT NULL DROP PROCEDURE NM.spObterTecidoPorCodigo;
GO

-- Lista todos os clientes para mostrar na aplicacao.
CREATE PROCEDURE NM.spListarClientes
AS
BEGIN
    SET NOCOUNT ON;

    SELECT ID, Nome, Telefone, Email
    FROM NM.vwClientes
    ORDER BY Nome;
END;
GO

-- Pesquisa clientes pelo nome, telefone ou email.
CREATE PROCEDURE NM.spPesquisarClientes
    @textoPesquisa NVARCHAR(255) = NULL
AS
BEGIN
    SET NOCOUNT ON;

    SET @textoPesquisa = NULLIF(LTRIM(RTRIM(@textoPesquisa)), N'');

    SELECT ID, Nome, Telefone, Email
    FROM NM.vwClientes
    WHERE @textoPesquisa IS NULL
       OR Nome LIKE N'%' + @textoPesquisa + N'%'
       OR Telefone LIKE N'%' + @textoPesquisa + N'%'
       OR Email LIKE N'%' + @textoPesquisa + N'%'
    ORDER BY Nome;
END;
GO

-- Cria um novo cliente e devolve o id criado.
CREATE PROCEDURE NM.spCriarCliente
    @nome NVARCHAR(40),
    @telefone NVARCHAR(20),
    @email NVARCHAR(255) = NULL,
    @id_cliente INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    SET @nome = NULLIF(LTRIM(RTRIM(@nome)), N'');
    SET @telefone = NULLIF(LTRIM(RTRIM(@telefone)), N'');
    SET @email = NULLIF(LTRIM(RTRIM(@email)), N'');

    IF @nome IS NULL OR @telefone IS NULL
    BEGIN
        RAISERROR('Nome e telefone sao obrigatorios.', 16, 1);
        RETURN;
    END;

    SELECT @id_cliente = ISNULL(MAX(id_cliente), 0) + 1
    FROM NM.Cliente;

    INSERT INTO NM.Cliente (id_cliente, nome, telefone, email)
    VALUES (@id_cliente, @nome, @telefone, @email);

    SELECT @id_cliente AS id_cliente;
END;
GO

-- Atualiza os dados de um cliente existente.
CREATE PROCEDURE NM.spAtualizarCliente
    @id_cliente INT,
    @nome NVARCHAR(40),
    @telefone NVARCHAR(20),
    @email NVARCHAR(255) = NULL
AS
BEGIN
    SET NOCOUNT ON;

    SET @nome = NULLIF(LTRIM(RTRIM(@nome)), N'');
    SET @telefone = NULLIF(LTRIM(RTRIM(@telefone)), N'');
    SET @email = NULLIF(LTRIM(RTRIM(@email)), N'');

    IF @nome IS NULL OR @telefone IS NULL
    BEGIN
        RAISERROR('Nome e telefone sao obrigatorios.', 16, 1);
        RETURN;
    END;

    UPDATE NM.Cliente
    SET nome = @nome,
        telefone = @telefone,
        email = @email
    WHERE id_cliente = @id_cliente;

    IF @@ROWCOUNT = 0
        RAISERROR('O cliente indicado nao existe.', 16, 1);
END;
GO

-- Conta os dados associados a um cliente antes de apagar.
CREATE PROCEDURE NM.spObterResumoEliminacaoCliente
    @id_cliente INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @PerfisMedida    INT;
    DECLARE @Encomendas      INT;
    DECLARE @Compras         INT;
    DECLARE @AjustesEncomenda INT;
    DECLARE @AjustesCompra   INT;

    SELECT @PerfisMedida = COUNT(*)
    FROM NM.Perfil_Medida
    WHERE cliente = @id_cliente;

    SELECT @Encomendas = COUNT(*)
    FROM NM.Encomenda
    WHERE cliente = @id_cliente;

    SELECT @Compras = COUNT(*)
    FROM NM.Compra
    WHERE cliente = @id_cliente;

    SELECT @AjustesEncomenda = COUNT(*)
    FROM NM.Ajuste A
    INNER JOIN NM.Item_Encomenda IE ON A.item_encomenda = IE.id_item_encomenda
    INNER JOIN NM.Encomenda E      ON IE.encomenda = E.id_encomenda
    WHERE E.cliente = @id_cliente;

    SELECT @AjustesCompra = COUNT(*)
    FROM NM.Ajuste A
    INNER JOIN NM.Detalhe_Compra DC ON A.detalhe_compra = DC.id_detalhes
    INNER JOIN NM.Compra C          ON DC.compra = C.id_compra
    WHERE C.cliente = @id_cliente;

    SELECT
        @PerfisMedida     AS PerfisMedida,
        @Encomendas       AS Encomendas,
        @Compras          AS Compras,
        @AjustesEncomenda AS AjustesEncomenda,
        @AjustesCompra    AS AjustesCompra;
END;
GO

-- Apaga um cliente e os seus dados associados.
CREATE PROCEDURE NM.spEliminarClienteCompleto
    @id_cliente INT
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (SELECT 1 FROM NM.Cliente WHERE id_cliente = @id_cliente)
    BEGIN
        RAISERROR('O cliente indicado nao existe.', 16, 1);
        RETURN;
    END;

    BEGIN TRY
        BEGIN TRANSACTION;

        DELETE FROM NM.Encomenda WHERE cliente = @id_cliente;
        DELETE FROM NM.Compra WHERE cliente = @id_cliente;
        DELETE FROM NM.Perfil_Medida WHERE cliente = @id_cliente;
        DELETE FROM NM.Cliente WHERE id_cliente = @id_cliente;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        RAISERROR('Nao foi possivel eliminar o cliente.', 16, 1);
    END CATCH
END;
GO

-- Pesquisa perfis de medida por cliente e intervalo de datas.
CREATE PROCEDURE NM.spPesquisarPerfisMedida
    @cliente INT = NULL,
    @dataInicio DATE = NULL,
    @dataFim DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;

    IF @dataInicio IS NOT NULL AND @dataFim IS NOT NULL AND @dataInicio > @dataFim
    BEGIN
        RAISERROR('A data inicial nao pode ser superior a data final.', 16, 1);
        RETURN;
    END;

    SELECT ID, Perfil, ClienteID, Cliente, Data, Braco, Costas, Peito, Cinta, Anca
    FROM NM.vwPerfisMedida
    WHERE (@cliente IS NULL OR ClienteID = @cliente)
      AND (@dataInicio IS NULL OR Data >= @dataInicio)
      AND (@dataFim IS NULL OR Data <= @dataFim)
    ORDER BY Data DESC, Cliente, Perfil;
END;
GO

-- Cria um novo perfil de medida e devolve o id criado.
CREATE PROCEDURE NM.spCriarPerfilMedida
    @nome_perfil NVARCHAR(40),
    @braco INT,
    @costas INT,
    @peito INT,
    @cinta INT,
    @anca INT,
    @data_atualizacao DATE,
    @cliente INT,
    @id_perfil INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    SET @nome_perfil = NULLIF(LTRIM(RTRIM(@nome_perfil)), N'');

    IF @nome_perfil IS NULL OR @data_atualizacao IS NULL
    BEGIN
        RAISERROR('Nome do perfil e data sao obrigatorios.', 16, 1);
        RETURN;
    END;

    IF @braco < 0 OR @costas < 0 OR @peito < 0 OR @cinta < 0 OR @anca < 0
    BEGIN
        RAISERROR('As medidas nao podem ser negativas.', 16, 1);
        RETURN;
    END;

    SELECT @id_perfil = ISNULL(MAX(id_perfil), 0) + 1
    FROM NM.Perfil_Medida;

    INSERT INTO NM.Perfil_Medida
        (id_perfil, nome_perfil, braco, costas, peito, cinta, anca, data_atualizacao, cliente)
    VALUES
        (@id_perfil, @nome_perfil, @braco, @costas, @peito, @cinta, @anca, @data_atualizacao, @cliente);

    SELECT @id_perfil AS id_perfil;
END;
GO

-- Atualiza um perfil de medida existente.
CREATE PROCEDURE NM.spAtualizarPerfilMedida
    @id_perfil INT,
    @nome_perfil NVARCHAR(40),
    @braco INT,
    @costas INT,
    @peito INT,
    @cinta INT,
    @anca INT,
    @data_atualizacao DATE,
    @cliente INT
AS
BEGIN
    SET NOCOUNT ON;

    SET @nome_perfil = NULLIF(LTRIM(RTRIM(@nome_perfil)), N'');

    IF @nome_perfil IS NULL OR @data_atualizacao IS NULL
    BEGIN
        RAISERROR('Nome do perfil e data sao obrigatorios.', 16, 1);
        RETURN;
    END;

    IF @braco < 0 OR @costas < 0 OR @peito < 0 OR @cinta < 0 OR @anca < 0
    BEGIN
        RAISERROR('As medidas nao podem ser negativas.', 16, 1);
        RETURN;
    END;

    UPDATE NM.Perfil_Medida
    SET nome_perfil = @nome_perfil,
        braco = @braco,
        costas = @costas,
        peito = @peito,
        cinta = @cinta,
        anca = @anca,
        data_atualizacao = @data_atualizacao,
        cliente = @cliente
    WHERE id_perfil = @id_perfil;

    IF @@ROWCOUNT = 0
        RAISERROR('O perfil de medida indicado nao existe.', 16, 1);
END;
GO

-- Apaga um perfil de medida.
CREATE PROCEDURE NM.spEliminarPerfilMedida
    @id_perfil INT
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (SELECT 1 FROM NM.Perfil_Medida WHERE id_perfil = @id_perfil)
    BEGIN
        RAISERROR('O perfil de medida indicado nao existe.', 16, 1);
        RETURN;
    END;

    IF EXISTS (SELECT 1 FROM NM.Item_Encomenda WHERE perfil_medida = @id_perfil)
    BEGIN
        RAISERROR('Nao e possivel apagar este perfil de medida porque esta associado a uma ou mais encomendas.', 16, 1);
        RETURN;
    END;

    DELETE FROM NM.Perfil_Medida
    WHERE id_perfil = @id_perfil;
END;
GO

-- Pesquisa encomendas por cliente, estado e intervalo de datas.
CREATE PROCEDURE NM.spPesquisarEncomendas
    @cliente INT = NULL,
    @estado NVARCHAR(15) = NULL,
    @dataInicio DATE = NULL,
    @dataFim DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;

    SET @estado = NULLIF(LTRIM(RTRIM(@estado)), N'');

    IF @dataInicio IS NOT NULL AND @dataFim IS NOT NULL AND @dataInicio > @dataFim
    BEGIN
        RAISERROR('A data inicial nao pode ser superior a data final.', 16, 1);
        RETURN;
    END;

    SELECT
        ID, ClienteID, Cliente, Data, DataPrevista, Estado, DataPronto,
        DataEntrega, ValorTotal, NM.fnCalcularTotalEncomenda(ID) AS TotalItens
    FROM NM.vwEncomendas
    WHERE (@cliente IS NULL OR ClienteID = @cliente)
      AND (@estado IS NULL OR Estado = @estado)
      AND (@dataInicio IS NULL OR Data >= @dataInicio)
      AND (@dataFim IS NULL OR Data <= @dataFim)
    ORDER BY Data DESC, ID DESC;
END;
GO

-- Cria uma nova encomenda e devolve o id criado.
CREATE PROCEDURE NM.spCriarEncomenda
    @data_encomenda DATE,
    @data_prevista_entrega DATE = NULL,
    @estado NVARCHAR(15),
    @data_pronto DATE = NULL,
    @data_real_entrega DATE = NULL,
    @valor_total DECIMAL(10,2),
    @cliente INT,
    @id_encomenda INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    SET @estado = NULLIF(LTRIM(RTRIM(@estado)), N'');

    IF @data_encomenda IS NULL OR @estado IS NULL
    BEGIN
        RAISERROR('Data e estado sao obrigatorios.', 16, 1);
        RETURN;
    END;

    SELECT @id_encomenda = ISNULL(MAX(id_encomenda), 0) + 1
    FROM NM.Encomenda;

    INSERT INTO NM.Encomenda
        (id_encomenda, data_encomenda, data_prevista_entrega, estado, data_pronto, data_real_entrega, valor_total, cliente)
    VALUES
        (@id_encomenda, @data_encomenda, @data_prevista_entrega, @estado, @data_pronto, @data_real_entrega, @valor_total, @cliente);

    SELECT @id_encomenda AS id_encomenda;
END;
GO

-- Atualiza os dados principais de uma encomenda.
CREATE PROCEDURE NM.spAtualizarEncomenda
    @id_encomenda INT,
    @data_encomenda DATE,
    @data_prevista_entrega DATE = NULL,
    @estado NVARCHAR(15),
    @data_pronto DATE = NULL,
    @data_real_entrega DATE = NULL,
    @valor_total DECIMAL(10,2),
    @cliente INT
AS
BEGIN
    SET NOCOUNT ON;

    SET @estado = NULLIF(LTRIM(RTRIM(@estado)), N'');

    IF @data_encomenda IS NULL OR @estado IS NULL
    BEGIN
        RAISERROR('Data e estado sao obrigatorios.', 16, 1);
        RETURN;
    END;

    UPDATE NM.Encomenda
    SET data_encomenda = @data_encomenda,
        data_prevista_entrega = @data_prevista_entrega,
        estado = @estado,
        data_pronto = @data_pronto,
        data_real_entrega = @data_real_entrega,
        valor_total = @valor_total,
        cliente = @cliente
    WHERE id_encomenda = @id_encomenda;

    IF @@ROWCOUNT = 0
        RAISERROR('A encomenda indicada nao existe.', 16, 1);
END;
GO

-- Conta os dados associados a uma encomenda antes de apagar.
CREATE PROCEDURE NM.spObterResumoEliminacaoEncomenda
    @id_encomenda INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        (SELECT COUNT(*) FROM NM.Item_Encomenda WHERE encomenda = @id_encomenda) AS Itens,
        (SELECT COUNT(*) FROM NM.Pagamento_Encomenda WHERE encomenda = @id_encomenda) AS Pagamentos,
        (SELECT COUNT(*)
         FROM NM.Ajuste A
         INNER JOIN NM.Item_Encomenda IE ON A.item_encomenda = IE.id_item_encomenda
         WHERE IE.encomenda = @id_encomenda) AS Ajustes;
END;
GO

-- Apaga uma encomenda e os seus dados dependentes.
CREATE PROCEDURE NM.spEliminarEncomendaCompleta
    @id_encomenda INT
AS
BEGIN
    SET NOCOUNT ON;

    DELETE FROM NM.Encomenda
    WHERE id_encomenda = @id_encomenda;

    IF @@ROWCOUNT = 0
        RAISERROR('A encomenda indicada nao existe.', 16, 1);
END;
GO

-- Lista os modelos disponiveis para usar numa encomenda.
CREATE PROCEDURE NM.spListarModelosEncomenda
AS
BEGIN
    SET NOCOUNT ON;

    SELECT id_modelo AS ID, nome_modelo AS Nome, tipo_peca AS TipoPeca
    FROM NM.Modelo
    ORDER BY nome_modelo;
END;
GO

-- Lista perfis de medida para preencher a combo box de encomendas.
CREATE PROCEDURE NM.spListarPerfisMedidaCombo
    @cliente INT = NULL
AS
BEGIN
    SET NOCOUNT ON;

    SELECT ID, Perfil, ClienteID
    FROM NM.vwPerfisMedida
    WHERE @cliente IS NULL OR ClienteID = @cliente
    ORDER BY Perfil;
END;
GO

-- Lista os itens associados a uma encomenda.
CREATE PROCEDURE NM.spListarItensEncomenda
    @id_encomenda INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT ID, EncomendaID, PerfilID, Perfil, ModeloID, Modelo, Tamanho, Preco,
           TipoPeca, CustoProducao, Descricao
    FROM NM.vwItensEncomenda
    WHERE EncomendaID = @id_encomenda
    ORDER BY ID;
END;
GO

-- Cria um item de encomenda e recalcula o total da encomenda.
-- Removemos o parâmetro @custo_producao
CREATE PROCEDURE NM.spCriarItemEncomenda
    @tamanho INT,
    @preco DECIMAL(6,2),
    @custo_mao_obra DECIMAL(6,2),
    @tipo_peca NVARCHAR(15),
    @descricao_personalizacao NVARCHAR(50) = NULL,
    @perfil_medida INT,
    @modelo INT = NULL,
    @encomenda INT,
    @id_item_encomenda INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    SET @tipo_peca = NULLIF(LTRIM(RTRIM(@tipo_peca)), N'');
    SET @descricao_personalizacao = NULLIF(LTRIM(RTRIM(@descricao_personalizacao)), N'');

    IF @tipo_peca IS NULL OR @tamanho <= 0 OR @preco < 0
    BEGIN
        RAISERROR('Os dados do item nao sao validos.', 16, 1);
        RETURN;
    END;

    BEGIN TRY
        BEGIN TRANSACTION;

        SELECT @id_item_encomenda = ISNULL(MAX(id_item_encomenda), 0) + 1
        FROM NM.Item_Encomenda;

        -- Inserimos NULL no custo_producao pois vai deixar de ser usado
        INSERT INTO NM.Item_Encomenda
            (id_item_encomenda, tamanho, preco, tipo_peca, custo_producao, custo_mao_obra,
             descricao_personalizacao, perfil_medida, modelo, encomenda)
        VALUES
            (@id_item_encomenda, @tamanho, @preco, @tipo_peca, NULL, @custo_mao_obra,
             @descricao_personalizacao, @perfil_medida, @modelo, @encomenda);

        UPDATE NM.Encomenda
        SET valor_total = NM.fnCalcularTotalEncomenda(@encomenda)
        WHERE id_encomenda = @encomenda;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        RAISERROR('Nao foi possivel guardar o item da encomenda.', 16, 1);
    END CATCH

    SELECT @id_item_encomenda AS id_item_encomenda;
END;
GO

-- Atualiza um item de encomenda e recalcula o total da encomenda.
-- Removemos o parâmetro @custo_producao
CREATE PROCEDURE NM.spAtualizarItemEncomenda
    @id_item_encomenda INT,
    @tamanho INT,
    @preco DECIMAL(6,2),
    @custo_mao_obra DECIMAL(6,2),
    @tipo_peca NVARCHAR(15),
    @descricao_personalizacao NVARCHAR(50) = NULL,
    @perfil_medida INT,
    @modelo INT = NULL
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @encomenda INT;

    SET @tipo_peca = NULLIF(LTRIM(RTRIM(@tipo_peca)), N'');
    SET @descricao_personalizacao = NULLIF(LTRIM(RTRIM(@descricao_personalizacao)), N'');

    SELECT @encomenda = encomenda
    FROM NM.Item_Encomenda
    WHERE id_item_encomenda = @id_item_encomenda;

    IF @encomenda IS NULL
    BEGIN
        RAISERROR('O item indicado nao existe.', 16, 1);
        RETURN;
    END;

    IF @tipo_peca IS NULL OR @tamanho <= 0 OR @preco < 0
    BEGIN
        RAISERROR('Os dados do item nao sao validos.', 16, 1);
        RETURN;
    END;

    BEGIN TRY
        BEGIN TRANSACTION;

        -- Não tocamos no custo_producao no UPDATE
        UPDATE NM.Item_Encomenda
        SET tamanho = @tamanho,
            preco = @preco,
            custo_mao_obra = @custo_mao_obra,
            tipo_peca = @tipo_peca,
            descricao_personalizacao = @descricao_personalizacao,
            perfil_medida = @perfil_medida,
            modelo = @modelo
        WHERE id_item_encomenda = @id_item_encomenda;

        UPDATE NM.Encomenda
        SET valor_total = NM.fnCalcularTotalEncomenda(@encomenda)
        WHERE id_encomenda = @encomenda;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        RAISERROR('Nao foi possivel atualizar o item da encomenda.', 16, 1);
    END CATCH
END;
GO

-- Remove a stored procedure para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.spGuardarItemEncTecido', N'P') IS NOT NULL
    DROP PROCEDURE NM.spGuardarItemEncTecido;
GO

-- Guarda (insere/atualiza) o tecido usado num item de encomenda com preco historico congelado.
CREATE PROCEDURE NM.spGuardarItemEncTecido
    @tecido INT,
    @item_encomenda INT,
    @metros_usados DECIMAL(6,2),
    @preco_cobrado DECIMAL(6,2)
AS
BEGIN
    SET NOCOUNT ON;

    IF @metros_usados < 0 OR @preco_cobrado < 0
    BEGIN
        RAISERROR('Os valores de tecido usados nao sao validos.', 16, 1);
        RETURN;
    END;

    IF EXISTS (SELECT 1 FROM NM.ItemEnc_Tecido WHERE tecido = @tecido AND item_encomenda = @item_encomenda)
    BEGIN
        UPDATE NM.ItemEnc_Tecido
        SET metros_usados = @metros_usados,
            preco_cobrado = @preco_cobrado
        WHERE tecido = @tecido
          AND item_encomenda = @item_encomenda;
    END
    ELSE
    BEGIN
        INSERT INTO NM.ItemEnc_Tecido (tecido, item_encomenda, metros_usados, preco_cobrado)
        VALUES (@tecido, @item_encomenda, @metros_usados, @preco_cobrado);
    END
END;
GO

-- Remove a stored procedure para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.spGuardarItemEncMaterial', N'P') IS NOT NULL
    DROP PROCEDURE NM.spGuardarItemEncMaterial;
GO

-- Guarda (insere/atualiza) o material usado num item de encomenda com preco historico congelado.
CREATE PROCEDURE NM.spGuardarItemEncMaterial
    @material INT,
    @item_encomenda INT,
    @quantidade_usada INT,
    @preco_cobrado DECIMAL(6,2)
AS
BEGIN
    SET NOCOUNT ON;

    IF @quantidade_usada < 0 OR @preco_cobrado < 0
    BEGIN
        RAISERROR('Os valores de material usados nao sao validos.', 16, 1);
        RETURN;
    END;

    IF EXISTS (SELECT 1 FROM NM.ItemEnc_Material WHERE material = @material AND item_encomenda = @item_encomenda)
    BEGIN
        UPDATE NM.ItemEnc_Material
        SET quantidade_usada = @quantidade_usada,
            preco_cobrado = @preco_cobrado
        WHERE material = @material
          AND item_encomenda = @item_encomenda;
    END
    ELSE
    BEGIN
        INSERT INTO NM.ItemEnc_Material (material, item_encomenda, quantidade_usada, preco_cobrado)
        VALUES (@material, @item_encomenda, @quantidade_usada, @preco_cobrado);
    END
END;
GO

-- Apaga um item de encomenda e recalcula o total da encomenda.
CREATE PROCEDURE NM.spEliminarItemEncomenda
    @id_item_encomenda INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @encomenda INT;

    SELECT @encomenda = encomenda
    FROM NM.Item_Encomenda
    WHERE id_item_encomenda = @id_item_encomenda;

    IF @encomenda IS NULL
    BEGIN
        RAISERROR('O item indicado nao existe.', 16, 1);
        RETURN;
    END;

    BEGIN TRY
        BEGIN TRANSACTION;

        DELETE FROM NM.Item_Encomenda
        WHERE id_item_encomenda = @id_item_encomenda;

        UPDATE NM.Encomenda
        SET valor_total = NM.fnCalcularTotalEncomenda(@encomenda)
        WHERE id_encomenda = @encomenda;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        RAISERROR('Nao foi possivel eliminar o item da encomenda.', 16, 1);
    END CATCH
END;
GO

-- Lista produtos prontos para preencher a combo box de vendas.
CREATE PROCEDURE NM.spListarProdutosProntosCombo
AS
BEGIN
    SET NOCOUNT ON;

    SELECT id_produto_pronto AS ID,
           nome + ' - ' + CAST(codigo AS VARCHAR(20)) AS Produto,
           preco AS Preco
    FROM NM.Produto_Pronto
    ORDER BY nome;
END;
GO

-- Pesquisa vendas de pronto a vestir por cliente, pagamento e data.
CREATE PROCEDURE NM.spPesquisarComprasProntoVestir
    @cliente INT = NULL,
    @metodo_pagamento NVARCHAR(20) = NULL,
    @dataInicio DATE = NULL,
    @dataFim DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;

    SET @metodo_pagamento = NULLIF(LTRIM(RTRIM(@metodo_pagamento)), N'');

    IF @dataInicio IS NOT NULL AND @dataFim IS NOT NULL AND @dataInicio > @dataFim
    BEGIN
        RAISERROR('A data inicial nao pode ser superior a data final.', 16, 1);
        RETURN;
    END;

    SELECT ID, ClienteID, Cliente, Data, ValorTotal, MetodoPagamento
    FROM NM.vwComprasProntoVestir
    WHERE (@cliente IS NULL OR ClienteID = @cliente)
      AND (@metodo_pagamento IS NULL OR MetodoPagamento = @metodo_pagamento)
      AND (@dataInicio IS NULL OR Data >= @dataInicio)
      AND (@dataFim IS NULL OR Data <= @dataFim)
    ORDER BY Data DESC, ID DESC;
END;
GO

-- Cria uma venda de pronto a vestir e devolve o id criado.
CREATE PROCEDURE NM.spCriarCompraProntoVestir
    @data_compra DATE,
    @metodo_pagamento NVARCHAR(20),
    @cliente INT = NULL,
    @id_compra INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    SET @metodo_pagamento = NULLIF(LTRIM(RTRIM(@metodo_pagamento)), N'');

    IF @data_compra IS NULL OR @metodo_pagamento IS NULL
    BEGIN
        RAISERROR('Data e metodo de pagamento sao obrigatorios.', 16, 1);
        RETURN;
    END;

    SELECT @id_compra = ISNULL(MAX(id_compra), 0) + 1
    FROM NM.Compra;

    INSERT INTO NM.Compra (id_compra, data_compra, valor_total, metodo_pagamento, cliente)
    VALUES (@id_compra, @data_compra, 0, @metodo_pagamento, @cliente);

    SELECT @id_compra AS id_compra;
END;
GO

-- Atualiza os dados principais de uma venda de pronto a vestir.
CREATE PROCEDURE NM.spAtualizarCompraProntoVestir
    @id_compra INT,
    @data_compra DATE,
    @metodo_pagamento NVARCHAR(20),
    @cliente INT = NULL
AS
BEGIN
    SET NOCOUNT ON;

    SET @metodo_pagamento = NULLIF(LTRIM(RTRIM(@metodo_pagamento)), N'');

    IF @data_compra IS NULL OR @metodo_pagamento IS NULL
    BEGIN
        RAISERROR('Data e metodo de pagamento sao obrigatorios.', 16, 1);
        RETURN;
    END;

    UPDATE NM.Compra
    SET data_compra = @data_compra,
        metodo_pagamento = @metodo_pagamento,
        cliente = @cliente
    WHERE id_compra = @id_compra;

    IF @@ROWCOUNT = 0
        RAISERROR('A compra indicada nao existe.', 16, 1);
END;
GO

-- Conta os dados associados a uma venda antes de apagar.
CREATE PROCEDURE NM.spObterResumoEliminacaoCompraProntoVestir
    @id_compra INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        (SELECT COUNT(*) FROM NM.Detalhe_Compra WHERE compra = @id_compra) AS Detalhes,
        (SELECT COUNT(*)
         FROM NM.Ajuste A
         INNER JOIN NM.Detalhe_Compra DC ON A.detalhe_compra = DC.id_detalhes
         WHERE DC.compra = @id_compra) AS Ajustes;
END;
GO

-- Apaga uma venda de pronto a vestir e os seus detalhes.
CREATE PROCEDURE NM.spEliminarCompraProntoVestirCompleta
    @id_compra INT
AS
BEGIN
    SET NOCOUNT ON;

    DELETE FROM NM.Compra
    WHERE id_compra = @id_compra;

    IF @@ROWCOUNT = 0
        RAISERROR('A compra indicada nao existe.', 16, 1);
END;
GO

-- Lista os produtos de uma venda de pronto a vestir.
CREATE PROCEDURE NM.spListarDetalhesCompraProntoVestir
    @id_compra INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT ID, CompraID, ProdutoID, Produto, Codigo, Tamanho, Cor, Categoria,
           Quantidade, PrecoUnitario, Subtotal
    FROM NM.vwDetalhesCompraProntoVestir
    WHERE CompraID = @id_compra
    ORDER BY ID;
END;
GO

-- Cria um detalhe de venda e recalcula o total da venda.
CREATE PROCEDURE NM.spCriarDetalheCompraProntoVestir
    @quantidade INT,
    @preco_unitario DECIMAL(6,2),
    @compra INT,
    @produto_pronto INT,
    @id_detalhes INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    IF @quantidade <= 0 OR @preco_unitario < 0
    BEGIN
        RAISERROR('A quantidade e o preco unitario devem ser validos.', 16, 1);
        RETURN;
    END;

    BEGIN TRY
        BEGIN TRANSACTION;

        SELECT @id_detalhes = ISNULL(MAX(id_detalhes), 0) + 1
        FROM NM.Detalhe_Compra;

        INSERT INTO NM.Detalhe_Compra (id_detalhes, quantidade, preco_unitario, compra, produto_pronto)
        VALUES (@id_detalhes, @quantidade, @preco_unitario, @compra, @produto_pronto);

        UPDATE NM.Compra
        SET valor_total = NM.fnCalcularTotalCompraProntoVestir(@compra)
        WHERE id_compra = @compra;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        RAISERROR('Nao foi possivel guardar o detalhe da compra.', 16, 1);
    END CATCH

    SELECT @id_detalhes AS id_detalhes;
END;
GO

-- Atualiza um detalhe de venda e recalcula o total da venda.
CREATE PROCEDURE NM.spAtualizarDetalheCompraProntoVestir
    @id_detalhes INT,
    @quantidade INT,
    @preco_unitario DECIMAL(6,2),
    @produto_pronto INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @compra INT;

    SELECT @compra = compra
    FROM NM.Detalhe_Compra
    WHERE id_detalhes = @id_detalhes;

    IF @compra IS NULL
    BEGIN
        RAISERROR('O detalhe indicado nao existe.', 16, 1);
        RETURN;
    END;

    IF @quantidade <= 0 OR @preco_unitario < 0
    BEGIN
        RAISERROR('A quantidade e o preco unitario devem ser validos.', 16, 1);
        RETURN;
    END;

    BEGIN TRY
        BEGIN TRANSACTION;

        UPDATE NM.Detalhe_Compra
        SET quantidade = @quantidade,
            preco_unitario = @preco_unitario,
            produto_pronto = @produto_pronto
        WHERE id_detalhes = @id_detalhes;

        UPDATE NM.Compra
        SET valor_total = NM.fnCalcularTotalCompraProntoVestir(@compra)
        WHERE id_compra = @compra;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        RAISERROR('Nao foi possivel atualizar o detalhe da compra.', 16, 1);
    END CATCH
END;
GO

-- Apaga um detalhe de venda e recalcula o total da venda.
CREATE PROCEDURE NM.spEliminarDetalheCompraProntoVestir
    @id_detalhes INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @compra INT;

    SELECT @compra = compra
    FROM NM.Detalhe_Compra
    WHERE id_detalhes = @id_detalhes;

    IF @compra IS NULL
    BEGIN
        RAISERROR('O detalhe indicado nao existe.', 16, 1);
        RETURN;
    END;

    BEGIN TRY
        BEGIN TRANSACTION;

        DELETE FROM NM.Detalhe_Compra
        WHERE id_detalhes = @id_detalhes;

        UPDATE NM.Compra
        SET valor_total = NM.fnCalcularTotalCompraProntoVestir(@compra)
        WHERE id_compra = @compra;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        RAISERROR('Nao foi possivel eliminar o detalhe da compra.', 16, 1);
    END CATCH
END;
GO


-- Remove a procedure para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.spListarTecidos', N'P') IS NOT NULL
    DROP PROCEDURE NM.spListarTecidos;
GO

-- Lista todos os tecidos
CREATE PROCEDURE NM.spListarTecidos
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        id_tecido AS ID, 
        Tecido,
        PrecoPorMetro,
        StockTecido,
        CodigoTecido,
        CorTecido,
        TipoTecido,
        PadraoTecido,
        FornecedorID,
        FornecedorExibicao
    FROM NM.vwListarTecidos
    ORDER BY CodigoTecido;
END;
GO

-- Remove a procedure se ja existir
IF OBJECT_ID(N'NM.spInserirTecido', N'P') IS NOT NULL
    DROP PROCEDURE NM.spInserirTecido;
GO

-- Procedure para inserir novo tecido
CREATE PROCEDURE NM.spInserirTecido
    @nome VARCHAR(30),
    @preco_metro DECIMAL(6,2),
    @quantidade_stock DECIMAL(10,2),
    @codigo INT,
    @cor VARCHAR(10),
    @tipo VARCHAR(15),
    @padrao VARCHAR(15),
    @id_fornecedor INT
AS
BEGIN
    SET NOCOUNT ON;
    
    DECLARE @novo_id INT;

    BEGIN TRY
        -- Inicia a transacao
        BEGIN TRAN;

        -- Calcula o ID 
        SELECT @novo_id = ISNULL(MAX(id_tecido), 0) + 1
        FROM NM.Tecido;

        INSERT INTO NM.Tecido (id_tecido, nome, preco_metro, quantidade_stock, codigo, cor, tipo, padrao, fornecedor)
        VALUES (@novo_id, @nome, @preco_metro, @quantidade_stock, @codigo, @cor, @tipo, @padrao, @id_fornecedor);

        -- Confirma o insert
        COMMIT TRAN;
    END TRY
    BEGIN CATCH
        -- Se algo falhar, desfaz tudo
        IF @@TRANCOUNT > 0
            ROLLBACK TRAN;
            
        -- Lanca o erro para a aplicacao poder capturar e mostrar ao utilizador
        DECLARE @msg   NVARCHAR(2048) = ERROR_MESSAGE();
        DECLARE @sev   INT            = ERROR_SEVERITY();
        DECLARE @state INT            = ERROR_STATE();
        RAISERROR(@msg, @sev, @state);
    END CATCH
END;
GO

-- Procedure para atualizar um tecido existente
IF OBJECT_ID(N'NM.spAtualizarTecido', N'P') IS NOT NULL
    DROP PROCEDURE NM.spAtualizarTecido;
GO

CREATE PROCEDURE NM.spAtualizarTecido
    @id_tecido INT,
    @nome VARCHAR(30),
    @preco_metro DECIMAL(6,2),
    @quantidade_stock DECIMAL(10,2),
    @codigo INT,
    @cor VARCHAR(10),
    @tipo VARCHAR(15),
    @padrao VARCHAR(15),
    @id_fornecedor INT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRAN;

        UPDATE NM.Tecido
        SET nome = @nome,
            preco_metro = @preco_metro,
            quantidade_stock = @quantidade_stock,
            codigo = @codigo,
            cor = @cor,
            tipo = @tipo,
            padrao = @padrao,
            fornecedor = @id_fornecedor
        WHERE id_tecido = @id_tecido;

        COMMIT TRAN;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRAN;
        THROW;
    END CATCH
END;
GO


-- ===========================================
-- Procedures Dashboard

-- Faturacao total do mes corrente (encomendas concluidas ou entregues).
CREATE PROCEDURE NM.spObterFaturacaoMesAtual
AS
BEGIN
    SET NOCOUNT ON;
    SELECT Total FROM NM.vwDashboard_FaturacaoMesAtual;
END;
GO

-- Numero de encomendas activas (Pendente ou Em Producao).
CREATE PROCEDURE NM.spObterEncomendasAtivas
AS
BEGIN
    SET NOCOUNT ON;
    SELECT Total FROM NM.vwDashboard_EncomendasAtivas;
END;
GO

-- Total de pecas em stock de produtos prontos.
CREATE PROCEDURE NM.spObterTotalPecasStock
AS
BEGIN
    SET NOCOUNT ON;
    SELECT Total FROM NM.vwDashboard_StockTotal;
END;
GO

-- TOP 5 produtos com stock critico (stock <= 3), ordenados por stock ascendente.
CREATE PROCEDURE NM.spObterAlertasStock
AS
BEGIN
    SET NOCOUNT ON;
    SELECT TOP 5
        nome             AS Nome,
        quantidade_stock AS Stock
    FROM NM.Produto_Pronto
    WHERE quantidade_stock <= 3
    ORDER BY quantidade_stock ASC;
END;
GO

-- Receita total acumulada de vendas de pronto a vestir.
CREATE PROCEDURE NM.spObterReceitaProntoVestir
AS
BEGIN
    SET NOCOUNT ON;
    SELECT Total FROM NM.vwDashboard_ReceitaProntoVestir;
END;
GO

-- Receita total acumulada de encomendas por medida.
CREATE PROCEDURE NM.spObterReceitaPorMedida
AS
BEGIN
    SET NOCOUNT ON;
    SELECT Total FROM NM.vwDashboard_ReceitaPorMedida;
END;
GO


-- ===========================================================================
-- Procedures Pronto Pronto


-- Lista todos os produtos prontos com categoria, ordenados por nome/tamanho/cor.
CREATE PROCEDURE NM.spListarProdutosProntos
AS
BEGIN
    SET NOCOUNT ON;
    SELECT ID, Codigo, Nome, Tamanho, Cor, Preco, Stock, Categoria
    FROM NM.vwProdutosProntos
    ORDER BY Nome, Tamanho, Cor;
END;
GO

-- Lista produtos prontos com coluna Produto calculada, para combos.
CREATE PROCEDURE NM.spListarProdutosDetalhados
AS
BEGIN
    SET NOCOUNT ON;
    -- Faz o JOIN com a tabela base para garantir que vamos buscar a coluna quantidade_stock
    SELECT 
        v.ID, 
        v.Nome, 
        v.Produto, 
        v.Tamanho, 
        v.Cor, 
        v.Codigo, 
        v.Preco,
        p.quantidade_stock AS Stock
    FROM NM.vwProdutosDetalhados v
    INNER JOIN NM.Produto_Pronto p ON v.ID = p.id_produto_pronto
    ORDER BY v.Nome, v.Tamanho, v.Cor;
END;
GO

-- Lista categorias de produto pronto para combo de seleccao.
CREATE PROCEDURE NM.spListarCategoriasProduto
AS
BEGIN
    SET NOCOUNT ON;
    SELECT id_categoria_produto, nome_categoria
    FROM NM.Categoria_Produto_Pronto
    ORDER BY nome_categoria;
END;
GO

-- Devolve o produto pronto cujo codigo coincide com o parametro.
CREATE PROCEDURE NM.spObterProdutoPorCodigo
    @codigo INT
AS
BEGIN
    SET NOCOUNT ON;
    SELECT id_produto_pronto
    FROM NM.Produto_Pronto
    WHERE codigo = @codigo;
END;
GO

-- Verifica se existem vendas associadas antes de eliminar um produto.
-- Devolve numero de vendas; aplicacao interpreta valor > 0 como bloqueio.
CREATE PROCEDURE NM.spVerificarBloqueiosProduto
    @id INT
AS
BEGIN
    SET NOCOUNT ON;
    SELECT COUNT(*) AS Vendas
    FROM NM.Detalhe_Compra
    WHERE produto_pronto = @id;
END;
GO

-- Elimina fisicamente um produto pronto (apenas se sem vendas associadas).
CREATE PROCEDURE NM.spEliminarProduto
    @id INT
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (SELECT 1 FROM NM.Detalhe_Compra WHERE produto_pronto = @id)
    BEGIN
        RAISERROR('Este produto nao pode ser eliminado porque tem registos de venda associados.', 16, 1);
        RETURN;
    END;

    DELETE FROM NM.Produto_Pronto WHERE id_produto_pronto = @id;

    IF @@ROWCOUNT = 0
        RAISERROR('O produto indicado nao existe.', 16, 1);
END;
GO

-- Insere um novo produto pronto.
CREATE PROCEDURE NM.spInserirProduto
    @codigo      INT,
    @nome        VARCHAR(30),
    @tamanho     VARCHAR(10),
    @cor         VARCHAR(10),
    @preco       DECIMAL(6,2),
    @stock       INT,
    @idCategoria INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @novo_id INT;

    BEGIN TRY
        BEGIN TRANSACTION;

        SELECT @novo_id = ISNULL(MAX(id_produto_pronto), 0) + 1
        FROM NM.Produto_Pronto;

        INSERT INTO NM.Produto_Pronto
            (id_produto_pronto, codigo, nome, tamanho, cor, preco, quantidade_stock, classificacao_produto)
        VALUES
            (@novo_id, @codigo, @nome, @tamanho, @cor, @preco, @stock, @idCategoria);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0 ROLLBACK TRANSACTION;
        RAISERROR('Nao foi possivel inserir o produto.', 16, 1);
    END CATCH
END;
GO

-- Atualiza todos os campos editaveis de um produto pronto.
CREATE PROCEDURE NM.spAtualizarProduto
    @id          INT,
    @codigo      INT,
    @nome        VARCHAR(30),
    @tamanho     VARCHAR(10),
    @cor         VARCHAR(10),
    @preco       DECIMAL(6,2),
    @stock       INT,
    @idCategoria INT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE NM.Produto_Pronto
    SET codigo               = @codigo,
        nome                 = @nome,
        tamanho              = @tamanho,
        cor                  = @cor,
        preco                = @preco,
        quantidade_stock     = @stock,
        classificacao_produto = @idCategoria
    WHERE id_produto_pronto = @id;

    IF @@ROWCOUNT = 0
        RAISERROR('O produto indicado nao existe.', 16, 1);
END;
GO

-- Atualiza apenas nome, tamanho e cor de um produto pronto (usado na edicao de detalhe de compra).
CREATE PROCEDURE NM.spAtualizarProdutoProntoBasico
    @produto INT,
    @nome    VARCHAR(30),
    @tamanho VARCHAR(10),
    @cor     VARCHAR(10)
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE NM.Produto_Pronto
    SET nome    = @nome,
        tamanho = @tamanho,
        cor     = @cor
    WHERE id_produto_pronto = @produto;

    IF @@ROWCOUNT = 0
        RAISERROR('O produto indicado nao existe.', 16, 1);
END;
GO


-- ===========================================================================
-- Procedures auxiliares encomendas


-- Lista tecidos formatados para a combo box de seleccao em encomendas.
CREATE PROCEDURE NM.spListarTecidosParaEncomenda
AS
BEGIN
    SET NOCOUNT ON;
    SELECT ID, Nome FROM NM.vwTecidosParaEncomenda ORDER BY Nome;
END;
GO

-- Lista materiais formatados para a combo box de seleccao em encomendas.
CREATE PROCEDURE NM.spListarMateriaisParaEncomenda
AS
BEGIN
    SET NOCOUNT ON;
    SELECT ID, Nome FROM NM.vwMateriaisParaEncomenda ORDER BY Nome;
END;
GO

-- Lista pedidos de encomenda em estado activo (Pendente, Em Producao, Pronta).
CREATE PROCEDURE NM.spListarPedidosPendentes
    @cliente INT = NULL
AS
BEGIN
    SET NOCOUNT ON;

    SELECT ID, ClienteID, Cliente, Data, DataPrevista, Estado, DataPronto,
           DataEntrega, ValorTotal, NM.fnCalcularTotalEncomenda(ID) AS TotalItens
    FROM NM.vwEncomendas
    WHERE (@cliente IS NULL OR ClienteID = @cliente)
      AND Estado IN ('Pendente', 'Em Produção', 'Pronta')
    ORDER BY Data DESC, ID DESC;
END;
GO

-- Lista o historico de encomendas entregues.
CREATE PROCEDURE NM.spListarHistoricoEncomendas
    @cliente INT = NULL
AS
BEGIN
    SET NOCOUNT ON;

    SELECT ID, ClienteID, Cliente, Data, DataPrevista, Estado, DataPronto,
           DataEntrega, ValorTotal, NM.fnCalcularTotalEncomenda(ID) AS TotalItens
    FROM NM.vwEncomendas
    WHERE (@cliente IS NULL OR ClienteID = @cliente)
      AND Estado = 'Entregue'
    ORDER BY Data DESC, ID DESC;
END;
GO

-- Atualiza o estado de uma encomenda e preenche as datas de conclusao/entrega.
CREATE PROCEDURE NM.spAtualizarEstadoEncomenda
    @id_encomenda      INT,
    @estado            NVARCHAR(15),
    @definirDataPronto BIT,
    @definirDataEntrega BIT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE NM.Encomenda
    SET estado = @estado,
        data_pronto = CASE
            WHEN @definirDataPronto = 1 AND data_pronto IS NULL
            THEN CAST(GETDATE() AS DATE)
            ELSE data_pronto
        END,
        data_real_entrega = CASE
            WHEN @definirDataEntrega = 1
            THEN CAST(GETDATE() AS DATE)
            ELSE data_real_entrega
        END
    WHERE id_encomenda = @id_encomenda;

    IF @@ROWCOUNT = 0
        RAISERROR('A encomenda indicada nao existe.', 16, 1);
END;
GO

-- Devolve o preco por metro actual de um tecido (para congelar no momento da encomenda).
CREATE PROCEDURE NM.spObterPrecoAtualTecido
    @id_tecido INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT preco_metro AS Preco
    FROM NM.Tecido
    WHERE id_tecido = @id_tecido;
END;
GO

-- Devolve o custo unitario actual de um material (para congelar no momento da encomenda).
CREATE PROCEDURE NM.spObterPrecoAtualMaterial
    @id_material INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT custo_unitario AS Preco
    FROM NM.Material
    WHERE id_material = @id_material;
END;
GO


-- ===========================================================================
-- Procedures materiais e fornecedores

-- Lista todos os fornecedores para combos de seleccao.
CREATE PROCEDURE NM.spListarFornecedores
AS
BEGIN
    SET NOCOUNT ON;
    SELECT ID, Nome FROM NM.vwFornecedores ORDER BY Nome;
END;
GO

-- Lista todos os materiais com o respetivo fornecedor.
CREATE PROCEDURE NM.spListarMateriais
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        M.id_material          AS ID,
        M.nome                 AS Nome,
        M.custo_unitario       AS CustoUnitario,
        M.quantidade_stock     AS Stock,
        M.unidade_medida       AS UnidadeMedida,
        M.tipo                 AS Tipo,
        M.fornecedor           AS FornecedorID,
        F.nome                 AS FornecedorNome
    FROM NM.Material M
    JOIN NM.Fornecedor F ON M.fornecedor = F.id_fornecedor
    ORDER BY M.nome;
END;
GO

-- Devolve material cujos nome e tipo coincidem (para validar duplicados).
CREATE PROCEDURE NM.spObterMaterialPorNomeETipo
    @nome VARCHAR(30),
    @tipo VARCHAR(15)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT id_material AS ID, nome AS Nome, tipo AS Tipo
    FROM NM.Material
    WHERE nome = @nome AND tipo = @tipo;
END;
GO

-- Insere um novo material no inventario.
CREATE PROCEDURE NM.spInserirMaterial
    @nome            VARCHAR(30),
    @custo_unitario  DECIMAL(6,2),
    @quantidade_stock INT,
    @unidade_medida  VARCHAR(15),
    @tipo            VARCHAR(15),
    @fornecedor      INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @novo_id INT;

    BEGIN TRY
        BEGIN TRANSACTION;

        SELECT @novo_id = ISNULL(MAX(id_material), 0) + 1
        FROM NM.Material;

        INSERT INTO NM.Material
            (id_material, nome, custo_unitario, quantidade_stock, unidade_medida, tipo, fornecedor)
        VALUES
            (@novo_id, @nome, @custo_unitario, @quantidade_stock, @unidade_medida, @tipo, @fornecedor);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0 ROLLBACK TRANSACTION;
        RAISERROR('Nao foi possivel inserir o material.', 16, 1);
    END CATCH
END;
GO

-- Atualiza os campos de um material existente.
CREATE PROCEDURE NM.spAtualizarMaterial
    @id_material     INT,
    @nome            VARCHAR(30),
    @custo_unitario  DECIMAL(6,2),
    @quantidade_stock INT,
    @unidade_medida  VARCHAR(15),
    @tipo            VARCHAR(15),
    @fornecedor      INT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE NM.Material
    SET nome             = @nome,
        custo_unitario   = @custo_unitario,
        quantidade_stock = @quantidade_stock,
        unidade_medida   = @unidade_medida,
        tipo             = @tipo,
        fornecedor       = @fornecedor
    WHERE id_material = @id_material;

    IF @@ROWCOUNT = 0
        RAISERROR('O material indicado nao existe.', 16, 1);
END;
GO


-- ===========================================================================
-- Procedures tecidos

-- Devolve o id do tecido cujo codigo coincide com o parametro.
CREATE PROCEDURE NM.spObterTecidoPorCodigo
    @codigo INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT id_tecido
    FROM NM.Tecido
    WHERE codigo = @codigo;
END;
GO

-- Calcula a faturacao num intervalo de datas personalizado
CREATE PROCEDURE NM.spObterFaturacaoPorIntervalo
    @DataInicio DATE,
    @DataFim DATE
AS
BEGIN
    SET NOCOUNT ON;
    SELECT ISNULL(SUM(ie.preco), 0) AS Total
    FROM NM.Item_Encomenda ie
    INNER JOIN NM.Encomenda e ON ie.encomenda = e.id_encomenda
    WHERE e.estado IN ('Concluída', 'Entregue')
      AND ISNULL(e.data_real_entrega, ISNULL(e.data_pronto, e.data_encomenda)) >= @DataInicio
      AND ISNULL(e.data_real_entrega, ISNULL(e.data_pronto, e.data_encomenda)) <= @DataFim;
END;
GO