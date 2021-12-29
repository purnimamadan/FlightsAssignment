package com.example.flightsassignment.data.repository.flight

interface FlightDataSource {
    interface Remote {
        fun getFlights(callback: FlightRepository.LoadFlightsCallback?)
    }
}