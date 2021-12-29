package com.example.flightsassignment.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flightsassignment.data.domain.Flight
import com.example.flightsassignment.databinding.ItemFlightBinding
import com.example.flightsassignment.ui.main.FlightAdapter.FlightViewHolder
import java.util.ArrayList

class FlightAdapter(listener: FlightListener) :
    RecyclerView.Adapter<FlightViewHolder?>() {
    interface FlightListener {
        fun onFlightClicked(flight: Flight?)
    }

    private var items: List<Flight>
    private val listener: FlightListener
    fun setItems(items: List<Flight>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlightViewHolder {
        val binding: ItemFlightBinding =
            ItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FlightViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun getItem(position: Int): Flight {
        return items[position]
    }

    inner class FlightViewHolder internal constructor(binding: ItemFlightBinding) :
        RecyclerView.ViewHolder(binding.getRoot()), View.OnClickListener {
        var binding: ItemFlightBinding
        fun bind(position: Int) {
            val flight: Flight = getItem(position)
            setClickListener(flight)
            flight.logo?.let { setImage(it) }
            flight.source?.let { setSource(it) }
            flight.destination?.let { setDestination(it) }
            flight.Departure?.let { setDeparture(it) }
            flight.Arrival?.let { setArrival(it) }
            flight.ticketPrice?.let { setTicketPrice(it) }
            flight.Airline?.let { setAirline(it) }
        }

        private fun setImage(imageUrl: String) {
            Glide.with(itemView.getContext()).load(imageUrl).into(binding.image)
        }

        private fun setSource(source: String) {
            binding.source.setText(source)
        }

        private fun setDestination(destination: String) {
            binding.destination.setText(destination)
        }

        private fun setDeparture(departure: String) {
            binding.Departure.setText(departure)
        }

        private fun setArrival(arrival: String) {
            binding.Arrival.setText(arrival)
        }

        private fun setTicketPrice(ticketPrice: String) {
            binding.ticketPrice.setText(ticketPrice)
        }

        private fun setAirline(airline: String) {
            binding.Airline.setText(airline)
        }

        private fun setClickListener(flight: Flight) {
            itemView.setTag(flight)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            listener.onFlightClicked(view.tag as Flight)
        }

        init {
            this.binding = binding
        }
    }

    init {
        this.listener = listener
        items = ArrayList<Flight>()
    }
}