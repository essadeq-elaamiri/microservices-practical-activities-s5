package me.elaamiri.bankaccountmicroservice.controllers;

import lombok.AllArgsConstructor;
import me.elaamiri.bankaccountmicroservice.dto.BankAccountRequestDTO;
import me.elaamiri.bankaccountmicroservice.dto.BankAccountResponseDTO;
import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import me.elaamiri.bankaccountmicroservice.mappers.BankAccountMapper;
import me.elaamiri.bankaccountmicroservice.repositories.BankAccountRepository;
import me.elaamiri.bankaccountmicroservice.services.BankAccountService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class BankAccountGraphQLController {
    private BankAccountRepository bankAccountRepository;
    private BankAccountMapper bankAccountMapper;
    BankAccountService bankAccountService;


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

    @MutationMapping
    public BankAccount createBankAccount(@Argument BankAccountRequestDTO bankAccount){
        //System.out.println(bankAccount);
        BankAccount toBeSaved = bankAccountMapper.fromBankAccountRequestDTO(bankAccount);
        // Should use the service layer, but just to skip the process I do this
        toBeSaved.setId(UUID.randomUUID().toString());
        toBeSaved.setCreatedAt(new Date());
        return bankAccountRepository.save(toBeSaved);
    }

    @MutationMapping
    public BankAccountResponseDTO updateBankAccount(@Argument String accountId,
                                                    @Argument BankAccountRequestDTO bankAccount){
        // Should use the service layer, but just to skip the process I do this

        return bankAccountService.updateAccount(accountId, bankAccount);
    }

    @MutationMapping
    public String  deleteBankAccount(@Argument String accountId){
        bankAccountRepository.deleteById(accountId);
        return accountId;
    }

    /// REcord (Class alias, Java will generate the rest of it )
 record BankAccountDTOInput(Float balance, String accountType, String currencyCode){}
}

