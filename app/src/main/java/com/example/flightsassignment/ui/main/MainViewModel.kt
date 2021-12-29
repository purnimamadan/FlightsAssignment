package com.example.flightsassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flightsassignment.data.domain.Flight
import com.example.flightsassignment.data.repository.flight.FlightRepository
import com.example.flightsassignment.ui.base.BaseViewModel

class MainViewModel internal constructor(moviesRepository: FlightRepository) :
    BaseViewModel() {
    /**
     * LiveData
     */
    var moviesLiveData: MutableLiveData<List<Flight>> = MutableLiveData()
//        private set(moviesLiveData) {
//            setIsLoading(false)
//            val x: List<Flight>
//            val y: MutableLiveData<List<Flight>> = moviesLiveData
//            y.postValue(x)
//            this.moviesLiveData.postValue(moviesLiveData)
//        }
    private val showErrorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    private val showLoadingLiveData: MutableLiveData<Void> = MutableLiveData()
    private val hideLoadingLiveData: MutableLiveData<Void> = MutableLiveData()
    private val navigateToDetailsLiveData: MutableLiveData<Flight> = MutableLiveData()
    private val flightRepository: FlightRepository
    private val movieCallback: MovieCallback
        get() {
            return MovieCallback()
        }

    fun loadFlights() {
        setIsLoading(true)
        flightRepository.getFlights(movieCallback)
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
    private inner class MovieCallback : FlightRepository.LoadFlightsCallback {
        override fun onFlightsLoaded(flights: List<Flight>?) {
            if (flights != null)
                setMoviesLiveData(flights)
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

    private fun setMoviesLiveData(moviesLiveData: List<Flight>) {
        setIsLoading(false)
        this.moviesLiveData.postValue(moviesLiveData)
    }

    init {
        flightRepository = moviesRepository
    }
}