package me.elaamiri.bankaccountmicroservice;

import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import me.elaamiri.bankaccountmicroservice.enumerations.CurrencyCode;
import me.elaamiri.bankaccountmicroservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class BankAccountMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountMicroServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				// creating some test data
				for (int i=0; i<10; i++ ){
					// using the builder
					BankAccount bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.accountType(Math.random()>0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
							.balance(2000+ (Math.random() * 500000))
							.currencyCode(CurrencyCode.MAD)
							.build();
					bankAccountRepository.save(bankAccount);
				}
			}
		};
	}
}
