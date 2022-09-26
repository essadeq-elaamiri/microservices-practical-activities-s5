package me.elaamiri.bankaccountmicroservice.services;

import me.elaamiri.bankaccountmicroservice.dto.BankAccountDTO;
import me.elaamiri.bankaccountmicroservice.dto.BankAccountRequestDTO;
import me.elaamiri.bankaccountmicroservice.dto.BankAccountResponseDTO;
import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;

public interface BankAccountService {
    // Without DTO
    public BankAccount insertAccount(Double initialBalance, AccountType accountType);

    // using DTOs: that is one cause why we should use DTOs
    public BankAccountResponseDTO insertAccount(BankAccountRequestDTO bankAccountDTO);
}
