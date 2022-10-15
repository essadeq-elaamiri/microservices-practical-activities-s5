package me.elaamiri.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

//	@Bean
//	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
//		return builder.routes()
//				.route(r -> r.path("/customers/**").uri("http://localhost:8081"))
//				.route(r -> r.path("/products/**").uri("http://localhost:8082"))
//				.build();
//	}

//	@Bean // Dynamic using the discovery
//	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
//		return builder.routes()
//				.route(r -> r.path("/customers/**").uri("lb://COSTUMER-SERVICE"))
//				.route(r -> r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
//				.build();
//	}


	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient reactiveDiscoveryClient,
														DiscoveryLocatorProperties discoveryLocatorProperties){
		return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, discoveryLocatorProperties);
	}
}
