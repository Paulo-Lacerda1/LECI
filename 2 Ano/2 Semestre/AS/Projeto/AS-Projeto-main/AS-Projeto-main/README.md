# AutoHub – Serviços Automóveis

## Projeto de Análise de Sistemas – Grupo II

### Equipa

- Rui Albuquerque – 110509  
- Ellen Sales – 117450  
- João Leite – 119859  
- Paulo Lacerda – 120202  

---

## 📌 Descrição

O AutoHub é uma aplicação web e mobile destinada à gestão de serviços automóveis, ligando clientes e prestadores de serviço (oficinas). O sistema permite:

- Registo e autenticação de utilizadores (clientes e prestadores)
- Pesquisa e filtragem de serviços
- Agendamento de serviços
- Sistema de avaliações e feedback

Neste incremento foi validada a arquitetura base, com comunicação entre plataformas simulada via API e dados armazenados em `LocalStorage`, prevendo futura ligação a uma base de dados real.

---

## 📁 Estrutura da Aplicação

- **Frontend Web (Prestadores):** React + Vite  
- **Frontend Mobile (Clientes):** React (adaptado para mobile)  
- **Persistência Temporária:** LocalStorage  
- **Comunicação:** Simulação de API RESTful

---

## 🌐 Acesso às Aplicações

- **Versão Web (Prestadores):** https://autohub-provider.vercel.app
#### Para entrares na aplicação, utiliza as seguintes credenciais:
Email: thompson@gmail.com
Password: thompson@password 
- **Versão Mobile (Clientes):** https://autohub-client.vercel.app  
> 💡 Para melhor visualização da versão mobile, usar modo mobile nas DevTools do navegador.

---

## 📌 Requisitos

- Node.js (v18 ou superior)
- npm

---

## ✅ Funcionalidades Implementadas

- Registo e login de utilizadores (clientes e prestadores)
- Pesquisa e filtragem de serviços
- Agendamento de serviços
- Sistema de avaliação e feedback
- Gestão de serviços (prestador)
- Gestão de agendamentos (prestador)

---

## 📌 Planeamento

- **Trello Board:** https://trello.com/b/PaiDUG2V/autohub-implementation

---

## 📄 Relatório

O relatório detalhado encontra-se em `relatorio.pdf` e inclui:

- Backlog com user stories selecionadas
- Critérios de aceitação por tipo de utilizador
- Estratégia de desenvolvimento (Kanban + GitHub)
- Explicação da arquitetura e simulação da API
- Diagrama de sequência de agendamento

---

## 🛠️ Futuras Melhorias

- Substituição do LocalStorage por base de dados real (Firebase ou PostgreSQL)
- Backend real com autenticação e persistência
- Integração com serviços externos (emails, notificações)

---
