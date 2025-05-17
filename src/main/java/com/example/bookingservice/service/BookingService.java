package com.example.bookingservice.service;

import com.example.bookingservice.client.InventoryServiceClient;
import com.example.bookingservice.entity.Customer;
import com.example.bookingservice.event.BookingEvent;
import com.example.bookingservice.repository.CustomerRepository;
import com.example.bookingservice.request.BookingRequest;
import com.example.bookingservice.response.BookingResponse;
import com.example.bookingservice.response.EventInventoryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    @Autowired
    private KafkaTemplate<String,BookingEvent> kafkaTemplate;


    public BookingResponse createBooking(BookingRequest bookingRequest) {
        //check if the user exits
        final Customer customer = customerRepository.findById(bookingRequest.getUserId()).orElse(null);
        if(customer == null) {
            throw new RuntimeException("Customer not found");
        }
        //check if the enough inventory is available
        final EventInventoryResponse eventInventoryResponse = inventoryServiceClient.getEventInventory(bookingRequest.getEventId());
        //create the booking
        final BookingEvent bookingEvent =createBookingEvent(bookingRequest, customer, eventInventoryResponse);
        //send booking to Order service on a kafka topic
        kafkaTemplate.send("booking-topic", bookingEvent);
        log.info("Booking event sent to Kafka topic: {}", bookingEvent);
        //return the booking response
        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

    private BookingEvent createBookingEvent(BookingRequest bookingRequest, Customer customer, EventInventoryResponse eventInventoryResponse) {
        //create the booking event
        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(bookingRequest.getEventId())
                .ticketCount(bookingRequest.getTicketCount())
                .totalPrice(bookingRequest.getTicketCount().multiply(eventInventoryResponse.getTicketPrice()))
                .build();
    }
}
