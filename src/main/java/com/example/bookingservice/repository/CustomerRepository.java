package com.example.bookingservice.repository;

import com.example.bookingservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query methods can be defined here if needed
}
