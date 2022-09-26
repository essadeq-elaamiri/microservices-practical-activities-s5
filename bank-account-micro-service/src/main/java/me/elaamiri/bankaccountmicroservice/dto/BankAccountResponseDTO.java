package me.elaamiri.bankaccountmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import me.elaamiri.bankaccountmicroservice.enumerations.CurrencyCode;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountResponseDTO {
    private String id;
    private Date createdAt;
    // private double balance;
    private Double balance; // to avoid error on update
    private CurrencyCode currencyCode;
    private AccountType accountType;
}
