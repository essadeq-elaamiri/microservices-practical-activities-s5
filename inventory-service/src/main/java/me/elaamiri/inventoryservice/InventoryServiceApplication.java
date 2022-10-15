package me.elaamiri.inventoryservice;

import me.elaamiri.inventoryservice.entities.Product;
import me.elaamiri.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			productRepository.save(new Product(null, "Lenovo w541", 7400.99));
			productRepository.save(new Product(null, "HP 478S", 10000.99));
			productRepository.save(new Product(null, "MacPro 2023", 17400.99));
			productRepository.save(new Product(null, "KitMan hj", 700.99));

			productRepository.findAll().forEach(System.out::println);
		};
	}
}
