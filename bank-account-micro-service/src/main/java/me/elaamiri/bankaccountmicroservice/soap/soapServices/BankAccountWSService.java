package me.elaamiri.bankaccountmicroservice.soap.soapServices;

import org.springframework.stereotype.Component;
import soap_web_service.BankAccountWS;

import javax.xml.datatype.DatatypeConfigurationException;

@Component
public interface BankAccountWSService {
    BankAccountWS getBankAccountById(String id) throws DatatypeConfigurationException;
}
