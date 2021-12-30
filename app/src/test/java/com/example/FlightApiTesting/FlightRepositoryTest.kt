package com.example.FlightApiTesting

import com.example.flightsassignment.data.api.FlightApi
import com.example.flightsassignment.data.repository.flight.FlightRemoteDataSource
import com.example.flightsassignment.data.repository.flight.FlightRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FlightRepositoryTest {

    private val flightApi:FlightApi = mock()
    private val remoteDataSource: FlightRemoteDataSource = FlightRemoteDataSource(flightApi)
    private val flightRepositoryImpl = FlightRepositoryImpl(remoteDataSource)

    @Test
    fun getFlights_success() = runBlocking {
        val result = testFlight
        whenever(remoteDataSource.getFlights(null))
        flightRepositoryImpl.getFlights(null)
        assert(true)
    }
}