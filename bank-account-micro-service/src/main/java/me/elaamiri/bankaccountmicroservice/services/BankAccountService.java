package me.elaamiri.bankaccountmicroservice.services;

import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;

public interface BankAccountService {
    // Without DTO
    public BankAccount insertAccount(Double initialBalance, AccountType accountType);
}
