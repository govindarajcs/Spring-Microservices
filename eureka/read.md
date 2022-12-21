# Eureka The naming server

## What is Eureka?

Eureka is a microservice application naming server.

Microservice endpoints are registered with the application name defined in the application.properties file. So that any microservices can contact other microservice by application name instead of using hostname and port as they are not constant i.e. if the microservice that is hosted in a container goes down then the ip address of the microservice gets changed and hence consumer has to update the ip address before making the request but with this Eureka naming server ip address and port number is mapped to application name and hence consumer can only need to specify the application name but not the hostname and port.

## How to create a Eureka server?

Just follow the following steps to create an Eureka server using spring boot

  1. In the start.spring.io website, speicfy the artifact id and group id.
  2. Now add the following dependencies
    a. spring-web
    b. spring dev-tools
    c. eureka-server
    d. spring-config-server(to commnuicate with config server, but this is not required)
    
  3. Now generate the project.
  4. Once generated add the following properties in the application.properties.
     1. spring.application.name=eureka-server (name of the application which will replace the host name and port no when accessing this microservice)
     2. server.port=8761
     3. eureka.client.register-with-eureka=false (Eureka is a naming server and it is also a microservice and in order to avoid self-registering this microservice to this naming server, set this property to false)
     4. eureka.client.fetch-registry=false (In order to avoid fetching the metadata information about the microservice endpoints from eureka server this property is set).
  5. Add the following annotation in the where @SpringBootApplication is defined <br> 

> @EnableEurekaServer

