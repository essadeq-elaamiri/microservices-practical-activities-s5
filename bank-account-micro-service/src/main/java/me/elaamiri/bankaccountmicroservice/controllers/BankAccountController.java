package me.elaamiri.bankaccountmicroservice.controllers;

import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BankAccountController {
    // Dependency injection by constructor
    BankAccountRepository bankAccountRepository;
    public BankAccountController(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository = bankAccountRepository;
    }


    // Routes
    @GetMapping("/bankAccounts")
    /*
    The route has the same name of the entity
     */
    public List<BankAccount> getBankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{bankAccountId}")
    public BankAccount getBankAccount(@PathVariable(name = "bankAccountId") String bankAccountId){
        return bankAccountRepository.findById(bankAccountId)
                .orElseThrow(()-> new RuntimeException(String.format("No bankAccount with the ID: %s found !", bankAccountId)));

    }

    @PostMapping("/bankAccounts")
    public BankAccount saveBankAccount(@RequestBody(required = true) BankAccount bankAccount){
        bankAccount.setId(UUID.randomUUID().toString());
        return  bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{bankAccountId}")
    public BankAccount updateBankAccount(@RequestBody(required = true) BankAccount bankAccount, @PathVariable String bankAccountId){
        BankAccount bankAccount1 = bankAccountRepository.findById(bankAccountId).orElse(null);
        if(bankAccount1 == null) new RuntimeException(String.format("No bankAccount with the ID: %s found !", bankAccountId));
        if (bankAccount.getBalance() != null) bankAccount1.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt() != null) bankAccount1.setCreatedAt(bankAccount.getCreatedAt());
        if (bankAccount.getAccountType() != null) bankAccount1.setAccountType(bankAccount.getAccountType());
        if (bankAccount.getCurrencyCode() != null) bankAccount1.setCurrencyCode(bankAccount.getCurrencyCode());
        return bankAccountRepository.save(bankAccount1);
    }

    @DeleteMapping("/bankAccounts/{bankAccountId}")
    public void deleteBankAccount(@PathVariable String bankAccountId){
        bankAccountRepository.deleteById(bankAccountId);
    }
}
