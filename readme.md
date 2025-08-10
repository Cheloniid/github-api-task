# GitHub API Task

Spring Boot backend that presents specified user repositories with branches, retrieved from GitHub.

Displays only non-fork repositories.  
  
Endpoint:   
'/repo/{username}'

Default port: 8080


## Technologies:
- Java 21
- Spring Boot 3
- Maven

## Usage

### IntelliJ IDEA users
- File -> New -> Project from Version Control
- Enter URL: https://github.com/Cheloniid/github-api-task
- Enter destination
- Clone
- To run application: right-click on GithubApiTaskApplication -> Run
- To run tests: right-click on GithubApiTaskApplicationTests -> Run

### CLI users

```bash
git clone https://github.com/Cheloniid/github-api-task
cd github-api-task
mvn clean install
mvn spring-boot:run
```

To run tests:

```bash
mvn test
```  
  
## Example usage:

Request:  
'GET http://localhost:8080/repo/cheloniid'
