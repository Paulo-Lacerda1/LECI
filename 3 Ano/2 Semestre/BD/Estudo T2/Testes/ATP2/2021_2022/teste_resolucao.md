# Teste - 2021/2022 (resolução)

---

1. Verdadeiro. Um escalonamento é recuperável se nenhuma transação Ti em E for concluída (commited) até que todas as outras transações que escrevem elementos lidos por Ti tenham sido concluídas. Isso é verdade porque garante que, se uma transação falhar, todas as transações que dependem dela também serão revertidas, mantendo a consistência do banco de dados.

2. Verdadeiro. É preferível guardar as senhas dos usuários na forma de Hash do que em texto livre (plain text) porque o hash é uma forma unidirecional de criptografia. Se um invasor obtiver acesso ao banco de dados, ele não poderá reverter o hash para obter a senha original.

3. Verdadeiro.

4. d. Todas as anteriores. Todas as consultas listadas (a, b, c) retornam funcionários que não trabalham em projetos. Elas usam diferentes métodos (NOT IN, LEFT JOIN com IS NULL, EXCEPT) para alcançar o mesmo resultado.

5. Verdadeiro. Um Checkpoint é um ponto de sincronismo (em disco) entre o transaction log e a base de dados. Isso é verdade porque um checkpoint é um ponto no processamento de transações onde todas as alterações de transações anteriores ao checkpoint são garantidas de terem sido gravadas em disco.

6. Falso.

7. Falso. No controle de concorrência de transações, o mecanismo de locking não é um método do tipo otimista. É um método pessimista, pois assume que conflitos de transação são prováveis de acontecer e, portanto, requer bloqueios para prevenir conflitos. Portanto, esta afirmação é falsa.

8. Verdadeiro. Num trigger do tipo After Update sobre a tabela X, a tabela lógica deleted contém tuplos caso a operação tenha alterado tuplos da tabela X. Isso é verdade porque a tabela deleted contém as versões antigas dos registros que foram atualizados.

9. Verdadeiro. No processo de normalização de uma base de dados relacional, a redundância está associada a dependências funcionais não desejadas. Isso é verdade porque a normalização é o processo de organizar os dados para minimizar a redundância e as dependências funcionais não desejadas.

10. Verdadeiro. Numa transação, podemos ter um Rollback implícito quando ocorre um erro numa instrução SQL da transação. Isso é verdade porque um erro em uma instrução SQL pode deixar o banco de dados em um estado inconsistente, portanto, um rollback é necessário para restaurar o banco de dados ao seu estado anterior.

11. Falso.

12. Falso. Num trigger do tipo Instead Of, definido com conteúdo vazio, a instrução DML associada ao trigger não é executada. Portanto, esta afirmação é falsa.

13. Verdadeiro. No modelo relacional, devemos evitar o estabelecimento de relacionamentos entre duas relações que não sejam baseados em atributos chave primária e estrangeira. Isso é verdade porque esses tipos de relacionamentos garantem a integridade referencial entre as tabelas.

14. Verdadeiro. Um Stored Procedure permite retornar um inteiro que podemos atribuir a uma variável. Isso é verdade porque os procedimentos armazenados podem retornar valores que podem ser atribuídos a variáveis.

15. Verdadeiro.

16. Verdadeiro. Em SQL, os INNER e os OUTER JOIN incluem os tuplos de ambas as tabelas que satisfazem as condições de junção. Isso é verdade porque a função de um JOIN é combinar linhas de duas ou mais tabelas com base em uma coluna relacionada entre elas.

17. Verdadeiro. Em SQL, não devemos utilizar a cláusula WHERE para filtrar dados já agregados pela cláusula GROUP BY. Isso é verdade porque a cláusula WHERE é processada antes da cláusula GROUP BY, portanto, não pode ser usada para filtrar os resultados da agregação. Em vez disso, devemos usar a cláusula HAVING.

18. Verdadeiro. Em SQL Server, uma heap é uma tabela sem um clustered index. Isso é verdade porque uma heap é uma tabela que não tem um índice clusterizado e, portanto, as páginas de dados não são organizadas de forma específica.

19. d.

20. Falso.

21. Falso. A utilização de SQL dinâmico pode levar a vulnerabilidades de segurança, como a injeção de SQL, se não for adequadamente sanitizada.

22. Falso. Em um trigger After Delete, a tabela lógica inserted está vazia, pois ela contém as linhas que foram inseridas na operação de DML. No caso de uma operação de DELETE, nenhuma linha é inserida.

23. d. Todas são verdadeiras. Todas as opções descrevem corretamente o comportamento da função COUNT em SQL.

24. Verdadeiro. Em SQL Server, para criar uma chave estrangeira que referencie uma tabela, o usuário precisa ter permissão do tipo References para essa tabela.

25. Verdadeiro. Em SQL Server, se ocorrer um erro dentro de um bloco try-catch, todas as operações anteriores da transação serão revertidas.

26. Verdadeiro. Um bom índice é aquele que minimiza a quantidade de páginas que precisam ser carregadas do disco para a memória durante uma pesquisa.

27. c. SELECT fname, Iname FROM employee WHERE salary > ALL(SELECT salary FROM employee WHERE dno = 2). Esta consulta retorna os nomes dos funcionários que ganham mais do que qualquer funcionário do departamento número 2.

28. Falso.

29. Falso.

30. Verdadeiro. Em SQL Server, podemos incluir atributos não-chave nas folhas de um índice não-clusterizado.

31. Verdadeiro. Em SQL Server, um índice não-clusterizado contém referências para a tabela base em seus nós folhas.

32. Verdadeiro. Em transações concorrentes, uma leitura suja ocorre quando uma segunda transação lê um elemento modificado por uma primeira transação que é revertida posteriormente.

33. Verdadeiro. A instrução SQL fornecida retorna três escalares com os mesmos valores.

34. Verdadeiro. Em SQL Server, podemos ter usuários de banco de dados distintos mapeados para o mesmo login.

35. Verdadeiro. Em normalização de banco de dados relacional, uma dependência funcional total ocorre quando um atributo depende de toda a chave da relação.

36. Falso. A cláusula HAVING é usada em conjunto com a cláusula GROUP BY, não pode ser usada sozinha.

37. Verdadeiro. A instrução SQL fornecida executa sem erros.

38. Falso. A instrução SQL fornecida não retorna nenhum tuplo, pois a comparação com NULL usando o operador IN sempre retorna falso.

39. Verdadeiro. A criação de um índice para um atributo não garante necessariamente uma melhoria no tempo de consulta, especialmente se o atributo não tem uma distribuição de dados única ou se a consulta não é otimizada para usar o índice.

40. Falso. Em SQL Server, os triggers são disparados uma vez por operação de DML, não por cada tuplo afetado.
