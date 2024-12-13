## Guia de Execução do Projeto

### Pré-requisitos

Certifique-se de que as seguintes ferramentas estejam instaladas em sua máquina:

- **Docker**

### Como Executar o Projeto Localmente

1. Inicie os serviços necessários:
   ```bash
   docker compose up database
   docker compose up rabbitmq
   ```

2. Após os serviços estarem ativos, execute o projeto normalmente no ambiente de desenvolvimento.

### Como Executar o Projeto no Docker

Para executar todo o projeto diretamente no Docker, utilize o comando:

```bash
docker compose up
```

Isso irá iniciar todos os serviços necessários automaticamente.
