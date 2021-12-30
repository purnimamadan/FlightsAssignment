package com.example.flightsassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flightsassignment.data.domain.Flight
import com.example.flightsassignment.data.repository.flight.FlightRepository
import com.example.flightsassignment.ui.base.BaseViewModel

class MainViewModel internal constructor(flightRepository: FlightRepository) :
    BaseViewModel() {
    /**
     * LiveData
     */
    var flightsLiveData: MutableLiveData<List<Flight>> = MutableLiveData()
    private val showErrorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    private val showLoadingLiveData: MutableLiveData<Void> = MutableLiveData()
    private val hideLoadingLiveData: MutableLiveData<Void> = MutableLiveData()
    private val navigateToDetailsLiveData: MutableLiveData<Flight> = MutableLiveData()
    private val flightRepository: FlightRepository
    private val flightCallback: FlightCallback
        get() {
            return FlightCallback()
        }

    fun loadFlights() {
        setIsLoading(true)
        flightRepository.getFlights(flightCallback)
    }

    private fun setIsLoading(loading: Boolean) {
        if (loading) {
            showLoadingLiveData.postValue(null)
        } else {
            hideLoadingLiveData.postValue(null)
        }
    }

    /**
     * Callback
     */
    private inner class FlightCallback : FlightRepository.LoadFlightsCallback {
        override fun onFlightsLoaded(flights: List<Flight>?) {
            if (flights != null)
                setFlightsLiveData(flights)
        }

        override fun onDataNotAvailable() {
            setIsLoading(false)
            showErrorMessageLiveData.postValue("There is not items!")
        }

        override fun onError() {
            setIsLoading(false)
            showErrorMessageLiveData.postValue("Something Went Wrong!")
        }
    }

    fun getShowLoadingLiveData(): LiveData<Void> {
        return showLoadingLiveData
    }

    fun getShowErrorMessageLiveData(): LiveData<String> {
        return showErrorMessageLiveData
    }

    fun getHideLoadingLiveData(): LiveData<Void> {
        return hideLoadingLiveData
    }

    fun getNavigateToDetailsLiveData(): LiveData<Flight> {
        return navigateToDetailsLiveData
    }

    private fun setFlightsLiveData(flightsLiveData: List<Flight>) {
        setIsLoading(false)
        this.flightsLiveData.postValue(flightsLiveData)
    }

    init {
        this.flightRepository = flightRepository
    }
}