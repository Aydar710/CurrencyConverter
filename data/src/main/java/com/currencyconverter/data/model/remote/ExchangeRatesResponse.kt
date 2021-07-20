package com.currencyconverter.data.model.remote

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.util.*

data class ExchangeRatesResponse(
    @SerializedName("Timestamp")
    val date: Date,
    @SerializedName("Valute")
    @JsonAdapter(GsonValutesDeserializer::class)
    val exchangeRates: List<ExchangeRate>
)