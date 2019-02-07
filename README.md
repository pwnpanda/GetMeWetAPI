Things to do before deploying:
- Set up mysql db
- Implement Security feature (login - https://www.baeldung.com/spring-security-login )
- Setup application.properties correctly
    * spring.jpa.hibernate.ddl-auto=update
- In mysql server:
    * mysql> revoke all on db_example.* from 'springuser'@'localhost';
    * mysql> grant select, insert, delete, update on db_example.* to 'springuser'@'localhost';

Resources:
- http://websystique.com/spring-boot/spring-boot-rest-api-example/
- https://medium.com/@salisuwy/building-a-spring-boot-rest-api-part-iii-integrating-mysql-database-and-jpa-81391404046a
- https://springframework.guru/spring-boot-web-application-part-1-spring-initializr/
- Full code: https://github.com/springframeworkguru/springbootwebapp/tree/part6

