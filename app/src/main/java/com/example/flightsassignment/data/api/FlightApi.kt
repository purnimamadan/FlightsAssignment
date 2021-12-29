package com.example.flightsassignment.data.api

import com.example.flightsassignment.data.model.FlightResponse
import retrofit2.Call
import retrofit2.http.GET

interface FlightApi {
    @get:GET("v3/ec830f3d-6523-40c7-80d8-e9da5b5efc27")
    public val getFlights: Call<FlightResponse?>?
}