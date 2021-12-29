package com.example.flightsassignment.data.repository.flight

import com.example.flightsassignment.data.api.FlightApi
import com.example.flightsassignment.data.domain.Flight
import com.example.flightsassignment.data.mapper.FlightMapper
import com.example.flightsassignment.data.model.FlightRemote
import com.example.flightsassignment.data.model.FlightResponse
import com.example.flightsassignment.data.repository.flight.FlightRepository.LoadFlightsCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class FlightRemoteDataSource private constructor(flightApi: FlightApi) : FlightDataSource.Remote {
    private val flightApi: FlightApi
    override fun getFlights(callback: LoadFlightsCallback?) {
        flightApi.getFlights?.enqueue(object : Callback<FlightResponse?> {
            override fun onResponse(
                call: Call<FlightResponse?>,
                response: Response<FlightResponse?>
            ) {
                val flights: List<FlightRemote>? =
                    if (response.body() != null) response.body()!!.flights else null
                if (flights != null && !flights.isEmpty()) {
                    val flightsDomain: MutableList<Flight> = ArrayList<Flight>()
                    for (flightRemote in flights) {
                        flightsDomain.add(FlightMapper.toDomain(flightRemote))
                    }
                    callback?.onFlightsLoaded(flightsDomain)
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
        private var instance: FlightRemoteDataSource? =
            null

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