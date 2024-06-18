# CONFIGURANDO A APLICAÇÃO
> [!IMPORTANT]
> ### DEPENDÊNCIAS:
> Antes de inicializar a aplicação, certifique-se que as dependências estejam instaladas
> - Java 17
> - MySQL

## INSTALAÇÃO DA APLICAÇÃO
### 1° Clone o repositório DomRock Pipeline Configurator:<br>
` https://github.com/wiz-fatec/dom-rock-pipeline-configurator.git`
<div align="left"><hr width=70%></div>

### 2° Abra o terminal no path onde instalou o projeto;<br>
<div align="left"><hr width=70%></div>

### 3° Entre na pasta "web-server":<br>
`cd web-server`
<div align="left"><hr width=70%></div>

### 4° Execute o comando para rodar o back-end da aplicação:<br>
`mvn clean spring-boot:run`
<div align="left"><hr width=70%></div>

### 5° Abra outro terminal no path onde instalou o projeto;<br>
<div align="left"><hr width=70%></div>

### 6° Entre na pasta "web-client":<br>
`cd web-client`
<div align="left"><hr width=70%></div>

### 7° Execute o comando para instalar as dependências:<br>
`npm i`
<div align="left"><hr width=70%></div>

### 8° Execute o comando para instalar a dependência dos gráficos (chart.js):<br>
`npm install chart.js`
<div align="left"><hr width=70%></div>

### 9° Execute o comando para rodar o front-end da aplicação:<br>
`npm run dev`
<div align="left"><hr width=70%></div>

>[!NOTE]
> Agora sua API (back-end) estará rodando na porta 8080 e o client (front-end) estará rodando na porta 5173.
  
