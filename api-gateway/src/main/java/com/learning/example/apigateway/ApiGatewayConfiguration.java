
package com.learning.example.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator; 
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;

@Configuration 
public class ApiGatewayConfiguration {
	
	
	@Bean 
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes() .route(p ->
		p.path("/rbac/**").uri("lb://rbac/**"))
				.route(p->p.path("/user-inventory/**").uri("lb://user-inventory/**"))
				.build();
		
	}
}
