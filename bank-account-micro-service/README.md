## Activity 1 
### Micro-services Spring 
#### Initializing the project 

![initilizr](imgs/init.png)


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


#### 