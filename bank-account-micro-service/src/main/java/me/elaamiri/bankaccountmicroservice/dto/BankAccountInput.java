package me.elaamiri.bankaccountmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import me.elaamiri.bankaccountmicroservice.enumerations.CurrencyCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountInput {
    private Float balance;
    private CurrencyCode currencyCode;
    private AccountType accountType;
}
