#   Sistema de Gestão Empresarial de Pagamentos

Este é um sistema Java que calcula salários e benefícios de funcionários de uma empresa.

##   Funcionalidades

O sistema implementa as seguintes funcionalidades:

* Cálculo do valor total pago em salários a funcionários em um determinado mês.
* Cálculo do valor total pago em salários e benefícios a funcionários em um determinado mês.
* Cálculo do valor total pago em benefícios a funcionários em um determinado mês.
* Identificação do funcionário que recebeu o valor mais alto no mês.
* Identificação do funcionário que recebeu o valor mais alto em benefícios no mês.
* Identificação do vendedor que mais vendeu em um determinado mês.

##   Classes

O sistema é composto pelas seguintes classes:

* `Cargo`: Representa um cargo na empresa, com informações como nome, salário, bonificação por ano de serviço e percentual de benefícios.
* `Funcionario`: Representa um funcionário da empresa, com informações como nome, cargo e data de contratação.
* `Gerente`, `Secretario`, `Vendedor`: Representam tipos específicos de cargos, herdando de `Cargo`.
* `Vendas`: Representa o registro de vendas de um vendedor em um determinado mês.
* `CalculaClass`: Contém os métodos para realizar os cálculos das funcionalidades descritas acima.

##   Como Funciona

1.  **Estrutura do Projeto**

    O projeto está estruturado da seguinte forma:

    ```
    src/
    ├── Cargo.java
    ├── CalculaClass.java
    ├── Funcionario.java
    ├── Gerente.java
    ├── Secretario.java
    ├── Vendas.java
    └── Vendedor.java
    ```

    Todas as classes estão localizadas diretamente no diretório `src`. 

    Isso separa as classes de modelo (entidades), serviços (lógica de negócios) e a classe principal de execução.

2.  **Execução**

    * Compile os arquivos Java usando o compilador Java (`javac`).
    * Execute a classe `Main` (você precisará criar uma classe `Main` para iniciar a aplicação) para interagir com o sistema.

3.  **Utilização**

    * Crie instâncias das classes `Cargo`, `Funcionario` e `Vendas` para representar os dados da empresa.
    * Utilize os métodos da classe `CalculaClass` para realizar os cálculos desejados.

##   Observações

* A classe `Cargo` armazena o percentual de benefícios dividido por 100 (ex: 0.20 para 20%).
* A precisão dos cálculos financeiros é garantida pelo uso da classe `BigDecimal`.

##   Autor

\João Pedro Varela 

##   Contato

\joaopedrov.borges@gmail.com