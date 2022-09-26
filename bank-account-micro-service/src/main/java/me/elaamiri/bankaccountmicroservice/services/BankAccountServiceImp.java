package me.elaamiri.bankaccountmicroservice.services;

import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import me.elaamiri.bankaccountmicroservice.repositories.BankAccountRepository;

public class BankAccountServiceImp implements BankAccountService {
    BankAccountRepository bankAccountRepository;
    public BankAccountServiceImp(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository  = bankAccountRepository;
    }
    @Override
    public BankAccount insertAccount(Double initialBalance, AccountType accountType) {
        /*
        Doing some 'metier' code
         */
        return null;
    }
}
