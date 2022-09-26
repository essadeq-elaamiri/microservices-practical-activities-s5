package me.elaamiri.bankaccountmicroservice.mappers;

import me.elaamiri.bankaccountmicroservice.dto.BankAccountRequestDTO;
import me.elaamiri.bankaccountmicroservice.dto.BankAccountResponseDTO;
import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import org.springframework.stereotype.Component;

@Component
public interface BankAccountMapper {
    BankAccount fromBankAccountRequestDTO(BankAccountRequestDTO bankAccountRequestDTO);
    BankAccountResponseDTO fromBankAccount(BankAccount bankAccount );
}
