package me.elaamiri.customerservice;

import me.elaamiri.customerservice.entities.Customer;
import me.elaamiri.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return  args -> {
			customerRepository.save(new Customer(null, "Essadeq", "essade@gmail.com"));
			customerRepository.save(new Customer(null, "Mariam", "mariam@gmail.com"));
			customerRepository.save(new Customer(null, "Laila", "la88745@gmail.com"));
			customerRepository.save(new Customer(null, "Consal", "sal87@gmail.com"));
			customerRepository.save(new Customer(null, "Zadeq", "pakista@gmail.com"));

			customerRepository.findAll().forEach(System.out::println);
		};
	}
}
