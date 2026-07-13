CREATE DATABASE PrescricaoDB;
GO

USE PrescricaoDB;
GO

CREATE SCHEMA sns;
GO

CREATE TABLE sns.Medico (
    NumSNS INT          NOT NULL,
    Nome VARCHAR(100)   NOT NULL,
    Especialidade VARCHAR(100) NOT NULL,
    PRIMARY KEY (NumSNS)
);

CREATE TABLE sns.Paciente (
    NumUtente INT       NOT NULL,
    Nome VARCHAR(100)   NOT NULL,
    DataNascimento DATE NOT NULL,
    Endereco VARCHAR(200) NOT NULL,
    PRIMARY KEY (NumUtente)
);

CREATE TABLE sns.Farmaceutica (
    NumRegistoNacional INT NOT NULL,
    Nome VARCHAR(100)    NOT NULL,
    Endereco VARCHAR(200) NOT NULL,
    Telefone VARCHAR(20) NOT NULL,
    PRIMARY KEY (NumRegistoNacional)
);

CREATE TABLE sns.Farmacia (
    NIF VARCHAR(20)     NOT NULL,
    Nome VARCHAR(100)   NOT NULL,
    Endereco VARCHAR(200) NOT NULL,
    Telefone VARCHAR(20) NOT NULL,
    PRIMARY KEY (NIF)
);

CREATE TABLE sns.Farmaco (
    NomeComercial VARCHAR(100) NOT NULL,
    Formula VARCHAR(200) NOT NULL,
    RegistoFarmaceutica INT NOT NULL,
    PRIMARY KEY (RegistoFarmaceutica, NomeComercial),
    FOREIGN KEY (RegistoFarmaceutica) REFERENCES sns.Farmaceutica(NumRegistoNacional)
);

CREATE TABLE sns.Prescricao (
    NumPrescricao INT NOT NULL,
    DataPrescricao DATE NOT NULL,
    NumSNSMedico INT NOT NULL,
    NumUtentePaciente INT NOT NULL,
    NIFFarmacia VARCHAR(20) NULL,
    DataProcessamento DATE NULL,
    PRIMARY KEY (NumPrescricao),
    FOREIGN KEY (NumSNSMedico) REFERENCES sns.Medico(NumSNS),
    FOREIGN KEY (NumUtentePaciente)  REFERENCES sns.Paciente(NumUtente),
    FOREIGN KEY (NIFFarmacia)  REFERENCES sns.Farmacia(NIF)
);

CREATE TABLE sns.PrescricaoFarmaco (
    NumPrescricao INT NOT NULL,
    RegistoFarmaceutica INT NOT NULL,
    NomeComercial VARCHAR(100) NOT NULL,
    PRIMARY KEY (NumPrescricao, RegistoFarmaceutica, NomeComercial),
    FOREIGN KEY (NumPrescricao) REFERENCES sns.Prescricao(NumPrescricao),
    FOREIGN KEY (RegistoFarmaceutica, NomeComercial) REFERENCES sns.Farmaco(RegistoFarmaceutica, NomeComercial)
);