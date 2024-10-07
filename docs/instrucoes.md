# Instruções de Uso do Sistema de Estacionamento

## 1. Introdução
Este sistema de estacionamento permite o registro de clientes, veículos e a gestão de vagas em um estacionamento. Os usuários podem cadastrar veículos, registrar entradas e saídas, e calcular cobranças baseadas no tempo de permanência e tipo de vaga.

## 2. Pré-requisitos
- **Java JDK**: Certifique-se de ter o Java Development Kit (JDK) instalado. Você pode baixar a versão mais recente do [site oficial do Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) ou usar uma versão open source, como o OpenJDK.
- **IDE**: Uma IDE como Eclipse, IntelliJ IDEA ou Visual Studio Code é recomendada para facilitar o desenvolvimento e a execução do código.

## 3. Estrutura de Diretórios
A estrutura do projeto deve ser conforme abaixo:

XulambsParking/ ├── src/ │ ├── Cliente.java │ ├── Cobranca.java │ ├── Main.java │ ├── ParqueDeEstacionamento.java │ ├── TipoVaga.java │ ├── Vaga.java │ └── Veiculo.java └── README.md

## 4. Configuração do Ambiente
1. **Clone ou baixe o repositório**:
   - Se você estiver usando um sistema de controle de versão, clone o repositório para o seu ambiente local.
   - Se não, faça o download dos arquivos `.java` e coloque-os na pasta `src` conforme a estrutura acima.

2. **Compilação**:
   - Navegue até a pasta `src` no terminal:
     ```bash
     cd caminho/para/XulambsParking/src
     ```
   - Compile os arquivos:
     ```bash
     javac *.java
     ```

3. **Execução**:
   - Após a compilação bem-sucedida, execute o programa principal:
     ```bash
     java Main
     ```

## 5. Arquivos de Configuração
Não há arquivos de configuração externos necessários para este sistema. Todas as configurações são gerenciadas através do código.

## 6. Dados de Teste
Para testar o sistema, você pode usar os seguintes dados:

- **Clientes**:
  - Nome: João, ID: 1
  - Nome: Maria, ID: 2

- **Veículos**:
  - Placa: ABC-1234, Modelo: Sedan
  - Placa: XYZ-5678, Modelo: SUV

- **Vagas**:
  - Vagas disponíveis: 5 (pode ser alterado no construtor da classe `ParqueDeEstacionamento`)

## 7. Considerações Finais
- **Manutenção**: Verifique regularmente se o código está atualizado e funcional. É recomendável implementar testes unitários para garantir a estabilidade do sistema.
- **Melhorias Futuras**: Considere adicionar funcionalidades, como:
  - Persistência de dados em um banco de dados.
  - Interface gráfica do usuário (GUI).
  - Relatórios de uso do estacionamento.

## 8. Licença
Este projeto é de uso livre. Sinta-se à vontade para modificar e adaptar conforme necessário.
