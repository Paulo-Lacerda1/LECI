USE NewModus;
GO

-- Garante que o stock de tecido permite metros com casas decimais em bases ja existentes.
IF EXISTS (
    SELECT 1
    FROM sys.columns
    WHERE object_id = OBJECT_ID(N'NM.Tecido')
      AND name = N'quantidade_stock'
      AND (
            system_type_id <> TYPE_ID(N'decimal')
         OR precision <> 10
         OR scale <> 2
      )
)
BEGIN
    ALTER TABLE NM.Tecido
    ALTER COLUMN quantidade_stock DECIMAL(10,2) NULL;
END;
GO

-- Remove o trigger para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.trgCliente_ValidarEmail', N'TR') IS NOT NULL
    DROP TRIGGER NM.trgCliente_ValidarEmail;
GO

-- Valida o formato do email quando um cliente e inserido ou alterado.
CREATE TRIGGER NM.trgCliente_ValidarEmail
ON NM.Cliente
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE email IS NOT NULL
          AND email NOT LIKE '%_@_%._%'
    )
    BEGIN
        RAISERROR('O email do cliente nao tem um formato valido.', 16, 1);
        ROLLBACK TRAN;
    END;
END;
GO

-- Remove o trigger para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.trgPerfilMedida_ValidarValores', N'TR') IS NOT NULL
    DROP TRIGGER NM.trgPerfilMedida_ValidarValores;
GO

-- Garante regras basicas tambem quando os dados entram fora da aplicacao.
CREATE TRIGGER NM.trgPerfilMedida_ValidarValores
ON NM.Perfil_Medida
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE LTRIM(RTRIM(nome_perfil)) = ''
           OR braco < 0
           OR costas < 0
           OR peito < 0
           OR cinta < 0
           OR anca < 0
    )
    BEGIN
        RAISERROR('O perfil de medida tem valores invalidos.', 16, 1);
        ROLLBACK TRAN;
    END;
END;
GO

-- Remove o trigger para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.trgEncomenda_ValidarValores', N'TR') IS NOT NULL
    DROP TRIGGER NM.trgEncomenda_ValidarValores;
GO

-- Garante regras basicas tambem quando as encomendas entram fora da aplicacao.
CREATE TRIGGER NM.trgEncomenda_ValidarValores
ON NM.Encomenda
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE valor_total < 0
           OR estado NOT IN ('Pendente', 'Em Produção', 'Pronta', 'Entregue', 'Cancelada')
           OR (data_pronto IS NOT NULL AND data_pronto < data_encomenda)
           OR (data_real_entrega IS NOT NULL AND data_real_entrega < data_encomenda)
    )
    BEGIN
        RAISERROR('A encomenda tem valores invalidos.', 16, 1);
        ROLLBACK TRAN;
    END;
END;
GO

-- Remove o trigger para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.trgCompraProntoVestir_ValidarValores', N'TR') IS NOT NULL
    DROP TRIGGER NM.trgCompraProntoVestir_ValidarValores;
GO

-- Garante regras basicas nas compras pronto a vestir.
CREATE TRIGGER NM.trgCompraProntoVestir_ValidarValores
ON NM.Compra
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE valor_total < 0
           OR LTRIM(RTRIM(metodo_pagamento)) = ''
    )
    BEGIN
        RAISERROR('A compra pronto a vestir tem valores invalidos.', 16, 1);
        ROLLBACK TRAN;
    END;
END;
GO

-- Remove o trigger para permitir executar o script varias vezes.
IF OBJECT_ID(N'NM.trgDetalheCompra_ValidarValores', N'TR') IS NOT NULL
    DROP TRIGGER NM.trgDetalheCompra_ValidarValores;
GO

-- Garante regras basicas nas linhas da compra pronto a vestir.
CREATE TRIGGER NM.trgDetalheCompra_ValidarValores
ON NM.Detalhe_Compra
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (
        SELECT 1
        FROM inserted
        WHERE quantidade <= 0
           OR preco_unitario < 0
    )
    BEGIN
        RAISERROR('O detalhe da compra tem valores invalidos.', 16, 1);
        ROLLBACK TRAN;
    END;
END;
GO

-- nova lógica para descontar e devolver stock (quando uma encomenda é cancelada)
IF OBJECT_ID(N'NM.trgEncomenda_MaquinaEstadosStock', N'TR') IS NOT NULL DROP TRIGGER NM.trgEncomenda_MaquinaEstadosStock;
GO

CREATE TRIGGER NM.trgEncomenda_MaquinaEstadosStock
ON NM.Encomenda
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Se a coluna estado nao foi alterada, nao precisamos de fazer nada
    IF NOT UPDATE(estado) RETURN;

    -- Estado: "Em Produção" -> Descontar Stock e Registar no inventário
    IF EXISTS (SELECT 1 FROM inserted i INNER JOIN deleted d ON i.id_encomenda = d.id_encomenda WHERE i.estado = 'Em Produção' AND d.estado = 'Pendente')
    BEGIN
        -- Descontar Tecidos
        UPDATE T
        SET quantidade_stock = T.quantidade_stock - IET.metros_usados
        FROM NM.Tecido T
        INNER JOIN NM.ItemEnc_Tecido IET ON T.id_tecido = IET.tecido
        INNER JOIN NM.Item_Encomenda IE ON IET.item_encomenda = IE.id_item_encomenda
        INNER JOIN inserted I ON IE.encomenda = I.id_encomenda;

        -- Registar Tecidos no inventário (Movimentos_Stock)
        INSERT INTO NM.Movimentos_Stock (tipo_item, id_item, quantidade, tipo_movimento, observacoes)
        SELECT 'Tecido', IET.tecido, -IET.metros_usados, 'Produção', 'Encomenda ' + CAST(I.id_encomenda AS VARCHAR)
        FROM NM.ItemEnc_Tecido IET
        INNER JOIN NM.Item_Encomenda IE ON IET.item_encomenda = IE.id_item_encomenda
        INNER JOIN inserted I ON IE.encomenda = I.id_encomenda;

        -- Descontar Materiais
        UPDATE M
        SET quantidade_stock = M.quantidade_stock - IEM.quantidade_usada
        FROM NM.Material M
        INNER JOIN NM.ItemEnc_Material IEM ON M.id_material = IEM.material
        INNER JOIN NM.Item_Encomenda IE ON IEM.item_encomenda = IE.id_item_encomenda
        INNER JOIN inserted I ON IE.encomenda = I.id_encomenda;

        -- Registar Materiais no inventário (Movimentos_Stock)
        INSERT INTO NM.Movimentos_Stock (tipo_item, id_item, quantidade, tipo_movimento, observacoes)
        SELECT 'Material', IEM.material, -IEM.quantidade_usada, 'Produção', 'Encomenda ' + CAST(I.id_encomenda AS VARCHAR)
        FROM NM.ItemEnc_Material IEM
        INNER JOIN NM.Item_Encomenda IE ON IEM.item_encomenda = IE.id_item_encomenda
        INNER JOIN inserted I ON IE.encomenda = I.id_encomenda;
    END

    -- Estado: "Cancelada" -> Devolver Stock (apenas se já tinha sido retirado)
    IF EXISTS (SELECT 1 FROM inserted i INNER JOIN deleted d ON i.id_encomenda = d.id_encomenda WHERE i.estado = 'Cancelada' AND d.estado IN ('Em Produção', 'Pronta'))
    BEGIN
        -- Devolver Tecidos (Soma em vez de subtrair)
        UPDATE T
        SET quantidade_stock = T.quantidade_stock + IET.metros_usados
        FROM NM.Tecido T
        INNER JOIN NM.ItemEnc_Tecido IET ON T.id_tecido = IET.tecido
        INNER JOIN NM.Item_Encomenda IE ON IET.item_encomenda = IE.id_item_encomenda
        INNER JOIN inserted I ON IE.encomenda = I.id_encomenda;

        -- Registar Devolução Tecidos no inventário
        INSERT INTO NM.Movimentos_Stock (tipo_item, id_item, quantidade, tipo_movimento, observacoes)
        SELECT 'Tecido', IET.tecido, IET.metros_usados, 'Ajuste', 'Cancelamento Encomenda ' + CAST(I.id_encomenda AS VARCHAR)
        FROM NM.ItemEnc_Tecido IET
        INNER JOIN NM.Item_Encomenda IE ON IET.item_encomenda = IE.id_item_encomenda
        INNER JOIN inserted I ON IE.encomenda = I.id_encomenda;

        -- Devolver Materiais
        UPDATE M
        SET quantidade_stock = M.quantidade_stock + IEM.quantidade_usada
        FROM NM.Material M
        INNER JOIN NM.ItemEnc_Material IEM ON M.id_material = IEM.material
        INNER JOIN NM.Item_Encomenda IE ON IEM.item_encomenda = IE.id_item_encomenda
        INNER JOIN inserted I ON IE.encomenda = I.id_encomenda;

        -- Registar Devolução Materiais no inventário
        INSERT INTO NM.Movimentos_Stock (tipo_item, id_item, quantidade, tipo_movimento, observacoes)
        SELECT 'Material', IEM.material, IEM.quantidade_usada, 'Ajuste', 'Cancelamento Encomenda ' + CAST(I.id_encomenda AS VARCHAR)
        FROM NM.ItemEnc_Material IEM
        INNER JOIN NM.Item_Encomenda IE ON IEM.item_encomenda = IE.id_item_encomenda
        INNER JOIN inserted I ON IE.encomenda = I.id_encomenda;
    END
END;
GO


-- Bloqueio defensivo - impedir alterações quando nao está pendente
--
-- não podemos deixar adicionar/remover tecidos se a encomenda já estiver a ser feita ou finalizada.
IF OBJECT_ID(N'NM.trgBloquearAlteracoesTecidoEmProducao', N'TR') IS NOT NULL DROP TRIGGER NM.trgBloquearAlteracoesTecidoEmProducao;
GO

CREATE TRIGGER NM.trgBloquearAlteracoesTecidoEmProducao
ON NM.ItemEnc_Tecido
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Verifica se alguma das encomendas afetadas não está "Pendente"
    IF EXISTS (
        SELECT 1 FROM inserted i 
        JOIN NM.Item_Encomenda ie ON i.item_encomenda = ie.id_item_encomenda 
        JOIN NM.Encomenda e ON ie.encomenda = e.id_encomenda 
        WHERE e.estado != 'Pendente'
    ) OR EXISTS (
        SELECT 1 FROM deleted d 
        JOIN NM.Item_Encomenda ie ON d.item_encomenda = ie.id_item_encomenda 
        JOIN NM.Encomenda e ON ie.encomenda = e.id_encomenda 
        WHERE e.estado != 'Pendente'
    )
    BEGIN
        RAISERROR('Não pode alterar materiais de uma encomenda que já não está Pendente.', 16, 1);
        ROLLBACK TRAN;
    END
END;
GO

IF OBJECT_ID(N'NM.trgBloquearAlteracoesMaterialEmProducao', N'TR') IS NOT NULL DROP TRIGGER NM.trgBloquearAlteracoesMaterialEmProducao;
GO

CREATE TRIGGER NM.trgBloquearAlteracoesMaterialEmProducao
ON NM.ItemEnc_Material
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    
    IF EXISTS (
        SELECT 1 FROM inserted i JOIN NM.Item_Encomenda ie ON i.item_encomenda = ie.id_item_encomenda JOIN NM.Encomenda e ON ie.encomenda = e.id_encomenda WHERE e.estado != 'Pendente'
    ) OR EXISTS (
        SELECT 1 FROM deleted d JOIN NM.Item_Encomenda ie ON d.item_encomenda = ie.id_item_encomenda JOIN NM.Encomenda e ON ie.encomenda = e.id_encomenda WHERE e.estado != 'Pendente'
    )
    BEGIN
        RAISERROR('Não pode alterar materiais de uma encomenda que já não está Pendente.', 16, 1);
        ROLLBACK TRAN;
    END
END;
GO

-- Trigger que debita/repõe o stock e regista o movimento
IF OBJECT_ID(N'NM.trgDetalheCompra_MovimentoStock', N'TR') IS NOT NULL 
    DROP TRIGGER NM.trgDetalheCompra_MovimentoStock;
GO

CREATE TRIGGER NM.trgDetalheCompra_MovimentoStock
ON NM.Detalhe_Compra
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    -- A. DEVOLVER stock dos registos antigos (no caso de DELETE ou UPDATE)
    IF EXISTS (SELECT 1 FROM deleted)
    BEGIN
        UPDATE P
        SET quantidade_stock = P.quantidade_stock + D.QtdDevolver
        FROM NM.Produto_Pronto P
        INNER JOIN (
            SELECT produto_pronto, SUM(quantidade) AS QtdDevolver
            FROM deleted
            GROUP BY produto_pronto
        ) D ON P.id_produto_pronto = D.produto_pronto;
    END

    -- B. RETIRAR stock dos registos novos (no caso de INSERT ou UPDATE)
    IF EXISTS (SELECT 1 FROM inserted)
    BEGIN
        UPDATE P
        SET quantidade_stock = P.quantidade_stock - I.QtdRetirar
        FROM NM.Produto_Pronto P
        INNER JOIN (
            SELECT produto_pronto, SUM(quantidade) AS QtdRetirar
            FROM inserted
            GROUP BY produto_pronto
        ) I ON P.id_produto_pronto = I.produto_pronto;
    END

    -- C. REGISTAR Movimentos no Histórico
    -- C1. INSERT PURO (Venda nova)
    IF EXISTS (SELECT 1 FROM inserted) AND NOT EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO NM.Movimentos_Stock (tipo_item, id_item, quantidade, tipo_movimento, observacoes)
        SELECT 'Produto_Pronto', produto_pronto, -quantidade, 'Venda', 'Compra ID ' + CAST(compra AS VARCHAR)
        FROM inserted;
    END

    -- C2. UPDATE (Alteração da quantidade na compra)
    IF EXISTS (SELECT 1 FROM inserted) AND EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO NM.Movimentos_Stock (tipo_item, id_item, quantidade, tipo_movimento, observacoes)
        SELECT 'Produto_Pronto', I.produto_pronto, D.quantidade - I.quantidade, 'Ajuste', 'Alteração Compra ID ' + CAST(I.compra AS VARCHAR)
        FROM inserted I
        INNER JOIN deleted D ON I.id_detalhes = D.id_detalhes
        WHERE I.quantidade <> D.quantidade;
    END

    -- C3. DELETE PURO (Remoção da linha / Cancelamento)
    IF EXISTS (SELECT 1 FROM deleted) AND NOT EXISTS (SELECT 1 FROM inserted)
    BEGIN
        INSERT INTO NM.Movimentos_Stock (tipo_item, id_item, quantidade, tipo_movimento, observacoes)
        SELECT 'Produto_Pronto', produto_pronto, quantidade, 'Ajuste', 'Remoção Venda ID ' + CAST(compra AS VARCHAR)
        FROM deleted;
    END
END;
GO