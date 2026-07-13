@echo off
setlocal

echo ========================================
echo A instalar a base de dados NewModus...
echo ========================================

sqlcmd -S localhost\SQLEXPRESS -E -i "NewModusV2.sql"

if %errorlevel% neq 0 (
    echo.
    echo ERRO: Falhou a criacao da base de dados.
    pause
    exit /b %errorlevel%
)

echo.
echo Base de dados criada com sucesso.
echo A inserir dados...

sqlcmd -S localhost\SQLEXPRESS -E -i "inserts_newmodusV2.sql"

if %errorlevel% neq 0 (
    echo.
    echo ERRO: Falhou a insercao dos dados.
    pause
    exit /b %errorlevel%
)

echo.
echo ========================================
echo Base de dados instalada com sucesso!
echo ========================================
pause