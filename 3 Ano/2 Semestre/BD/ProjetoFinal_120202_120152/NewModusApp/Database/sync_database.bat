@echo off
setlocal

echo [Pre-Build] A procurar instancia SQL Server local...

:: 1. SQLEXPRESS
sqlcmd -S .\SQLSERVER -E -Q "SELECT 1" >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    set SERVER=.\SQLSERVER
    goto Execute
)

:: 2. localhost
sqlcmd -S localhost -E -Q "SELECT 1" >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    set SERVER=localhost
    goto Execute
)

:: 3. Falha se ninguem tiver o SQL Server ligado
echo [Pre-Build] ERRO: Nenhuma instancia SQL (localhost ou localhost\SQLEXPRESS) esta acessivel.
exit /b 1

:Execute
echo [Pre-Build] Ligado com sucesso a %SERVER%. A sincronizar a logica...
sqlcmd -S %SERVER% -E -i "sync_database.sql" -f 65001

if %ERRORLEVEL% NEQ 0 (
    echo [Pre-Build] ERRO: Erro de sintaxe nos ficheiros SQL. Verifica a Error List.
    exit /b %ERRORLEVEL%
)

echo [Pre-Build] Sincronizacao concluida com sucesso.