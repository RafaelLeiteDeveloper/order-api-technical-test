## Guia de Execução do Projeto

### Pré-requisitos

Certifique-se de que as seguintes ferramentas estejam instaladas em sua máquina:

- **Docker**

---

### Como Executar o Projeto Localmente

1. Inicie os serviços necessários:
   ```bash
   docker compose up database
   docker compose up rabbitmq
   ```

2. Após os serviços estarem ativos, execute o projeto normalmente no ambiente de desenvolvimento.

---

### Como Executar o Projeto no Docker

Para executar todo o projeto diretamente no Docker, utilize o comando:

```bash
docker compose up
```

Isso irá iniciar todos os serviços necessários automaticamente.

---

## Arquitetura e Comunicação

### Diagrama

Abaixo está o diagrama ilustrando a arquitetura do sistema:

<img src="doc/Order-api.svg" width="600" height="600" />

### Componentes do Sistema

### 1. **Projeto A**
Este componente é responsável por gerar os pedidos e disponibilizá-los por meio de uma fila para o serviço de pedidos.

### 2. **Order API**
A **Order API** consome os pedidos gerados pelo Projeto A, processa as informações e realiza cálculos, como a soma dos produtos.
O **Order API** possui validações para consumir os pedidos, caso o pedido não atenda,ele não será processado.

### 3. **Projeto B**
O Projeto B consome o serviço **Order API**. O **Order API** expõe um endpoint que permite buscar um pedido específico pelo seu ID.