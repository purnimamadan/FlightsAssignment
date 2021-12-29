package com.example.flightsassignment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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

class MainActivity : BaseActivity<ActivityMainBinding?, MainViewModel?>(),
    FlightAdapter.FlightListener {
    private var flightAdapter: FlightAdapter? = null
    @NonNull
    protected override fun createViewModel(): MainViewModel {
        val movieRepository: FlightRepository = DataManager.instance.flightRepository
        val factory = MainViewModelFactory(movieRepository)
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    @NonNull
    protected override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flightAdapter = FlightAdapter(this)
        binding?.recyclerView?.setAdapter(flightAdapter)
        setListeners()
        observeViewModel()
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
        viewModel?.moviesLiveData?.observe(this, object : Observer<List<Flight>?> {
            override fun onChanged(@Nullable flights: List<Flight>?) {
                if (flights != null)
                    flightAdapter?.setItems(flights)
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

    override fun onFlightClicked(flight: Flight?) {}
}