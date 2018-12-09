# Temperature Data Sensor

>Java 8, Spring-Boot, Spring-MVC, Spring-Security, Spring-Data Jpa

It is a simple server, which validates the temperature statistic and stores it.
Sensor sends location data (coordinates) and temperature. The user is able to get the list of last 10 inputs, also there 
is a possibility to filter inputs by current location.


To run this application:
1. Clone the repository.
2. Download and install PostgreSql
    https://www.postgresql.org/download/
3. In application.properties file you have to add this settings
    ~~~
        spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    ~~~
    You have to point port of your local database location (5432 is default), 
    also next two properties point your username and password
    ~~~
        spring.datasource.username=postgres
        spring.datasource.password=
    ~~~
    The database name "postgres" is default.
4. Run Application class from the Idea
 
 
 
