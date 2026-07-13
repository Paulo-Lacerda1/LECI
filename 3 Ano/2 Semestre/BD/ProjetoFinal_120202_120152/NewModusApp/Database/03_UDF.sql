USE NewModus;
GO

-- Remove a funcao para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.fnTotalDadosAssociadosCliente', N'FN') IS NOT NULL
    DROP FUNCTION NM.fnTotalDadosAssociadosCliente;
GO

-- Calcula o total principal de dados associados a um cliente.
CREATE FUNCTION NM.fnTotalDadosAssociadosCliente
(
    @id_cliente INT
)
RETURNS INT
AS
BEGIN
    DECLARE @total INT;

    SELECT @total =
        (SELECT COUNT(*) FROM NM.Perfil_Medida WHERE cliente = @id_cliente) +
        (SELECT COUNT(*) FROM NM.Encomenda WHERE cliente = @id_cliente) +
        (SELECT COUNT(*) FROM NM.Compra WHERE cliente = @id_cliente);

    RETURN ISNULL(@total, 0);
END;
GO

-- Remove a funcao para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.fnCalcularTotalEncomenda', N'FN') IS NOT NULL
    DROP FUNCTION NM.fnCalcularTotalEncomenda;
GO

-- Calcula o total de uma encomenda a partir dos itens associados.
CREATE FUNCTION NM.fnCalcularTotalEncomenda
(
    @id_encomenda INT
)
RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @total DECIMAL(10,2);

    SELECT @total = SUM(preco)
    FROM NM.Item_Encomenda
    WHERE encomenda = @id_encomenda;

    RETURN ISNULL(@total, 0);
END;
GO

-- Remove a funcao para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.fnCalcularTotalCompraProntoVestir', N'FN') IS NOT NULL
    DROP FUNCTION NM.fnCalcularTotalCompraProntoVestir;
GO

-- Calcula o total de uma compra pronto a vestir a partir das linhas.
CREATE FUNCTION NM.fnCalcularTotalCompraProntoVestir
(
    @id_compra INT
)
RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @total DECIMAL(10,2);

    SELECT @total = SUM(quantidade * preco_unitario)
    FROM NM.Detalhe_Compra
    WHERE compra = @id_compra;

    RETURN ISNULL(@total, 0);
END;
GO

-- Remove a função se já existir para facilitar reexecução
IF OBJECT_ID(N'NM.fnCalcularCustoRealItem', N'FN') IS NOT NULL
    DROP FUNCTION NM.fnCalcularCustoRealItem;
GO

-- Calcula o custo real de produção de uma peça com base no histórico de preços congelados
CREATE FUNCTION NM.fnCalcularCustoRealItem (@id_item_encomenda INT)
RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @CustoTecidos DECIMAL(10,2) = 0;
    DECLARE @CustoMateriais DECIMAL(10,2) = 0;
    DECLARE @CustoTotal DECIMAL(10,2) = 0;

    -- Calcula os tecidos (metros usados * preço_cobrado)
    SELECT @CustoTecidos = ISNULL(SUM(metros_usados * preco_cobrado), 0)
    FROM NM.ItemEnc_Tecido 
    WHERE item_encomenda = @id_item_encomenda;

    -- Calcula os materiais (quantidade usada * preço_cobrado)
    SELECT @CustoMateriais = ISNULL(SUM(quantidade_usada * preco_cobrado), 0)
    FROM NM.ItemEnc_Material 
    WHERE item_encomenda = @id_item_encomenda;

    -- 3. Soma tudo
    SET @CustoTotal = @CustoTecidos + @CustoMateriais;

    RETURN @CustoTotal;
END;
GO