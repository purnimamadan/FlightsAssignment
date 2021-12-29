package com.example.flightsassignment.data.service

import com.example.flightsassignment.data.api.FlightApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlightService private constructor() {
    private val flightApi: FlightApi
    fun getFlightApi(): FlightApi {
        return flightApi
    }

    companion object {
        private const val URL = "https://run.mocky.io/"
        private var singleton: FlightService? = null
        val instance: FlightService
            get() {
                if (singleton == null) {
                    synchronized(FlightService::class.java) {
                        if (singleton == null) {
                            singleton =
                                FlightService()
                        }
                    }
                }
                return singleton!!
            }
    }

    init {
        val mRetrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL).build()
        flightApi = mRetrofit.create(FlightApi::class.java)
    }
}