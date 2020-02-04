# lesson-5.8 Spring Boot 整合 Spring Data JPA

### Spring Data

*Spring Data 子模块:*
* Spring Data JPA   ——  关系型数据库
* Spring Data MongoDB   ——  非关系型数据库 MongoDB
* Spring Data Redis  ——  非关系型数据库 Redis

***JPA ???***   
*JPA（Java Persistence API）是 Java 持久层规范，定义了一系列 ORM 接口，它本身是不能直接使用的，需要接口实现才能使用  。*

***Spring Data JPA ???***   
*Spring Data JPA 是 Spring 框架提供的对 JPA 规范的抽象，在不需要实现接口的情况下，就可以完成对数据苦的操作。简而言之就是通过 Spring Boot JAP，只需要定义接口，不需要实现，就能完成 CRUD 操作。*

* 1、创建简单 Maven 工程，pom.xml 中添加相关依赖，此处 spring-boot-starter-data-jpa 是 Spring Boot 整合 Spring Data JPA 的关键依赖代码。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>springboot6</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!-- 继承父包 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.4.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <dependencies>
        <!-- web启动jar -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--Spring Data JPA 依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>

</project>
```
* 2、创建数据表
![image](D2246DD13DC345E1A083BA535040776A)

* 3、创建数据表对应的实体类，Spring Data JPA 支持 ORM 规范，可以直接将实体类于数据表进行映射，具体一行数据可以直接映射成一个实例化对象。常用注解如下：
+ @Entity 将实体类与数据表进行映射。
+ @Id 将实体类中的成员变量与数据表的主键进行映射，一般是 id。
+ @GenerateValue 表示自动生成主键。
+ @column 将实体类中的成员变量与数据表的普通字段进行映射。   

*实体类具体代码：*
```java
package xyz.fusheng.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Double score;
    @Column
    private Date birthday;
}
```
* 4、创建 StudentRepository 接口类   

```java
package xyz.fusheng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fusheng.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
```
***PS : 此处StudentREpository接口，直接继承JpaRepository 接口即可，泛型列表的第一个为实体类数据类型（此处为Student），第二个为实体类中于主键映射的成员变量数据类型（此处为Long）。***   
***同时，我们只需要定义接口并且继承Jparepository接口，这一点和 MyBatis 相识，只是使用 Spring Data JPA 比 MyBatis 更加方便，MyBatis 虽然不用自定义实现 Mapper 接口，但是需要自定义方法的 SQL,同时设置解析策略，所以 MyBatis 是一个半自动化的 ORM 框架。***    
***Spring Data JPA 是一个全自动化的 ORM 框架，底层是 Hibernate 框架提供支持，开发者只需要调用接口方法即可，不必关心具体 SQL 语句和结果集的解析，非常方便。***

* 5、创建 StudentHandler，同时将 StudentRepository 直接注入。
```java
package xyz.fusheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.entity.Student;
import xyz.fusheng.repository.StudentRepository;

import java.util.List;

@RestController
public class StudentHandler {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/findAll")
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") Long id){
        return studentRepository.findById(id).get();
    }

    @PostMapping("/save")
    public Student save(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @PutMapping("/update")
    public Student update(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id){
        studentRepository.deleteById(id);
    }
}
```
* 6、创建配置文件 application.yml，添加了 MySQL 数据源和 Spring Data JPA 的相关配置，如果格式化打印 SQL 语句。
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot6?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Hongkong
    username: root
    password: sa123
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    show-sql: true            # 输出 SQL
    properties:
      hibernate:
        format_sql: true      # 格式化 SQL
```
* 7、创建启动类 Application。
```java
package xyz.fusheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
```
* 8、启动 Application，使用 Postman 工具来测试接口。[和之前的操作一样，只是多了控制台会输出SQL语句]
