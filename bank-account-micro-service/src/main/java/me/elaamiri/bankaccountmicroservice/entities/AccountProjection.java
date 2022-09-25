package me.elaamiri.bankaccountmicroservice.entities;

import me.elaamiri.bankaccountmicroservice.enumerations.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "projection1" , types = BankAccount.class)
public interface AccountProjection {
    /*
    needed attributes
     */

    public Double getBalance();
    public AccountType getAccountType();
}
