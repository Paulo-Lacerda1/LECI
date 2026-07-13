<!-- This Heuristic Evaluation Workbook replicates the one proposed by the 
Nielsen Norman Group available at: https://media.nngroup.com/media/articles/attachments/Heuristic_Evaluation_Workbook_-_Nielsen_Norman_Group.pdf
-->

**Evaluator**: Fernando De Almeida Ferreira
**Date**: 02-03-2026
**Product**: Wanderlog

Severity Scale adopted: [[severity_scale_heuristic_evaluation]]
Summary of each usability heuristic: [here](https://media.nngroup.com/media/articles/attachments/Heuristic_Summary1-compressed.pdf)

# 1 Visibility of System Status
>	The design should always keep users informed about what is going on, through appropriate feedback within a reasonable amount of time. 
>	- Does the design clearly communicate its state?
>	- Is feedback presented quickly after user actions?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Após adicionar locais ao roteiro, o sistema não apresenta uma notificação clara e imediata de que a ação foi concluída com sucesso e que o local foi adicionado.| 3            | Adicionar uma mensagem visível e temporária a notificar por exemplo que o local em questão foi adicionado com sucesso à rota.               |
| O indicador "salvo" no canto superior esquerdo é muito discreto e pode passar despercebido, fazendo com que o utilizador não tenha a certeza que o que está a adicionar está a ser guardado.   | 2            | Aparecer uma notificação a dizer por exemplo "alterações efetuadas com sucesso" ou mudar a cor consoante a alteração de estado.               |
| O sistema não apresenta de forma clara o estado geral do planeamento, obrigando o utilizador a verificar manualmente cada secção.    | 2            | Na secção onde mostra o roteiro e os dias poderia por exemplo aparecer a vermelho quando o dia tivesse cheio ou aparecer com um cadeado de modo a ser perceptivel que não existem mais atividades disponiveis para adicionar aquele dia.               |

# 2 Match Between System and The Real World
>	The design should speak the users' language. Use words, phrases, and concepts familiar to the user, rather than internal jargon. Follow real-world conventions, making information appear in a natural and logical order. 
>	- Will user be familiar with the terminology used in the design? 
>	- Do the design’s controls follow real-world conventions?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| O sistema foca-se mais na adição de locais do que na experiência real de decisão em grupo, que no mundo real envolve discussão e um certo consenso. | 3            | Adicionar mecanismos de votação ou consenso para aproximar a ferramenta da dinâmica real de grupo.               |
| A organização por dias é clara, mas a lógica de horários não simula totalmente a experiência real de planeamento (manhã/tarde/noite não estão visualmente muito diferenciadas).   | 2            | Divisão por períodos do dia.                |

# 3 User Control and Freedom
>	Users often perform actions by mistake. They need a clearly marked "emergency exit" to leave the unwanted action without having to go through an extended process. 
>	- Does the design allow users to go back a step in the process? 
>	- Are exit links easily discoverable? 
>	- Can users easily cancel an action? 
>	- Is Undo and Redo supported?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Não existe confirmação clara antes de eliminar elementos importantes do roteiro. | 2            | Adicionar janela de confirmação de eliminar algo.                |
| A divisão entre mapa e lista pode gerar alguma confusão, não sendo claro como voltar rapidamente ao estado anterior.   | 1            | Melhorar opções de retorno.               |

# 4 Consistency and Standards
>	Users should not have to wonder whether different words, situations, or actions mean the same thing. Follow platform and industry conventions. 
>	- Does the design follow industry conventions? 
>	- Are visual treatments used consistently throughout the design?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Alguns elementos clicáveis não têm indicação visual clara de que são interativos. | 2            | Melhorar feedback visual (por exemplo sublinhado, mudança de cor).               |

# 5 Error Prevention
>	Good error messages are important, but the best designs carefully prevent problems from occurring in the first place. Either eliminate error-prone conditions, or check for them and present users with a confirmation option before they commit to the action. 
>	- Does the design prevent slips by using helpful constraints? 
>	- Does the design warn users before they perform risky actions?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Não existe aviso claro antes de eliminar dias ou algumas atividades. | 3            | Inserir confirmação antes de fazer ações potencialmente prejudiciais.                |
| Quando se move um local a visitar para uma nova secção aparece uma breve notificação a indicar essa mudança   | 0            |                |
# 6 Recognition Rather than Recall
>	Minimize the user's memory load by making elements, actions, and options visible. The user should not have to remember information from one part of the interface to another. Information required to use the design (e.g. field labels or menu items) should be visible or easily retrievable when needed. 
>	- Does the design keep important information visible, so that users do not have to memorize it? 
>	- Does the design offer help in-context?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| O histórico de alterações não é claramente visível, obrigando o utilizador a memorizar quem fez o quê. | 3            | Incluir painel de histórico de atividades recente.               |
| Algumas funcionalidades não possuem ajuda contextual imediata.   | 2            | Inserir tooltips explicativos ao passar o cursor apenas em alguns casos.                |
# 7 Flexibility and Efficiency of Use
>	Shortcuts — hidden from novice users — may speed up the interaction for the expert user such that the design can cater to both inexperienced and experienced users. Allow users to tailor frequent actions. 
>	- Does the design provide accelerators like keyboard shortcuts and touch gestures? 
>	- Is content and funtionality personalized or customized for individual users?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Não há atalhos de teclado visíveis para utilizadores avançados. | 2            | Disponibilizar shortcuts opcionais para ações frequentes.               |
| Não diferencia claramente viagem individual vs grupo.   | 3            | Permitir modos distintos de planeamento.               |
# 8 Aesthetic and Minimalist Design
>	Interfaces should not contain information that is irrelevant or rarely needed. Every extra unit of information in an interface competes with the relevant units of information and diminishes their relative visibility. 
>	- Is the visual design and content focused on the essentials? 
>	- Have all distracting, unnescessary elements been removed?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| A interface apresenta elevada densidade de informação, podendo sobrecarregar utilizadores novos. | 3            | Simplificar a hierarquia visual e reduzir elementos simultâneos.               |
| Existem várias opções laterais visíveis mesmo quando não são necessárias.   | 3            | Reduzir a sobrecarga de opço~es nas laterais focando apenas no mais importante.               |
| O mapa e o painel competem visualmente pela atenção do utilizador.   | 2            | Melhorar contraste e hierarquia visual.          |
# 9 Help Users Recognize, Diagnose, and Recover from Errors
>	Error messages should be expressed in plain language (no error codes), precisely indicate the problem, and constructively suggest a solution. 
>	- Does the design use traditional error message visuals, like bold, red text? 
>	- Does the design offer a solution that solves the error immediately?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Não há explicação clara quando uma ação falha por questões técnicas. | 2            |  Mostrar mensagens simples e compreensíveis.              |


# 10 Help and Documentation
>	It’s best if the system doesn’t need any additional explanation. However, it may be necessary to provide documentation to help users understand how to complete their tasks. 
>	- Is help documentation easy to search? 
>	- Is help provided in context right at the moment when the user requires it?

| **Issue**       | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Não existem exemplos práticos de como planear uma viagem em grupo. | 2            | Disponibilizar templates ou exemplos pré-criados.                |
| Não existe onboarding guiado para novos utilizadores.   | 3            | Criar tutorial inicial interativo.                |
