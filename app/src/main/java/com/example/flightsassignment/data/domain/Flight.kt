package com.example.flightsassignment.data.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Flight : Parcelable {
    @Expose
    @SerializedName("source")
    var source: String?

    @Expose
    @SerializedName("destination")
    var destination: String?

    @Expose
    @SerializedName("logo")
    var logo: String?

    @Expose
    @SerializedName("Departure")
    var Departure: String?

    @Expose
    @SerializedName("Arrival")
    var Arrival: String?

    @Expose
    @SerializedName("ticketPrice")
    var ticketPrice: String?

    @Expose
    @SerializedName("Airline")
    var Airline: String?

    constructor(
        source: String?, destination: String?,
        logo: String?, Departure: String?, Arrival: String?, ticketPrice: String?, Airline: String?
    ) {
        this.source = source
        this.destination = destination
        this.logo = logo
        this.Departure = Departure
        this.Arrival = Arrival
        this.ticketPrice = ticketPrice
        this.Airline = Airline
    }

    protected constructor(`in`: Parcel) {
        source = `in`.readString()
        destination = `in`.readString()
        logo = `in`.readString()
        Departure = `in`.readString()
        Arrival = `in`.readString()
        ticketPrice = `in`.readString()
        Airline = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(source)
        parcel.writeString(destination)
        parcel.writeString(logo)
        parcel.writeString(Departure)
        parcel.writeString(Arrival)
        parcel.writeString(ticketPrice)
        parcel.writeString(Airline)
    }

    companion object CREATOR : Parcelable.Creator<Flight> {
        override fun createFromParcel(parcel: Parcel): Flight {
            return Flight(parcel)
        }

        override fun newArray(size: Int): Array<Flight?> {
            return arrayOfNulls(size)
        }
    }

}