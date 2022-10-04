package me.elaamiri.bankaccountmicroservice;

import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.entities.Customer;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import me.elaamiri.bankaccountmicroservice.enumerations.CurrencyCode;
import me.elaamiri.bankaccountmicroservice.repositories.BankAccountRepository;
import me.elaamiri.bankaccountmicroservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountMicroServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository,
							CustomerRepository customerRepository){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				Stream.of("Laila", "Essadeq", "Reda").forEach(
						(cust)-> {
							Customer customer  = Customer.builder()
									.name(cust)
									.build();
							customerRepository.save(customer);
						}
				);

				customerRepository.findAll().forEach((customer -> {
					// creating some test data
					List<BankAccount> bankAccounts = new ArrayList<>();
					for (int i=0; i<5; i++ ){
						// using the builder
						BankAccount bankAccount = BankAccount.builder()
								.id(UUID.randomUUID().toString())
								.accountType(Math.random()>0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
								.balance(2000+ (Math.random() * 500000))
								.currencyCode(CurrencyCode.MAD)
								.customer(customer)
								.build();
						BankAccount saved = bankAccountRepository.save(bankAccount);
						// add it to customer
						bankAccounts.add(saved);
					}

					customer.setBankAccounts(bankAccounts);
					// update
					customerRepository.save(customer);
				}));
			}
		};
	}
}
