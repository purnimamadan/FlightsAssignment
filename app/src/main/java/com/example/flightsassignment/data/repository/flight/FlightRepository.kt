package com.example.flightsassignment.data.repository.flight

import com.example.flightsassignment.data.domain.Flight

interface FlightRepository {
    interface LoadFlightsCallback {
        fun onFlightsLoaded(flights: List<Flight>?)
        fun onDataNotAvailable()
        fun onError()
    }

    fun getFlights(callback: LoadFlightsCallback?)
}