:ON ERROR EXIT

-- Executa todos os scripts pela ordem correta.
:r .\01_DDL.sql
:r .\02_Inserts.sql
:r .\03_UDF.sql
:r .\04_Views.sql
:r .\05_StoredProcedures.sql
:r .\06_Triggers.sql
:r .\07_Indexes.sql
