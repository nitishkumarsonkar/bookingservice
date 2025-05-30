package com.example.bookingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Long id;
    private String name;
    private String email;
    private String address;

    // Constructors, getters, and setters can be generated by Lombok
}
