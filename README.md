# Ticketing System - Booking Service


BookingService is part of the ticketing system. It is responsible for creating bookings for tickets and publishing events to a Kafka topic, which the OrderService consumes as a subscriber.
## Overview

BookingService manages the lifecycle of ticket bookings. It receives booking requests, processes them, and ensures that the booking information is communicated to other services in the system, such as the OrderService.

## Architecture

- **Microservices-based**: BookingService operates as an independent microservice within the ticketing platform.
- **Kafka Integration**: Uses Kafka for asynchronous communication with other services, especially OrderService.
- **REST API**: Exposes endpoints for creating and managing bookings.

## Main Components

- **Booking Controller**: Handles HTTP requests related to bookings.
- **Booking Service**: Contains business logic for creating and managing bookings.
- **Kafka Producer**: Publishes booking events to a Kafka topic to notify OrderService.
- **Kafka Listener**: Listens to relevant Kafka topics for updates or commands from other services.

## Kafka Integration

- **Booking Events Topic**: When a booking is created, an event is published to a Kafka topic (e.g., `booking-events`).
- **OrderService Notification**: OrderService listens to the Kafka topic and processes booking events accordingly.
- **Resilience**: Kafka ensures reliable delivery and decoupling between BookingService and OrderService.

## Usage

1. **Create Booking**: Send a POST request to the booking endpoint with ticket and user details.
2. **Event Notification**: Upon successful booking, an event is published to Kafka.
3. **OrderService Reaction**: OrderService consumes the event and processes the order.

## Configuration

- Kafka broker details and topic names are configurable via environment variables or application properties.

## Dependencies

- Spring Boot (or relevant framework)
- Kafka client library

## Example Booking Flow

1. User requests a ticket booking via API.
2. BookingService validates and creates the booking.
3. BookingService publishes a booking event to Kafka.
4. OrderService listens to the topic and processes the order.

## License

MIT
