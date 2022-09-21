## Activity 1 
### Micro-services Spring 
#### Initializing the project 

![initilizr](imgs/init.png)


There is two types of Spring:
1. Classic Spring MVC, Based on the imperative programming.
2. Reactive Spring (WebFlux), Based on the interactive programming.

Resources:
(Part 1- De L'impératif vers le réactif- Reactive Programming avec Spring Web FLUX-Concepts de base: Professeur Mohamed YOUSSFI)[https://www.youtube.com/watch?v=h58yIiHgoBg ]
(Build Reactive RESTFUL APIs using Spring Boot/WebFlux : Code With Dilip  19)[https://www.youtube.com/watch?v=IK26KdGRl48&list=PLnXn1AViWyL70R5GuXt_nIDZytYBnvBdd]


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

#### Repositories 
[BankAccountRepository](src/main/java/me/elaamiri/bankaccountmicroservice/repositories/BankAccountRepository.java)

````java
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
````

#### Controllers

[BankAccountRepository](src/main/java/me/elaamiri/bankaccountmicroservice/repositories/BankAccountRepository.java)

````java

````

