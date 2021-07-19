package com.currencyconverter.data.model

import com.google.gson.annotations.SerializedName

data class Currency(
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
