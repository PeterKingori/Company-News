# Company News
## Description
This is a REST API for querying and retrieving news and information within a company. There can
 be articles with general news concerning the whole company or news for specific departments.
## Technologies used
1. Java
2. Spark
3. Postman
4. Gradle
5. Postgresql

## Setup/Installation
1. Install Postgres SQL to be able to create a local database on your machine.
2. Fork this repository.
3. Clone the repository to your machine.
4. Open the folder in your IDE of choice.
5. Run the command `postgres` in a terminal.
6. Create two `sql` files named `create.sql` and `drop.sql`. The `create.sql` file should contain
 the following commands
        ``` CREATE DATABASE company_news;
            \c company_news;
            
            CREATE TABLE IF NOT EXISTS users (
            id SERIAL PRIMARY KEY,
            name VARCHAR,
            position VARCHAR,
            role VARCHAR,
            department VARCHAR
            );
            
            CREATE TABLE IF NOT EXISTS news (
            id SERIAL PRIMARY KEY,
            title VARCHAR,
            content VARCHAR,
            type VARCHAR,
            author VARCHAR
            );
            
            CREATE TABLE IF NOT EXISTS departments (
            id SERIAL PRIMARY KEY,
            name VARCHAR,
            description VARCHAR,
            totalemployees INTEGER
            );
            
            
            CREATE DATABASE company_news_test WITH TEMPLATE company_news;```
            
 The `drop.sql` file should have the following commands
         ```DROP DATABASE company_news_test;
            DROP DATABASE company_news; ``` 
 
 7.Run the command `psql < create.sql` in a separate terminal window.
 
 8.Go to the IDE and navigate to the folder with the main file App.java, then compile and run the
  program on the
  terminal. Alternatively, run the program in your IDE.
 9.In the terminal, locate the url address showing the spark server port number. For example
 , this can be http://localhost:4567/user
 You can then change the routes to access different pages.
  
 ## Support  and Contacts
 Peter Kingori Ndegwa. <br/> If you run into any issues or would like to make a contribution to the
  work kindly email me at kpndegwa@gmail.com
 
 ## License
 This project is free to use under the **GNU General Public License**. See the full [LICENSE](https://choosealicense.com/licenses/gpl-3.0/) for details.