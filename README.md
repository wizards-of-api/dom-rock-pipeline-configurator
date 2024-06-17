<h1 align="center"> Dom Rock Pipeline Configurator </h1>
<h3 align="center">Repositório dedicado ao versionamento do projeto de API do terceiro semestre de Banco de Dados.</h3>

# Tópicos
- [Membros](https://github.com/wiz-fatec/dom-rock-pipeline-configurator/tree/dev?tab=readme-ov-file#team-members)
- [Requisitos](https://github.com/wiz-fatec/dom-rock-pipeline-configurator/tree/dev?tab=readme-ov-file#requisitos)
- [Product Backlog](https://github.com/wiz-fatec/dom-rock-pipeline-configurator/tree/dev?tab=readme-ov-file#product-backlog)
- [Documentação](https://github.com/wiz-fatec/dom-rock-pipeline-configurator/tree/dev?tab=readme-ov-file#documentação)
- [Tecnologias](https://github.com/wiz-fatec/dom-rock-pipeline-configurator/tree/dev?tab=readme-ov-file#tecnologias)

# Team Members

|Nome|Função|LinkedIn|
| -------- | -------- | -------- |
|**Julio Cesar Ferreira de Freitas**|Product Owner|[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/julio-freitas-415b73216)|
|**Paulo Arantes Machado**|Scrum Master|[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/paulo-antonio-arantes-machado-a8a89b23b)|
|**Alita Amancio**|Desenvolvedora|[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/alitaamancio)|
|**Cauan Vinicius Barbaglio**|Desenvolvedor|[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/cauan-barbaglio/)|
|**Gabriel de Souza Mota**|Desenvolvedor|[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/gabriel-mota-4a0816a0/)|
|**Otavio Calderan Bruguel**|Desenvolvedor|[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/otavio-calderan-578b48239)|
|**Pedro Henrique da Silva Bozzano Pereira**|Desenvolvedor|[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/pedro-bozzano)|
|**Vinícius da Silva Chaves**|Desenvolvedor|[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/vinícius-chaves-197353244/)|
|**André Hideaki Wakugawa**|Desenvolvedor|[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/andrewakugawa/)|
|**Cauê Vieira da Silva**|Desenvolvedor|[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/cau%C3%AA-vieira-ba62b4244/)|

# Requisitos
### Requisitos Funcionais

|Requisito|Descrição|
| :--------: |-------- |
|1|Desenvolver uma interface de cadastro do cliente, solução e usuários autorizados a configurar os dados|
|2|Desenvolver uma interface de upload de dados csv ou excel e apresentação da estrutura dos dados (campos, tipos, regras – pode ou não conter nulos, descrição do significado do campo na perspectiva do negócio)|
|3|Desenvolver uma interface que define quais campos serão mapeados como chave de identificação dos dados.|
|4|Desenvolver uma interface que será aplicada uma regra comum aos clientes que  chamamos de “de para” (exemplo: tipo de movimento 802 = transferência de estoque)|
|5|Desenvolver uma interface que será feito o mapeamento de cruzamento de dados (pode ser 1 ou mais fontes de dados com as respectivas chaves, para cada cruzamento ter o espaço para descrever o significado do negócio). NÃO APLICÁVEL|
|6|Desenvolver um dashboad a respeito dos dados configurados para um perfil administrador para visões quantitativas como clientes, tipos de fontes de dados, quantidade de campos, etc.|
|7|Desenvolver uma interface que mostre a visão analítica completa da configuração|

### Requisitos Não Funcionais

|Requisito|Descrição|
| :--------: | -------- |
|8|Login e autenticação e permissão de usuários (por cliente, solução e estágio de configuração)|
|9|Modelagem de Banco de Dados que será utilizado para gerar arquivos YAML.NÃO APLICÁVEL O YALM|
|10|Log contendo a rastreabilidade das configurações|
|11|O front-end deve ser desenvolvido de forma minimalista. |

# Product Backlog

|Rank|Prioridade|User Story|Sprint|Requisitos|
| :--------: | :--------: | -------- | :--------: | :--------: |
|1|Alta|Eu como usuário de permissão LZ quero importar arquivos de dados em csv ou excel para configurar informações referentes aos dados do arquivo em uma visualização de tabela|1|2, 11|
|2|Média|Eu como usuário de permissão LZ quero informar a fonte dos meus dados e a frequência na qual eles serão enviados para subir essas informações no esquema de banco de dados|1|2, 11|
|3|Alta|Eu como usuário de permissão LZ após upload do arquivo, preciso definir os tipos de dados de cada coluna, informar se a coluna pode haver dados "null"/vazios, selecionar colunas que devem ser processadas, nomear cabeçalhos e descrever as regras de negócios de cada cabeçalho para subir essas informações no banco de dados|1|2, 11|
|4|Baixa|Eu como usuário de permissão LZ preciso ter acesso aos arquivos configurados anteriormente e poder alterar as informações inseridas anteriormente|1|2, 11|
|5|Alta|Eu como usuário de permissão Bronze quero configurar a chave identificadora da minha tabela de dados, e validar os dados que vieram etapa LZ para que eu a use em etapas futuras|2|3, 11|
|6|Média|Eu como usuário bronze, quero visualizar e editar minhas configurações de chaves identificadoras para analisar a configuração feita e caso necessário, alterar os campos que compõem a chave identificadora|2|3, 11|
|7|Alta|Eu como usuário de permissão Silver, quero fazer um de/para referente às informações que podem surgir nos campos já configurados para dar significância aos dados, por exemplo, em um campo de "nota" de 1 a 5, esse range deve ser transformado para bom, muito bom, ... Etc|3|4, 11|
|8|Alta|Eu como usuário de permissão Silver quero ver as configurações de de/para após realizadas, para analisar e editar caso necessário|3|4, 11|
|9|Alta|Eu como usuário administrador quero cadastrar outros usuários para que eles tenham acesso às etapas permitidas por mim|4|1, 8, 11|
|10|Alta|Eu como usuário administrador quero dar permissões para os usuários cadastrados para que eles acessem somente as etapas permitidas por mim|4|1, 8, 11|
|11|Alta|Eu como usuário administrador quero ter acesso a um log de acesso de cada usuário que eu criei, para verificar quem realizou o que, quando e qual foi a ação realizada.|4|10, 11|
|12|Alta|Eu como usuário administrador quero visualizar dashboards quantitativos para fazer análises|4|6, 7, 11|

# Documentação

+ [Sprints](https://github.com/wiz-fatec/dom-rock-pipeline-configurator/wiki/Home)
+ [Documentação](https://github.com/wiz-fatec/dom-rock-pipeline-configurator/blob/dev/Documentação%20Dom%20Rock%20Pipeline%20Configurator.pdf)
+ [ComoExecutar](https://github.com/wiz-fatec/dom-rock-pipeline-configurator/blob/dev/Como-Executar.md)
+ [Manual do Usuário](https://github.com/wiz-fatec/dom-rock-pipeline-configurator/blob/dev/Manual%20do%20Usuário%20Dom%20Rock%20Configurator.pdf)

# Tecnologias
<h4 align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![Vue.js](https://img.shields.io/badge/vuejs-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white)
![Stack Overflow](https://img.shields.io/badge/-Stackoverflow-FE7A16?style=for-the-badge&logo=stack-overflow&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-%235865F2.svg?style=for-the-badge&logo=discord&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)

</h4>
<div align="center"><hr width=70%></div>







