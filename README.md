# common-configuration
### Configuration management as a microservice using a database store

Properties file work well to externalize application configurations from being embedded in the source code but
often they require the application to be redeployed whenever they are changed. One solution to this issue would
be to have the properties file live outside the application package. This is ok when you have a few applications
but it can be quite tedious and error prone when you have to manage a lot of applications.

With this solution all the application configurations (runtime) are store in a database datastore and are centralized. 

### Benefits

* One place to store and manage
* Easily backed up and versioned
* Easy to manage even with a lot of applications
* Can be clustered 


This service is implemented using JavaEE core APIs and more
* EJB 3.2
* JPA 2.1 
* CDI 1.1
* H2 Database (can be easily adapted to your preferred database(MySQL, Oracle, SQL Server, PostgresSQL etc))
* Apache DeltaSpike
* Wildfly 10

## Roadmap
### To be implemented

* ~~Expose service via REST~~
* Add UI front-End (e.g. JSF)
* Add Security (e.g. Apache Shiro)
