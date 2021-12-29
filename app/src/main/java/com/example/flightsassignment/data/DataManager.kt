package com.example.flightsassignment.data

import com.example.flightsassignment.data.api.FlightApi
import com.example.flightsassignment.data.repository.flight.FlightRemoteDataSource
import com.example.flightsassignment.data.repository.flight.FlightRepository
import com.example.flightsassignment.data.repository.flight.FlightRepositoryImpl
import com.example.flightsassignment.data.service.FlightService
import com.preference.PowerPreference

class DataManager private constructor() {
    val prefs: com.preference.Preference?
        get() = PowerPreference.getDefaultFile()

    //can pass local sources
    val flightRepository: FlightRepository
        get() {
            val flightApi: FlightApi = FlightService.instance.getFlightApi()
            val flightRemote: FlightRemoteDataSource = FlightRemoteDataSource.getInstance(flightApi)
            //can pass local sources
            return FlightRepositoryImpl.getInstance(flightRemote)
        }

    companion object {
        private var sInstance: DataManager? = null

        @get:Synchronized
        val instance: DataManager
            get() {
                if (sInstance == null) {
                    sInstance = DataManager()
                }
                return sInstance!!
            }
    }
}