package me.elaamiri.bankaccountmicroservice.soap.soapServices;

import lombok.AllArgsConstructor;
import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Component;
import soap_web_service.AccountTypeWS;
import soap_web_service.BankAccountWS;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

@Component
@AllArgsConstructor
public class BankAccountWSServiceImp implements BankAccountWSService {
    BankAccountRepository bankAccountRepository;

    @Override
    public BankAccountWS getBankAccountById(String id) throws DatatypeConfigurationException {
        BankAccount bankAccount =  bankAccountRepository.findById(id).orElseThrow(()-> {
            return new RuntimeException("No Account Found with this ID: "+id);
        });

        BankAccountWS bankAccountWS = new BankAccountWS();
        bankAccountWS.setId(bankAccount.getId());
        bankAccountWS.setBalance(bankAccount.getBalance());
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(bankAccount.getCreatedAt());
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        bankAccountWS.setCreatedAt(date2);
        bankAccountWS.setAccountType(AccountTypeWS.valueOf(bankAccount.getAccountType().toString()));

        return bankAccountWS;
    }
}
