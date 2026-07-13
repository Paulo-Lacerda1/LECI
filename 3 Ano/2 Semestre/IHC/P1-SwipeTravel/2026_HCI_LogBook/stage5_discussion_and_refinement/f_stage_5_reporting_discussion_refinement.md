[Back to main Logbook Page](../hci_logbook.md)

---

# F. Discussion of Evaluation Results

Nesta fase, analisámos criticamente os dados recolhidos durante os testes de usabilidade com 5 participantes, cruzando as observações do moderador com o feedback direto dos utilizadores. 

Globalmente, o protótipo funcional foi muito bem recebido. A maioria das tarefas foi classificada com um grau de dificuldade "5 (Muito Fácil)" pelos participantes. No entanto, a análise detalhada dos tempos de execução e das observações permitiu identificar áreas onde os utilizadores hesitaram ou sentiram ligeira dificuldade, resultando em classificações mais baixas ("3" ou "4") em tarefas específicas.


# Refinement List

Com base na triangulação dos dados, definimos a seguinte lista de refinamentos prioritários para a interface:

1. **Consistência de Idioma (Severidade: 2 - Menor):** O observador notou que um dos atritos de usabilidade foi a mistura de idiomas na interface.
   * *Ação:* Uniformizar todo o texto, botões e *placeholders* do protótipo funcional para um único idioma, evitando confusão cognitiva.

2. **Descoberta do Grupo e Votação (Severidade: 3 - Maior):** Na tarefa 2, um participante classificou a dificuldade com "3", e o observador registou que houve necessidade de ajuda "Para encontrar o grupo e na votação". 
   * *Ação:* Melhorar a navegação para aceder à área de votação do grupo, tornando os botões mais óbvios e adicionando *feedback* visual imediato quando um voto é registado.

3. **Divisão de Custos/Despesas (Severidade: 3 - Maior):** Na tarefa 3, verificou-se alguma hesitação por parte de um utilizador que também atribuiu o grau "3".
   * *Ação:* Simplificar o ecrã de despesas, tornando o botão de "Adicionar Despesa" ou "Dividir" mais proeminente e garantindo que o sistema mostra claramente a divisão dos valores.

4. **Onboarding Contextual (Severidade: 2 - Menor):** O observador reportou que foi necessário dar uma "Pequena explicação do que era necessário fazer" a pelo menos um utilizador.
   * *Ação:* Incluir *tooltips* simples ou *empty states* (ecrãs sem conteúdo) mais descritivos que guiem o utilizador de forma intuitiva na sua primeira interação.


# Refined Prototype

O protótipo refinado reflete a implementação direta das soluções identificadas na *Refinement List*, focando-se em reduzir a carga cognitiva, aumentar a visibilidade do estado do sistema e prevenir erros de navegação. 

As principais alterações implementadas no protótipo de alta fidelidade foram:

* **Navegação Global e Linguagem:** Corrigimos as inconsistências linguísticas em toda a interface e adicionámos notificações visuais (como *snackbars* ou alertas discretos) sempre que uma ação importante é concluída com sucesso. 
* **Otimização da Votação:** O ecrã de votação (*Swipe*) foi clarificado para garantir que os utilizadores percebem exatamente onde clicar para entrar na viagem do grupo e como interagir com os cartões de sugestão, mitigando as perdas de tempo registadas nos testes. 
* **Gestão de Despesas Simplificada:** A interface para dividir custos foi redesenhada. Agora, o sistema garante que o utilizador percebe de forma imediata quem está incluído na divisão do custo e qual a quota de cada membro, respondendo diretamente à fricção observada na Tarefa 3.

Estas intervenções garantem que a usabilidade da aplicação está mais robusta e alinhada com as expectativas dos utilizadores, mantendo a intuição e rapidez que levaram a larga maioria das tarefas a serem classificadas como "Muito Fácil".

---
[Back to main Logbook Page](../hci_logbook.md)

---