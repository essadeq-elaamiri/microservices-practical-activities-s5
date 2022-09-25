package me.elaamiri.bankaccountmicroservice.repositories;

import me.elaamiri.bankaccountmicroservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
