## Activity 1 
### Micro-services Spring 
#### Initializing the project 

![initilizr](imgs/init.png)


There is two types of Spring:
1. Classic Spring MVC, Based on the imperative programming.
2. Reactive Spring (WebFlux), Based on the interactive programming.

Resources:
[Part 1- De L'impératif vers le réactif- Reactive Programming avec Spring Web FLUX-Concepts de base: Professeur Mohamed YOUSSFI](https://www.youtube.com/watch?v=h58yIiHgoBg)
[Build Reactive RESTFUL APIs using Spring Boot/WebFlux : Code With Dilip  19](https://www.youtube.com/watch?v=IK26KdGRl48&list=PLnXn1AViWyL70R5GuXt_nIDZytYBnvBdd)


<fieldset>
    <legend>Note:</legend>
    Intellij Idea could throw a maven error 

```diff
-Plugin 'org.springframework.boot:spring-boot-maven-plugin:' not found or can not be resolved
```
that can be solved by adding the version of the plugin in the pom.xml, which is the same of spring boot's version. (and it's working since spring boot 2.2 until 2.7.X)

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <version>${project.parent.version}</version>
    <configuration>
        <excludes>
            <exclude>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </exclude>
        </excludes>
    </configuration>
</plugin>
```
</fieldset>


#### Entities 
[BankAccount Entity](src/main/java/me/elaamiri/bankaccountmicroservice/entities/BankAccount.java)
````java
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
Builder is a design pattern : https://refactoring.guru/design-patterns/builder
Builder is a creational design pattern that lets you construct complex objects step by step. The pattern allows you to produce different types and representations of an object using the same construction code.

*/
public class BankAccount {
    @Id
    private String id;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

}
````

**Notes**:
- [Builder pattern](https://refactoring.guru/design-patterns/builder)


#### Repositories 
[BankAccountRepository](src/main/java/me/elaamiri/bankaccountmicroservice/repositories/BankAccountRepository.java)

````java
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
````

#### Configring our App
````properties
spring.datasource.url=jdbc:h2:mem:accounts-db
spring.h2.console.enabled=true
server.port=8081
````

#### Testing our implementation

In the main point [BankAccountMicroServiceApplication](src/main/java/me/elaamiri/bankaccountmicroservice/BankAccountMicroServiceApplication.java)
````java
@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository){
            return new CommandLineRunner() {
    @Override
    public void run(String... args) throws Exception {
            // creating some test data
            for (int i=0; i<10; i++ ){
            // using the builder
            BankAccount bankAccount = BankAccount.builder()
            .id(UUID.randomUUID().toString())
            .accountType(Math.random()>0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
            .balance(2000+ (Math.random() * 500000))
            .currencyCode(CurrencyCode.MAD)
            .build();
            bankAccountRepository.save(bankAccount);
            }
            }
            };
            }
````



>> It seems like, the application have been started in a big duration (13 seconds),
> and that is not normal.
> As a solution we could create Native applications using

[GraalVM](https://www.graalvm.org/)

> It is a virtual machine lets us recompile jar files to be Native apps.
> "GraalVM is a high-performance JDK distribution designed to accelerate the execution of applications written in Java and other JVM languages along with ..."

[Run Code in Any Language Anywhere with GraalVM : Oracle Developers](https://www.youtube.com/watch?v=JoDOo4FyYMU)

#### Res
Visit : `http:localhost:8081/h2-console`

![H2 db](imgs/h2-db.png)
#### Controllers

[BankAccountRepository](src/main/java/me/elaamiri/bankaccountmicroservice/repositories/BankAccountRepository.java)

````java

````

