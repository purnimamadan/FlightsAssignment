package com.example.flightsassignment.data.repository.flight

import com.example.flightsassignment.data.domain.Flight

class FlightRepositoryImpl private constructor(flightRemote: FlightRemoteDataSource) :
    FlightRepository {
    private val flightRemote: FlightDataSource.Remote
    override fun getFlights(callback: FlightRepository.LoadFlightsCallback?) {
        if (callback == null) return
        //here can check between local and remote
        //currently directly picking from remote
        getFlightsFromRemoteDataSource(callback)
    }

    private fun getFlightsFromRemoteDataSource(callback: FlightRepository.LoadFlightsCallback) {
        flightRemote.getFlights(object : FlightRepository.LoadFlightsCallback {
            override fun onFlightsLoaded(flights: List<Flight>?) {
                callback.onFlightsLoaded(flights)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

            override fun onError() {
                callback.onError()
            }
        })
    }

    companion object {
        private var instance: FlightRepositoryImpl? = null
        fun getInstance(flightRemote: FlightRemoteDataSource?): FlightRepositoryImpl {
            if (instance == null) {
                instance = FlightRepositoryImpl(flightRemote!!)
            }
            return instance!!
        }
    }

    init {
        this.flightRemote = flightRemote
    }
}