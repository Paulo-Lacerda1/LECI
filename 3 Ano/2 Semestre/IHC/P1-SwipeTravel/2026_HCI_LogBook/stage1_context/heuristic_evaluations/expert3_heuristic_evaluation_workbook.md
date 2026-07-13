<!-- This Heuristic Evaluation Workbook replicates the one proposed by the 
Nielsen Norman Group available at: https://media.nngroup.com/media/articles/attachments/Heuristic_Evaluation_Workbook_-_Nielsen_Norman_Group.pdf
-->

**Evaluator**: João António Henriques Vieira
**Date**: 02/03/2026
**Product**: TripIt

Severity Scale adopted: [[severity_scale_heuristic_evaluation]]
Summary of each usability heuristic: [here](https://media.nngroup.com/media/articles/attachments/Heuristic_Summary1-compressed.pdf)

# 1 Visibility of System Status
> The design should always keep users informed about what is going on, through appropriate feedback within a reasonable amount of time. 
> - Does the design clearly communicate its state?
> - Is feedback presented quickly after user actions?

| **Issue** | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Quando o utilizador reencaminha um e-mail de reserva para o plans@tripit.com, a aplicação não fornece feedback imediato (ex: "A processar e-mail...") de que a informação está a ser lida, deixando o utilizador na dúvida se o plano foi recebido. | 3 | Apresentar uma notificação ou barra de estado na app a indicar "A sincronizar novas reservas do e-mail" até o plano aparecer na timeline. |

# 2 Match Between System and The Real World
> The design should speak the users' language. Use words, phrases, and concepts familiar to the user, rather than internal jargon. Follow real-world conventions, making information appear in a natural and logical order. 
> - Will user be familiar with the terminology used in the design? 
> - Do the design’s controls follow real-world conventions?

| **Issue** | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Em voos importados automaticamente, a app exibe frequentemente códigos de aeroportos (ex: LHR, CDG) com mais destaque do que o nome da cidade, o que obriga utilizadores menos experientes a descodificar o jargão da aviação. | 2 | Dar prioridade ao nome da cidade (ex: "Paris") em fonte maior, colocando o código do aeroporto (CDG) como informação secundária. |

# 3 User Control and Freedom
> Users often perform actions by mistake. They need a clearly marked "emergency exit" to leave the unwanted action without having to go through an extended process. 
> - Does the design allow users to go back a step in the process? 
> - Are exit links easily discoverable? 
> - Can users easily cancel an action? 
> - Is Undo and Redo supported?

| **Issue** | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Editar manualmente a hora de um voo auto-gerado é um processo rígido; alterar a hora de partida não recalcula de forma inteligente o fuso horário de chegada, e não existe uma opção clara de "Desfazer" se o utilizador errar na edição. | 2 | Permitir edição flexível com recálculo automático de fusos horários e incluir um botão "Desfazer alterações" após uma edição manual. |

# 4 Consistency and Standards
> Users should not have to wonder whether different words, situations, or actions mean the same thing. Follow platform and industry conventions. 
> - Does the design follow industry conventions? 
> - Are visual treatments used consistently throughout the design?

| **Issue** | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| O layout do formulário para adicionar um plano manualmente varia consoante o tipo de plano (Restaurante vs. Atividade vs. Alojamento), apresentando campos obrigatórios em locais diferentes. | 2 | Uniformizar o layout base de todos os formulários de inserção manual, mantendo campos como "Nome", "Data" e "Hora" sempre na mesma posição. |

# 5 Error Prevention
> Good error messages are important, but the best designs carefully prevent problems from occurring in the first place. Either eliminate error-prone conditions, or check for them and present users with a confirmation option before they commit to the action. 
> - Does the design prevent slips by using helpful constraints? 
> - Does the design warn users before they perform risky actions?

| **Issue** | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| O sistema permite adicionar um evento (ex: jantar num restaurante) numa data e hora em que o utilizador, segundo o próprio itinerário, ainda estará dentro de um avião noutro país, não prevenindo erros de sobreposição logística. | 3 | Adicionar um aviso visual suave (soft warning): "Atenção: Este evento sobrepõe-se ao seu voo para Londres." |

# 6 Recognition Rather than Recall
> Minimize the user's memory load by making elements, actions, and options visible. The user should not have to remember information from one part of the interface to another. Information required to use the design (e.g. field labels or menu items) should be visible or easily retrievable when needed. 
> - Does the design keep important information visible, so that users do not have to memorize it? 
> - Does the design offer help in-context?

| **Issue** | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Na vista diária (Timeline), os números de confirmação de hotéis ou voos estão escondidos dentro do ecrã de detalhes. O utilizador tem de memorizar o código ou andar para trás e para a frente entre ecrãs ao fazer o check-in. | 2 | Mostrar o "Número de Confirmação" (PNR) diretamente no cartão de resumo do evento na vista principal da timeline. |

# 7 Flexibility and Efficiency of Use
> Shortcuts — hidden from novice users — may speed up the interaction for the expert user such that the design can cater to both inexperienced and experienced users. Allow users to tailor frequent actions. 
> - Does the design provide accelerators like keyboard shortcuts and touch gestures? 
> - Is content and funtionality personalized or customized for individual users?

| **Issue** | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Se um voo for cancelado ou adiado 24 horas, o utilizador tem de editar a data de cada alojamento e atividade manualmente, um a um. Falta um atalho para ações em massa (bulk-edit). | 3 | Adicionar a funcionalidade "Avançar/Recuar itinerário", permitindo arrastar múltiplos eventos de uma só vez para o dia seguinte. |

# 8 Aesthetic and Minimalist Design
> Interfaces should not contain information that is irrelevant or rarely needed. Every extra unit of information in an interface competes with the relevant units of information and diminishes their relative visibility. 
> - Is the visual design and content focused on the essentials? 
> - Have all distracting, unnescessary elements been removed?

| **Issue** | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Na versão gratuita da aplicação, existem anúncios (ex: aluguer de carros ou upgrades para a versão Pro) inseridos diretamente no meio do itinerário cronológico da viagem, quebrando a estética e distraindo o utilizador da sua rota. | 3 | Mover os banners de publicidade para o topo ou fundo do ecrã, fora do fluxo temporal da viagem, respeitando a hierarquia visual. |

# 9 Help Users Recognize, Diagnose, and Recover from Errors
> Error messages should be expressed in plain language (no error codes), precisely indicate the problem, and constructively suggest a solution. 
> - Does the design use traditional error message visuals, like bold, red text? 
> - Does the design offer a solution that solves the error immediately?

| **Issue** | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Se o utilizador reencaminhar um e-mail de reserva com um formato não suportado, a app transforma-o numa "Nota" genérica sem alertar claramente que falhou a leitura automática dos dados (datas, horas), deixando o utilizador confuso. | 3 | Enviar um alerta claro na app: "Não conseguimos extrair as datas deste e-mail. Por favor, reveja e insira os detalhes em falta manualmente", com um atalho direto para a edição. |

# 10 Help and Documentation
> It’s best if the system doesn’t need any additional explanation. However, it may be necessary to provide documentation to help users understand how to complete their tasks. 
> - Is help documentation easy to search? 
> - Is help provided in context right at the moment when the user requires it?

| **Issue** | **Severity** | Recommendation |
| --------------- | ------------ | -------------- |
| Ao clicar na secção de Ajuda dentro da app móvel, o utilizador é redirecionado para o navegador web do telemóvel (browser), quebrando a experiência e forçando uma mudança de contexto. | 2 | Integrar o centro de ajuda (FAQs e documentação) nativamente dentro da aplicação, para que o utilizador não tenha de sair da interface. |