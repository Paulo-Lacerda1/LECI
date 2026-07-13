# CHANGELOG.md

## 1.2.0 (2026-05-31)

### Dashboard (Painel de Gestão)
- **Criação do Painel de Bordo:** Implementação do ecrã inicial (`FrmDashboard`) com KPIs vitais: Faturação do Mês, Encomendas Pendentes e Total de Stock.
- **Gráfico de Vendas Dinâmico:** Integração de um `Chart` que compara visualmente as receitas geradas por Pronto-a-Vestir vs. Encomendas Por Medida.
- **Alertas de Rutura de Stock:** Nova grelha inteligente (`dgvAlertasStock`) que destaca os 5 produtos críticos com stock <= 3.
- **Filtro de Faturação Interativo:** Adição de interface (Checkbox e DateTimePickers) para permitir ao utilizador consultar a faturação num intervalo de datas personalizado, alimentado pela nova `spObterFaturacaoPorIntervalo`.
- **Correção da Lógica de Faturação:** A "Faturação do Mês" passou a ser calculada com base na data de fecho/entrega da encomenda (`data_real_entrega` ou `data_pronto`) em vez da data de criação do pedido.

### Encomendas e UI
- **Desbloqueio da Máquina de Estados:** Adição do botão "Em Produção" na grelha de encomendas. Esta interface crítica permite transitar os pedidos do estado Pendente, disparando os gatilhos da base de dados que descontam os materiais do inventário físico.

### Arquitetura e Débito Técnico
- **Eliminação de SQL Inline:** Migração massiva de consultas SQL embebidas no código C# para a base de dados. Criação de 28 novas *Stored Procedures* e múltiplas *Views* para garantir uma Camada de Dados hermética e profissional.
- **Auditoria e Defensibilidade Académica:** - Simplificação da `spObterResumoEliminacaoCliente`, substituindo subconsultas correlacionadas profundas por uma lógica sequencial mais natural e fácil de explicar na defesa oral.
  - Remoção de lógicas transacionais puramente empresariais (como a hint `WITH (UPDLOCK)` e re-lançamentos puros `THROW`) da `spInserirTecido`, substituindo-as por `RAISERROR` clássico para adequação ao escopo de uma cadeira do 3º ano.

---

## 1.1.0 (2026-05-31)

### Módulo de Inventário (Tecidos e Materiais)
- **Gestão de Consumíveis:** Implementação das tabelas e interface para controlo de Tecidos (metros) e Materiais (unidades).
- **Rastreabilidade de Stock:** Criação da tabela `Movimentos_Stock` para registar o histórico completo de entradas, saídas e ajustes manuais, garantindo auditoria total ao inventário.
- **Blindagem de Dados:** Aplicação de restrições `CHECK` e `NOT NULL` nas bases de dados para impedir a existência de stocks negativos por falha humana.

### Módulo de Pronto-a-Vestir
- **Gestão de Produtos:** Interface completa (CRUD) operando diretamente na grelha para criação, atualização e eliminação de artigos de Pronto-a-Vestir.
- **Painel de Inputs Dinâmico:** Dropdowns populados automaticamente com os valores existentes (Tamanho, Cor) para acelerar a inserção de dados.
- **Sistema de Alertas (Semáforo):** - Fundo vermelho e símbolo `⚠` para ruturas de stock (Stock = 0).
  - Fundo amarelo para aviso de stock baixo (Stock entre 1 e 3).
- **Segurança de Eliminação:** Interceção de integridade referencial (Foreign Keys); o sistema impede a eliminação de produtos que já possuam histórico de vendas, mostrando um aviso limpo ao utilizador em vez de um erro de sistema.

### Máquina de Estados e Encomendas
- **Automação de Consumo:** A transição de uma encomenda para o estado "Em Produção" desconta agora automaticamente os materiais e tecidos associados do inventário geral.
- **Estorno Automático:** A transição para o estado "Cancelada" devolve automaticamente as quantidades não utilizadas ao stock.
- **Proteção Visual (UI):** Botões de transição de estado são ativados/desativados visualmente consoante a lógica de negócio (ex: impossível cancelar uma encomenda já "Entregue" ou marcar como "Em Produção" uma encomenda "Pronta").
- **Bloqueio Defensivo:** Triggers na base de dados bloqueiam qualquer alteração aos materiais de uma encomenda assim que ela deixa de estar no estado "Pendente".

### Arquitetura Financeira (Custos e Lucros)
- **Congelamento Histórico de Preços:** Adição da coluna `preco_cobrado` nas tabelas de ligação `NM.ItemEnc_Tecido` e `NM.ItemEnc_Material`. O preço do fornecedor é capturado no momento exato em que o material é usado na encomenda, impedindo que flutuações futuras de mercado afetem a contabilidade passada.
- **Separação de Mão de Obra e Materiais:** Criação do campo `custo_mao_obra` para o utilizador introduzir explicitamente o valor do seu trabalho, separando-o do Preço Final ao Cliente e do Custo dos Materiais.
- **Custo de Produção em Tempo Real:** A função `fnCalcularCustoRealItem` foi otimizada para calcular o custo total do item lendo exclusivamente o histórico financeiro.
- **Simulação Visual de Custos:** O formulário de nova encomenda calcula visualmente os custos à medida que o utilizador insere tecidos e materiais, servindo como rascunho em memória antes da gravação definitiva.


## 1.0.0 (2026-05-30)

Security:

  - Blindagem do inventário contra stock negativo aplicando restrições CHECK e NOT NULL nas tabelas Tecido, Material e Produto_Pronto
  - Bloqueio defensivo contra modificações em encomendas fora do estado Pendente

Features:

  - Estruturação do histórico de movimentos de stock através da tabela Movimentos_Stock
  - Implementação da Máquina de Estados para desconto automático de stock em transição para 'Em Produção' e estorno em 'Cancelada'
  - Criação de tabelas de associação de consumíveis (Tecido e Material) para o módulo de Ajustes
  - Automatização completa do Custo de Produção via UDF fnCalcularCustoRealItem acoplada dinamicamente à view vwItensEncomenda

Fix:

  - Stored Procedures spCriarItemEncomenda e spAtualizarItemEncomenda com remoção do parâmetro manual de custo
