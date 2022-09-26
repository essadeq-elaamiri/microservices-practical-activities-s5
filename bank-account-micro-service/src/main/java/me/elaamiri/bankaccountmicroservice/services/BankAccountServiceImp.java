package me.elaamiri.bankaccountmicroservice.services;

import me.elaamiri.bankaccountmicroservice.dto.BankAccountDTO;
import me.elaamiri.bankaccountmicroservice.dto.BankAccountRequestDTO;
import me.elaamiri.bankaccountmicroservice.dto.BankAccountResponseDTO;
import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import me.elaamiri.bankaccountmicroservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional // import org.springframework.transaction.annotation.Transactional;
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

    @Override
    public BankAccountResponseDTO insertAccount(BankAccountRequestDTO bankAccountDTO) {
        // Mapping
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .accountType(bankAccountDTO.getAccountType())
                .currencyCode(bankAccountDTO.getCurrencyCode())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .build();
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);

        return BankAccountResponseDTO.builder()
                .id(savedAccount.getId())
                .balance(savedAccount.getBalance())
                .createdAt(savedAccount.getCreatedAt())
                .accountType(savedAccount.getAccountType())
                .currencyCode(savedAccount.getCurrencyCode())
                .build();
    }

}
