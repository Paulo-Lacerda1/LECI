# BD: Trabalho Prático APFE

**Grupo**: PXGY
- Tiago Pita, MEC: 120152
- Paulo Lacerda, MEC: 120202

## Introdução / Introduction
 
Este projeto tem como objetivo o desenvolvimento de uma base de dados para a loja NewModus, uma loja física dedicada à venda de roupa já feita e à confeção de roupa por medida. A loja permite aos clientes adquirir peças prontas, como vestidos, saias e blusas, bem como solicitar a criação de peças personalizadas, escolhendo tecidos, modelos e fornecendo as suas medidas.

O principal objetivo deste trabalho é modelar e organizar a informação associada ao funcionamento da loja, incluindo dados de clientes, produtos, encomendas, tecidos, pagamentos e materiais utilizados. Pretende-se criar uma estrutura de dados coerente e eficiente que permita gerir o stock, acompanhar encomendas por medida e manter um registo do histórico de compras.

## ​Análise de Requisitos / Requirements



### 1. Entidades principais

- **Cliente**  
  Representa uma pessoa registada no sistema, podendo realizar compras de produtos prontos e fazer encomendas por medida.  
  **Atributos:** `id_cliente`, `nome`, `telefone`, `email`.

- **Medida_Cliente**  
  Guarda as medidas corporais gerais associadas a um cliente.  
  **Atributos:** `id_medida_cliente`, `braço`, `costas`, `peito`, `cinta`, `anca`, `data_registo`.

- **Encomenda**  
  Representa um pedido de confeção de roupa por medida feito por um cliente.  
  **Atributos:** `id_encomenda`, `data_encomenda`, `data_prevista_entrega`, `data_real_entrega`, `data_pronto`, `estado`, `valor_total`, `valor_pago`, `valor_em_falta`.

- **Pagamento_Encomenda**  
  Regista os pagamentos efetuados no contexto de uma encomenda.  
  **Atributos:** `id_pagamento_encomenda`, `data_pagamento`, `valor`, `metodo_pagamento`.

- **Item_Encomenda**  
  Representa cada peça individual incluída numa encomenda.  
  **Atributos:** `id_item_encomenda`, `descricao_personalizacao`, `custo_producao`, `tipo_peca`, `tamanho`, `preco`.

- **Medida_Item_Encomenda**  
  Guarda as medidas específicas de um item de encomenda, quando a peça exige medidas próprias.  
  **Atributos:** `id_medida_item`, `braço`, `costas`, `peito`, `cinta`, `anca`.

- **Modelo**  
  Representa um modelo base de peça que pode servir de referência para uma encomenda.  
  **Atributos:** `id_modelo`, `nome_modelo`, `descricao`, `tipo_peca`, `origem_modelo`.

- **Tecido**  
  Representa os tecidos usados na produção das peças.  
  **Atributos:** `id_tecido`, `nome`, `tipo`, `cor`, `padrao`, `preco_metro`, `quantidade_stock`.

- **Material**  
  Representa outros materiais usados na confeção, como botões, fechos ou varetas.  
  **Atributos:** `id_material`, `nome`, `tipo`, `custo_unitario`, `quantidade_stock`, `unidade_medida`.

- **Fornecedor**  
  Representa as entidades que fornecem tecidos e materiais à loja.  
  **Atributos:** `id_fornecedor`, `nome`, `telefone`, `email`, `morada`.

- **Compra**  
  Representa a compra de produtos prontos feita por um cliente.  
  **Atributos:** `id_compra`, `data_compra`, `valor_total`, `metodo_pagamento`.

- **Detalhe_Compra**  
  Representa cada linha de uma compra, isto é, cada produto pronto incluído na compra.  
  **Atributos:** `id_detalhes`, `quantidade`, `preco_unitario`.

- **Produto_Pronto**  
  Representa peças já prontas para venda.  
  **Atributos:** `id_produto_pronto`, `codigo`, `nome`, `tamanho`, `cor`, `preco`, `quantidade_stock`.

- **Categoria_Produto_Pronto**  
  Permite classificar os produtos prontos por categoria.  
  **Atributos:** `id_categoria_produto`, `nome_categoria`.

- **Ajuste**  
  Representa alterações feitas a produtos após a compra.  
  **Atributos:** `id_ajuste`, `descricao`, `data_ajuste`, `custo`, `estado`.

### 2. Relações principais

A análise do funcionamento da loja permite identificar as seguintes relações entre entidades:

- Um **cliente** pode fazer **várias encomendas**.  
  Uma **encomenda** está associada a **um cliente**.

- Um **cliente** pode realizar **várias compras**.  
  Uma **compra** está associada a **um cliente**.

- Um **cliente** tem associadas as suas **medidas gerais**.  
  Um registo de **medida de cliente** pertence a **um cliente**.

- Uma **encomenda** pode conter **vários itens de encomenda**.  
  Um **item de encomenda** pertence a **uma encomenda**.

- Uma **encomenda** pode receber **vários pagamentos**.  
  Um **pagamento de encomenda** está associado a **uma encomenda**.

- Um **item de encomenda** pode ter um conjunto próprio de **medidas específicas**.  
  Um registo de **medida de item de encomenda** pertence a **um item de encomenda**.

- Um **item de encomenda** pode ser baseado num **modelo**, sendo essa associação opcional.  
  Um **modelo** pode servir de base a **vários itens de encomenda**.

- Um **item de encomenda** pode usar **vários tecidos**, sendo necessário registar os **metros usados**.  
  Um **tecido** pode ser usado em **vários itens de encomenda**.

- Um **item de encomenda** pode usar **vários materiais**, sendo necessário registar a **quantidade usada**.  
  Um **material** pode ser usado em **vários itens de encomenda**.

- Um **fornecedor** pode fornecer **vários tecidos**.  
  Um **tecido** é fornecido por **um fornecedor**.

- Um **fornecedor** pode fornecer **vários materiais**.  
  Um **material** é fornecido por **um fornecedor**.

- Uma **compra** pode conter **vários detalhes de compra**.  
  Um **detalhe de compra** pertence a **uma compra**.

- Cada **detalhe de compra** corresponde a **um produto pronto**.  
  Um **produto pronto** pode aparecer em **vários detalhes de compra**.

- Cada **produto pronto** pertence a **uma categoria**.  
  Uma **categoria de produto pronto** pode classificar **vários produtos prontos**.

- Um **detalhe de compra** pode necessitar de **vários ajustes**.  
  Um **ajuste** está associado a **um detalhe de compra**.

- Um **ajuste** permite acompanhar o seu **estado** e **custo** no contexto do produto comprado.  
  O **produto comprado**, através do **detalhe de compra**, pode ter **ajustes associados**.

### 3. Regras de negócio e restrições

Para além das entidades e relações, foram identificadas algumas restrições importantes do domínio no nosso projeto:

- Cada entidade deve ser identificada de forma única pelo seu respetivo identificador.
- Os valores monetários, como `valor_total`, `valor_pago`, `valor_em_falta`, `preco`, `custo_producao` e `custo`, não devem assumir valores negativos.
- As quantidades em stock e as quantidades usadas também não devem assumir valores negativos.
- Uma **encomenda** deve estar associada a um **cliente** existente.
- Um **pagamento de encomenda** deve estar sempre associado a uma **encomenda** existente.
- Um **item de encomenda** deve pertencer a uma **encomenda**.
- Um **detalhe de compra** deve pertencer a uma **compra** e referir um **produto pronto** existente.
- Um **produto pronto** deve pertencer a uma **categoria**.
- A data real de entrega de uma encomenda não deve ser anterior à data da encomenda.
- O valor em falta de uma encomenda deve corresponder à diferença entre o valor total e o valor já pago.

### 4. Filtragem da informação

Durante a análise foi feita uma filtragem da informação recolhida, mantendo apenas os dados relevantes para a base de dados. Assim, foram considerados apenas os elementos necessários para:

- identificar clientes, fornecedores, produtos, materiais e encomendas;
- acompanhar compras e encomendas;
- controlar pagamentos;
- controlar stock de tecidos, materiais e produtos prontos;
- registar ajustes e personalizações.


### 5. Aspetos a clarificar com o cliente

Após uma análise dos requisitos, ainda existem alguns pontos a clarificar com o cliente. Entre eles:

- se um **cliente** pode ter apenas um registo de medidas ou vários registos ao longo do tempo;
- se um **item de encomenda** pode sempre existir sem **modelo**, ou se em alguns casos o modelo deve ser obrigatório;
- se um **ajuste** deve existir apenas para produtos comprados ou também para peças feitas por medida;
- se o atributo `valor_em_falta` deve ser guardado diretamente ou apenas calculado a partir dos pagamentos;
- se cada **tecido** e cada **material** têm exatamente um fornecedor ou se podem existir vários fornecedores para o mesmo artigo.

## DER


![DER Diagram!](der.png "AnImage")

## ER

![ER Diagram!](er.jpg "AnImage")