package com.currencyconverter.data.model.remote

import com.google.gson.annotations.SerializedName

data class ExchangeRate(
    @SerializedName("ID")
    val id: String,
    @SerializedName("NumCode")
    val numCode: Int,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val value: Double
)
