package com.example.flightsassignment.data.repository.flight

import com.example.flightsassignment.data.domain.Flight

class FlightRepositoryImpl private constructor(movieRemote: FlightRemoteDataSource) :
    FlightRepository {
    private val movieRemote: FlightDataSource.Remote
    override fun getFlights(callback: FlightRepository.LoadFlightsCallback?) {
        if (callback == null) return
        //here can check between local and remote
        //currently directly picking from remote
        getMoviesFromRemoteDataSource(callback)
    }

    private fun getMoviesFromRemoteDataSource(callback: FlightRepository.LoadFlightsCallback) {
        movieRemote.getFlights(object : FlightRepository.LoadFlightsCallback {
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
        fun getInstance(movieRemote: FlightRemoteDataSource?): FlightRepositoryImpl {
            if (instance == null) {
                instance = FlightRepositoryImpl(movieRemote!!)
            }
            return instance!!
        }
    }

    init {
        this.movieRemote = movieRemote
    }
}