package com.example.FlightApiTesting

import com.example.flightsassignment.data.model.Flight
import com.example.flightsassignment.data.model.FlightResponse

val testFlight = Flight(
    source = "",
    destination = "test_total_vat_on_basket",
    logo = "test_basket_amount_without_vat",
    Departure = "0",
    Arrival = "0",
    ticketPrice = "0",
    Airline = "0"
)

val testFlightResponse = FlightResponse(
    flights = arrayListOf(testFlight),
)



