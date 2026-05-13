# 🍳 ReceitasCOT - Gerador de Receitas com IA

Aplicação web full-stack desenvolvida para ajudar usuários a gerenciarem seus ingredientes disponíveis em casa e gerarem receitas personalizadas de forma inteligente utilizando a API do Groq.

---

## 🚀 Funcionalidades

*   **Gestão de Ingredientes:** Cadastro, listagem e remoção de alimentos em tempo real.
*   **Geração de Receitas por IA:** Criação automatizada de receitas baseada estritamente nos itens cadastrados no banco de dados.
*   **Interface Web Moderna:** Painel responsivo unificado para controle de estoque e exibição da receita.
*   **Categorização por Enums:** Organização dos alimentos por classes aceitas no back-end (`CARNES`, `VEGETAL`, `VERDURAS`, etc.).

---

## 📦 Tecnologias Utilizadas

### Back-end
*   **Java 21**
*   **Spring Boot 4.0.5**
*   **Spring Data JPA** (Camada de persistência)
*   **Spring WebFlux / Project Reactor** (Processamento assíncrono via `Mono`)
*   **Banco de Dados H2** (Armazenamento local em arquivo)
*   **Flyway Migration** (Versionamento automatizado do schema do banco)
*   **Lombok** (Redução de código boilerplate)

### Front-end
*   **HTML5 / CSS3** (Interface com variáveis nativas e scroll dinâmico)
*   **JavaScript (ES6+)** (Comunicação assíncrona via `Fetch API`)

---

## 🛠️ Arquitetura de Endpoints

### Gerenciamento de Alimentos (`FoodItemController`)
*   `GET /food` -> Retorna a lista de todos os ingredientes ativos.
*   `POST /food` -> Cadastra um novo item enviando JSON mapeado para `FoodItemModel`.
*   `DELETE /food/{id}` -> Remove o registro de um ingrediente do banco pelo ID.

### Integração com IA (`RecipeController`)
*   `GET /generate` -> Dispara a requisição reativa ao `GroqService` contendo os alimentos em estoque. Retorna o texto plano da receita gerada.

---

## 🔧 Instalação e Execução

### Pré-requisitos
*   Java JDK 21 ou superior.
*   Maven 3.9+.

### Passos para Inicialização

1. Clone o repositório:
   ```bash
   git clone github.com
   ```

2. Configure as credenciais e a sua chave da IA no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:h2:file:./data/receitasdb
   spring.datasource.username=sa
   spring.datasource.password=
   
   spring.flyway.user=sa
   spring.flyway.password=

   # Chave de acesso do Groq Cloud
   groq.api.key=SUA_API_KEY_AQUI
   ```

3. Compile e execute a aplicação via Maven:
   ```bash
   mvn spring-boot:run
   ```

4. Abra o navegador e acesse a interface gráfica em:
   `http://localhost:8080/`

---

## 📋 Uso do Sistema

*   **Cadastrar Ingrediente:** Preencha o nome, quantidade, data de validade, selecione a categoria no seletor e clique em `➕ Adicionar`.
*   **Excluir Item:** Clique no ícone `❌` ao lado do ingrediente indesejado na barra lateral.
*   **Gerar Receita:** Clique no botão inferior `🍳 Gerar Receita com IA`. O painel central exibirá o progresso e o resultado final formatado.