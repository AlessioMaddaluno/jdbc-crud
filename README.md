# jdbc-crud
A Demo for CRUD operations using JDBC in Java (8) on H2 database.
The content of the project consists of:
* The class ``` Client ``` in the project root. It is just an example of DAO repository usage written for the model example;
*  ``` db ``` -  contains a singleton class that manage the database connection giving a single entrypoint for the entire app;
* ``` model ``` - contains a simple model class for a Customer;
* ``` mapper ``` -  contains a mapper class to map the result set from the queries to the object model;
* ``` dao ``` - contains a simple dao implementation for creating a repository to manage the customers;
* ``` bootstrap ``` - contains the creation of the datbase table used for this example;
