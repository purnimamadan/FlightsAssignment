package com.example.flightsassignment.data.repository.flight

import com.example.flightsassignment.data.api.FlightApi
import com.example.flightsassignment.data.model.Flight
import com.example.flightsassignment.data.model.FlightResponse
import com.example.flightsassignment.data.repository.flight.FlightRepository.LoadFlightsCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlightRemoteDataSource(flightApi: FlightApi) : FlightDataSource.Remote {
    private val flightApi: FlightApi
    override fun getFlights(callback: LoadFlightsCallback?) {
        flightApi.getFlights?.enqueue(object : Callback<FlightResponse?> {
            override fun onResponse(
                call: Call<FlightResponse?>,
                response: Response<FlightResponse?>) {
                val flights: List<Flight>? =
                    if (response.body() != null) response.body()!!.flights else null
                if (flights != null && !flights.isEmpty()) {
                    callback?.onFlightsLoaded(flights)
                } else {
                    callback?.onDataNotAvailable()
                }
            }

            override fun onFailure(call: Call<FlightResponse?>, t: Throwable) {
                callback?.onError()
            }
        })
    }

    companion object {
        private var instance: FlightRemoteDataSource? = null

        fun getInstance(flightApi: FlightApi?): FlightRemoteDataSource {
            if (instance == null) {
                instance = FlightRemoteDataSource(flightApi!!)
            }
            return instance!!
        }
    }

    init {
        this.flightApi = flightApi
    }
}