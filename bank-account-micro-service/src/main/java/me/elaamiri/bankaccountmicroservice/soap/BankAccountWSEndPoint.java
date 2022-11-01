package me.elaamiri.bankaccountmicroservice.soap;

import lombok.AllArgsConstructor;
import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import me.elaamiri.bankaccountmicroservice.services.BankAccountService;
import me.elaamiri.bankaccountmicroservice.soap.soapServices.BankAccountWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap_web_service.GetBankAccountRequest;
import soap_web_service.GetBankAccountResponse;


import javax.websocket.server.ServerEndpoint;
import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
@AllArgsConstructor
public class BankAccountWSEndPoint {
    private static final String NAMESPACE_URI = "soap_web_service";

    BankAccountWSService bankAccountWSService;
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBankAccountRequest")
    @ResponsePayload
    public GetBankAccountResponse getBankAccount(@RequestPayload GetBankAccountRequest request) throws DatatypeConfigurationException {
        GetBankAccountResponse response = new GetBankAccountResponse();
        response.setBankAccountType(bankAccountWSService.getBankAccountById(request.getId()));
        return  response;
    }
}
