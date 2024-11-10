# O que é o Spring Framework?
É um Framework open source desenvolvido para a plataforma Java baseado nos padrões de projetos, inversão de controle e injeção de dependência.

O Spring é baseado em módulos, para reduir a complexidade no desenvolvimento de aplicações.

## Módulos
### Core Container
Beans
Core
Context
Expression Language

### Data Acess/Integration
JDBC
ORM
OXM
JSM
TRANSACTIONS

### Web
MVC / Remoting
WEB
Servlet
Portlet
Struts

### Test

## Spring x Java EE
Spring foi um movimento diante da alta burocracia e verbosidade que inúmeros processos que o Java EE exigia. Diante da necessidade de funcionalidades mais simples, surgiu o Spring.
Depois do Java 5, voltou-se uma discussão sobre usar Spring ou Java EE, mas hoje em dia isso depende muito da filosofia e escolhes das empresas, assim como seu domínio sobre cada um.

## Inversão de Controle
*Inversion of Control* ou *IoC*, tratra-se do redirecionamento do fluxo de execução de um código retirando parcialmente o controle sobre ele e delegando-o para um container.
O principal propósito é minimizar o acoplamento do código.
Deixar o container gerenciar os objetos.
Todos os objetos que precisam existir previamente já estarão no container.

![[Pasted image 20241108110250.png]]

## Injeção de Dependências
É um padrão de desenvolviemnto com a finalidade de manter o baixo nível de acoplamento entre módulos de um sistema.
![[Pasted image 20241108111739.png]]


## Beans
Objeto que é instanciado (criado), montado e gerenciado por um container através do princípio da inversão de controle.

## Scopes
### Standalone
Singleton
Prototype
Spring Bean Scopes
### Http
Request
Session
Global

#### Singleton
O contâiner do Spring IoC define apenas uma instância do objeto para toda a aplicação.
#### Prototype
Será criado um novo objeto a cada solicitação ao container.

#### Request
Um bean será criado para cada requisição HTTP. Os objetos existirão enquanto a requisição estiver em execução.

#### Session
Um bean será criado para a sessão de usuário. Precisamos acssar a mesma solicitação duas vezes para testar os escopos específicos da web.

#### Global
Ou *Application Scope* cria um bean para o ciclo de vida do contexto da aplicação.
Objetos compartilhados por toda a aplicação.

#### Autowired
Uma anotação (indicação) onde deverá ocorrer uma injeção aumática de dependência.
- byName: Buscado um método set que corresponde ao nome do Bean. São indicações de onde irá ocorrer a injeção de objetos.
- byType: Considerado o tipo da classe para inclusão do Bean.
- byConstrutor: Usamos o construtor para incluir a dependência.

# Springboot
É um recurso do framework Spring.

Enquanto o Spring Framework é baseado no padrão de injeção de dependências, o Springboot foca na configuração automática.

## Antes do SpringBoot
Desafios com a configuração do projeto
- Dependência individual
- Verbosidade
- Incompatibilidade de versões
- Complexidade de gestão
- Configurações complexas e repetitivas

Starters
Simplificação a adição de depêndencias
Alguns mais utilizados:
Spring-boot-starter-*
- data-jpa: integração ao BD via JPA - Hibernate.
- data-mongodb: integração com MongoDB.
- web: Inclusão do container Tomcat para aplicações REST.
- web-services: Webservices baseados na arquitetura SOAP.
- batch: Implementação de JOBs de processos.
- test: Disponibilização de recursos para testes unitários como JUnit.
- openfeign: Client HTTP baseado em interfaces.
- actuator: Gerenciamento de monitoramento da aplicação.

## Spring Boot Primerios Passos
- Acessar o site do spring initializr
    - Configurar o projeto.
    - Adicionar dependências

Após criar o projeto, ele virá com uma classe com o sufixo Application anotada com:
```
@SpringBootApplication
```

A classe conterá um método main, cuja instrução é:
```
	SpringApplication.run(NomeDaclasseApplication.class, args)
```
A instrução é a inicializaçao do contexto SpringBoot.

O POM virá com o componente <parent> que serve para herdar o projeto do spring-boot-starter-parent.

```
<parent>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-parent</artifactId>  
    <version>3.3.5</version>  
    <relativePath/> <!-- lookup parent from repository -->  
</parent>
``` 

E também virá com as dependências escolhidas durante a configuração no spring initiazr, como o spring-boot-starter e o spring-boot-start-test.
```
<dependencies>  
    <dependency>       
		<groupId>org.springframework.boot</groupId>  
		<artifactId>spring-boot-starter</artifactId>  
    </dependency>  
    <dependency>       
		<groupId>org.springframework.boot</groupId>  
		<artifactId>spring-boot-starter-test</artifactId>  
		<scope>test</scope>  
    </dependency>
</dependencies>
```

Na dependência spring-boot-starter é baixado o Spring Core, Beans e Context.

O projeto também já vem com uma classe para testes, anotadas com @SpringBootTest:
```
@SpringBootTest  
class PrimeirosPassosSpringBootApplicationTests {  
  
    @Test  
    void contextLoads() {  
    }  
  
}
```

E vem, dentro da pasta resourses, com o arquivo application.properties:
```
spring.application.name=primeiros-passos-spring-boot
```
Onde podemos adicionar configurações e propriedades de servidores, banco de dados e outras.


## Desenvolvimento
No desenvolvimento, nao utilizaremos mais o new. Exemplo:
```
@SpringBootApplication  
public class PrimeirosPassosSpringBootApplication {  
  
    public static void main(String[] args) {  
       SpringApplication.run(PrimeirosPassosSpringBootApplication.class, args);  
  
       Calculadora calculadora = new Calculadora();  
  
       System.out.println(calculadora.somar(1, 2));  
    }  
  
}
```

Ao invés disso, para trabalharmos dentro do context Spring, devemos criar uma classe que implementa **ComandLineRunner** anotada com **@Container**, que usa o **@Autowired** para injetar a dependência da classe Calculadora, evitando assim o uso do new e utilizando o Spring Context. A classe Calculadora também deve ser anotada com @Component.
```
@Component  
public class MyApp implements CommandLineRunner {  
  
    @Autowired  
    private Calculadora calculadora;  
  
    @Override  
    public void run(String... args) throws Exception {  
        System.out.println(calculadora.somar(1, 2));  
    }  
}
```

## Bean x Components
Usar component quando tenho acesso ao código fonte.
Usar Bean em artefatos externos, como CommandLineRunner.

## Scope
### Prototype
```
@Bean  
@Scope("prototype")  
public Remetente remetente() {  
System.out.println("CRIANDO UM OBJETO REMETENTE");  
Remetente remetente = new Remetente();  
remetente.setEmail("noreply@dio.com.br");  
remetente.setNome("Digital Innovation One");  
return remetente;  
}
```

## Properts Value
Adicioar propriedades no application.properties
```
nome = Gustavo  
email = gustavo.kraemer@gmail.com  
telefones= 45 999999999, 45 30000000
```
Depois para pegar o valor do properties, basta usar o @Value(${"nomePropriedade"})

```
@Component  
public class SistemaMensagem implements CommandLineRunner {  
    @Value("${nome:Gustavo}")  
    private String nome;  
  
    @Value("${email}")  
    private String email;  
  
    @Value("${telefones}")  
    private List<Long> telefones;  
  
    @Override  
    public void run(String... args) throws Exception {  
       System.out.println("Mensagem enviada por: " + nome  
             + "\nE-mail:" + email  
             + "\nCom telefones para contato: " + telefones);  
       System.out.println("Seu cadastro foi aprovado");  
    }  
}
```

Para evitar erro quando nao tiver configuração de uma propriedade no arquivo applications, usasse dois pontos ":" para definir um valor default.

## Configuration Properties
```
remetente.nome = Gustavo  
remetente.email = gustavo.kraemer@gmail.com  
remetente.telefones= 45 999999999, 45 30000000
```
Nesse caso, cria a classe Remetente anotada com @Configuration, pois é uma configuração, e a anotação "ConfigurationProperties(prefix = "remetente")" para que a classe seja entendida como o rementente do application.properties
```
package dio.springboot.app;  
  
import org.springframework.boot.context.properties.ConfigurationProperties;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.stereotype.Component;  
  
import java.util.List;  
  
@Configuration  
@ConfigurationProperties(prefix = "remetente")  
public class Remetente {  
  
    private String nome;  
  
    private String email;  
  
    private List<Long> telefones;  
  
    public String getNome() {  
        return nome;  
    }  
  
    public void setNome(String nome) {  
        this.nome = nome;  
    }  
  
    public String getEmail() {  
        return email;  
    }  
  
    public void setEmail(String email) {  
        this.email = email;  
    }  
  
    public List<Long> getTelefones() {  
        return telefones;  
    }  
  
    public void setTelefones(List<Long> telefones) {  
        this.telefones = telefones;  
    }  
}
```

Resultado na classe de uso:
```
@Component  
public class SistemaMensagem implements CommandLineRunner {  
  
    @Autowired  
    private Remetente remetente;  
  
    @Override  
    public void run(String... args) throws Exception {  
       System.out.println("Mensagem enviada por: " + remetente.getNome()  
             + "\nE-mail:" + remetente.getEmail()  
             + "\nCom telefones para contato: " + remetente.getTelefones());  
       System.out.println("Seu cadastro foi aprovado");  
    }  
}
```