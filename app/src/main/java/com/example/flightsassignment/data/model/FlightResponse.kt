package com.example.flightsassignment.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FlightResponse (
    @Expose
    @SerializedName("flights")
    var flights: List<Flight>? = null
)