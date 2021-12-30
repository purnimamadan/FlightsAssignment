package com.example.flightsassignment.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.*

@Parcelize
data class Flight(
    @Expose
    @SerializedName("source")
    var source: String?,

    @Expose
    @SerializedName("destination")
    var destination: String?,

    @Expose
    @SerializedName("logo")
    var logo: String?,

    @Expose
    @SerializedName("Departure")
    var Departure: String?,

    @Expose
    @SerializedName("Arrival")
    var Arrival: String?,

    @Expose
    @SerializedName("ticketPrice")
    var ticketPrice: String?,

    @Expose
    @SerializedName("Airline")
    var Airline: String?
): Parcelable