CREATE DATABASE ATLDB;
GO

USE ATLDB;
GO

CREATE SCHEMA atl;
GO

CREATE TABLE atl.Pessoa (
    NumCC VARCHAR(20) NOT NULL,
    Nome VARCHAR(100) NOT NULL,
    Morada VARCHAR(200) NOT NULL,
    DataNascimento DATE NOT NULL,
    PRIMARY KEY (NumCC)
);

CREATE TABLE atl.ATLClasse (
    Classe INT NOT NULL,
    PRIMARY KEY (Classe),
);

CREATE TABLE atl.Professor (
    NumFuncionario INT NOT NULL,
    NumCC VARCHAR(20) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Contacto VARCHAR(20) NOT NULL,
    PRIMARY KEY (NumFuncionario),
    UNIQUE (NumCC),
    FOREIGN KEY (NumCC) REFERENCES atl.Pessoa(NumCC)
);

CREATE TABLE atl.EncarregadoEducacao (
    Email VARCHAR(100) NOT NULL,
    NumCC VARCHAR(20) NOT NULL,
    Contacto VARCHAR(20) NOT NULL,
    PRIMARY KEY (Email),
    UNIQUE (NumCC),
    FOREIGN KEY (NumCC) REFERENCES atl.Pessoa(NumCC)
);

CREATE TABLE atl.PessoaAutorizada (
    Email VARCHAR(100) NOT NULL,
    NumCC VARCHAR(20) NOT NULL,
    Contacto VARCHAR(20) NOT NULL,
    PRIMARY KEY (Email),
    UNIQUE (NumCC),
    FOREIGN KEY (NumCC) REFERENCES atl.Pessoa(NumCC)
);

CREATE TABLE atl.Turma (
    ID INT NOT NULL,
    AnoLetivo VARCHAR(9) NOT NULL,
    Designacao VARCHAR(20) NOT NULL,
    MaxAlunos INT NOT NULL,
    ClasseATL INT NOT NULL,
    ProfessorNumFuncionario INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (ClasseATL) REFERENCES atl.ATLClasse(Classe),
    FOREIGN KEY (ProfessorNumFuncionario) REFERENCES atl.Professor(NumFuncionario)
);

CREATE TABLE atl.Atividade (
    ID INT NOT NULL,
    Designacao VARCHAR(20) NOT NULL,
    Custo DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE atl.Aluno (
    NumCC VARCHAR(20) NOT NULL,
    EmailEncEdu VARCHAR(100) NOT NULL,
    RelacaoEncEdu VARCHAR(20) NOT NULL,
    PRIMARY KEY (NumCC),
    FOREIGN KEY (NumCC) REFERENCES atl.Pessoa(NumCC),
    FOREIGN KEY (EmailEncEdu) REFERENCES atl.EncarregadoEducacao(Email)
);

CREATE TABLE atl.TurmaAluno (
    AlunoNumCC VARCHAR(20) NOT NULL,
    TurmaID INT NOT NULL,
    PRIMARY KEY (AlunoNumCC, TurmaID),
    FOREIGN KEY (AlunoNumCC) REFERENCES atl.Aluno(NumCC),
    FOREIGN KEY (TurmaID) REFERENCES atl.Turma(ID)
);

CREATE TABLE atl.TurmaAtividade (
    TurmaID INT NOT NULL,
    AtividadeID INT NOT NULL,
    PRIMARY KEY (TurmaID, AtividadeID),
    FOREIGN KEY (TurmaID) REFERENCES atl.Turma(ID),
    FOREIGN KEY (AtividadeID) REFERENCES atl.Atividade(ID)
);

CREATE TABLE atl.Frequenta (
    AlunoNumCC VARCHAR(20) NOT NULL,
    AtividadeID INT NOT NULL,
    PRIMARY KEY (AlunoNumCC, AtividadeID),
    FOREIGN KEY (AlunoNumCC) REFERENCES atl.Aluno(NumCC),
    FOREIGN KEY (AtividadeID) REFERENCES atl.Atividade(ID)
);

CREATE TABLE atl.AlunoPessoaAutorizada (
    AlunoNumCC VARCHAR(20) NOT NULL,
    EmailPessoaAutorizada VARCHAR(100) NOT NULL,
    Relacao VARCHAR(20) NOT NULL,
    PRIMARY KEY (AlunoNumCC, EmailPessoaAutorizada),
    FOREIGN KEY (AlunoNumCC) REFERENCES atl.Aluno(NumCC),
    FOREIGN KEY (EmailPessoaAutorizada) REFERENCES atl.PessoaAutorizada(Email)
);