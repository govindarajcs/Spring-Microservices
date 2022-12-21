# What is Config Server?
As the name itself indicates that config server is a centralized configuration repository for microservice applications.

## What are the ways to store the configuration files?

There are different modes of creating and maintaining config server. They are 

1. Store them in git repository and microservice application can access them via config sever.
2. Store them directly in the config server location.
3. Store them in the database and access the database from the config server.

**Note** <br>
The difference between the first and second approach is that in the first case, there will be a separate git config location where configuration files are stored and they can be accessible by config server which is in different location but in the second case configuration files and server are stored in the same location.

## How to create a config-server?

In order to create a configuration server, do the following 
<ol>
<li>. In the https://start.spring.io/ create a project by adding the following dependency
     <ol>
      <li>spring-devtools</li>
      <li>config-server</li>
     </ol>
     </li>
<li>Add the following annotation in the main class where @SpringBootApplication is defined 
   
> @EnableConfigServer
</li>

<li> Add the following parameters in the application.properties
      <ol>
       <li>spring.application.name=config-server</li>
       <li>server.port=8000</li>
       <li>spring.profiles.active=native (If the configuration properties file is maintained within the config-server and they are accessible from the same server then set this parameter to value native)</li>
     <li>Keep the properties file under src/main/resources folder in the following format </li>
	
> **<application-name>-<profile>**.properties
>
>> where,
>> application-name is the name of the application defined in bootstrap.properties defined in microservice (e.g. spring.application.name=<application-name>)
>> profile is the name of the application defined in bootstrap.properties defined in microservice application not in the config server application.properties (e.g. spring.profiles.active=dev (refer rbac project bootstrap.properties)
</ol>
</li>
</ol>

** Note** <br>
When the microservice application is using config server based architecture then in the application microservice instead of using application.properties use bootstrap.properties.