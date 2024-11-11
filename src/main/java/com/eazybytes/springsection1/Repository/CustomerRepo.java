package com.eazybytes.springsection1.Repository;

import com.eazybytes.springsection1.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
}
