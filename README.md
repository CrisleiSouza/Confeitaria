# 🍰 Sistema de Confeitaria

Sistema desenvolvido em Java utilizando JavaFX para gerenciamento de bolos e confeiteiros de uma confeitaria.

## 📖 Sobre o Projeto

O objetivo do projeto é permitir o gerenciamento de um catálogo de bolos e dos confeiteiros responsáveis pela produção.

O sistema possui dois níveis de acesso:

* **Administrador:** pode cadastrar, editar, excluir e consultar informações.
* **Usuário comum:** possui acesso somente para visualização dos dados cadastrados.

## ✨ Funcionalidades

### Bolos

* Cadastro de bolos
* Consulta de bolos
* Atualização de informações
* Exclusão de registros

### Confeiteiros

* Cadastro de confeiteiros
* Consulta de confeiteiros
* Atualização de informações
* Exclusão de registros

### Controle de Acesso

* Login de administrador
* Login de usuário comum
* Interface de visualização restrita para usuários sem privilégios administrativos

## 🏗️ Estrutura das Entidades

### Bolo

| **Campo**        | **Tipo**    |
| ------------ | ------- |
| id           | inteiro |
| nome         | texto   |
| descrição    | texto   |
| ingredientes | texto   |
| formato      | texto   |
| preço        | decimal |

### Confeiteiro

| Campo         | Tipo    |
| ------------- | ------- |
| id            | inteiro |
| nome          | texto   |
| email         | texto   |
| telefone      | texto   |
| sobre         | texto   |
| especialidade | texto   |

## 🛠️ Tecnologias Utilizadas

* Java 21 (testado na versão 21.0.10)
* JavaFX 21
* JDBC
* MariaDB
* Gradle 9.3.1

## 🗄️ Banco de Dados

O banco utilizado é o MariaDB.

Antes de executar a aplicação, execute o script presente em:

```text
database.sql
```

para criar a estrutura necessária.

## 🚀 Como Executar

### Pré-requisitos

* Java 21 (desenvolvido e testado na versão 21.0.10)
* Gradle 9.3.1
* MariaDB em execução

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/CrisleiSouza/Confeitaria.git
```

2. Execute o script `database.sql`.

3. Ajuste as credenciais de conexão com o banco, se necessário.

4. Execute:

```bash
gradlew run
```

## 🔐 Credenciais de Teste

### Administrador

```text
E-mail: admin@admin.com
Senha: 123456
```

### Usuário comum

```text
E-mail: view@view.com
Senha: 123456
```

## 📂 Arquitetura Utilizada

O projeto foi organizado seguindo o padrão MVC:

* Model (Entity)
* View (Boundary)
* Controller (Control)
* DAO para persistência de dados

## 👨‍💻 Autor

Crislei Rosa
