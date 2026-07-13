USE PrescricaoDB;
GO

-- Inserir Médicos e Pacientes
INSERT INTO sns.Medico (NumSNS, Nome, Especialidade) VALUES 
(111, 'Dr. Gregory House', 'Diagnóstico'),
(222, 'Dra. Meredith Grey', 'Cirurgia Geral');

INSERT INTO sns.Paciente (NumUtente, Nome, DataNascimento, Endereco) VALUES 
(1, 'João Silva', '1980-01-01', 'Rua Formosa, Porto'),
(2, 'Maria Santos', '1990-05-15', 'Avenida da Liberdade, Lisboa'),
(3, 'Carlos Saudável', '2000-10-10', 'Rua do Ouro, Braga'); -- Nunca terá prescrições

-- Inserir Farmacêuticas e Farmácias
INSERT INTO sns.Farmaceutica (NumRegistoNacional, Nome, Endereco, Telefone) VALUES 
(906, 'Bayer', 'Alemanha', '123456789'),
(100, 'Pfizer', 'EUA', '987654321');

INSERT INTO sns.Farmacia (NIF, Nome, Endereco, Telefone) VALUES 
('500111222', 'Farmácia Central', 'Rua Central', '911111111'),
('500333444', 'Farmácia Nova', 'Rua Nova', '922222222');

-- Inserir Fármacos
INSERT INTO sns.Farmaco (NomeComercial, Formula, RegistoFarmaceutica) VALUES 
('Aspirina', 'Acido Acetilsalicilico', 906),
('Benuron', 'Paracetamol', 906), -- Este nunca será prescrito para testar a query D
('VacinaX', 'Formula Secreta', 100);

-- Inserir Prescrições
-- Prescrição 1: Processada na Farmácia Central
INSERT INTO sns.Prescricao (NumPrescricao, DataPrescricao, NumSNSMedico, NumUtentePaciente, NIFFarmacia, DataProcessamento) 
VALUES (10, '2023-10-01', 111, 1, '500111222', '2023-10-02');

-- Prescrição 2: NÃO Processada (O paciente não foi à farmácia)
INSERT INTO sns.Prescricao (NumPrescricao, DataPrescricao, NumSNSMedico, NumUtentePaciente, NIFFarmacia, DataProcessamento) 
VALUES (11, '2023-10-10', 222, 1, NULL, NULL);

-- Prescrição 3: Processada na Farmácia Central para outro paciente
INSERT INTO sns.Prescricao (NumPrescricao, DataPrescricao, NumSNSMedico, NumUtentePaciente, NIFFarmacia, DataProcessamento) 
VALUES (12, '2023-10-15', 111, 2, '500111222', '2023-10-15');

-- Associar Fármacos às Prescrições
INSERT INTO sns.PrescricaoFarmaco (NumPrescricao, RegistoFarmaceutica, NomeComercial) VALUES 
(10, 906, 'Aspirina'),
(10, 100, 'VacinaX'),
(11, 100, 'VacinaX'),
(12, 906, 'Aspirina');