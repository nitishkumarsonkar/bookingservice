package com.example.bookingservice.client;

import com.example.bookingservice.response.EventInventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {
    // This class will be used to interact with the Inventory Service
    // You can use RestTemplate or WebClient to make HTTP calls to the Inventory Service
    // For example, using RestTemplate:

    // private final RestTemplate restTemplate;

    // public InventoryServiceClient(RestTemplate restTemplate) {
    //     this.restTemplate = restTemplate;
    // }

    // public VenueInventoryResponse getVenueInventory(Long venueId) {
    //     String url = "http://inventory-service/venues/" + venueId;
    //     return restTemplate.getForObject(url, VenueInventoryResponse.class);
    // }

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

     public EventInventoryResponse getEventInventory(Long eventId) {
          final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl+"/event/"+ eventId, EventInventoryResponse.class);
     }
}
