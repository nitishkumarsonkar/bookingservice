package com.example.bookingservice.response;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryResponse {
    private String name;
    private Long leftCapacity;
    private VenueInventoryResponse venue;
    private BigDecimal ticketPrice;

}
