### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues


#### Redis Caching
- Download Redis: http://download.redis.io/releases/redis-6.0.8.tar.gz
- tar xzf redis-6.0.8.tar.gz
- Go to redis-6.0.8
- Run "make"
- Click on src/redis-server
- Click on src/redis-cli
- Added the Logger to distinguish whether the call is made to the DB or to the Redis.
- Implemented Caching for 
	* Get Employee By ID
	* Delete Employee By ID
	* Update Employee By ID 
	
This will connect the Application to the Redis port 6379 by default.
 
 
#### Lombok Changes
- Removed the @Getter & @Setter Annotation from jp.co.axa.apidemo.entities.Employee.java
- Added Lombok's @Data at the Class Level, @NoArgsConstructor, @AllArgsConstructor.
- @Data Annotation bundles @ToString, @EqualsAndHashCode, @Getter on all Fields, 
  @Setter on all non final Fields and @RequiredArgsConstructor
      
      
#### Controller UTs
- Added the UTs for all the Controllers.


#### Java Documentation
- Added the Java Documentation to all classes and UTs.


#### Performance Improvement
- After implementation of the cache there is improvement in response time.