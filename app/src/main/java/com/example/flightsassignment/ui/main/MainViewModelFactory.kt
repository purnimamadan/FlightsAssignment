package com.example.flightsassignment.ui.main

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flightsassignment.data.repository.flight.FlightRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(flightRepository: FlightRepository) :
    ViewModelProvider.Factory {
    private val flightRepository: FlightRepository
    @NonNull
    override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(flightRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    init {
        this.flightRepository = flightRepository
    }
}