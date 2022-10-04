package me.elaamiri.bankaccountmicroservice.controllers;

import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import me.elaamiri.bankaccountmicroservice.repositories.BankAccountRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    private BankAccountRepository bankAccountRepository;

    public BankAccountGraphQLController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    // Using the same names as in the GraphQL Schema
    @QueryMapping // mapping the function to the graphql query
    public List<BankAccount> bankAccounts(){
        return  bankAccountRepository.findAll();
    }

    @QueryMapping
    public List<BankAccount> bankAccountsByType(@Argument String accountType){
        System.out.println(AccountType.valueOf(accountType));
        return bankAccountRepository.findByAccountType(AccountType.valueOf(accountType));
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException(String.format("Account with ID: %s Not Found !", id)));
    }
}
