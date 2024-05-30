# CONFIGURANDO A APLICAÇÃO
> [!IMPORTANT]
> ### DEPENDÊNCIAS:
> Antes de inicializar a aplicação, certifique-se que as dependências estejam instaladas
> - Java 17
> - MySQL

## INSTALAÇÃO DA APLICAÇÃO
### 1° Clone o repositório DomRock Pipeline Configurator:<br>
` https://github.com/wiz-fatec/dom-rock-pipeline-configurator.git`
  
### 2° Abra o terminal no path onde instalou o projeto<br>

### 3° Entre na pasta "web-server":<br>
`cd web-server`

### 4° Execute o comando para rodar o back-end da aplicação:<br>
`mvn clean spring-boot:run`

### 5° Abra outro terminal no path onde instalou o projeto<br>

### 6° Entre na pasta "web-client":<br>
`cd web-client`

### 7° Execute o comando para instalar as dependências:<br>
`npm i`

### 8° Execute o comando para rodar o front-end da aplicação:<br>
`npm run dev`

>[!NOTE]
> Agora sua API (back-end) estará rodando na porta 8080 e o client (front-end) estará rodando na porta 5173.
  
