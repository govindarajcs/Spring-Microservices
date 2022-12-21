# What is a microservice?

Microservice is an REST webservice application which has only application related endpoints exposed. In otherwords it can be said like an atomic webservice application which cannot be broken further into multiple smaller applications.

## How to create a microservice application or microservice webservice?

In order to create a microservice application, just do the following 
 <ol>
  <li> Go to the website spring.start.io </li>
  <li> Add the following dependency
     <ol>
       <li>spring-web</li>
       <li>spring-devtools</li>
       <li>spring-actuator</li>
       <li>config-client</li>
    </ol>
    **Note** <br>
    Although the spring-actuator is not required but for monitoring purpose it can be added. config-client dependency is added to support accessing the configuration parameters from the config-server.
  </li>
  <li>Add the following in the application.properties
  	<ol>
  		<li>
  			server.address = 127.0.0.1 defines the hostname of the application 
  		</li>
  		<li>
  			server.port = 7001 defines the port number on which the application is running
  		</li>
  		<li>
  			server.servlet.context-path=/user-inventory defines the root path of the application
  		</li>
  		<li>
  			spring.application.name=user-inventory
  		</li>
        <li>
        	spring.config.import=configserver:http://localhost:8000 define the config server url
        </li>
  		<li>
  			spring.profiles.active=dev define the profile name using which configuration properties defined in config server named as <spring.application.name>-<spring.profile.active>.properties.
  		</li>
  		<li>
  			eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka define the url of the eureka server for registering this service endpoints to naming server Eureka.
  		</li>
  		<li>
  			eureka.instance.hostname=localhost define the hostname of the eureka which is required for intercommunication of microservices registered within the same Eureka server.
  		</li>
  	</ol>
  </li>
 </ol>