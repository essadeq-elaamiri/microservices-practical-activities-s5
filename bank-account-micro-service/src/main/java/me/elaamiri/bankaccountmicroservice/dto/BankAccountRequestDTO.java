package me.elaamiri.bankaccountmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import me.elaamiri.bankaccountmicroservice.enumerations.CurrencyCode;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountRequestDTO {
    /**
     * For request DTOs we keep just what we need in input
     */
    private Double balance; // to avoid error on update
    private CurrencyCode currencyCode;
    private AccountType accountType;
}
