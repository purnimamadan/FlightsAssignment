package com.example.flightsassignment.data.mapper

import com.example.flightsassignment.data.domain.Flight
import com.example.flightsassignment.data.model.FlightRemote

object FlightMapper {
    fun toDomain(flightRemote: FlightRemote): Flight {
        return Flight(
            flightRemote.source, flightRemote.destination,
            flightRemote.logo, flightRemote.Departure, flightRemote.Arrival,
            flightRemote.ticketPrice, flightRemote.Airline
        )
    }
}