# Teste - 2018/2019 (resolução)

---

1. No SQL Server, você pode ter vários usuários de banco de dados (um para cada banco de dados) mapeados para o mesmo login. Isso permite que um único login tenha permissões diferentes em diferentes bancos de dados. Portanto, a afirmação é Verdadeira (V).

2. A cláusula WITH CHECK OPTION em uma view no SQL Server garante que todas as operações de INSERT e UPDATE na view estejam em conformidade com a condição definida na cláusula WHERE da view. Se a operação de INSERT ou UPDATE violar a condição, o SQL Server retornará um erro e a operação será cancelada. Portanto, a afirmação é Verdadeira (V).

3. O mecanismo de bloqueio (locking) é um método de controle de concorrência pessimista, não otimista. No controle de concorrência pessimista, como o bloqueio, o sistema assume que conflitos de transações são prováveis e usa bloqueios para prevenir conflitos. Por outro lado, o controle de concorrência otimista assume que conflitos são raros e permite que as transações procedam sem restrições, mas verifica se houve conflitos no momento do commit. Portanto, a afirmação é Falsa (F).

4. Triggers do tipo AFTER são disparados após a operação de INSERT, UPDATE ou DELETE ter sido executada, mas antes que a transação seja confirmada. Se houver uma alta probabilidade de que a operação seja revertida (rolled back), o uso de triggers AFTER pode levar a comportamentos indesejados ou inesperados, porque o trigger pode ter efeitos colaterais que não são revertidos quando a operação é revertida. Portanto, a afirmação é Verdadeira (V).

5. Em um escalonamento concorrente, conflitos geralmente ocorrem quando temos transações simultâneas que estão tentando modificar (ou seja, INSERT, UPDATE, DELETE) os mesmos dados. Transações simultâneas que apenas consultam (ou seja, SELECT) os mesmos dados geralmente não causam conflitos, porque elas não estão tentando alterar os dados. Portanto, a afirmação é Falsa (F).

6. No SQL Server, você não pode usar um stored procedure diretamente como fonte de dados na cláusula FROM de uma consulta SELECT. No entanto, você pode inserir os resultados de um stored procedure em uma tabela temporária e, em seguida, consultar essa tabela temporária na sua consulta SELECT. Portanto, a afirmação é Falsa (F).

7. No SQL Server, o processo de page split ocorre quando uma página de dados está cheia e uma operação de inserção ou atualização precisa adicionar mais dados à página. Isso não está diretamente relacionado à execução de consultas (queries). Portanto, a afirmação é Falsa (F).

8. F - Falso. Embora seja comum e muitas vezes aconselhável estabelecer relacionamentos entre tabelas com base em chaves primárias e estrangeiras, não é uma regra rígida no modelo relacional. Existem situações em que pode ser útil ou necessário estabelecer um relacionamento com base em outros atributos que não sejam chaves primárias e estrangeiras. No entanto, fazer isso pode ter implicações para a integridade referencial e o desempenho das consultas.

9. No SQL, a instrução REVOKE é usada para remover privilégios concedidos ou negados anteriormente a um usuário ou grupo. Portanto, você pode usar REVOKE para cancelar um GRANT ou DENY anterior. Portanto, a afirmação é Verdadeira (V).

10. No SQL Server, em um trigger AFTER UPDATE, a tabela lógica deleted contém as versões antigas das linhas que foram atualizadas. Portanto, não estará vazia se o trigger foi disparado por uma operação de UPDATE. A tabela deleted é usada em conjunto com a tabela inserted (que contém as novas versões das linhas) para determinar quais alterações foram feitas. Portanto, a afirmação é Falsa (F).

11. No SQL Server, um checkpoint é um ponto no processamento de transações em que todas as alterações de dados no buffer de log de transações são gravadas no disco. Isso ajuda a minimizar a quantidade de trabalho necessária para a recuperação em caso de falha do sistema. Portanto, a afirmação é Verdadeira (V).

12. No SQL Server, uma função definida pelo usuário (UDF) do tipo escalar não pode ser usada em uma restrição de integridade do tipo CHECK. As restrições CHECK são usadas para limitar os valores que podem ser inseridos em uma coluna e devem ser determinísticas, o que significa que elas devem sempre retornar o mesmo resultado para os mesmos valores de entrada. As UDFs escalares podem conter código que não é determinístico, portanto, elas não são permitidas em restrições CHECK. Portanto, a afirmação é Verdadeira (V).

13. No SQL Server, um cursor é uma estrutura de controle de banco de dados que permite percorrer e manipular as linhas de um conjunto de resultados de uma consulta SQL. Um cursor permite recuperar linhas uma de cada vez, permitindo operações mais complexas em cada linha. Portanto, a afirmação é Verdadeira (V).

14. No SQL Server, os triggers são disparados uma vez por instrução, não por linha. Isso significa que, independentemente de quantas linhas uma instrução INSERT, UPDATE ou DELETE afeta, o trigger correspondente será disparado apenas uma vez. Portanto, a afirmação é Falsa (F).

15. Sim, isso é verdade. Uma leitura suja (dirty read) ocorre quando uma transação lê dados que foram modificados por outra transação que ainda não foi confirmada (commit). Se a transação que modificou os dados for revertida (rolled back), a transação que leu os dados terá lido dados que, efetivamente, nunca existiram. Isso pode levar a resultados inconsistentes ou incorretos. Portanto, a afirmação é Verdadeira (V).

16. Embora o SQL dinâmico possa oferecer flexibilidade, ele também pode abrir a porta para vulnerabilidades de segurança, como a injeção de SQL. A injeção de SQL ocorre quando um invasor consegue inserir comandos SQL maliciosos em uma consulta, que são então executados pelo banco de dados. Isso pode levar a uma variedade de problemas, incluindo a exposição de dados sensíveis, a modificação de dados e até mesmo a destruição de dados. Portanto, é importante tratar adequadamente os elementos fornecidos pelos usuários para evitar esses problemas. Portanto, a afirmação é Falsa (F).

17. No SQL Server, se ocorrer um erro durante a execução de uma instrução SQL dentro de uma transação, a transação será revertida implicitamente. Isso é feito para manter a integridade dos dados, garantindo que a transação seja tratada como uma unidade atômica de trabalho. Se qualquer parte da transação falhar, todas as alterações feitas dentro da transação são revertidas. Portanto, a afirmação é Verdadeira (V).

18. No SQL Server, uma coluna definida como UNIQUE aceita múltiplos NULLs. Isso ocorre porque, de acordo com a lógica do SQL, NULL significa "desconhecido" e dois valores "desconhecidos" não são considerados iguais. Portanto, a afirmação é Falsa (F).

19. A variável global @@ROWCOUNT no SQL Server retorna o número de linhas afetadas pela última instrução executada, não o número de linhas de código executadas na batch atual. Portanto, a afirmação é Falsa (F).

20. No SQL Server, você pode definir o nível de isolamento de uma transação usando a instrução SET TRANSACTION ISOLATION LEVEL. Os níveis de isolamento determinam como e quando os bloqueios são colocados durante uma transação, o que pode afetar a concorrência e a consistência dos dados. Portanto, a afirmação é Falsa (F).

21. a)

    A) F. A ordem das ações está incorreta. A ordem correta seria:
    Verificar se o funcionário é gestor de um departamento, abortando o processo em caso afirmativo.
    Remover a referência nos funcionários supervisionados (Super_ssn passa a null).
    Substituição do funcionário nos projetos associados pelo gestor do seu  departamento.
    Eliminar os dependentes do funcionário.
    Delete do funcionário de Employee.
    É importante garantir que todas as referências ao funcionário sejam tratadas antes de realmente excluir o funcionário da tabela Employee. Além disso, a remoção dos dependentes deve ocorrer antes da remoção do funcionário, pois a tabela Dependent tem uma chave estrangeira que referencia a tabela Employee. Se o funcionário for removido primeiro, qualquer tentativa de remover os dependentes resultará em um erro de violação de chave estrangeira.

    B) V. Isso é verdade. Sem o requisito iv (que exige a substituição do funcionário nos projetos associados pelo gestor do seu departamento), poderíamos usar restrições de integridade referencial no SQL Server para lidar com a remoção de um funcionário. Por exemplo, poderíamos definir a ação ON DELETE SET NULL para a chave estrangeira Super_ssn na tabela Employee, o que automaticamente definiria Super_ssn como NULL para todos os funcionários supervisionados pelo funcionário que está sendo removido. Similarmente, poderíamos definir a ação ON DELETE CASCADE para a chave estrangeira Essn na tabela Dependent, o que automaticamente removeria todos os dependentes do funcionário que está sendo removido. No entanto, o requisito iv exige uma lógica mais complexa que não pode ser implementada apenas com restrições de integridade referencial.

    C) V. Isso é verdade. O bloco de código T-SQL acima pode ser usado dentro de um trigger INSTEAD OF DELETE na tabela Employee para verificar se o funcionário que está sendo removido é um gerente de departamento. A variável @issn é usada para armazenar o número de segurança social do funcionário que está sendo removido (obtido da tabela virtual "inserted" que é usada em triggers INSTEAD OF DELETE). Em seguida, uma consulta é feita para contar o número de departamentos onde o funcionário é o gerente (ou seja, onde Mgr_ssn é igual a @issn). Se o resultado for maior que 0, isso significa que o funcionário é um gerente de departamento e o processo é abortado.
    
    D) V. Isso é verdade. Uma função definida pelo usuário (UDF) do tipo escalar pode ser usada para implementar o processo. A função pode retornar um inteiro que indica o sucesso ou falha do processo. Por exemplo, a função pode retornar 0 se todas as operações forem bem-sucedidas e 1 se ocorrer um erro (como o funcionário sendo um gerente de departamento). No entanto, é importante notar que as UDFs do tipo escalar no SQL Server não podem modificar os dados do banco de dados diretamente. Portanto, a função teria que ser usada em conjunto com outros comandos SQL (como DELETE, UPDATE, etc.) para implementar todo o processo.
    
    E) F. Isso é falso. Mesmo que a primeira instrução seja verificar se o funcionário é um gerente de departamento, ainda precisamos de uma transação para garantir a atomicidade de todas as ações do processo. A atomicidade é uma propriedade fundamental das transações que garante que todas as operações dentro da transação são tratadas como uma única unidade de trabalho. Isso significa que ou todas as operações são bem-sucedidas e os dados são alterados de acordo, ou se qualquer operação falhar, todas as alterações são revertidas e os dados retornam ao estado original. Portanto, mesmo que a primeira instrução seja uma verificação, ainda precisamos de uma transação para garantir que todas as operações subsequentes sejam tratadas atomicamente.
    
    F) V. Isso é verdade. Um Stored Procedure (SP) seria a ferramenta ideal para implementar o processo desejado. SPs são rotinas compiladas que são armazenadas no banco de dados. Eles podem executar várias instruções SQL, aceitar parâmetros de entrada e retornar resultados. Além disso, eles suportam controle de fluxo com instruções como IF, ELSE, WHILE, etc., o que os torna ideais para implementar lógica complexa. No caso do processo desejado, um SP poderia ser usado para verificar se o funcionário é um gerente de departamento, remover a referência nos funcionários supervisionados, substituir o funcionário nos projetos associados, remover os dependentes e, finalmente, remover o funcionário. Além disso, todas essas operações podem ser encapsuladas em uma transação para garantir a atomicidade.
    
    G) V. Isso é verdade. Um trigger AFTER DELETE não seria apropriado para este processo. Triggers AFTER DELETE são acionados após a operação DELETE ter sido concluída. No entanto, neste caso, precisamos realizar várias verificações e operações antes de realmente excluir o funcionário. Além disso, se o funcionário for um gerente de departamento, o processo deve ser abortado e o funcionário não deve ser excluído. Isso não seria possível com um trigger AFTER DELETE, pois a operação DELETE já teria ocorrido. Portanto, um trigger INSTEAD OF DELETE ou um Stored Procedure seria mais apropriado para este processo.


    b)

    A) F. Isso é falso.

    B) V. Isso é verdade. Criar um índice nonclustered para o atributo Dno (Department Number) na tabela Employee pode melhorar o desempenho da consulta que busca o salário médio dos funcionários por localização do departamento. Isso ocorre porque um índice nonclustered pode acelerar as operações de busca ao organizar os dados de uma maneira que o SQL Server possa encontrar rapidamente. No entanto, é importante notar que a criação de índices também tem custos, como o uso adicional de espaço em disco e o tempo adicional necessário para atualizar o índice sempre que os dados são modificados. Portanto, a decisão de criar um índice deve ser baseada em uma análise cuidadosa do padrão de consultas e atualizações.

    C) F. Isso é falso. Embora a criação de índices nonclustered para os atributos Fname e Lname possa melhorar o desempenho de consultas que buscam funcionários por esses atributos, criar um índice para cada um desses atributos separadamente não seria a melhor abordagem. Isso ocorre porque as consultas geralmente buscam funcionários pelo conjunto de primeiro (Fname) e último nome (Lname). Portanto, seria mais eficiente criar um único índice nonclustered que inclua ambos os atributos. Isso permitiria ao SQL Server encontrar rapidamente os funcionários com base em ambos os atributos, em vez de ter que consultar dois índices separados.

    D) V. Isso é verdade. Criar um índice clustered composto para os atributos Fname e Lname na tabela Employee pode melhorar o desempenho de consultas que buscam funcionários pelo conjunto de primeiro (Fname) e último nome (Lname). Um índice clustered determina a ordem física dos dados na tabela, o que pode tornar as consultas que correspondem ao índice muito rápidas. No entanto, é importante notar que cada tabela pode ter apenas um índice clustered, então essa decisão deve ser tomada com cuidado. Além disso, a criação de índices também tem custos, como o uso adicional de espaço em disco e o tempo adicional necessário para atualizar o índice sempre que os dados são modificados. Portanto, a decisão de criar um índice deve ser baseada em uma análise cuidadosa do padrão de consultas e atualizações.

    E) F. Isso é falso. O atributo Essn (Employee Social Security Number) na tabela Works_on já é parte da chave primária da tabela, e o SQL Server automaticamente cria um índice clustered para a chave primária. Portanto, não há necessidade de criar um índice nonclustered adicional para o atributo Essn. Além disso, a criação de índices adicionais tem custos, como o uso adicional de espaço em disco e o tempo adicional necessário para atualizar o índice sempre que os dados são modificados. Portanto, a decisão de criar um índice deve ser baseada em uma análise cuidadosa do padrão de consultas e atualizações.

    F) V. Isso é verdade.

    G) F. Isso é falso. Índices filtrados no SQL Server são úteis quando apenas uma pequena porção dos dados é acessada frequentemente. Eles permitem que você indexe apenas uma parte dos dados com base em uma condição. No entanto, no caso dos atributos Fname e Lname na tabela Employee, não parece haver uma condição que justifique a criação de um índice filtrado. As consultas parecem acessar todos os funcionários, não apenas um subconjunto deles. Portanto, um índice nonclustered ou clustered composto seria mais apropriado para esses atributos.

    H) V. Isso é verdade. No SQL Server, quando você define um atributo como UNIQUE, um índice nonclustered é criado automaticamente para esse atributo para garantir que todos os valores sejam únicos. Portanto, se você definir o atributo Dname (Department Name) na tabela Department como UNIQUE, não precisará criar explicitamente um índice nonclustered para esse atributo. No entanto, é importante notar que a definição de um atributo como UNIQUE impõe uma restrição que pode afetar as operações de inserção e atualização. Portanto, essa decisão deve ser tomada com cuidado.

