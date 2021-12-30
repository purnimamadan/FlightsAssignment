package com.example.flightsassignment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.flightsassignment.data.DataManager
import com.example.flightsassignment.data.domain.Flight
import com.example.flightsassignment.data.repository.flight.FlightRepository
import com.example.flightsassignment.databinding.ActivityMainBinding
import com.example.flightsassignment.ui.base.BaseActivity
import android.widget.ArrayAdapter
import com.example.flightsassignment.R

class MainActivity : BaseActivity<ActivityMainBinding?, MainViewModel?>(),
    FlightAdapter.FlightListener {

    private var flightAdapter: FlightAdapter? = null
    private var priceSorting = "price"
    private var flights: MutableList<Flight> = mutableListOf()

    @NonNull
    override fun createViewModel(): MainViewModel {
        val flightRepository: FlightRepository = DataManager.instance.flightRepository
        val factory = MainViewModelFactory(flightRepository)
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    @NonNull
    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flightAdapter = FlightAdapter(this)
        binding?.recyclerView?.setAdapter(flightAdapter)
        setUpPriceSpinner()
        setListeners()
        observeViewModel()
        getFlights()
    }

    private fun setUpPriceSpinner() {
        val spinnerArray: MutableList<String> = ArrayList()
        spinnerArray.add(resources.getString(R.string.price))
        spinnerArray.add(resources.getString(R.string.departure))
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, spinnerArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.sortbySpinner?.adapter = adapter
        binding?.sortbySpinner?.setSelection(0)
        priceSorting = binding?.sortbySpinner?.selectedItem.toString()

        binding?.sortbySpinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long) {
                    priceSorting = binding?.sortbySpinner?.selectedItem.toString()
                    sortFlights()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun getFlights() {
        viewModel?.loadFlights()
    }

    private fun setListeners() {
        binding?.load?.setOnClickListener(View.OnClickListener { viewModel?.loadFlights() })
    }

    private fun observeViewModel() {
        viewModel?.getShowLoadingLiveData()?.observe(this, object : Observer<Void?> {
            override fun onChanged(@Nullable aVoid: Void?) {
                binding?.progressBar?.setVisibility(View.VISIBLE)
            }
        })
        viewModel?.getHideLoadingLiveData()?.observe(this, object : Observer<Void?> {
            override fun onChanged(@Nullable aVoid: Void?) {
                binding?.progressBar?.setVisibility(View.GONE)
            }
        })
        viewModel?.flightsLiveData?.observe(this, object : Observer<List<Flight>?> {
            override fun onChanged(@Nullable flights: List<Flight>?) {
                if (flights != null) {
                    this@MainActivity.flights = flights.toMutableList()
                    sortFlights()
                }
            }
        })
        viewModel?.getNavigateToDetailsLiveData()?.observe(this, object : Observer<Flight?> {
            override fun onChanged(@Nullable flight: Flight?) {}
        })
        viewModel?.getShowErrorMessageLiveData()?.observe(this, object : Observer<String?> {
            override fun onChanged(@Nullable message: String?) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun sortFlights() {
        if (priceSorting.equals(resources.getString(R.string.price))) {
            this@MainActivity.flights.sortBy { it.ticketPrice?.toInt() }
        } else if (priceSorting.equals(resources.getString(R.string.departure))) {
            this@MainActivity.flights.sortBy { it.Departure }
        }
        flightAdapter?.setItems(this@MainActivity.flights)
    }

    override fun onFlightClicked(flight: Flight?) {}
}