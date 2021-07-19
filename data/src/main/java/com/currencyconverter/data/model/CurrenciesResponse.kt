package com.currencyconverter.data.model

import com.currencyconverter.data.gsonutils.GsonValutesDeserializer
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.util.*

data class CurrenciesResponse(
    @SerializedName("Date")
    val date: Date,
    @SerializedName("Valute")
    @JsonAdapter(GsonValutesDeserializer::class)
    val currencies: List<Currency>
)