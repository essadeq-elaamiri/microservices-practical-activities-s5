package me.elaamiri.bankaccountmicroservice.repositories;

import me.elaamiri.bankaccountmicroservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
