# Microservices


[Part 1: practical app](#microservices-practical-app)
[Part 2: technical info](#technical-info)

## Microservices practical app

### Creating customer-service microservice
Installing extensions :

```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-rest</artifactId>
        </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
	</dependencies>

```

- **Spring Web** : Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- **Spring Data JPA** : Persist data in SQL stores with Java Persistence  API using Spring Data and Hibernate.
- **H2 Database** : Provides a fast in-memory database that supports JDBC API and R2DBC access, with a small (2mb) footprint. Supports embedded and server modes as well as a browser-based console application.
- **Rest Repositories** : Exposing Spring Data repositories over REST via  Spring Data REST.
- **Lombok**: Java annotation library which helps to reduce boilerplate code.
- **Spring Boot DevTools** : Provides fast application restarts,  LiveReload, and configurations for enhanced development experience.
- **Eureka Discovery Client** : a REST based service for locating services for the purpose of load balancing and failover of middletier servers.
- **Spring Boot Actuator** : Supports built in (or custom) endpoints that  let you monitor and manage your application - such as application  health, metrics, sessions, etc.
- **Spring validation**: validating entity fields.

Properties file :
````properties
spring.cloud.discovery.enabled= false
server.port=8081
spring.application.name=customer-service

````
About Spring boot validation : https://reflectoring.io/bean-validation-with-spring-boot/

Some of the most common validation annotations are:

- @NotNull: to say that a field must not be null.
- @NotEmpty: to say that a list field must not empty.
- @NotBlank: to say that a string field must not be the empty string (i.e. it must have at least one character).
- @Min and @Max: to say that a numerical field is only valid when it’s value is above or below a certain value.
- @Pattern: to say that a string field is only valid when it matches a certain regular expression.
- @Email: to say that a string field must be a valid email address.

Customer entity:

````java

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;
}

````

Repository + rest sources

````java

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

````

In the application we test

```java
@SpringBootApplication
public class CustomerServiceApplication {
    //..... main 
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return  args -> {
            customerRepository.save(new Customer(null, "Essadeq", "essade@gmail.com"));
            customerRepository.save(new Customer(null, "Mariam", "mariam@gmail.com"));
            customerRepository.save(new Customer(null, "Laila", "la88745@gmail.com"));
            customerRepository.save(new Customer(null, "Consal", "sal87@gmail.com"));
            customerRepository.save(new Customer(null, "Zadeq", "pakista@gmail.com"));

            customerRepository.findAll().forEach(System.out::println);
        };
    }
}
```

Result :

````textmate
Customer(id=1, name=Essadeq, email=essade@gmail.com)
Customer(id=2, name=Mariam, email=mariam@gmail.com)
Customer(id=3, name=Laila, email=la88745@gmail.com)
Customer(id=4, name=Consal, email=sal87@gmail.com)
Customer(id=5, name=Zadeq, email=pakista@gmail.com)
````

Everything is fine, but why `System.out::println` :
<details>
The method reference System.out::println will evaluate System.out first, then create the equivalent of a lambda expression which captures the evaluated value. Usually, you would use
o -> System.out.println(o) to achieve the same as the method reference, but this lambda expression will evaluate System.out each time the method will be called.

So an exact equivalent would be:

````java
PrintStream p = Objects.requireNonNull(System.out);
numbers.forEach(o -> p.println(o));
````

which will make a difference if someone invokes System.setOut(…); in-between.
link : https://stackoverflow.com/questions/28023364/what-is-the-equivalent-lambda-expression-for-system-outprintln 
</details>



------------------------------------------------------------

## Technical info

How microservices work ?
