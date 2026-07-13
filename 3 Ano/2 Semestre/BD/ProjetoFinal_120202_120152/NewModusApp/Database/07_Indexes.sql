USE NewModus;
GO

-- Acelera pesquisas de clientes por nome.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_Cliente_Nome' AND object_id = OBJECT_ID(N'NM.Cliente'))
    CREATE INDEX IX_Cliente_Nome ON NM.Cliente(nome);
GO

-- Acelera pesquisas de clientes por telefone.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_Cliente_Telefone' AND object_id = OBJECT_ID(N'NM.Cliente'))
    CREATE INDEX IX_Cliente_Telefone ON NM.Cliente(telefone);
GO

-- Acelera pesquisas de clientes por email.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_Cliente_Email' AND object_id = OBJECT_ID(N'NM.Cliente'))
    CREATE INDEX IX_Cliente_Email ON NM.Cliente(email);
GO

-- Acelera consultas de encomendas por cliente.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_Encomenda_Cliente' AND object_id = OBJECT_ID(N'NM.Encomenda'))
    CREATE INDEX IX_Encomenda_Cliente ON NM.Encomenda(cliente);
GO

-- Acelera filtros de encomendas por estado.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_Encomenda_Estado' AND object_id = OBJECT_ID(N'NM.Encomenda'))
    CREATE INDEX IX_Encomenda_Estado ON NM.Encomenda(estado);
GO

-- Acelera filtros de encomendas por data.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_Encomenda_Data' AND object_id = OBJECT_ID(N'NM.Encomenda'))
    CREATE INDEX IX_Encomenda_Data ON NM.Encomenda(data_encomenda);
GO

-- Acelera consultas de compras por cliente.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_Compra_Cliente' AND object_id = OBJECT_ID(N'NM.Compra'))
    CREATE INDEX IX_Compra_Cliente ON NM.Compra(cliente);
GO

-- Acelera consultas de perfis de medida por cliente.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_PerfilMedida_Cliente' AND object_id = OBJECT_ID(N'NM.Perfil_Medida'))
    CREATE INDEX IX_PerfilMedida_Cliente ON NM.Perfil_Medida(cliente);
GO

-- Acelera filtros de perfis de medida por data.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_PerfilMedida_DataAtualizacao' AND object_id = OBJECT_ID(N'NM.Perfil_Medida'))
    CREATE INDEX IX_PerfilMedida_DataAtualizacao ON NM.Perfil_Medida(data_atualizacao);
GO

-- Acelera consultas de itens por encomenda.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_ItemEncomenda_Encomenda' AND object_id = OBJECT_ID(N'NM.Item_Encomenda'))
    CREATE INDEX IX_ItemEncomenda_Encomenda ON NM.Item_Encomenda(encomenda);
GO

-- Acelera consultas de itens por modelo.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_ItemEncomenda_Modelo' AND object_id = OBJECT_ID(N'NM.Item_Encomenda'))
    CREATE INDEX IX_ItemEncomenda_Modelo ON NM.Item_Encomenda(modelo);
GO

-- Acelera consultas de detalhes por compra.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_DetalheCompra_Compra' AND object_id = OBJECT_ID(N'NM.Detalhe_Compra'))
    CREATE INDEX IX_DetalheCompra_Compra ON NM.Detalhe_Compra(compra);
GO

-- Acelera consultas de detalhes por produto pronto.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_DetalheCompra_Produto' AND object_id = OBJECT_ID(N'NM.Detalhe_Compra'))
    CREATE INDEX IX_DetalheCompra_Produto ON NM.Detalhe_Compra(produto_pronto);
GO

-- Acelera filtros de compras pronto a vestir por metodo de pagamento.
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_Compra_MetodoPagamento' AND object_id = OBJECT_ID(N'NM.Compra'))
    CREATE INDEX IX_Compra_MetodoPagamento ON NM.Compra(metodo_pagamento);
GO
