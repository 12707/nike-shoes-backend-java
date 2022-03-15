### Nike Interview Backend Code Challenge [Java]

_The provided code document should contain more details._

This project uses Maven & Spring Boot (Web Starter Pack). So you will need to install `java (> 1.8)` & `maven` on your machine first.
For more information, you can visit the [Spring Boot docs](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started.introducing-spring-boot)

1. You can run `mvn dependency:tree` to list/install the maven dependencies used in this project.
2. To run the server (on port 8081), use this command: `mvn spring-boot:run`

APIs:

1. Get original price (randomly fetched) for a supplied shoe id:
```
URL (GET) - http://localhost:8081/api/shoe-price/1

Response:
{
    "shoePrice": 147
}
```
