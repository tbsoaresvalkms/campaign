package com.tbsoares.customer.repositories;

import com.tbsoares.customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    boolean existsByEmail(String email);

    Customer findByEmail(String email);
}
