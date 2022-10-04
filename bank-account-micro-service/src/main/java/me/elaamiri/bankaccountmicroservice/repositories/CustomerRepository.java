package me.elaamiri.bankaccountmicroservice.repositories;

import me.elaamiri.bankaccountmicroservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
