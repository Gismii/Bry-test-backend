
# API de Gerenciamento de Usuários

Esta API foi desenvolvida em Java com Spring Boot e PostgreSQL para gerenciar usuários, com funcionalidades para cadastro, listagem, atualização e exclusão de usuários, armazenando o CPF criptografado no banco de dados.

## Endpoints

### Criar Usuário

Cria um novo usuário com nome, sobrenome, CPF e imagem.

- **URL**: `/users`
- **Método**: `POST`
- **Headers**: `Content-Type: multipart/form-data`
- **Parâmetros**:
  - `firstName`: String
  - `lastName`: String
  - `cpf`: String (com tamanho exato de 11 caracteres)
  - `image`: Arquivo de imagem

### Listar Usuários

Retorna todos os usuários cadastrados.

- **URL**: `/users`
- **Método**: `GET`

### Buscar Usuário por ID

Retorna um usuário específico pelo ID.

- **URL**: `/users/{id}`
- **Método**: `GET`
- **Parâmetros**:
  - `id`: Long

### Atualizar Usuário

Atualiza os dados de um usuário existente.

- **URL**: `/users/{id}`
- **Método**: `PUT`
- **Parâmetros**:
  - `id`: Long
  - Corpo da requisição em formato `multipart/form-data` com:
    - `firstName`: String
    - `lastName`: String
    - `cpf`: String
    - `image`: Arquivo de imagem

### Editar Usuário

Atualiza apenas o primeiro nome, sobrenome e imagem de um usuário existente.

- **URL**: `/users/edit/{id}`
- **Método**: `PUT`
- **Parâmetros**:
  - `id`: Long
  - `firstName`: String
  - `lastName`: String
  - `image`: Arquivo de imagem

### Deletar Usuário

Remove um usuário existente pelo ID.

- **URL**: `/users/{id}`
- **Método**: `DELETE`
- **Parâmetros**:
  - `id`: Long

## Executando Localmente

Para rodar esta API localmente:

1. Clone o repositório:
   ```bash
   git clone <esse repositorio>
   cd "nome da pasta"
   ```

2. Configure o banco de dados PostgreSQL e ajuste as configurações de conexão em `application.properties`.

3. Compile e execute a aplicação usando Maven ou sua IDE preferida.

## Tecnologias Utilizadas

- Java
- Spring Boot
- PostgreSQL
- Maven


Este README.md fornece uma visão geral dos endpoints disponíveis, como usar a API e como configurar e executar localmente. Personalize conforme necessário com detalhes específicos do seu ambiente.
