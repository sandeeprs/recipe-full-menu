# Recipe Menu
This is a java spring boot rest application that inserts, retrives, updates and deletes recipes. 


## About the Recipe Menu
* Each recipes describes the region it belongs to. 
* Each recipes is categorize into veg or nonveg and number of people the disk is intake to.
* Ingredients, the dish need is too mentioned along with the cooking instructions.

## Install & Running
 
### Prerequisites
* 
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  - Programming language
* [Maven 3.3 +](https://maven.apache.org/download.cgi) - Build tool
* [MySql Installer 8.0.23] (https://dev.mysql.com/downloads/installer/)
* Executing/Running recipe.sql file on mysql workbench.

### Pull from git 
```
$ git clone https://github.com/maruf571/kalah
$ cd kalah
```

### Build & run 

* Run test
```
$ mvn test
```

* Run the web server on dev mode
```
$ mvn spring-boot:run
```

* Run the web server on prod mode
```
$ mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### Rest Services
After running the project & browse
Authentication : http://localhost:8080
Get all the recipes:  http://localhost:8080/recipes
Get 1 recipe  basedd on Id: http://localhost:8080/recipes/{id}
Adding/posting new recipe: http://localhost:8080/recipes
Updating recipe based on Id: http://localhost:8080/recipes/{id}
Deleting any Id: http://localhost:8080/recipes/{id}

** Replace '{id}' with integer value

## Built With
* [Spring boot 2.0.X](https://projects.spring.io/spring-boot/) -Backed Framework
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Sandeep Sharma**