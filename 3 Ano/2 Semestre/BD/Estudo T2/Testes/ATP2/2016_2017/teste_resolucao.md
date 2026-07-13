# Teste - 2016/2017 (resolução)

---

1. V - Verdadeiro. No SQL Server, você pode definir vários índices não agrupados (non-clustered indexes) para uma única tabela. Cada índice não agrupado pode melhorar o desempenho de consultas que usam qualquer combinação de colunas no índice.

2. V - Verdadeiro. Em um trigger do tipo AFTER UPDATE no SQL Server, a tabela lógica "deleted" contém as linhas que foram atualizadas, ou seja, o estado anterior das linhas antes da operação de UPDATE. Portanto, ela não estará vazia após uma operação de UPDATE.

3. V - Verdadeiro. Normalmente, é preferível usar restrições de integridade declarativas em vez de triggers para impor a integridade dos dados. As restrições de integridade declarativas são mais fáceis de entender e gerenciar, e o SQL Server pode otimizá-las melhor. Além disso, as restrições de integridade declarativas são verificadas automaticamente pelo SQL Server, enquanto os triggers requerem código personalizado que deve ser testado e mantido.

4. F - Falso. No SQL Server, um índice clustered (clustered index) organiza os dados da tabela fisicamente na ordem das colunas especificadas no índice. No entanto, os índices filtrados (filtered indexes) são uma forma especial de índice não-clustered que inclui apenas um subconjunto de filas na tabela, baseando-se numa condição de filtro. Portanto, enquanto um índice não-clustered pode ser filtrado, um índice clustered não pode ser do tipo filtrado no SQL Server.

5. V - Verdadeiro. As funções escalares definidas pelo utilizador (UDFs) podem ser utilizadas em check constraints para validar valores de colunas com base em critérios personalizados.

6. F - Falso. Numa UDF do tipo 'inline table-valued', apenas uma única instrução SELECT pode ser utilizada para retornar o conjunto de resultados. Para múltiplas instruções SQL, seria necessário uma UDF do tipo 'multi-statement table-valued'.

7. V - Verdadeiro. Um stored procedure pode retornar um valor inteiro através do código de retorno, que pode ser atribuído a uma variável.

8. F - Falso. Uma leitura suja ocorre quando uma transação T1 lê dados que foram modificados por uma transação T2, mas ainda não foram confirmados (committed).

9. V - Verdadeiro. Uma relação em Segunda Forma Normal (2FN) elimina dependências parciais, mas ainda pode ter dependências funcionais transitivas, que são eliminadas na Terceira Forma Normal (3FN).

10. V - Verdadeiro. Uma heap é uma tabela que não possui um clustered index.

11. F - Falso. O mecanismo de locking é considerado um método pessimista de controle de concorrência, pois assume que conflitos são prováveis e, portanto, bloqueia recursos para prevenir conflitos.

12. F - Falso. Conflitos em escalonamento concorrente geralmente ocorrem com operações de escrita (inserir, atualizar, excluir), não apenas com consultas (leitura).

13. F - Falso. Um checkpoint é o processo de escrever todas as páginas sujas (dirty pages) do buffer cache para o disco e registrar essa operação no transaction log, não está diretamente relacionado ao backup.

14. V - Verdadeiro. Índices são mais eficientes em colunas com alta cardinalidade (muitos valores distintos). Quando há muitos valores repetidos, o índice pode não ser eficaz.

15. F - Falso. Para inserções ordenadas, como uma coluna de identity, um fillfactor mais alto (próximo de 100%) faz mais sentido, já que não haverá muita fragmentação e splits de página.

16. V - Verdadeiro. A cláusula 'WITH CHECK OPTION' numa view assegura que as atualizações ou inserções feitas através da view cumpram as condições especificadas na cláusula WHERE da view.

17. F - Falso. Um DENY sobrepõe-se a um GRANT. Se uma permissão é explicitamente negada, essa negação prevalece sobre qualquer concessão de permissão.

18. F - Falso. Page splits ocorrem durante operações de inserção ou atualização, quando uma página cheia é dividida para acomodar novos dados.

19. V - Verdadeiro. Cursors são utilizados para percorrer linha a linha os resultados de uma consulta.

20. F - Falso. Triggers do tipo AFTER são utilizados para executar ações após a conclusão bem-sucedida da operação. Se uma operação tem uma alta probabilidade de ser rollback, triggers INSTEAD OF seriam mais apropriados.