[Back to main Logbook Page](../hci_logbook.md)

---
# B. Stage 1 - Context Definition


# B.1. Competitor Identification
>	The competitor analysis will entail an identification of all competitors, with brief descriptions and a collection of the look and feel of their solutions, e.g., with screenshots, etc. It will also include a detailed analysis of the competitor deemed the best or more representative. It ends with a summary of the main findings including an HCI SWOT analysis



## B.1a. Competitors


| **Competitor**    | **Description**                             | Information repository              |
| ----------------- | ------------------------------------------- | ----------------------------------- |
| Wanderlog    | Plataforma de planeamento de viagens colaborativa que permite criar roteiros, visualizar locais num mapa interativo e gerir as despesas da viagem apenas num único local.        | [[Competitor Analysis Wanderlog]] |
| TripIt | Sistema de gestão de itinerários de viagem que cria de forma automática planos de viagem através da leitura de e-mails de reservas e também organiza as atividades de forma cronológica. |    [[Competitor Analysis TripIt]]                                 |
| Google Sheets + WhatsApp                |  Combinação de ferramentas usualmente utilizada por grupos para organizar viagens através de folhas de cálculo partilhadas e também de comunicação por mensagens.                                           | [[Competitor Analysis GoogleSheets_WhatsApp]]                                    |




## B.1b. Detailed Competitor Analysis
>	Choose the most notable competitor and do a more thorough analysis of their interactive solution


### - Heuristic Evaluation

#### Method
A avaliação heurística foi realizada com base nas 10 heurísticas de usabilidade propostas por Nielsen.

Dois avaliadores analisaram de forma independente a interface da plataforma Wanderlog e um a interface da plataforma TripIt. Cada avaliador explorou as principais funcionalidades do sistema e identificou problemas de usabilidade, indicando a descrição do problema, a sua gravidade e uma possível recomendação de melhoria.

Foi utilizada uma escala de severidade de 0 a 4, onde:
0 – Não é considerado um problema de usabilidade  
1 – Problema cosmético  
2 – Problema menor de usabilidade  
3 – Problema grave de usabilidade  
4 – Catástrofe de usabilidade  

Após as avaliações individuais, os avaliadores compararam os resultados obtidos e construíram uma tabela de consenso. Sempre que um avaliador não tivesse identificado um problema encontrado por outro, esse problema era analisado novamente e era atribuída uma classificação de severidade adicional.


#### Individual Evaluations
<!-- For the individual heuristic evaluations by each member of the group, you can use the templates below, grouping problems by heuristic OR each evaluator can have a table listing all the detected problems with the number of the violated heuristics on the second column. Whichever your choice, you should have a list of problems, the severity, and a recommendation to mitigate it -->



- [expert1_heuristic_evaluation_workbook](heuristic_evaluations/expert1_heuristic_evaluation_workbook.md)

- [expert2_heuristic_evaluation_workbook](heuristic_evaluations/expert2_heuristic_evaluation_workbook.md)

- [expert3_heuristic_evaluation_workbook](heuristic_evaluations/expert3_heuristic_evaluation_workbook.md)


#### Consensus

>	After the individual analysis by each expert, all results should be gathered in a consensus table. If an expert has not found any of the problems found by other experts, they should analyse it, at this point, and give it a severity.

| **Issue**       | **Expert 1** | Expert 2 | Expert 3 | Recommendations                             |
| --------------- | ------------ | -------- | -------- | ------------------------------------------- |
| Interface com demasiada informação e elementos visuais (mapa, painéis e banners) | 3            | 3        | -        |  Reduzir a sobrecarga visual e permitir minimizar o mapa quando não está a ser utilizado. |
| Falta de feedback claro quando um local é adicionado ao roteiro   | 3            | 2        | -        | Apresentar uma notificação visível como "Local adicionado com sucesso".                    |
| Fraco suporte para tomada de decisões em grupo            |   3           |    -      |   -       |     Implementar mecanismos de votação ou consenso entre os utilizadores.                                        |
| Falta de confirmação antes de eliminar elementos importantes do roteiro           |   2           |    -      |   -       |     Adicionar janelas de confirmação antes de eliminar informação.                         |
| Presença de anúncios dentro do itinerário que interferem com a leitura | - | 3 | 3 | Reposicionar os anúncios fora do fluxo principal da viagem.

---
### - Cognitive Walkthrough

#### Method
Foi realizado um Cognitive Walkthrough para avaliar a facilidade com que um novo utilizador consegue realizar tarefas comuns na plataforma Wanderlog.

Os avaliadores simularam a interação de um utilizador iniciante com o sistema e analisaram cada passo necessário para completar determinadas tarefas. Em cada passo foi avaliado se o utilizador conseguiria perceber qual a ação correta a realizar, se conseguiria identificar o elemento de interface apropriado e se o sistema fornecia feedback adequado sobre o progresso da tarefa.

#### Task Selection and Task Analysis

Foram selecionadas duas tarefas representativas do uso típico da aplicação:

1. Criar um roteiro de viagem e adicionar locais a visitar.
2. Adicionar despesas ao orçamento da viagem.

Estas tarefas foram escolhidas por representarem funcionalidades centrais e extremamente importantes da aplicação e envolverem várias etapas de interação com a interface.


| Task                        | Subtasks                               |
| --------------------------- | -------------------------------------- |
| **1. Criar um roteiro de viagem e adicionar locais a visitar.** | Criar uma nova viagem     |
|                             | Pesquisar um destino ou local |
|                             | Adicionar o local a um dia específico do roteiro      |
|                             | Verificar se o local aparece no roteiro e no mapa                  |


| Task                          | Subtasks                                |
| ----------------------------- | --------------------------------------- |
| **2. Adicionar despesas ao orçamento da viagem** | Aceder à secção de orçamento |
|                               |  Criar uma nova despesa             |
|                               | Inserir descrição e valor             |
|                               | Verificar se a despesa aparece no resumo do orçamento        |


#### Results

Task: Criar um roteiro de viagem e adicionar locais a visitar

| Step # | Task/Action to Perform | Will User Know What to do at this step? (Yes/No) | Notes | If the user does the right thing, will they know it is progressing towards goal? (Yes/No) | Notes | Is Action Successful? (Yes/No) | Suggestions for Improvement |     |
| ------ | ---------------------- | ------------------------------------------------ | ----- | ----------------------------------------------------------------------------------------- | ----- | ------------------------------ | --------------------------- | --- |
| 1      | Criar uma nova viagem   | Yes                                         |  O botão "Create Trip" está visível na interface inicial     | Yes                                                                                  |  O sistema abre a página de configuração da viagem     | Yes                       | Nenhuma melhoria necessária              |     |
| 2      | Pesquisar um destino ou local   | Yes                                         |  Existe uma barra de pesquisa clara para procurar locais     | Yes                                                                                  |  Os resultados aparecem imediatamente no mapa e na lista     | Yes                       | Melhorar a hierarquia visual da barra de pesquisa              |     |
| 3      | Selecionar um local da lista de resultados   | Yes                                         |  Os resultados são clicáveis e relativamente claros     | Yes                                                                                  | O local aparece destacado no mapa      | Yes                      | Tornar o botão de seleção mais evidente              |     |
| 4    |  Adicionar o local ao dia específico do roteiro        | No                                         | O botão de adicionar ao roteiro não é imediatamente evidente para novos utilizadores      | Yes                                                                                  | Após adicionar, o local aparece no dia escolhido      | Yes                       | Tornar o botão "Add to itinerary" mais visível               |     |
|5 |Confirmar que o local foi adicionado ao roteiro | No | O feedback visual é muito discreto | No | O utilizador pode não perceber imediatamente que a ação foi concluída | Yes | Mostrar uma notificação clara como "Local adicionado com sucesso" |




Task: Adicionar despesas ao orçamento da viagem

| Step # | Task/Action to Perform | Will User Know What to do at this step? (Yes/No) | Notes | If the user does the right thing, will they know it is progressing towards goal? (Yes/No) | Notes | Is Action Successful? (Yes/No) | Suggestions for Improvement |     |
| ------ | ---------------------- | ------------------------------------------------ | ----- | ----------------------------------------------------------------------------------------- | ----- | ------------------------------ | --------------------------- | --- |
| 1      |  Aceder à secção de orçamento da viagem   | Yes                                         | A secção de orçamento está disponível no menu da viagem      | Yes                                                                                  | O utilizador é redirecionado para a página de despesas      | Yes                       | Tornar a secção mais destacada na navegação             |     |
| 2      | Criar uma nova despesa   | Yes                                         | Existe um botão para adicionar nova despesa      | Yes                                                                                  | Abre um formulário para inserir informação      | Yes                       | Melhorar a visibilidade do botão "Adicionar despesa"              |     |
| 3      |  Inserir descrição e valor da despesa   | Yes                                         | O formulário é simples e fácil de compreender      | Yes                                                                                  | O sistema mostra a despesa adicionada à lista       | Yes                      | Adicionar sugestões automáticas de categorias             |     |
| 4   | Verificar se a despesa aparece no resumo do orçamento        | Yes                                         | A despesa aparece listada no orçamento total      | Yes                                                                                  |  O valor total é atualizado automaticamente      | Yes                       |  Destacar melhor o impacto da nova despesa no orçamento total             |     |


## B.1c. Overall Analysis

A análise dos competidores permitiu identificar várias abordagens já existentes para o planeamento de viagens.

Plataformas como o Wanderlog oferecem ferramentas visuais interessantes e integração com mapas, permitindo aos utilizadores visualizar facilmente os locais incluídos no seu roteiro. No entanto, a interface apresenta frequentemente uma grande densidade de informação, o que pode dificultar a utilização por parte de novos utilizadores.

O TripIt destaca-se pela leitura de e-mails de reservas, reduzindo de forma significativa o esforço necessário para organizar informação logística. Contudo, a aplicação é menos adequada para a fase inicial de planeamento e brainstorming, especialmente em viagens organizadas em grupo.

Por outro lado, muitos utilizadores continuam a recorrer a soluções mais informais como Google Sheets e WhatsApp. Apesar de serem ferramentas familiares e flexíveis, exigem um  esforço manual muito grande e conduzem frequentemente à fragmentação da informação entre diferentes plataformas.

No geral, observa-se que as soluções existentes focam-se principalmente na organização logística ou na visualização de roteiros, mas oferecem um suporte reduzido para a tomada de decisões colaborativas durante o planeamento de viagens em grupo.

Estas limitações identificadas nos sistemas analisados evidenciam assim a oportunidade de desenvolver uma solução que integre o planeamento visual da viagem com mecanismos de tomada de decisão colaborativa entre os utilizadores, reduzindo simultaneamente a fragmentação da informação entre diferentes ferramentas.

---

# B.2. Users
>	For the users, there are two goals: 1) understand the current status of users in the domain you are addressing. How do they manage, what are the main tasks they do, if they use some tool for the purpose, what are current challenges, what might be improved, what might be new features, ...


## B.2a. Method

Para compreender melhor como os utilizadores atualmente organizam viagens e quais as dificuldades encontradas, foram realizadas entrevistas com potenciais utilizadores da aplicação.

Os participantes selecionados foram principalmente estudantes universitários que costumam viajar em grupo com amigos. O objetivo das entrevistas foi então compreender:

- Como os utilizadores organizam atualmente viagens em grupo;
- Que ferramentas utilizam para gerir informação da viagem;
- Quais são os principais problemas encontrados quando estão perante o processo de planeamento;
- Que funcionalidades gostariam de ter numa nova solução.

Nas entrevistas os participantes descreveram livremente as suas experiências e dificuldades.
## B.2b. Results

>	This section tracks all informal user interviews, summarizing key insights and linking to detailed notes for each session. 

### Interview List 
| Date       | Participant / Role | Key Insights                                                    | Link to Notes                |     |
| ---------- | ------------------ | --------------------------------------------------------------- | ---------------------------- | --- |
| 02-03-2026 | Jorge Marques / student      | A escolha do destino e chegar a consenso num grupo é difícil. Muitas ideias acabam por se perder nas conversas do WhatsApp. | [📄 Notes](interviews/interview-Jorge.md) |     |
| 09-03-2026 | Gabriel Marta / student      | O mais difícil é escolher o destino, tendo em conta as atrações disponíveis no mesmo, e os gastos que vão ser necessários. | [📄 Notes](interviews/interview-Gabriel.md) |     |


### Common Themes & Patterns 

- **Recurring Problems:** 
	-  Dificuldade em chegar a consenso quando várias pessoas planeiam a mesma viagem.
	- Informação importante perde-se facilmente em conversas longas no WhatsApp.
	- Necessidade de alternar entre várias ferramentas diferentes.
- **Frequently Used Tools:** 
	- WhatsApp
	- Booking.com
	- Google Sheets
- **Desired Features / Solutions:** 
	- Um sistema que permita votar ou escolher locais em grupo.
	- Um local centralizado para organizar toda a informação da viagem.
	- Uma forma mais visual e simples de comparar opções de destinos ou atividades.
- --- 



---
[Back to main Logbook Page](../hci_logbook.md)

---
