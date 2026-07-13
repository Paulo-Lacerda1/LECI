<!-- This Heuristic Evaluation Workbook replicates the one proposed by the 
Nielsen Norman Group available at: https://media.nngroup.com/media/articles/attachments/Heuristic_Evaluation_Workbook_-_Nielsen_Norman_Group.pdf
-->

**Evaluator**: Martim Gomes
**Date**: 01/03/2026
**Product**: Wanderlog


Severity Scale adopted: [[severity_scale_heuristic_evaluation]]
Summary of each usability heuristic: [here](https://media.nngroup.com/media/articles/attachments/Heuristic_Summary1-compressed.pdf)

# 1 Visibility of System Status
>	The design should always keep users informed about what is going on, through appropriate feedback within a reasonable amount of time. 
>	- Does the design clearly communicate its state?
>	- Is feedback presented quickly after user actions?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Quando se adiciona um novo lugar ao roteiro, a indicação de que foi adicionado com sucesso é pouco visível, deixando o utilizador na dúvida se o clique funcionou | 2 | Mostrar uma notificação temporária clara no ecrã a dizer "Local adicionado com sucesso ao Dia X". |
| O valor total das despesas ultrapassa o orçamento definido, mas o sistema não altera a cor da barra de progresso para vermelho nem apresenta nenhum alerta de que o limite foi excedido. | 2 | Mudar a cor da barra de progresso e do texto para vermelho, acompanhado de um aviso, sempre que as despesas ultrapassarem o valor do orçamento estipulado. |

# 2 Match Between System and The Real World
>	The design should speak the users' language. Use words, phrases, and concepts familiar to the user, rather than internal jargon. Follow real-world conventions, making information appear in a natural and logical order. 
>	- Will user be familiar with the terminology used in the design? 
>	- Do the design’s controls follow real-world conventions?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Em alguns locais (ex: no mapa do Japão), o sistema regista o nome do lugar usando "Plus Codes" (ex: 676J+H5 Nagawa) em vez do nome comum e percetível para humanos, quebrando a familiaridade com o mundo real. | 2 | Traduzir automaticamente as coordenadas ou Plus Codes para o nome da localidade, rua ou ponto de interesse mais próximo. |
| O menu superior usa a terminologia "Diário de viagem" para o que, na verdade, é a "Rota e lugares visitados num mapa". Para o utilizador comum, a palavra "Diário" remete para notas escritas e não para um mapa geográfico. | 2 | Alterar o nome para "Visualização de Mapa" ou "Rota no Mapa". |

# 3 User Control and Freedom
>	Users often perform actions by mistake. They need a clearly marked "emergency exit" to leave the unwanted action without having to go through an extended process. 
>	- Does the design allow users to go back a step in the process? 
>	- Are exit links easily discoverable? 
>	- Can users easily cancel an action? 
>	- Is Undo and Redo supported?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| A janela de chat "Wanderlog support" sobrepõe-se à interface, mas não possui um botão clássico e claro de "X" para fechar/cancelar, apresentando apenas um pequeno ícone de setas para encolher a janela, o que pode prender o utilizador inexperiente. | 2 | Adicionar um botão "X" bem visível no canto superior direito do pop-up de suporte. |

# 4 Consistency and Standards
>	Users should not have to wonder whether different words, situations, or actions mean the same thing. Follow platform and industry conventions. 
>	- Does the design follow industry conventions? 
>	- Are visual treatments used consistently throughout the design?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Existe inconsistência nas cores dos botões | 2 | Definir e utilizar uma única cor de destaque para as ações primárias em toda a plataforma, facilitando o reconhecimento por parte do utilizador. |

# 5 Error Prevention
>	Good error messages are important, but the best designs carefully prevent problems from occurring in the first place. Either eliminate error-prone conditions, or check for them and present users with a confirmation option before they commit to the action. 
>	- Does the design prevent slips by using helpful constraints? 
>	- Does the design warn users before they perform risky actions?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Na secção "Notas", o campo livre ("Escreva ou cole qualquer coisa aqui") permite inserir dados sem qualquer estruturação, o que pode levar a perdas de formatação ou links quebrados se o utilizador colar muito texto. | 2 | Incluir ferramentas básicas de formatação de texto (negrito, listas) diretamente visíveis no campo de notas para prevenir erros de legibilidade. |
| O sistema calcula uma rota de viagem utilizando o ícone de condução (carro) entre Paris (França) e o Japão, não prevenindo o erro geográfico de tentar conduzir entre locais separados por oceanos e continentes extensos. | 3 | Implementar restrições lógicas no cálculo de rotas: se a viagem for intercontinental, o sistema deve bloquear o modo "carro" por defeito e sugerir/exigir a adição de um voo. |

# 6 Recognition Rather than Recall
>	Minimize the user's memory load by making elements, actions, and options visible. The user should not have to remember information from one part of the interface to another. Information required to use the design (e.g. field labels or menu items) should be visible or easily retrievable when needed. 
>	- Does the design keep important information visible, so that users do not have to memorize it? 
>	- Does the design offer help in-context?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Na pesquisa de hotéis, ao aplicar filtros no menu "Todos", o sistema exibe apenas um pequeno distintivo com o número de filtros ativos (ex: "2"). O utilizador não consegue ver imediatamente quais os filtros específicos que estão aplicados (ex: Wi-fi, pequeno-almoço), sendo forçado a memorizá-los ou a clicar novamente para verificar. | 2 | Mostrar "tags" com o nome dos filtros ativos (ex: [Com Wi-Fi ✕] [Piscina ✕]) diretamente na barra de filtros, permitindo reconhecê-los e removê-los com um só clique. |

# 7 Flexibility and Efficiency of Use
>	Shortcuts — hidden from novice users — may speed up the interaction for the expert user such that the design can cater to both inexperienced and experienced users. Allow users to tailor frequent actions. 
>	- Does the design provide accelerators like keyboard shortcuts and touch gestures? 
>	- Is content and funtionality personalized or customized for individual users?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| A navegação exige muito scroll vertical no painel central para passar da "Visão Geral" para "Lugares a visitar" ou "Orçamento", tornando a navegação ineficiente em viagens longas. | 3 | Permitir recolher (collapse) blocos inteiros de dias ou usar separadores (tabs) horizontais no topo para alternar rapidamente entre Roteiro, Orçamento e Notas. |

# 8 Aesthetic and Minimalist Design
>	Interfaces should not contain information that is irrelevant or rarely needed. Every extra unit of information in an interface competes with the relevant units of information and diminishes their relative visibility. 
>	- Is the visual design and content focused on the essentials? 
>	- Have all distracting, unnescessary elements been removed?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| A interface está sobrecarregada de informação. O mapa ocupa quase 50% do ecrã mesmo quando o utilizador está focado no orçamento ou em editar notas, o que distrai e rouba espaço útil. | 3 | Adicionar a funcionalidade de esconder ou minimizar o mapa lateral quando não for estritamente necessário (ex: ao preencher despesas). |
| Banners de publicidade muito grandes e intrusivos (ex: banner roxo de "Precisa de lugar para ficar" ou banner laranja do Airbnb na home page) competem demasiadamente com o conteúdo principal da viagem do utilizador. | 3 | Reduzir o tamanho dos banners promocionais e integrá-los de forma mais subtil no final do roteiro diário. |

# 9 Help Users Recognize, Diagnose, and Recover from Errors
>	Error messages should be expressed in plain language (no error codes), precisely indicate the problem, and constructively suggest a solution. 
>	- Does the design use traditional error message visuals, like bold, red text? 
>	- Does the design offer a solution that solves the error immediately?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Em vez de alertar claramente o utilizador de que a rota terrestre selecionada (Paris -> Japão) é logicamente impossível ou inadequada, o sistema simplesmente exibe a rota de "6 dias 11 horas" e "12.791 km", falhando em ajudar o utilizador a reconhecer e diagnosticar a impossibilidade do plano. | 3 | Apresentar um aviso claro: "Rota de carro indisponível/impraticável para este trajeto. Por favor, adicione um meio de transporte aéreo/marítimo". |

# 10 Help and Documentation
>	It’s best if the system doesn’t need any additional explanation. However, it may be necessary to provide documentation to help users understand how to complete their tasks. 
>	- Is help documentation easy to search? 
>	- Is help provided in context right at the moment when the user requires it?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| A janela de chat de suporte informa que a equipa responde "no prazo de um dia útil", não fornecendo documentação imediata nem uma barra de pesquisa de FAQs para o utilizador tentar resolver a sua dúvida no momento. | 3 | Incluir uma barra de pesquisa para os artigos do centro de ajuda (Help Center) diretamente dentro da janela do chat, promovendo a autoajuda antes do envio da mensagem. |
