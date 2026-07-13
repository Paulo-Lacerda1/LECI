# NewModusApp
Camada de interação com a base de dados para o projeto da cadeira de Base de Dados.

## 1. Instalação Limpa da Base de Dados (Primeira Execução)
Este processo cria a base de dados do zero e insere os dados de teste. **Atenção: todos os dados existentes serão apagados.**

1. Abra a pasta do projeto.
2. Navegue até à pasta `Database`.
3. Execute o `install_database.sql`

O script de instalação executa a seguinte ordem de ficheiros SQL:
- `01_DDL.sql` (Criação de Tabelas e Esquemas)
- `02_Inserts.sql` (Dados de Teste)
- `03_Views.sql`
- `04_UDF.sql`
- `05_StoredProcedures.sql`
- `06_Triggers.sql`
- `07_Indexes.sql`

## 2. Configurar a Sincronização Automática (Para Desenvolvimento)
Para garantir que as Views, Procedures e Triggers estão sempre sincronizadas com o código sem perder os dados de teste, deve configurar um Evento Pre-Build no Visual Studio:

1. No Visual Studio, abra o **Solution Explorer**.
2. Clique com o botão direito no projeto `NewModusApp` e selecione **Properties** (Propriedades).
3. Aceda ao separador **Build Events** (Eventos de Compilação).
4. Na caixa de texto **Pre-build event command line**, insira exatamente as duas linhas abaixo:

```cmd
cd "$(ProjectDir)Database"
call sync_database.bat
```