package me.elaamiri.bankaccountmicroservice.services;

import me.elaamiri.bankaccountmicroservice.dto.BankAccountRequestDTO;
import me.elaamiri.bankaccountmicroservice.dto.BankAccountResponseDTO;
import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import me.elaamiri.bankaccountmicroservice.mappers.BankAccountMapper;
import me.elaamiri.bankaccountmicroservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional // import org.springframework.transaction.annotation.Transactional;
public class BankAccountServiceImp implements BankAccountService {
    BankAccountRepository bankAccountRepository;
    BankAccountMapper bankAccountMapper;
    public BankAccountServiceImp(BankAccountRepository bankAccountRepository,  BankAccountMapper bankAccountMapper){
        this.bankAccountRepository  = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public BankAccount getBankAccountById(String id){
        BankAccount bankAccount =  bankAccountRepository.findById(id).orElseThrow(()-> {
           return new RuntimeException("No Accound Found with this ID: "+id);
        });
        return bankAccount;
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
        /*
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .accountType(bankAccountDTO.getAccountType())
                .currencyCode(bankAccountDTO.getCurrencyCode())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .build();

         */
        // Using mappers

        BankAccount account = bankAccountMapper.fromBankAccountRequestDTO(bankAccountDTO);
        // giving IDs
        account.setId(UUID.randomUUID().toString());
        account.setCreatedAt(new Date());
        System.out.println(account);

        BankAccount saved = bankAccountRepository.save(account);
        /*
        return BankAccountResponseDTO.builder()
                .id(savedAccount.getId())
                .balance(savedAccount.getBalance())
                .createdAt(savedAccount.getCreatedAt())
                .accountType(savedAccount.getAccountType())
                .currencyCode(savedAccount.getCurrencyCode())
                .build();

         */
        return bankAccountMapper.fromBankAccount(saved);
    }

    @Override
    public BankAccountResponseDTO updateAccount(String accountId, BankAccountRequestDTO bankAccount) {
        BankAccount bankAccount1 = bankAccountRepository.findById(accountId).orElseThrow(
                ()-> new RuntimeException(String.format("Account with ID: %s Not Found!", accountId))
        );
        BankAccount account = bankAccountMapper.fromBankAccountRequestDTO(bankAccount);
        account.setId(accountId);
        BankAccount saved = bankAccountRepository.save(account);
        return bankAccountMapper.fromBankAccount(saved);
    }

}
