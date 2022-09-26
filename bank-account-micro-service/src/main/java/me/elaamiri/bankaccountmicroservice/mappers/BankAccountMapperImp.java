package me.elaamiri.bankaccountmicroservice.mappers;

import me.elaamiri.bankaccountmicroservice.dto.BankAccountRequestDTO;
import me.elaamiri.bankaccountmicroservice.dto.BankAccountResponseDTO;
import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapperImp implements BankAccountMapper{
    @Override
    public BankAccount fromBankAccountRequestDTO(BankAccountRequestDTO bankAccountRequestDTO) {
        //
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountRequestDTO, bankAccount);
        return bankAccount;
    }

    @Override
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
}
