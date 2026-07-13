@echo off
setlocal

echo ========================================
echo A procurar instancia SQL Server local...
echo ========================================

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
echo ERRO: Nenhuma instancia SQL (localhost ou localhost\\SQLEXPRESS) esta acessivel.
pause
exit /b 1

:Execute
echo Ligado com sucesso a %SERVER%.
echo A instalar a base de dados NewModus Completa...
echo ========================================

sqlcmd -S %SERVER% -E -i "install_database.sql" -f 65001

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERRO: Falhou a instalacao completa da base de dados.
    pause
    exit /b %errorlevel%
)

echo.
echo ========================================
echo Base de dados instalada e populada do zero com sucesso!
echo ========================================
pause